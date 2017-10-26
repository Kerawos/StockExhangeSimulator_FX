package pl.mareksowa.models.stockExchange.dao;

import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.Player;

import java.util.List;
import java.util.Map;

public interface PlayerDao {
    List<Player>getAllPlayers();
    boolean isAlreadyInWallet(StockModel stock);
    void buyStock(int playerNumber);
    void sellStock(int playerNumber);
    void sellAllStocks(int playerNumber);
    String getMyStocks(int playerNumber);
}
