package main.model.SalesManagement;

import java.util.ArrayList;

import main.model.OrderManagement.Order;
import main.model.Personnel.Person;
import main.model.Personnel.Profile;

public class SalesPersonProfile extends Profile {
    ArrayList<Order> salesorders;


    public SalesPersonProfile(Person p) {

        super(p); 
        salesorders = new ArrayList<Order>();

    }
    public void addSalesOrder(Order o){
        salesorders.add(o);
    }
    @Override
    public String getRole(){
        return  "Sales";
    }

}
