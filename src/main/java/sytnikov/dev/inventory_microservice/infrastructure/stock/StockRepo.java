package sytnikov.dev.inventory_microservice.infrastructure.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.stock.IStockRepo;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StockRepo implements IStockRepo {

    @Autowired
    private IStockJpaRepo _stockJpaRepo;


    @Override
    public Stock createOne(Stock stock) {
        return _stockJpaRepo.save(stock);
    }

    @Override
    public List<Stock> getAll() {
        return _stockJpaRepo.findAll();
    }

    @Override
    public Optional<Stock> getOneById(UUID stockId) {
        return _stockJpaRepo.findById(stockId);
    }

    @Override
    public Stock updateOne(Stock stock) {
        return _stockJpaRepo.save(stock);
    }

    @Override
    public void deleteOne(UUID stockId) {
        _stockJpaRepo.deleteById(stockId);
    }
}
