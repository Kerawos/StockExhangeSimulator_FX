package pl.mareksowa.models.stockExchange;

import pl.mareksowa.models.stock.StockModel;

import java.util.List;

public class Player {

    private double cach;
    private List<StockModel> wallet;

    public Player(double cach, List<StockModel> wallet) {
        this.cach = cach;
        this.wallet = wallet;
    }

    public double getCach() {
        return cach;
    }

    public void setCach(double cach) {
        this.cach = cach;
    }

    public List<StockModel> getWallet() {
        return wallet;
    }

    public void setWallet(List<StockModel> wallet) {
        this.wallet = wallet;
    }
}
