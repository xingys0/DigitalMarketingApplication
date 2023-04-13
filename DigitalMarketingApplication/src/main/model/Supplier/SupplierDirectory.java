package main.model.Supplier;

import java.util.ArrayList;

public class SupplierDirectory {
    
    ArrayList<Supplier> suppliers;

    public SupplierDirectory() {
      suppliers = new ArrayList<Supplier>();
    }
  
    public Supplier newSupplier(String n) {
      Supplier supplier = new Supplier(n);
      suppliers.add(supplier);
      return supplier;
    }
  
    public Supplier findSupplier(String id) {
      for (Supplier supplier : suppliers) {
        if (supplier.getName().equals(id)) return supplier;
      }
      return null;
    }
  
    public ArrayList<Supplier> getSuplierList() {
      return suppliers;
    }
  }
  
