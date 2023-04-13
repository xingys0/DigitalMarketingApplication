package main.model.CustomerManagement;


import java.util.ArrayList;

import main.model.Personnel.Person;
import main.model.Business.Business;



/**
 *
 * 
 */
public class CustomerDirectory {

    Business business;
    ArrayList<CustomerProfile> customerlist;

    public ArrayList<CustomerProfile> getCustomerlist() {
        return customerlist;
    }

    public CustomerDirectory(Business d) {

        business = d;
        customerlist = new ArrayList<CustomerProfile>();

    }

    public CustomerProfile newCustomerProfile(Person p) {

        CustomerProfile sp = new CustomerProfile(p);
        customerlist.add(sp);
        return sp;
    }

    public CustomerProfile findCustomer(String id) {

        for (CustomerProfile sp : customerlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }
        public CustomersReport generatCustomerPerformanceReport(){
        CustomersReport customersreport = new CustomersReport();
    
        for(CustomerProfile cp: customerlist){
            
            CustomerSummary cs = new CustomerSummary(cp);
            customersreport.addCustomerSummary(cs);
        }
        return customersreport; 
    } 
}

