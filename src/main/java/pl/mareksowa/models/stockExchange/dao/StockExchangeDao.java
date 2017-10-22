package pl.mareksowa.models.stockExchange.dao;

import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.StockExchange;

import java.util.List;
import java.util.Map;

public interface StockExchangeDao {
    List<StockExchange> getAllStockExchanges();
    void initializeStocksMarket(List<StockModel> marketStock);
    String getTimeOfPlay(int turns);
    String getStockList(List<StockModel> myList);
    void closeApp();
}
