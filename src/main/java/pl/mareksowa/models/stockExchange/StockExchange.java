package pl.mareksowa.models.stockExchange;

import pl.mareksowa.models.stock.StockModel;

import java.util.*;

public class StockExchange {
    private List<StockModel> marketStock;

    public StockExchange() {
        marketStock = new ArrayList<>();
    }

    public List<StockModel> getMarketStock() {
        return marketStock;
    }

    public void setMarketStock(List<StockModel> marketStock) {
        this.marketStock = marketStock;
    }
}
