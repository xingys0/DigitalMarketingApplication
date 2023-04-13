package main.model.CustomerManagement;

import java.util.ArrayList;

import main.model.MarketModel.Market;
import main.model.OrderManagement.Order;
import main.model.Personnel.Person;



public class CustomerProfile {
    ArrayList<Order> orders;
    ArrayList<Market> markets;
    
    Person person;

    public CustomerProfile(Person p) {

        person = p;
        orders = new ArrayList<Order>();

    }

           
    public int getTotalPricePerformance(){
        


        return 0;}
 
 
    
    public int getNumberOfOrdersBelowTotalTarget(){return 0;}

        
    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }
    public void addCustomerOrder(Order o){
        orders.add(o);
    }
    @Override
    public String toString(){
        return person.getPersonId();
    }
        public String getCustomerId(){
        return person.getPersonId();
    }
            public Person getPerson(){
        return person;
    }
        
        
    
}

