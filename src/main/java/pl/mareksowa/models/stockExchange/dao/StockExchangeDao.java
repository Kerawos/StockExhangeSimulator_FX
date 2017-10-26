package pl.mareksowa.models.stockExchange.dao;

import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.StockExchange;

import java.util.List;
import java.util.Map;

public interface StockExchangeDao {
    List<StockExchange> getAllStockExchanges();
    List<StockModel> createStockList();
    String getStockFromStockList(List<StockModel> stockList);
    void updatePricesOfStockList(List<StockModel> stockList);
    String getTimeOfPlay(int turns);

    void closeApp();
}
