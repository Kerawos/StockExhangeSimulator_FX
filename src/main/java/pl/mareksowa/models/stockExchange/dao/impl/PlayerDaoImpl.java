package pl.mareksowa.models.stockExchange.dao.impl;

import pl.mareksowa.controllers.MainController;
import pl.mareksowa.models.stock.StockModel;
import pl.mareksowa.models.stockExchange.Player;
import pl.mareksowa.models.stockExchange.dao.PlayerDao;
import pl.mareksowa.views.ShowOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerDaoImpl implements PlayerDao {

    List<Player> players;
    ShowOutput showOutput;
    MainController mainController;

    public PlayerDaoImpl() {
        players = new ArrayList<>();
        players.add(new Player(120));
        showOutput = new ShowOutput();
        mainController = new MainController();
    }

    @Override
    public List<Player> getAllPlayers() {
        return players;
    }

    @Override
    public boolean isAlreadyInWallet(StockModel stock) {
        return false;
    }

    //

    @Override
    public void buyStock(int playerNumber, List<StockModel> marketStock, StringBuilder sb) {
        int stockIndicator;
        do {
            sb.append("Type number of the Stock which you wanna buy:");
            showOutput.showToConsole(sb);
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

    @Override
    public void sellStock(int playerNumber) {
//        int loopNumber = 0;
//        for (Map.Entry<StockModel, Integer> element : players.get(playerNumber).getWallet().entrySet()){
//            if (loopNumber==stockNumber){
//
//            }
//            loopNumber++
//        }
//
//        int iIteratorFor = 0;
//        showToConsole("Type Stock number from your wallet which you wanna sell:");
//        comend = sc.nextLine();
//        comendOption = Integer.parseInt(comend);
//        isAlreadyInWallet = false;
//        for(Map.Entry<StockModel, Integer> iterat : myStock.entrySet()){
//            if (iIteratorFor==comendOption){ // check if we are in such index on our Map
//                do {
//                    showToConsole("You have " + iterat.getValue() + " Stocks " + iterat.getKey() + ", how many you wanna sell?:");
//                    comend = sc.nextLine();
//                    comendOption = Integer.parseInt(comend);
//                    if (comendOption>iterat.getValue()){ // sprawdza czy wgl mamy tyle w portfelu
//                        showToConsole("You haven't such amount... keep focus");
//                    }
//                }while(comendOption>iterat.getValue());
//                iterat.setValue(iterat.getValue()- comendOption); // decrement stock from wallet
//                if(iterat.getValue()==0){ //if we got 0 stock
//                    myStock.remove(iterat.getKey(), iterat.getValue()); // delete stock with 0
//                }
//                for (StockModel stock1 : marketStock) {//check market sell prise
//                    if (stock1.getName().equals(iterat.getKey().getName())){ // if found Stock check amount
//                        showToConsole(String.valueOf(stock1.getPriceSell()));
//                        cash += stock1.getPriceSell()*comendOption; // increse cash in wallet * amount of indexing Stocks
//                        isAlreadyInWallet = true;
//                        break;
//                    }
//                }
//            }
//            if (isAlreadyInWallet){ // guard for infinit loop
//                break;
//            }
//
//            iIteratorFor++;
//        }
//        isAlreadyInWallet = false;
    }

    @Override
    public String sellAllStocks(int playerNumber, List<StockModel> marketStocks, StringBuilder sb) {
        for (Map.Entry<StockModel, Integer> element : players.get(playerNumber).getWallet().entrySet()){ //loop thru our stock
            for (StockModel stock : marketStocks) {//loop for market for check if stock exists
                if (stock.getName().equals(element.getKey().getName())){ // check sell price
                    sb.append("\n" + stock.getName() + " has been sold " + element.getValue() + " times for $"
                            + stock.getPriceSell()*element.getValue());
                    players.get(playerNumber).setCach(players.get(playerNumber).getCach()+stock.getPriceSell()*element.getValue());
                    element.setValue(0);
                    players.get(playerNumber).adjustWallet(stock.getName(), 0);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String getMyStocks(int playerNumber) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Map.Entry<StockModel, Integer> element : players.get(playerNumber).getWallet().entrySet()) { //iterrate thru our Map
            builder.append(i + ". " + element.getKey() + ", ilosc x" + element.getValue()); // show our wallet
            i++;
        }
        return builder.toString();
    }
}
