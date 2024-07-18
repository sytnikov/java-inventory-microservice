package sytnikov.dev.inventory_microservice.application.stock;

import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockCreateDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockUpdateDto;
import sytnikov.dev.inventory_microservice.domain.stock.StockLevelEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IStockService {
    StockReadDto addStock(StockCreateDto stockDetails);
    List<StockReadDto> getAllStocks();
    StockReadDto getStockById(UUID stockId);
    StockReadDto modifyStock(UUID stockId, StockUpdateDto stockDetails);
    void deleteStock(UUID stockId);
    StockReadDto getStockByProductBarcode(String productBarcode);
    List<StockReadDto> getStocksBySupplierId(UUID supplierId);
    StockLevelEnum getStockLevel(String productBarcode);
    boolean isStockAvailable (String productBarcode, int requiredAmount);
}
