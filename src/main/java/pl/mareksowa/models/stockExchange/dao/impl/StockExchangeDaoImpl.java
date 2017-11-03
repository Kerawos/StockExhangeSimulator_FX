package pl.mareksowa.models.stockExchange.dao.impl;

import javafx.application.Platform;
import pl.mareksowa.models.stock.StockManager;
import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.StockExchange;
import pl.mareksowa.models.stockExchange.dao.StockExchangeDao;

import java.util.ArrayList;
import java.util.List;

public class StockExchangeDaoImpl implements StockExchangeDao {

    private List<StockExchange> markets;
    private StockManager stockManager;

    public StockExchangeDaoImpl() {
        markets = new ArrayList<>();
        markets.add(new StockExchange());
        stockManager = new StockManager();
    }

    @Override
    public List<StockExchange> getAllStockExchanges() {
        return markets;
    }

    @Override
    public List<StockModel> createStockList(int marketNumber) {
        markets.get(marketNumber).getMarketStock();
        for (int i = 0; i < 5; i++) { // create 5 default stock
            markets.get(marketNumber).getMarketStock().add(new StockModel(stockManager.generateName(), stockManager.generatePrice(), stockManager.generateType())); // add random Stock
            int repeats = 0;
            for (StockModel stock1 : markets.get(marketNumber).getMarketStock()) { // loop thru our list
                if (stock1.getName().equals(markets.get(marketNumber).getMarketStock().get(i).getName())) { // check repeats
                    repeats++;
                    if (repeats > 1) {// prevent comparing same stocks
                        markets.get(marketNumber).getMarketStock().remove(i); //if same, then delete
                        i--; // decrement i
                    }
                    break; // exit 1'st loop
                }
            }
        }
        return markets.get(marketNumber).getMarketStock();
    }


    @Override
    public String getTimeOfPlay(int turns) {
        int week = 1;
        int day = 1;
        int hour = 1;
        String hourS = null;

        if (turns > 210 ){
            week = 4;
            day = ((turns-210)/11)+1;
        } else if (turns > 140){
            week = 3;
            day = ((turns-140)/11)+1;
        } else if (turns > 70) {
            week = 2;
            day = ((turns-70)/11)+1;
        } else {
            week = 1;
            day = (turns/11)+1;
        }
        //check hour
        hourS = String.valueOf(turns-1);
        return "Week: " + week + ", Day: " + day + ", Hour " + (Character.getNumericValue(hourS.charAt(hourS.length()-1)) + 8) + ":00, Turn: " + turns;
    }


    @Override
    public String getStockFromStockList(List<StockModel> stockList) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (StockModel stock : stockList) {
            builder.append(i + ". " + stock + "\n");
            i++;
        }
        return builder.toString();
    }

    @Override
    public void updatePricesOfStockList(List<StockModel> stockList) {

    }

    public void closeApp() {
        Platform.exit();
        System.exit(0);
    }
}
