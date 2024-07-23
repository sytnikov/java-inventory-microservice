package sytnikov.dev.inventory_microservice.domain.stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockRepo {
    Stock createOne(Stock stock);
    List<Stock> getAll();
    Optional<Stock> getOneById(UUID stockId);
    Stock updateOne(Stock stock);
    void deleteOne(UUID stockId);
    Stock getOneByProductBarcode(String productBarcode);
    List<Stock> getAllBySupplierId(UUID supplierId);
}
