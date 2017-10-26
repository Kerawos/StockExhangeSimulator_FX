package pl.mareksowa.models.stock;

import java.util.Random;

public class StockModel {

    private String name;
    private double price;
    private double priceBuy;
    private double priceSell;
    private int type;

    public StockModel(String name, double price, int type) {
        this.name = name;
        this.price = price;
        this.priceBuy = Math.round((price*1.025) * 100) / 100;
        this.priceSell = Math.round((price*0.975)*100) / 100;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(double priceSell) {
        this.priceSell = priceSell;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "Name = '" + name + '\'' +
                ", Buy price $" + priceBuy +
                ", Sell price $" + priceSell +
                '}';
    }

}
