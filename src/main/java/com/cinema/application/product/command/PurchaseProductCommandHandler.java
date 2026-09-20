package com.cinema.application.product.command;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.ProductRepository;
import com.cinema.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseProductCommandHandler implements CommandHandler<PurchaseProductCommand, Void> {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Void handle(PurchaseProductCommand command) {
        Product product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı."));

        // Domain içindeki stok azaltma kuralını işletir. Stok yetersizse IllegalStateException fırlatır.
        product.decreaseStock(command.getQuantity());

        productRepository.save(product);

        // Not: Burada ProductPurchase tablosuna kayıt atma ve kullanıcıya puan (UserPoint) ekleme Event'i (Outbox) fırlatılabilir.

        return null;
    }
}