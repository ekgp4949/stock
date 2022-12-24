package com.example.stock.facade;

import com.example.stock.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class OptimisticLockStockFacade {
    private final StockService stockService;

    public OptimisticLockStockFacade(StockService stockService) {
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        while (true) {
            try {
                stockService.decrease(id, quantity);
                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }
}
