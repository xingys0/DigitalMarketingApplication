package main.model.ProductManagement;
import java.util.ArrayList;
import main.model.MarketModel.MarketChannelAssignment;

public class SolutionOffer {
    ArrayList<Product> products;
  

    int price; //floor, ceiling, and target ideas
  

    MarketChannelAssignment marketChannelAssignment;

  public SolutionOffer(MarketChannelAssignment m) {
    marketChannelAssignment = m;
    products = new ArrayList<Product>();
  }

  public void addProduct(Product p) {
    products.add(p);
  }
  
  public int getPrice() {
    return price;
  }

  public void setPrice(int p) {
    price = p;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public boolean isOfferTargetMarketChannel(MarketChannelAssignment mcc) {
    if (mcc == marketChannelAssignment) {
      return true;
    } else {
      return false;
    }
  }
}


