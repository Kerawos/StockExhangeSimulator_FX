package pl.mareksowa.models.stockExchange.dao.impl;

import javafx.application.Platform;
import pl.mareksowa.models.stock.StockManager;
import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.StockExchange;
import pl.mareksowa.models.stockExchange.dao.StockExchangeDao;

import java.util.ArrayList;
import java.util.List;

public class StockExchangeDaoImpl implements StockExchangeDao {

    List<StockExchange> makrets;
    private StockManager stockManager;

    public StockExchangeDaoImpl() {
        makrets = new ArrayList<>();
        makrets.add(new StockExchange());
        stockManager = new StockManager();
    }

    @Override
    public List<StockExchange> getAllStockExchanges() {
        return makrets;
    }

    @Override
    public List<StockModel> createStockList() {
        List<StockModel> marketStock = new ArrayList<>();
        for (int i = 0; i < 5; i++) { // create 5 default stock
            marketStock.add(new StockModel(stockManager.generateName(), stockManager.generatePrice(), stockManager.generateType())); // add random Stock
            int repeats = 0;
            for (StockModel stock1 : marketStock) { // loop thru our list
                if (stock1.getName().equals(marketStock.get(i).getName())) { // check repeats
                    repeats++;
                    if (repeats > 1) {// prevent comparing same stocks
                        marketStock.remove(i); //if same, then delete
                        i--; // decrement i
                    }
                    break; // exit 1'st loop
                }
            }
        }
        return marketStock;
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
            builder.append(i + ". " + stock);
            i++;
        }
        return builder.toString();
    }

    @Override
    public void updatePricesOfStockList(List<StockModel> stockList) {

    }

    @Override
    public void closeApp() {
        Platform.exit();
        System.exit(0);
    }
}
