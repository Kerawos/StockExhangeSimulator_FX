package pl.mareksowa.models;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.Player;
import pl.mareksowa.models.stockExchange.StockExchange;
import pl.mareksowa.models.stockExchange.dao.PlayerDao;
import pl.mareksowa.models.stockExchange.dao.StockExchangeDao;
import pl.mareksowa.models.stockExchange.dao.impl.PlayerDaoImpl;
import pl.mareksowa.models.stockExchange.dao.impl.StockExchangeDaoImpl;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class StockExchangeSimulator{

    //variables declaration
    private String comend;
    private int turns;
    private int comendOption;
    private Map<StockModel, Integer> myStock;
    private List<StockModel> marketStock;
    private StockModel stock;

    StockExchangeDao stockExchangeDao;
    StockExchange stockExchange;
    PlayerDao playerDao;
    Player player;


    public void startStockExhangeSimulator(){

        //init
        stockExchangeDao = new StockExchangeDaoImpl();
        stockExchange = stockExchangeDao.getAllStockExchanges().get(0);
        playerDao = new PlayerDaoImpl();
        player = playerDao.getAllPlayers().get(0);

        // create market stock list
        stockExchangeDao.initializeStocksMarket();


        //StockExchangeSimulator (from 1'st turn to 281'st turn
        for (turns = 1; turns < 281 ; turns++) {
            stockExchangeSimulator.print30EmptyLines(); // print empty 30 lines
            showToConsole(stockExchangeSimulator.timeOfPlay(turns) + "     *****     Currently you have $" + (double)(Math.round(cash)*100)/100 + " , Your STOCK: ");
            stockExchangeSimulator.showMyStocks(myStock);
            showToConsole("\nStock value:");

            for (StockModel stock1 : marketStock) {
                stock1.adjustPrice(); //adjust new prise
                if(stock1.getPrice()<10.0){ //check if company bankpurts
                    showToConsole("Company " + stock.getName() + " bankpurts...");
                    marketStock.remove(stock1);
                    marketStock.add(new StockModel());
                }
            }
            stockExchangeSimulator.showStockList(marketStock); //show to user current stock values

            showToConsole("\nTo buy stock type 'b', to sell 's', to sell All stocks 'sAll', to wait turn 'next', to StockExchangeSimulator Exit 'exit'. All you should confirm by presing 'Submit' button.");
            comend = txtInput.getText();

            //TODO trzeba jakos zastopowac zeby user wpisal text

            switch (comend){
                case "b": {
                    stockExchangeSimulator.buyStock();
                    break;
                }
                case "s":{
                    stockExchangeSimulator.sellStock();
                    break;
                }
                case "sAll":{
                    stockExchangeSimulator.sellAllStocks();
                    break;
                }
                case "next":{
                    continue;
                }
                case "exit":{
                    turns = 300;
                    break;
                }
                default:{
                    showToConsole("So loud here, Maklers doesn't understand yours intentions, try be more accurate.");
                    continue;
                }
            }
        }

        stockExchangeSimulator.sellAllStocks();
        if (turns<300){
            showToConsole("Month gone..\n \n   *****   You earn: $" + (double)(Math.round(cash - 500)*100)/100);
        } else {
            showToConsole("You abort the stockExchangeSimulator..\n\n    *****    You earn: $" + (double)(Math.round(cash-500)*100)/100);
        }

    }

    public void welcomeScreen(){
        showToConsole("Stock Exhanege Simulator 2017 by Marek Sowa");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showToConsole("\nHey There!\nYou came to the stock exhange with yours savings $500\nStock Exhange open 8-18\nTry to multiply your money after near month\nGood Luck!");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
