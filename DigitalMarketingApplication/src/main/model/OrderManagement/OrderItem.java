package main.model.OrderManagement;
import main.model.ProductManagement.SolutionOffer;

public class OrderItem {

    SolutionOffer solutionOffer;
    int quantity;
    boolean byAd;

    public OrderItem(SolutionOffer solutionOffer, int quantity) {
        this.solutionOffer = solutionOffer;
        this.quantity = quantity;
        byAd = false;
    }

    public boolean isByAd() {
        return byAd;
    }

    public void setByAd(boolean byAd) {
        this.byAd = byAd;
    }

    public SolutionOffer getSolutionOffer() {
        return solutionOffer;
    }
    public int getQuantity() {
        return quantity;
    }

    public int getOrderItemTotal() {
        // int total = 0;
        return 1;
    }
}
