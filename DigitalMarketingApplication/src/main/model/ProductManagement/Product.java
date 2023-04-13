package main.model.ProductManagement;

import java.util.ArrayList;

import main.model.OrderManagement.OrderItem;

public class Product {
    private String name;

    private int floorPrice;
    private int ceilingPrice;
    private int targetPrice;
    ArrayList<OrderItem> orderitems;
  
    
    public Product(String n) {
      name = n;
    }
    
    public Product(int fp, int cp, int tp) {
      floorPrice = fp;
      ceilingPrice = cp;
      targetPrice = tp;
      orderitems = new ArrayList<OrderItem>();
    }
  
    public Product(String n, int fp, int cp, int tp) {
      name = n;
      floorPrice = fp;
      ceilingPrice = cp;
      targetPrice = tp;
      orderitems = new ArrayList<OrderItem>();
    }
  
    public Product updateProduct(int fp, int cp, int tp) {
      floorPrice = fp;
      ceilingPrice = cp;
      targetPrice = tp;
      return this; //returns itself
    }
  
    public int getTargetPrice() {
      return targetPrice;
    }
  
    public void addOrderItem(OrderItem oi) {
      orderitems.add(oi);
    }
  
  
  
    public int getSalesVolume() {
      int sum = 0;
      for (OrderItem oi : orderitems) {
        sum = sum + oi.getOrderItemTotal(); //positive and negative values
      }
      return sum;
    }
  
    public void setName(String n) {
      name = n;
    }
  
    @Override
    public String toString() {
      return name;
    }
  
    public int getFloorPrice() {
      return floorPrice;
    }
  
    public int getCeilingPrice() {
      return ceilingPrice;
    }
  
    public ArrayList<OrderItem> getOrderitems() {
      return orderitems;
    }
    public String getName() {
      return name;
    }
    
}
  
