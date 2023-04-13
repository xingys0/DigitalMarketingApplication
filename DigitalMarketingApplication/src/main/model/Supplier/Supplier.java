package main.model.Supplier;

import java.util.ArrayList;

import main.model.ProductManagement.ProductCatalog;
import main.model.ProductManagement.ProductSummary;
import main.model.ProductManagement.ProductsReport;

public class Supplier {
    String name;
    ProductCatalog productcatalog;
    ProductsReport productsreport;
    public Supplier(String n){
        name = n;
        productcatalog = new ProductCatalog("software");
        
    }
    
   public ProductsReport prepareProductsReport(){
        
        productsreport = productcatalog.generatProductPerformanceReport();
        return productsreport;
    }
    
    public ArrayList<ProductSummary> getProductsAlwaysAboveTarget(){
       
        if(productsreport==null) productsreport = prepareProductsReport();
       return productsreport.getProductsAlwaysAboveTarget();
       
    }
    
    public String getName(){
        return name;
    }
        public ProductCatalog getProductCatalog(){
        return productcatalog;
    }
    //add supplier product ..
    
    //update supplier product ...
    @Override
   public String toString(){
       return name;
       
   }
}

