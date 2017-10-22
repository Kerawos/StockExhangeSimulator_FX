package pl.mareksowa.models.stockExchange;

import pl.mareksowa.models.StockExchangeSimulator;
import pl.mareksowa.models.stock.StockModel;

import java.util.*;

public class StockExchange {

    private double cash = 500.0;
    private boolean isAlreadyInWallet = false;
    private Map<StockModel, Integer> myStock = new HashMap<>();
    private List<StockModel> marketStock = new ArrayList<>();
    private StockModel stock = new StockModel();
    StockExchangeSimulator stockExchangeSimulator = new StockExchangeSimulator();


}
