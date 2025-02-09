package Investment_Simulator;

import java.util.ArrayList;

public class Stock {
        private String stockName;
        private Double stockPrice;
        private ArrayList<Double> priceHistory = new ArrayList<>(); 


    public Stock(String stockName, Double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.priceHistory = new ArrayList<>();
    } 

}
