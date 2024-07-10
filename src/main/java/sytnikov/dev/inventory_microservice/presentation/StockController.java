package sytnikov.dev.inventory_microservice.presentation;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.stock.IStockService;
import sytnikov.dev.inventory_microservice.application.supplier.ISupplierService;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;
import sytnikov.dev.inventory_microservice.domain.stock.StockLevelEnum;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;
import sytnikov.dev.inventory_microservice.dtos.stock.StockCreateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {
    
    @Autowired
    private IStockService _stockService;

    @Autowired
    private ISupplierService _supplierService;
    
    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody StockCreateDto stockDetails) {
        UUID supplierId = stockDetails.getSupplierId();
        Supplier foundSupplier = _supplierService.getSupplierById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with this id: " + supplierId));
        Stock createdStock = _stockService.addStock(
                foundSupplier,
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
    public ResponseEntity<Stock> getStockById(@PathVariable UUID stockId) {
        Stock foundStock = _stockService.getStockById(stockId)
                .orElseThrow(() -> new EntityNotFoundException("Stock with id " + stockId + " not found"));
        return ResponseEntity.ok(foundStock);
    }

    @GetMapping("/product/{productBarcode}")
    public ResponseEntity<List<Stock>> getStocksByProductId(@PathVariable String productBarcode) {
        List<Stock> stocksByProducts = _stockService.getStocksByProductId(productBarcode);
        return ResponseEntity.ok(stocksByProducts);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<Stock>> getStocksBySupplierId(@PathVariable UUID supplierId) {
        List<Stock> stocksBySupplier = _stockService.getStocksBySupplierId(supplierId);
        return ResponseEntity.ok(stocksBySupplier);
    }

    @GetMapping("/level/{productBarcode}")
    public ResponseEntity<StockLevelEnum> isStockAvailable(@PathVariable String productBarcode) {
        StockLevelEnum stockLevel = _stockService.getStockLevel(productBarcode);
        return ResponseEntity.ok(stockLevel);
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<Stock> modifyStock(@PathVariable UUID stockId, @RequestBody Stock stockDetails) {
        _stockService.getStockById(stockId)
                .orElseThrow(() -> new EntityNotFoundException("Stock cannot be updated as it was not found"));
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
