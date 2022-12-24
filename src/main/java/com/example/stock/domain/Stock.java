package com.example.stock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private Long quantity;

    protected Stock() { }

    public Stock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public synchronized void decrease(long quantity) {
        if (quantity > this.quantity) {
            throw new RuntimeException("재고보다 큰 값");
        }
        this.quantity -= quantity;
    }
}
