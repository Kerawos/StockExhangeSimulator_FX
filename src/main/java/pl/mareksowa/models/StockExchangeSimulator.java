package pl.mareksowa.models;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import pl.mareksowa.controllers.MainController;
import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.Player;
import pl.mareksowa.models.stockExchange.StockExchange;
import pl.mareksowa.models.stockExchange.dao.PlayerDao;
import pl.mareksowa.models.stockExchange.dao.StockExchangeDao;
import pl.mareksowa.models.stockExchange.dao.impl.PlayerDaoImpl;
import pl.mareksowa.models.stockExchange.dao.impl.StockExchangeDaoImpl;
import pl.mareksowa.views.ShowOutput;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class StockExchangeSimulator{

    //variables declaration
    private String comend;
    private int turns;
    private int comendOption;
    private Map<StockModel, Integer> myStock;
    private StockModel stock;
    private ShowOutput showOutput;

    private StockExchangeDao stockExchangeDao;
    private StockExchange stockExchange;
    private PlayerDao playerDao;
    private Player player;
    private MainController mainController;

    public void startStockExhangeSimulator(){

        //init
        stockExchangeDao = new StockExchangeDaoImpl();
        stockExchange = stockExchangeDao.getAllStockExchanges().get(0);
        playerDao = new PlayerDaoImpl();
        player = playerDao.getAllPlayers().get(0);
        showOutput = new ShowOutput();

        // create market stock list
        stockExchangeDao.createStockList(0);

        //StockExchangeSimulator (from 1'st turn to 281'st turn
        welcomeScreen();
        for (turns = 1; turns < 281 ; turns++) {
            showOutput.print10EmptyLines(); // print empty 10 lines
            showOutput.showToConsole(stockExchangeDao.getTimeOfPlay(turns) + "     *****     Currently you " +
                    "have $" + (double)(Math.round(player.getCach())*100)/100 + " , Your STOCK: ");
            showOutput.showToConsole(playerDao.getMyStocks(0));

            showOutput.showToConsole("\nStock value:");
            //show to user current stock values
            showOutput.showToConsole(stockExchangeDao.getStockFromStockList(stockExchange.getMarketStock()));
            showOutput.showToConsole("\nTo buy stock type 'b', to sell 's', to sell All stocks 'sAll', to wait " +
                    "turn 'next', to StockExchangeSimulator Exit 'exit'. All you should confirm by presing 'Submit' button.");

            comend = mainController.getUserInput();
            switch (comend){
                case "b": {
                    playerDao.buyStock(0);
                    break;
                }
                case "s":{
                    playerDao.sellStock(0);
                    break;
                }
                case "sAll":{
                    playerDao.sellAllStocks(0);
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
                    showOutput.showToConsole("So loud here, Maklers doesn't understand yours intentions, try be more accurate.");
                    continue;
                }
            }
        }

        playerDao.sellAllStocks(0);
        if (turns<300){
            showOutput.showToConsole("Month gone..\n \n   *****   You earn: $" + (double)(Math.round(player.getCach())*100)/100);
        } else {
            showOutput.showToConsole("You abort the stockExchangeSimulator..\n\n    *****    You earn: $" + (double)(Math.round(player.getCach())*100)/100);
        }

    }

    private void welcomeScreen(){
        showOutput.showToConsole("Stock Exhanege Simulator 2017 by Marek Sowa");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showOutput.showToConsole("\nHey There!\nYou came to the stock exhange with yours savings $500\nStock Exhange open 8-18\nTry to multiply your money after near month\nGood Luck!");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
