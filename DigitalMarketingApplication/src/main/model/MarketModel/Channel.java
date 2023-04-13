package main.model.MarketModel;

public class Channel {
    String name; //tv, internet, 
    int price;
    String unitofmeasure; //per minute, length, …

    public Channel(String t){
        name = t;
    }

    public String getName() {
        return name;
    }


}
