package main.model.CustomerManagement;

import java.util.ArrayList;

import main.model.MarketModel.Market;

public class MarketCatalog {
    
    ArrayList<Market> markets;
    
    public MarketCatalog(ArrayList<Market> m) {
        markets = m;
    }

    public ArrayList<Market> getMarkets() {
        return markets;
    }
    
    public void addMarket(Market market){
        markets.add(market);
    }

}
