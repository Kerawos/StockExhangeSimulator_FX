package pl.mareksowa.models.stockExchange;

import pl.mareksowa.models.stock.StockModel;

import java.util.*;

public class StockExchange {
    private List<StockModel> marketStock;
    private StockModel stock;

    public StockExchange(List<StockModel> marketStock, StockModel stock) {
        this.marketStock = marketStock;
        this.stock = stock;
    }

    public List<StockModel> getMarketStock() {
        return marketStock;
    }

    public void setMarketStock(List<StockModel> marketStock) {
        this.marketStock = marketStock;
    }

    public StockModel getStock() {
        return stock;
    }

    public void setStock(StockModel stock) {
        this.stock = stock;
    }
}
