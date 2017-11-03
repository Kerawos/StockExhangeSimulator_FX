package pl.mareksowa.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import pl.mareksowa.models.StockExchangeSimulator;
import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.Player;
import pl.mareksowa.models.stockExchange.StockExchange;
import pl.mareksowa.models.stockExchange.dao.PlayerDao;
import pl.mareksowa.models.stockExchange.dao.StockExchangeDao;
import pl.mareksowa.models.stockExchange.dao.impl.PlayerDaoImpl;
import pl.mareksowa.models.stockExchange.dao.impl.StockExchangeDaoImpl;
import pl.mareksowa.views.ShowOutput;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainController implements Initializable {

        //FX declaration
    @FXML
    TextField txtInput;

    @FXML
    TextArea txtOutput;

    //variables declaration
    private String commend;
    private int turns;
    private int comendOption;
    private Map<StockModel, Integer> myStock;
    private StockModel stock;
    private ShowOutput showOutput;
    private StringBuilder mainBuilder;

    private StockExchangeDao stockExchangeDao;
    private StockExchange stockExchange;
    private PlayerDao playerDao;
    private Player player;


    @Override //what will be loaded
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->txtInput.requestFocus());
        txtOutput.setWrapText(true);
        txtInput.requestFocus();

        registerHitEnterKey();

        //init
        stockExchangeDao = new StockExchangeDaoImpl();
        stockExchange = stockExchangeDao.getAllStockExchanges().get(0);
        playerDao = new PlayerDaoImpl();
        player = playerDao.getAllPlayers().get(0);
        showOutput = new ShowOutput();

        // create market stock list
        stockExchangeDao.createStockList(0);
        mainBuilder = new StringBuilder();
        mainBuilder.append(welcomeScreen());
        showToConsole(mainBuilder.toString());
        mainBuilder.append(currentGameScreen());
        showToConsole(mainBuilder.toString());

    }

    public void registerHitEnterKey(){
       txtInput.setOnKeyPressed(event -> {
           if (event.getCode() == KeyCode.ENTER) {
               if (txtInput.getText().length() > 0) {
                   mainBuilder.setLength(0);
                   turns++;
                   switch (txtInput.getText()){
                       case "b": {
                           playerDao.buyStock(0, stockExchange.getMarketStock(), mainBuilder);
                           txtInput.clear();
                           break;
                       }
                       case "s":{
                           playerDao.sellStock(0);
                           txtInput.clear();
                           break;
                       }
                       case "sAll":{
                           mainBuilder.append(playerDao.sellAllStocks(0, stockExchange.getMarketStock(), mainBuilder));
                           txtInput.clear();
                           mainBuilder.append(currentGameScreen());
                           showToConsole(mainBuilder.toString());
                           break;
                       }
                       case "next":{
                           txtInput.clear();
                           break;
                       }
                       case "exit":{
                           turns = 300;
                           txtInput.clear();
                           closeApp();
                           break;
                       }
                       default:{
                           txtInput.clear();
                           mainBuilder.append("So loud here, Maklers doesn't understand yours intentions, try be more accurate.\n");
                           mainBuilder.append(currentGameScreen());
                           showToConsole(mainBuilder.toString());
                           break;
                       }
                   }
               }
           }
       });
    }

    public void showToConsole(String output){
        txtOutput.setText(output);
    }

    public String getUserInput(){
        String input = txtInput.getText();
        txtInput.clear();
        return input;
    }


    //private MainController mainController;

//    public void startStockExhangeSimulator(){
//
//        //init
//        stockExchangeDao = new StockExchangeDaoImpl();
//        stockExchange = stockExchangeDao.getAllStockExchanges().get(0);
//        playerDao = new PlayerDaoImpl();
//        player = playerDao.getAllPlayers().get(0);
//        showOutput = new ShowOutput();
//
//        // create market stock list
//        stockExchangeDao.createStockList(0);
//
//        //StockExchangeSimulator (from 1'st turn to 281'st turn
//        welcomeScreen();
//        System.out.println(turns + "przed forem");
//        for (turns = 1; turns < 281 ; turns++) {
//            System.out.println("w forze");
//            //showOutput.print10EmptyLines(); // print empty 10 lines
//
//            for (int i = 0; i < 10 ; i++) {
//                showToConsole("");
//            }
//
//            showToConsole(stockExchangeDao.getTimeOfPlay(turns) + "     *****     Currently you " +
//                    "have $" + (double)(Math.round(player.getCach())*100)/100 + " , Your STOCK: ");
//            showToConsole(playerDao.getMyStocks(0));
//
//            showToConsole("\nStock value:");
//            //show to user current stock values
//            showToConsole(stockExchangeDao.getStockFromStockList(stockExchange.getMarketStock()));
//            showToConsole("\nTo buy stock type 'b', to sell 's', to sell All stocks 'sAll', to wait " +
//                    "turn 'next', to StockExchangeSimulator Exit 'exit'. All you should confirm by presing 'Submit' button.");
//
//            commend = getUserInput();
//            switch (commend){
//                case "b": {
//                    playerDao.buyStock(0);
//                    break;
//                }
//                case "s":{
//                    playerDao.sellStock(0);
//                    break;
//                }
//                case "sAll":{
//                    playerDao.sellAllStocks(0);
//                    break;
//                }
//                case "next":{
//                    continue;
//                }
//                case "exit":{
//                    turns = 300;
//                    break;
//                }
//                default:{
//                    showToConsole("So loud here, Maklers doesn't understand yours intentions, try be more accurate.");
//                }
//            }
//
//            System.out.println(turns);
//        }
//
//        playerDao.sellAllStocks(0);
//        if (turns<300){
//            showToConsole("Month gone..\n \n   *****   You earn: $" + (double)(Math.round(player.getCach())
//                    *100)/100);
//        } else {
//            showToConsole("You abort the stockExchangeSimulator..\n\n    *****    You earn: $" + (double)(Math.
//                    round(player.getCach())*100)/100);
//        }
//
//    }

    private String welcomeScreen(){
        StringBuilder sb = new StringBuilder();
        sb.append("Stock Exhanege Simulator 2017 by Marek Sowa\n");
        sb.append("\nHey There!\nYou came to the stock exhange with yours savings $" + playerDao.getAllPlayers().get(0).
                getCach()+"\nStock Exhange open 8-18\nTry to multiply your money after near month\nGood Luck!\n\n");
        return sb.toString();
    }

    private String currentGameScreen(){
        StringBuilder sb = new StringBuilder();
        sb.append("_____________________________________\n"+stockExchangeDao.getTimeOfPlay(turns)+"\n");
        sb.append("\n*** Player:\n");
        sb.append("Cash $"+playerDao.getAllPlayers().get(0).getCach()+"\n");
        sb.append("Wallet: " + playerDao.getAllPlayers().get(0).getWallet()+"\n");
        sb.append("\n*** Market:"+"\n");
        sb.append(stockExchangeDao.getStockFromStockList(stockExchange.getMarketStock()));
        sb.append("***\n\nTo buy stock type 'b', to sell 's', to sell All stocks 'sAll', to wait turn 'next', to Exit " +
                "'exit'. Every option confirm by pressing 'Enter'..\n");
        sb.append("~~");
        return sb.toString();
    }

    public void closeApp(){
        Platform.exit();
        System.exit(0);
    }

}
