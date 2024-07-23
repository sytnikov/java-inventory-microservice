package sytnikov.dev.inventory_microservice.presentation;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.stock.IStockService;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockCreateDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockUpdateDto;
import sytnikov.dev.inventory_microservice.domain.stock.StockLevelEnum;
import sytnikov.dev.inventory_microservice.presentation.loggers.LoggerUtil;
import sytnikov.dev.inventory_microservice.presentation.shared.SuccessResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {

    private static final Logger _logger = LoggerUtil.getLogger(StockController.class);

    @Autowired
    private IStockService _stockService;

    @PostMapping
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> addStock(@RequestBody @Valid StockCreateDto stockDetails) {
        _logger.debug("Received request to add stock: {}", stockDetails);
        StockReadDto addedStock = _stockService.addStock(stockDetails);
        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity
                .<StockReadDto>builder()
                .data(Collections.singletonList(addedStock))
                .build();
        _logger.info("Stock added with id: {}", addedStock.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseEntity<List<StockReadDto>>> getAllStocks() {
        _logger.debug("Received request to get all stocks");
        List<StockReadDto> stocks = _stockService.getAllStocks();
        SuccessResponseEntity<List<StockReadDto>> response = SuccessResponseEntity
                .<List<StockReadDto>>builder()
                .data(Collections.singletonList(stocks))
                .build();
        _logger.info("Fetched all stocks, count: {}", stocks.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> getStockById(@PathVariable UUID stockId) {
        _logger.debug("Received request to get stock by id: {}", stockId);
        StockReadDto foundStock = _stockService.getStockById(stockId);
        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity
                .<StockReadDto>builder()
                .data(Collections.singletonList(foundStock))
                .build();
        _logger.info("Stock fetched successfully with id: {}", foundStock.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productBarcode}")
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> getStockByProductBarcode(@PathVariable String productBarcode) {
        _logger.debug("Received request to get stock by product barcode: {}", productBarcode);
        StockReadDto stockByProductBarcode = _stockService.getStockByProductBarcode(productBarcode);
        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity
                .<StockReadDto>builder()
                .data(Collections.singletonList(stockByProductBarcode))
                .build();
        _logger.info("Stock fetched by product barcode: {}", productBarcode);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<List<StockReadDto>>> getStocksBySupplierId(@PathVariable UUID supplierId) {
        _logger.debug("Received request to get stocks by supplier id: {}", supplierId);
        List<StockReadDto> stocksBySupplier = _stockService.getStocksBySupplierId(supplierId);
        SuccessResponseEntity<List<StockReadDto>> response = SuccessResponseEntity
                .<List<StockReadDto>>builder()
                .data(Collections.singletonList(stocksBySupplier))
                .build();
        _logger.info("Fetched stocks by supplier id: {}", supplierId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/level/{productBarcode}")
    public ResponseEntity<StockLevelEnum> checkStockLevel(@PathVariable String productBarcode) {
        _logger.debug("Received request to check stock level for product barcode: {}", productBarcode);
        StockLevelEnum stockLevel = _stockService.getStockLevel(productBarcode);
        _logger.info("Stock level for product barcode {}: {}", productBarcode, stockLevel);
        return ResponseEntity.ok(stockLevel);
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> modifyStock(@PathVariable UUID stockId, @RequestBody @Valid StockUpdateDto stockDetails) {
        _logger.debug("Received request to modify stock with id: {}", stockId);
        StockReadDto updatedStock = _stockService.modifyStock(stockId, stockDetails);
        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity
                .<StockReadDto>builder()
                .data(Collections.singletonList(updatedStock))
                .build();
        _logger.info("Stock modified with id: {}", updatedStock.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<SuccessResponseEntity<String>> deleteStock(@PathVariable UUID stockId) {
        _logger.debug("Received request to delete stock with id: {}", stockId);
        _stockService.deleteStock(stockId);
        SuccessResponseEntity<String> response = SuccessResponseEntity
                .<String>builder()
                .data(Collections.singletonList("Stock with id " + stockId + " was successfully deleted"))
                .build();
        _logger.info("Stock deleted with id: {}", stockId);
        return ResponseEntity.ok(response);
    }
}
