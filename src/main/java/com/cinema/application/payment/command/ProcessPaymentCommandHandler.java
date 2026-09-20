package com.cinema.application.payment.command;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.external.PaymentGatewayContract;
import com.cinema.application.contracts.persistence.PaymentRepository;
import com.cinema.application.contracts.persistence.ReservationRepository;
import com.cinema.application.payment.dto.PaymentResultDto;
import com.cinema.domain.payment.Payment;
import com.cinema.domain.payment.PaymentStatus;
import com.cinema.domain.reservation.Reservation;
import com.cinema.domain.reservation.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessPaymentCommandHandler implements CommandHandler<ProcessPaymentCommand, PaymentResultDto> {

    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayContract paymentGatewayContract;
    // ConfirmCommandHandler doğrudan çağrılabilir veya Event fırlatılarak asenkron yapılabilir.
    // Clean Architecture gereği CommandHandler içinden başka bir Handler'ı inject etmek yerine Event veya Domain servisi kullanılır.

    @Override
    @Transactional
    public PaymentResultDto handle(ProcessPaymentCommand command) {
        Reservation reservation = reservationRepository.findById(command.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Rezervasyon bulunamadı."));

        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("Sadece bekleyen rezervasyonlar için ödeme yapılabilir.");
        }

        // Iyzipay Contract Çağrısı (Altyapı bağımsız)
        boolean isSuccess = paymentGatewayContract.processPayment(
                reservation.getId(),
                reservation.getTotalPrice(),
                command.getCardNumber(),
                command.getExpireMonth(),
                command.getExpireYear(),
                command.getCvc()
        );

        if (!isSuccess) {
            throw new IllegalStateException("Ödeme işlemi başarısız oldu.");
        }

        // Başarılı ise Payment Entity oluştur ve kaydet
        Payment payment = new Payment(
                UUID.randomUUID(),
                reservation.getId(),
                reservation.getTotalPrice(),
                "CREDIT_CARD",
                PaymentStatus.SUCCESS,
                LocalDateTime.now(),
                "TRX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()
        );
        paymentRepository.save(payment);

        // Rezervasyonu Onayla
        reservation.confirm();
        reservationRepository.save(reservation);

        return new PaymentResultDto(
                payment.getId(),
                payment.getStatus().name(),
                payment.getTransactionRef(),
                payment.getPaidAt()
        );
    }
}