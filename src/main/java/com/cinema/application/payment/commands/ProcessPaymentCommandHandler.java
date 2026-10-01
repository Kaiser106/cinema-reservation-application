package com.cinema.application.payment.commands;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.external.PaymentGatewayContract;
import com.cinema.application.contracts.external.PaymentResult;
import com.cinema.application.contracts.persistence.OutboxPort;
import com.cinema.application.contracts.persistence.PaymentRepositoryPort;
import com.cinema.application.contracts.reservation.ReservationContract;
import com.cinema.application.contracts.reservation.ReservationDto;
import com.cinema.domain.payment.Payment;
import com.cinema.domain.payment.events.PaymentFailedEvent;
import com.cinema.domain.payment.events.PaymentSucceededEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProcessPaymentCommandHandler implements CommandHandler<ProcessPaymentCommand, Boolean> {

    private final ReservationContract reservationContract;
    private final PaymentGatewayContract paymentGatewayContract;
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final OutboxPort outboxPort;

    public ProcessPaymentCommandHandler(ReservationContract reservationContract,
                                        PaymentGatewayContract paymentGatewayContract,
                                        PaymentRepositoryPort paymentRepositoryPort,
                                        OutboxPort outboxPort) {
        this.reservationContract = reservationContract;
        this.paymentGatewayContract = paymentGatewayContract;
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.outboxPort = outboxPort;
    }

    @Override
    @Transactional
    public Boolean handle(ProcessPaymentCommand command) {
        // Idempotency check: Have we processed this key before?
        if (paymentRepositoryPort.existsByIdempotencyKey(command.idempotencyKey())) {
            return true; // Assume success if already processed to prevent double charging
        }

        ReservationDto reservation = reservationContract.getReservationById(command.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        Payment payment = new Payment(
                UUID.randomUUID(),
                reservation.id(),
                new com.cinema.domain.common.Money(reservation.totalPrice()),
                command.paymentMethod(),
                command.idempotencyKey()
        );

        PaymentResult result = paymentGatewayContract.processPayment(
                reservation.id(), reservation.totalPrice(), command.paymentMethod(), command.idempotencyKey()
        );

        if (result.isSuccess()) {
            payment.markAsSuccess(result.transactionReference(), LocalDateTime.now());
            outboxPort.saveEvents("Payment", payment.getId().toString(),
                    List.of(new PaymentSucceededEvent(payment.getId(), reservation.id())));
        } else {
            payment.markAsFailed();
            outboxPort.saveEvents("Payment", payment.getId().toString(),
                    List.of(new PaymentFailedEvent(payment.getId(), reservation.id(), result.errorMessage())));
        }

        paymentRepositoryPort.save(payment);
        return result.isSuccess();
    }
}