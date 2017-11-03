package pl.mareksowa.models.stockExchange;

import pl.mareksowa.models.stock.StockModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private double cash;
    private HashMap<StockModel, Integer> wallet;

    public Player(double cash) {
        this.cash = cash;
        wallet = new HashMap<>();
    }

    public double getCach() {
        return cash;
    }

    public void setCach(double cach) {
        this.cash = cach;
    }

    public HashMap<StockModel, Integer> getWallet() {
        return wallet;
    }

    public void setWallet(HashMap<StockModel, Integer> wallet) {
        this.wallet = wallet;
    }

    public void adjustWallet(String keyName, int setValue){
        for (Map.Entry<StockModel, Integer> element : wallet.entrySet()){
            if (element.getKey().getName().equals(keyName)){
                element.setValue(setValue);
            }
        }
    }
}
