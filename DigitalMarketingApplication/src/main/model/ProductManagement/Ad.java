package main.model.ProductManagement;

public class Ad {
    SolutionOffer so;
    int NumberOfSales;
    int revenue;

    public Ad(SolutionOffer so) {
        this.so = so;
    }

    public int getNumberOfSales() {
        return NumberOfSales;
    }

    public int getRevenue() {
        return revenue;
    }

    public void purchaseByAd() {
        NumberOfSales += 1;
        revenue += so.getPrice();
    }

    
}
