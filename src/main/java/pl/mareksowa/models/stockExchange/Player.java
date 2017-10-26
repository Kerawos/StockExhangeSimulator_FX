package pl.mareksowa.models.stockExchange;

import pl.mareksowa.models.stock.StockModel;

import java.util.List;

public class Player {

    private double cash;
    private List<StockModel> wallet;

    public Player(double cash, List<StockModel> wallet) {
        this.cash = cash;
        this.wallet = wallet;
    }

    public double getCach() {
        return cash;
    }

    public void setCach(double cach) {
        this.cash = cach;
    }

    public List<StockModel> getWallet() {
        return wallet;
    }

    public void setWallet(List<StockModel> wallet) {
        this.wallet = wallet;
    }
}
