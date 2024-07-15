//package sytnikov.dev.inventory_microservice.application.stock;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import sytnikov.dev.inventory_microservice.domain.stock.IStockRepo;
//import sytnikov.dev.inventory_microservice.domain.stock.Stock;
//import sytnikov.dev.inventory_microservice.domain.stock.StockLevelEnum;
//import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class StockService implements IStockService{
//    private static final int LOW_STOCK_THRESHOLD = 10;
//    private static final int OUT_OF_STOCK_THRESHOLD = 0;
//
//    @Autowired
//    private IStockRepo _stockRepo;
//
//    @Override
//    public Stock addStock(Supplier supplier, String productBarcode, int quantity) {
//        Stock stock = new Stock(UUID.randomUUID(), productBarcode, quantity, supplier);
//        return _stockRepo.createOne(stock);
//    }
//
//    @Override
//    public List<Stock> getAllStocks() {
//        return _stockRepo.getAll();
//    }
//
//    @Override
//    public Optional<Stock> getStockById(UUID stockId) {
//        return _stockRepo.getOneById(stockId);
//    }
//
//    @Override
//    public Stock modifyStock(Stock stock) {
//        return _stockRepo.updateOne(stock);
//    }
//
//    @Override
//    public void deleteStock(UUID stockId) {
//        _stockRepo.deleteOne(stockId);
//    }
//
//    @Override
//    public Stock getStockByProductBarcode(String productBarcode) {
//        return _stockRepo.getOneByProductBarcode(productBarcode);
//    }
//
//    @Override
//    public List<Stock> getStocksBySupplierId(UUID supplierId) {
//        return _stockRepo.getAllBySupplierId(supplierId);
//    }
//
//    @Override
//    public StockLevelEnum getStockLevel(String productBarcode) {
//        Stock foundStocks = _stockRepo.getOneByProductBarcode(productBarcode);
//        int totalAmount = foundStocks.getQuantity();
//        if (totalAmount == OUT_OF_STOCK_THRESHOLD) {
//            return StockLevelEnum.OUT_OF_STOCK;
//        } else if (totalAmount <= LOW_STOCK_THRESHOLD) {
//            return StockLevelEnum.LOW_STOCK;
//        } else return StockLevelEnum.ENOUGH_STOCK;
//    }
//
//    @Override
//    public boolean isStockAvailable(String productBarcode, int requiredAmount) {
//        Stock foundStocks = _stockRepo.getOneByProductBarcode(productBarcode);
//        int totalAmount = foundStocks.getQuantity();
//        return totalAmount >= requiredAmount;
//    }
//}
