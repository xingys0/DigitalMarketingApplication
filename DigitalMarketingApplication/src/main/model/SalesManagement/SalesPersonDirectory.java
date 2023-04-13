package main.model.SalesManagement;

import java.util.ArrayList;

import main.model.Business.*;
import main.model.Personnel.Person;

public class SalesPersonDirectory {
    Business business;
    ArrayList<SalesPersonProfile> salespersonlist;

    public SalesPersonDirectory(Business d) {

        business = d;
        salespersonlist = new ArrayList<SalesPersonProfile>();

    }

    public SalesPersonProfile newSalesPersonProfile(Person p) {

        SalesPersonProfile sp = new SalesPersonProfile(p);
        salespersonlist.add(sp);
        return sp;
    }

    public SalesPersonProfile findSalesPerson(String id) {

        for (SalesPersonProfile sp : salespersonlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
    }

}

    
