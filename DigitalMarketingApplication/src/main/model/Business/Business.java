package main.model.Business;

import java.util.ArrayList;

import main.model.CustomerManagement.ChannelCatalog;
import main.model.CustomerManagement.CustomerDirectory;
import main.model.CustomerManagement.MarketCatalog;
import main.model.MarketModel.Channel;
import main.model.MarketModel.Market;
import main.model.MarketingManagement.MarketingPersonDirectory;
import main.model.OrderManagement.MasterOrderList;
import main.model.Personnel.EmployeeDirectory;
import main.model.Personnel.PersonDirectory;
import main.model.ProductManagement.SolutionOfferCatalog;
import main.model.SalesManagement.SalesPersonDirectory;
import main.model.Supplier.SupplierDirectory;
import main.model.UserAccountManagement.UserAccountDirectory;

public class Business {

    String name;
    PersonDirectory persondirectory;
    MasterOrderList masterorderlist;
    CustomerDirectory customers;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    
  
    ChannelCatalog channelcatalog;
    
    SolutionOfferCatalog solutionoffercatalog;
    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;
  
    public Business(String n) {
      name = n;
      masterorderlist = new MasterOrderList();
      suppliers = new SupplierDirectory();
      //        solutionoffercatalog = new SolutionOfferCatalog();
      persondirectory = new PersonDirectory();
      customerdirectory = new CustomerDirectory(this);
      salespersondirectory = new SalesPersonDirectory(this);
      useraccountdirectory = new UserAccountDirectory();
      marketingpersondirectory = new MarketingPersonDirectory(this);
      employeedirectory = new EmployeeDirectory(this);
      marketcatalog = new MarketCatalog(new ArrayList<Market>());
      channelcatalog = new ChannelCatalog(new ArrayList<Channel>());
    }
  
    public MarketCatalog getMarketcatalog() {
      return marketcatalog;
    }
  
    public ChannelCatalog getChannelcatalog() {
      return channelcatalog;
    }
  
    
    public int getSalesVolume() {
      return masterorderlist.getSalesVolume();
    }
  
    public PersonDirectory getPersonDirectory() {
      return persondirectory;
    }
  
    public UserAccountDirectory getUserAccountDirectory() {
      return useraccountdirectory;
    }
  
    public MarketingPersonDirectory getMarketingPersonDirectory() {
      return marketingpersondirectory;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
      }
  }