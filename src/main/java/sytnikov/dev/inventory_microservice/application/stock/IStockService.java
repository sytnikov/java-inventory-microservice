package sytnikov.dev.inventory_microservice.application.stock;

import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;
import sytnikov.dev.inventory_microservice.domain.stock.StockLevelEnum;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IStockService {
    public Stock addStock(Supplier supplier, String productBarcode, int quantity);
    public List<Stock> getAllStocks();
    public Optional<Stock> getStockById(UUID stockId);
    public Stock modifyStock(Stock stock);
    public void deleteStock(UUID stockId);
    public Stock getStockByProductBarcode(String productBarcode);
    public List<Stock> getStocksBySupplierId(UUID supplierId);
    public StockLevelEnum getStockLevel(String productBarcode);
    public boolean isStockAvailable (String productBarcode, int requiredAmount);
}
