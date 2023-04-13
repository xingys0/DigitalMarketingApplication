package main.model.MarketModel;
import java.util.ArrayList;

import main.model.ProductManagement.SolutionOffer;


public class Market {
    String name;
    ArrayList<SolutionOffer> so;
    ArrayList<MarketChannelAssignment> marketChannelAssignments;
    ArrayList<String> characteristics;
    ArrayList<Market> submarkets;
    int size;
  
    public Market(String n) {
      name = n;
      so = new ArrayList<SolutionOffer>();
      marketChannelAssignments = new ArrayList<MarketChannelAssignment>();
      characteristics = new ArrayList<String>();
    }
  
    public ArrayList<MarketChannelAssignment> getMarketChannelAssignments() {
      return marketChannelAssignments;
    }
  
    public void addMarketChannelAssignments(MarketChannelAssignment marketChannelAssignment) {
      marketChannelAssignments.add(marketChannelAssignment);
    }
    public String getName() {
      return name;
    }
  

    
}
