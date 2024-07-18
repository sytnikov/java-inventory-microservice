package sytnikov.dev.inventory_microservice.application.stock;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockCreateDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockUpdateDto;
import sytnikov.dev.inventory_microservice.application.supplier.ISupplierService;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.domain.stock.IStockRepo;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;
import sytnikov.dev.inventory_microservice.domain.stock.StockLevelEnum;
import sytnikov.dev.inventory_microservice.domain.supplier.ISupplierRepo;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;

import java.util.UUID;

@Service
public class StockService implements IStockService{
    private static final int LOW_STOCK_THRESHOLD = 10;
    private static final int OUT_OF_STOCK_THRESHOLD = 0;

    @Autowired
    private IStockRepo _stockRepo;

    @Autowired
    private ISupplierRepo _supplierRepo;

    @Autowired
    private ISupplierService _supplierService;

    @Autowired
    private StockMapper _stockMapper;

    @Override
    public StockReadDto addStock(StockCreateDto stockDetails) {

        // check if there's a supplier to add a stock
        UUID supplierId = stockDetails.getSupplierId();
        _supplierService.getSupplierById(supplierId);

        Stock newStock = _stockMapper.createDtoToEntity(stockDetails);
        Stock addedStock = _stockRepo.createOne(newStock);
        return _stockMapper.entityToReadDto(addedStock);
    }

    @Override
    public List<StockReadDto> getAllStocks() {
        List<Stock> stocks = _stockRepo.getAll();
        return stocks.stream().map(_stockMapper::entityToReadDto).toList();
    }

    @Override
    public StockReadDto getStockById(UUID stockId) {
        Stock foundStock = _stockRepo.getOneById(stockId)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id " + stockId));
        return _stockMapper.entityToReadDto(foundStock);
    }

    @Override
    public StockReadDto modifyStock(UUID stockId, StockUpdateDto stockDetails) {
        Stock foundStock = _stockRepo.getOneById(stockId)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id " + stockId));
        _stockMapper.entityFromUpdateDto(stockDetails, foundStock);
        Stock updatedStock = _stockRepo.updateOne(foundStock);
        return _stockMapper.entityToReadDto(updatedStock);
    }

    @Override
    public void deleteStock(UUID stockId) {
        _stockRepo.getOneById(stockId)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id " + stockId));
        _stockRepo.deleteOne(stockId);
    }

    @Override
    public StockReadDto getStockByProductBarcode(String productBarcode) {
        Stock foundStock = _stockRepo.getOneByProductBarcode(productBarcode);
        if (foundStock == null) {
            throw new EntityNotFoundException("Product with the barcode " + productBarcode + " not found");
        }
        return _stockMapper.entityToReadDto(foundStock);
    }

    @Override
    public List<StockReadDto> getStocksBySupplierId(UUID supplierId) {
        _supplierRepo.getOneById(supplierId).orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
        List<Stock> foundStocks = _stockRepo.getAllBySupplierId(supplierId);
        return foundStocks.stream().map(_stockMapper::entityToReadDto).toList();
    }

    @Override
    public StockLevelEnum getStockLevel(String productBarcode) {
        Stock foundStock = _stockRepo.getOneByProductBarcode(productBarcode);
        if (foundStock == null) {
            throw new EntityNotFoundException("Product with the barcode " + productBarcode + " not found");
        }
        int totalAmount = foundStock.getQuantity();
        if (totalAmount == OUT_OF_STOCK_THRESHOLD) {
            return StockLevelEnum.OUT_OF_STOCK;
        } else if (totalAmount <= LOW_STOCK_THRESHOLD) {
            return StockLevelEnum.LOW_STOCK;
        } else return StockLevelEnum.ENOUGH_STOCK;
    }

    @Override
    public boolean isStockAvailable(String productBarcode, int requiredAmount) {
        Stock foundStock = _stockRepo.getOneByProductBarcode(productBarcode);
        if (foundStock == null) {
            throw new EntityNotFoundException("Product with the barcode " + productBarcode + " not found");
        }
        int totalAmount = foundStock.getQuantity();
        return totalAmount >= requiredAmount;
    }
}
