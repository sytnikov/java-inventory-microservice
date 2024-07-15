package sytnikov.dev.inventory_microservice.domain.stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockRepo {
    public Stock createOne(Stock stock);
    public List<Stock> getAll();
    public Optional<Stock> getOneById(UUID stockId);
    public Stock updateOne(Stock stock);
    public void deleteOne(UUID stockId);
    public Stock getOneByProductBarcode(String productBarcode);
    public List<Stock> getAllBySupplierId(UUID supplierId);
}
