package pl.mareksowa.models.stockExchange.dao;

import pl.mareksowa.models.stock.StockModel;

import java.util.List;
import java.util.Map;

public interface PlayerDao {
    void buyStock();
    void sellStock();
    void sellAllStocks();
    String getMyStocks(Map<StockModel, Integer> myList);
}
