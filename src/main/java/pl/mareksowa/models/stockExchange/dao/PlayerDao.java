package pl.mareksowa.models.stockExchange.dao;

import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.Player;

import java.util.List;
import java.util.Map;

public interface PlayerDao {
    List<Player>getAllPlayers();
    boolean isAlreadyInWallet(StockModel stock);
    void buyStock(int playerNumber, List<StockModel> marketStock, StringBuilder sb);
    void sellStock(int playerNumber);
    String sellAllStocks(int playerNumber,  List<StockModel> marketStock, StringBuilder sb);
    String getMyStocks(int playerNumber);
}
