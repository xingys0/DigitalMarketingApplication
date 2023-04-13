package main.model.ProductManagement;

public class ProductSummary {
    
    Product subjectproduct;
    int numberofsalesabovetarget;
    int numberofsalesbelowtarget;
    int productpriceperformance; //total profit above target --could be negative too
    int acutalsalesvolume;
    int rank; // will be done later
    
    public ProductSummary(Product p){
        
        // numberofsalesabovetarget = p.getNumberOfProductSalesAboveTarget();
        // productpriceperformance = p.getOrderPricePerformance();
        subjectproduct = p; //keeps track of the product itself not as well;
        acutalsalesvolume = p.getSalesVolume();
        // numberofsalesbelowtarget = p.getNumberOfProductSalesBelowTarget();
    
    }
    
    public int getSalesRevenues(){
        return acutalsalesvolume;
    }
    public int getNumberAboveTarget(){
        return numberofsalesabovetarget;
    }
    public int getProductPricePerformance(){
        return productpriceperformance;
    }
    public int getNumberBelowTarget(){
        return numberofsalesbelowtarget;
    }            
    public boolean isProductAlwaysAboveTarget(){
        return false; // to be implemented
    }
}

