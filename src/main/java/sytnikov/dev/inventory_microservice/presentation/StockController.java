package sytnikov.dev.inventory_microservice.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.stock.IStockService;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;
import sytnikov.dev.inventory_microservice.dtos.stock.StockCreateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {
    
    @Autowired
    private IStockService _stockService;
    
    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody StockCreateDto stockDetails) {
        System.out.println(stockDetails.getQuantity());
        Stock createdStock = _stockService.addStock(
                stockDetails.getSupplierId(),
                stockDetails.getProductId(),
                stockDetails.getProductBarcode(),
                stockDetails.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = _stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Optional<Stock>> getStockById(@PathVariable UUID stockId) {
        Optional<Stock> foundStock = _stockService.getStockById(stockId);
        return ResponseEntity.ok(foundStock);
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<Stock> modifyStock(@PathVariable UUID stockId, @RequestBody Stock stockDetails) {
        Optional<Stock> existingStock = _stockService.getStockById(stockId);

        if (existingStock.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        stockDetails.setId(stockId);
        Stock updatedStock = _stockService.modifyStock(stockDetails);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID stockId) {
        _stockService.deleteStock(stockId);
        return ResponseEntity.noContent().build();
    }
}
