package sytnikov.dev.inventory_microservice.application.stock;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.stock.IStockRepo;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;
import sytnikov.dev.inventory_microservice.domain.supplier.ISupplierRepo;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService implements IStockService{

    @Autowired
    private IStockRepo _stockRepo;
    @Autowired
    private ISupplierRepo _supplierRepo;

    @Override
    public Stock addStock(Supplier supplier, UUID productId, String productBarcode, int quantity) throws EntityNotFoundException {
        Stock stock = new Stock(UUID.randomUUID(), productId, productBarcode, quantity, supplier, new ArrayList<>());
        return _stockRepo.createOne(stock);
    }

    @Override
    public List<Stock> getAllStocks() {
        return _stockRepo.getAll();
    }

    @Override
    public Optional<Stock> getStockById(UUID stockId) {
        return _stockRepo.getOneById(stockId);
    }

    @Override
    public Stock modifyStock(Stock stock) {
        return _stockRepo.updateOne(stock);
    }

    @Override
    public void deleteStock(UUID stockId) {
        _stockRepo.deleteOne(stockId);
    }

    @Override
    public List<Stock> getStocksByProductId(String productBarcode) {
        return _stockRepo.getAllByProductId(productBarcode);
    }

    @Override
    public List<Stock> getStocksBySupplierId(UUID supplierId) {
        return _stockRepo.getAllBySupplierId(supplierId);
    }
}
