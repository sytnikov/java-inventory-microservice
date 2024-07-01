package sytnikov.dev.inventory_microservice.application.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.stock.IStockRepo;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService implements IStockService{

    @Autowired
    private IStockRepo _stockRepo;

    @Override
    public Stock addStock(Stock stock) {
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
}
