package com.example.stock.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class StockTest {

    Stock stock;

    @BeforeEach
    void setup() {
        stock = new Stock(1L, 1000L);
    }

    @Test
    void decreaseQuantity() {
        stock.decrease(100L);

        Assertions.assertThat(stock.getQuantity()).isEqualTo(900L);
    }

    @Test
    void decreaseQuantityOverExistingQuantity() {
        Assertions.assertThatThrownBy(() -> stock.decrease(2000L))
                .isInstanceOf(RuntimeException.class)
                .message()
                .contains("재고보다");
    }

    @Test
    @Disabled
    void decreaseQuantityOnMultiThread() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for(int i = 0; i < 100; i++) {
            try {
                executorService.submit(() -> stock.decrease(1L));
            } finally {
                countDownLatch.countDown();
            }
        }

        countDownLatch.await();

        Assertions.assertThat(stock.getQuantity()).isEqualTo(0L);
    }
}