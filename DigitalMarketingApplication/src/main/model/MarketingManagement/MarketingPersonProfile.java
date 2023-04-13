package main.model.MarketingManagement;

import java.util.ArrayList;

import main.model.OrderManagement.Order;
import main.model.Personnel.Person;
import main.model.Personnel.Profile;

public class MarketingPersonProfile extends Profile {
    ArrayList<Order> salesorders;


    public MarketingPersonProfile(Person p) {

        super(p); 
        salesorders = new ArrayList<Order>();

    }
    public void addSalesOrder(Order o){
        salesorders.add(o);
    }
    @Override
    public String getRole(){
        return  "Marketing";
    }

}