package main.model.OrderManagement;
import java.util.ArrayList;

import main.model.CustomerManagement.CustomerProfile;
import main.model.MarketModel.MarketChannelAssignment;
import main.model.ProductManagement.SolutionOffer;
import main.model.SalesManagement.SalesPersonProfile;

public class Order {
    ArrayList<OrderItem> orderitems;
    
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment mca;

    

    String status;

    public Order(){
    }

    public void setMca(MarketChannelAssignment mca) {
        this.mca = mca;
    }
    
    public MarketChannelAssignment getMca() {
        return mca;
    }

    public Order(CustomerProfile cp) {
        orderitems = new ArrayList<OrderItem>();
        customer = cp;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson = null;
        status = "in process";
    }

    public Order(CustomerProfile cp, SalesPersonProfile ep) {
        orderitems = new ArrayList<OrderItem>();
        customer = cp;
        salesperson = ep;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson.addSalesOrder(this);  
    }

    public OrderItem newOrderItem(SolutionOffer so, int q) {
        OrderItem oi = new OrderItem(so, q);
        orderitems.add(oi);
        return oi;
    }

  
    public int getOrderTotal() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTotal();
        }
        return sum;
    }

    public int getOrderTotalSales() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getQuantity() * oi.getSolutionOffer().getPrice();
        }
        return sum;
    }

    public ArrayList<OrderItem> getOrderitems() {
        return orderitems;
    }


    
    public void CancelOrder(){
        status = "Cancelled";
    }
    public void Submit(){
        status = "Submitted";
    }

}

    
   