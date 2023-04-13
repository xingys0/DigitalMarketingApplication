package main.model.OrderManagement;

public class OrderSummary {
    int salesvolume;
    boolean totalabovetarget;
    int orderpriceperformance;
    int numberofOrderitemsabovetarget;
    
public OrderSummary(Order o){
    salesvolume = o.getOrderTotal();

}
public int getOrderProfit(){
    return orderpriceperformance;
}
}
