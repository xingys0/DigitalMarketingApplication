package main.model.ProductManagement;
import java.util.ArrayList;

import main.model.MarketModel.MarketChannelAssignment;


public class SolutionOfferCatalog {


    
    ArrayList<SolutionOffer> solutionoffers;

    public ArrayList<SolutionOffer> getSolutionoffers() {
        return solutionoffers;
    }

    public SolutionOfferCatalog() {
        solutionoffers = new ArrayList<SolutionOffer>();
    }
    
    public SolutionOffer newSolutionOffer(MarketChannelAssignment mca){
        
        SolutionOffer so = new SolutionOffer(mca);
        solutionoffers.add(so);
        return so;
    }

     //this method will identify all solution offers meant for customers in a market coming in over a channel
    // the app will extract the market and channel combo from the user profile and use it to pull all offers

    public ArrayList<SolutionOffer> findSolutionsForMarketChannelCombo(MarketChannelAssignment mcc) {
        ArrayList<SolutionOffer> foundsolutions = new ArrayList<SolutionOffer>() ;

        for (SolutionOffer so : solutionoffers) {
            if (so.isOfferTargetMarketChannel(mcc) == true) {
                foundsolutions.add(so);
            }
            //find all solution offers available in the market/channel combin
        }
        return foundsolutions;
    }

}
