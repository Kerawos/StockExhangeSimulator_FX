package pl.mareksowa.models.stock;

import java.util.Random;

public class StockManager {

    private double progressPrice = 0.4;
    private Random random = new Random();

    public String generateName(){
        Random random = new Random();
        String[] prefix = {"Tech", "Pol", "Tin", "Logi", "M", "TI", "I", "Wa", "Na", "My", "An", "Sob", "Eru&A", "Sata", "Gc", "Fir", "Log", "Master", "Key"};
        String[] suffix = {"pool", "staj", "wa", "tiv", "saj", "o-Mix", "uje", "truth", "-buy", "gaming", "delicatis", "tech", "lux", "stratos", "ange"};
        return prefix[random.nextInt(prefix.length-1)] + suffix[random.nextInt(suffix.length-1)];
    }

    public double generatePrice(){
        return random.nextInt(89)+11;
    }

    public double updatePriceBuy(double price){

        return Math.round((price*1.025) * 100) / 100;
    }

    public double updatePriceSell(double price){
        return Math.round((price*0.975)*100) / 100;
    }

    public int generateType(){
        return random.nextInt(9)+1;
    }

    public void adjustPrice(double price, int type){
        price += (type*((double)Math.round((random.nextDouble()-progressPrice)*100)/100));
        updatePriceBuy(price);
        updatePriceSell(price);
    }

    public String showProgressPrice(){
        double sum = 0;
        double rnd;
        for (int i = 0; i <100 ; i++) {
            rnd = (double)Math.round((random.nextDouble()-progressPrice)*100)/100;
            System.out.print(rnd+", ");
            sum+=rnd;
        }
        return "Suma to: " + sum;
    }
}
