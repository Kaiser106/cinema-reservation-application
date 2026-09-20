package com.cinema.application.reservation.command.create;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.outbox.EventOutboxPort;
import com.cinema.application.contracts.persistence.ReservationRepository;
import com.cinema.application.contracts.persistence.SeatRepository;
import com.cinema.application.contracts.persistence.SessionRepository;
import com.cinema.application.reservation.dto.ReservationResultDto;
import com.cinema.domain.hall.Seat;
import com.cinema.domain.movie.Session;
import com.cinema.domain.reservation.Reservation;
import com.cinema.domain.reservation.events.ReservationCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand, ReservationResultDto> {

    private final ReservationRepository reservationRepository;
    private final SessionRepository sessionRepository;
    private final SeatRepository seatRepository;
    private final EventOutboxPort eventOutboxPort;

    @Override
    @Transactional // İş mantığı ve Outbox kaydı ATOMİK olmalıdır.
    public ReservationResultDto handle(CreateReservationCommand command) {

        // 1. Seansı getir ve doğrula
        Session session = sessionRepository.findById(command.getSessionId())
                .orElseThrow(() -> new IllegalArgumentException("Seans bulunamadı."));

        // 2. Koltukları getir
        List<Seat> seats = seatRepository.findAllByIdIn(command.getSeatIds());
        if (seats.size() != command.getSeatIds().size()) {
            throw new IllegalArgumentException("Geçersiz veya bulunamayan koltuk seçimi.");
        }

        // 3. Domain nesnesini oluştur (İş kuralları Domain'de işler)
        Reservation reservation = new Reservation(command.getUserId(), session.getId());

        for (Seat seat : seats) {
            // Görseldeki şemaya göre koltuk ve salon doğrulaması yapılabilir
            if (!seat.getHallId().equals(session.getHallId())) {
                throw new IllegalStateException("Seçilen koltuk bu seansın salonuna ait değil.");
            }
            // Domain nesnesine koltuğu ekle
            reservation.addSeat(seat.getId(), session.getPrice());
        }

        // 4. Rezervasyonu veritabanına kaydet
        reservationRepository.save(reservation);

        // 5. Transactional Outbox - Olayı yayınla (Asenkron işlenmek üzere veritabanına yazılır)
        ReservationCreatedEvent event = new ReservationCreatedEvent(reservation.getId(), reservation.getUserId());
        eventOutboxPort.save(event);

        // 6. DTO dönüştür ve döndür
        return new ReservationResultDto(
                reservation.getId(),
                reservation.getStatus().name(),
                reservation.getTotalPrice(),
                reservation.getExpiresAt()
        );
    }
}