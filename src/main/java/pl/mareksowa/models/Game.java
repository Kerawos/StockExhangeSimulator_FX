package pl.mareksowa.models;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import pl.mareksowa.models.stock.StockModel;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game implements Initializable{

    //FX declaration
    @FXML
    TextField txtInput;

    @FXML
    TextArea txtArea;

    @FXML
    Button btnSubmit;

    //variables declaration
    public static String comend;
    public static int turns;
    public static int comendOption;
    public static double cash;
    public static boolean isAlreadyInWallet;
    public static Map<StockModel, Integer> myStock;
    public static List<StockModel> marketStock;
    public static StockModel stock;
    public static Scanner sc;

    StringBuilder stringBuilder;




    public void submitAction(String input){
        showToConsole(input);
        txtInput.clear();
    }


    public void registerSubmitButtonAction(){
        btnSubmit.setOnMouseClicked(e->submitAction(txtInput.getText()));
    }


    private void registerSubmitEnterButtonAction(){
        txtInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                submitAction(txtInput.getText());
            }
        });
    }

    public void showToConsole(String output){
        txtArea.setText(output);
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






    public void start(){

        //initialization
        cash = 500.0;
        isAlreadyInWallet = false;

        Game game = new Game();
        myStock = new HashMap<>();
        marketStock = new ArrayList<>();
        stock = new StockModel();
        sc = new Scanner(System.in);

        game.initializeStocksMarket(marketStock); // create market stock list


        //Game (from 1'st turn to 281'st turn
        for (turns = 1; turns < 281 ; turns++) {
            game.print30EmptyLines(); // print empty 30 lines
            showToConsole(game.timeOfPlay(turns) + "     *****     Currently you have $" + (double)(Math.round(cash)*100)/100 + " , Your STOCK: ");
            game.showMyStocks(myStock);
            showToConsole("\nStock value:");

            for (StockModel stock1 : marketStock) {
                stock1.adjustPrice(); //adjust new prise
                if(stock1.getPrice()<10.0){ //check if company bankpurts
                    showToConsole("Company " + stock.getName() + " bankpurts...");
                    marketStock.remove(stock1);
                    marketStock.add(new StockModel());
                }
            }
            game.showStockList(marketStock); //show to user current stock values

            showToConsole("\nTo buy stock type 'b', to sell 's', to sell All stocks 'sAll', to wait turn 'next', to Game Exit 'exit'. All you should confirm by presing 'Submit' button.");
            comend = txtInput.getText();

            //TODO trzeba jakos zastopowac zeby user wpisal text

            switch (comend){
                case "b": {
                    game.buyStock();
                    break;
                }
                case "s":{
                    game.sellStock();
                    break;
                }
                case "sAll":{
                    game.sellAllStocks();
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

        game.sellAllStocks();
        if (turns<300){
            showToConsole("Month gone..\n \n   *****   You earn: $" + (double)(Math.round(cash - 500)*100)/100);
        } else {
            showToConsole("You abort the game..\n\n    *****    You earn: $" + (double)(Math.round(cash-500)*100)/100);
        }

    }


    //return time of play
    public String timeOfPlay(int turns){
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


    public void showMyStocks(Map<StockModel, Integer> myList) {
        int i = 0;
        for (Map.Entry<StockModel, Integer> iterat : myList.entrySet()) { //iterrate thru our Map
            showToConsole(i + ". " + iterat.getKey() + ", ilosc x" + iterat.getValue()); // show our wallet
            i++;
        }
    }


    public void print30EmptyLines(){
        for (int i = 0; i < 30 ; i++) {
            showToConsole("");
        }
    }


    public void showStockList(List<StockModel> myList){
        int i = 0;
        for (StockModel stock : myList) {
            showToConsole(i + ". " + stock);
            i++;
        }
    }


    public void buyStock(){
        int stockIndicator;

        do {
            showToConsole("Type number of the Stock which you wanna buy:");
            comend = sc.nextLine();
            stockIndicator = Integer.parseInt(comend);
            if (stockIndicator>=marketStock.size()-1){
                showToConsole("There is no such a Stock in the market, try again..");
            }
        }while (stockIndicator>=marketStock.size());

        showToConsole("You can buy max : " + (int)(cash/marketStock.get(stockIndicator).getPriceBuy()));
        showToConsole("How many Stock you wanna buy " + marketStock.get(stockIndicator).getName() + " ?");
        comend = sc.nextLine();
        comendOption = Integer.parseInt(comend);
        if (comendOption*marketStock.get(stockIndicator).getPriceBuy()>cash){
            showToConsole("You haven't cash for such of amount of Stocks..");
        } else {
            //we buy
            for(Map.Entry<StockModel, Integer> iterat : myStock.entrySet()) { // iterate for our stocks (wallet)
                if (iterat.getKey().getName().equals(marketStock.get(stockIndicator).getName())) { // check if we have such Stock in our wallet
                    iterat.setValue(iterat.getValue() + comendOption); // increment Stock in wallet
                    isAlreadyInWallet = true;
                    cash -= marketStock.get(stockIndicator).getPriceBuy()*comendOption; // take cash
                    break; // break loop after bouy
                }
            } // exit loop
            if (!isAlreadyInWallet) { //if Stock has been not allocated in wallet we have to add it
                myStock.put(marketStock.get(stockIndicator), comendOption);
                isAlreadyInWallet = false; // reset position in wallet
                cash -= marketStock.get(stockIndicator).getPriceBuy()*comendOption; // pay
            }
        }
    }


    public void sellStock(){
        int iIteratorFor = 0;
        showToConsole("Type Stock number from your wallet which you wanna sell:");
        comend = sc.nextLine();
        comendOption = Integer.parseInt(comend);
        isAlreadyInWallet = false;
        for(Map.Entry<StockModel, Integer> iterat : myStock.entrySet()){
            if (iIteratorFor==comendOption){ // check if we are in such index on our Map
                do {
                    showToConsole("You have " + iterat.getValue() + " Stocks " + iterat.getKey() + ", how many you wanna sell?:");
                    comend = sc.nextLine();
                    comendOption = Integer.parseInt(comend);
                    if (comendOption>iterat.getValue()){ // sprawdza czy wgl mamy tyle w portfelu
                        showToConsole("You haven't such amount... keep focus");
                    }
                }while(comendOption>iterat.getValue());
                iterat.setValue(iterat.getValue()- comendOption); // decrement stock from wallet
                if(iterat.getValue()==0){ //if we got 0 stock
                    myStock.remove(iterat.getKey(), iterat.getValue()); // delete stock with 0
                }
                for (StockModel stock1 : marketStock) {//check market sell prise
                    if (stock1.getName().equals(iterat.getKey().getName())){ // if found Stock check amount
                        showToConsole(String.valueOf(stock1.getPriceSell()));
                        cash += stock1.getPriceSell()*comendOption; // increse cash in wallet * amount of indexing Stocks
                        isAlreadyInWallet = true;
                        break;
                    }
                }
            }
            if (isAlreadyInWallet){ // guard for infinit loop
                break;
            }

            iIteratorFor++;
        }
        isAlreadyInWallet = false;
    }


    public void sellAllStocks(){
        int valueTimes = 0;
        for (Map.Entry<StockModel, Integer> iterat : myStock.entrySet()){ //loop thru our stock
            valueTimes = iterat.getValue();
            for (StockModel stock1 : marketStock) {//loop for market for check if stock exists
                if (stock1.getName().equals(iterat.getKey().getName())){ // check sell price
                    cash += (stock1.getPriceSell()*valueTimes); // sell xTimes our stock
                    iterat.setValue(0);
                    myStock.remove(iterat.getKey(), iterat.getValue());
                    break;
                }
            }
        }
    }


    public void initializeStocksMarket(List<StockModel> marketStock) { //create random stock to our market
        for (int i = 0; i < 5; i++) { // create 5 default stock
            marketStock.add(new StockModel()); // add random Stock
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->txtInput.requestFocus());
        txtArea.setWrapText(true);
        registerSubmitEnterButtonAction();
        registerSubmitButtonAction();
        Game game = new Game();
        game.test();
    }

    public static void test(){
        Game game = new Game();
        game.showToConsole("dupa");
        //mainController.showToConsole("hello");
    }
}
