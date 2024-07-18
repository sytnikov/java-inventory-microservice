package sytnikov.dev.inventory_microservice.presentation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.stock.IStockService;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockCreateDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockUpdateDto;
import sytnikov.dev.inventory_microservice.application.supplier.ISupplierService;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;
import sytnikov.dev.inventory_microservice.domain.stock.StockLevelEnum;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;
import sytnikov.dev.inventory_microservice.presentation.shared.SuccessResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {

    @Autowired
    private IStockService _stockService;

    @PostMapping
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> addStock(@RequestBody @Valid StockCreateDto stockDetails) {
        StockReadDto addedStock = _stockService.addStock(stockDetails);
        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity
                .<StockReadDto>builder()
                .data(Collections.singletonList(addedStock))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseEntity<List<StockReadDto>>> getAllStocks() {
        List<StockReadDto> stocks = _stockService.getAllStocks();
        SuccessResponseEntity<List<StockReadDto>> response = SuccessResponseEntity
                .<List<StockReadDto>>builder()
                .data(Collections.singletonList(stocks))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> getStockById(@PathVariable UUID stockId) {
        StockReadDto foundStock = _stockService.getStockById(stockId);
        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity
                .<StockReadDto>builder()
                .data(Collections.singletonList(foundStock))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productBarcode}")
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> getStockByProductBarcode(@PathVariable String productBarcode) {
        StockReadDto stockByProductBarcode = _stockService.getStockByProductBarcode(productBarcode);
        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity
                .<StockReadDto>builder()
                .data(Collections.singletonList(stockByProductBarcode))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<List<StockReadDto>>> getStocksBySupplierId(@PathVariable UUID supplierId) {
        List<StockReadDto> stocksBySupplier = _stockService.getStocksBySupplierId(supplierId);
        SuccessResponseEntity<List<StockReadDto>> response = SuccessResponseEntity
                .<List<StockReadDto>>builder()
                .data(Collections.singletonList(stocksBySupplier))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/level/{productBarcode}")
    public ResponseEntity<StockLevelEnum> checkStockLevel(@PathVariable String productBarcode) {
        StockLevelEnum stockLevel = _stockService.getStockLevel(productBarcode);
        return ResponseEntity.ok(stockLevel);
    }

    // can't change supplierId
    @PutMapping("/{stockId}")
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> modifyStock(@PathVariable UUID stockId, @RequestBody @Valid StockUpdateDto stockDetails) {
        StockReadDto updatedStock = _stockService.modifyStock(stockId, stockDetails);
        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity
                .<StockReadDto>builder()
                .data(Collections.singletonList(updatedStock))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<SuccessResponseEntity<String>> deleteStock(@PathVariable UUID stockId) {
        _stockService.deleteStock(stockId);
        SuccessResponseEntity<String> response = SuccessResponseEntity
                .<String>builder()
                .data(Collections.singletonList("Stock with id " + stockId + " was successfully deleted"))
                .build();
        return ResponseEntity.ok(response);
    }
}
