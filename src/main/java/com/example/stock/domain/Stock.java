package com.example.stock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private Long quantity;

    @Version
    private Long version;

    protected Stock() { }

    public Stock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void decrease(long quantity) {
        if (quantity > this.quantity) {
            throw new RuntimeException("재고보다 큰 값");
        }
        this.quantity -= quantity;
    }
}
