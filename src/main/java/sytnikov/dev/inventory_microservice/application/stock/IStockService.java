package sytnikov.dev.inventory_microservice.application.stock;

import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IStockService {
    public Stock addStock(Supplier supplier, UUID productId, String productBarcode, int quantity);
    public List<Stock> getAllStocks();
    public Optional<Stock> getStockById(UUID stockId);
    public Stock modifyStock(Stock stock);
    public void deleteStock(UUID stockId);
}
