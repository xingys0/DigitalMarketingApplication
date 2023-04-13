package main.model.MarketingManagement;

import java.util.ArrayList;

import main.model.Business.Business;
import main.model.Personnel.Person;




public class MarketingPersonDirectory {

    Business business;
    ArrayList<MarketingPersonProfile> marketingpersonlist;

    public MarketingPersonDirectory(Business d) {

        business = d;
        marketingpersonlist = new ArrayList<MarketingPersonProfile>();

    }

    public MarketingPersonProfile newMarketingPersonProfile(Person p) {

        MarketingPersonProfile sp = new MarketingPersonProfile(p);
        marketingpersonlist.add(sp);
        return sp;
    }

    public MarketingPersonProfile findMarketingPerson(String id) {

        for (MarketingPersonProfile sp : marketingpersonlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }

}