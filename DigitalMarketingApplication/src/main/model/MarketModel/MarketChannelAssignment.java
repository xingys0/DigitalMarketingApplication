package main.model.MarketModel;
import main.model.ProductManagement.SolutionOfferCatalog;


public class MarketChannelAssignment{
    Market market;
    Channel channel;


    int adbudget;
    int targetrevenue;
    SolutionOfferCatalog solutionOfferCatalog;

    public MarketChannelAssignment(Market m, Channel c){
        market = m;
        channel = c;
        solutionOfferCatalog = new SolutionOfferCatalog();
    }

    public MarketChannelAssignment() {
    }

    public SolutionOfferCatalog getSolutionOfferCatalog() {
        return solutionOfferCatalog;
    }

    public void setSolutionOfferCatalog(SolutionOfferCatalog solutionOfferCatalog) {
        this.solutionOfferCatalog = solutionOfferCatalog;
    }

    public Market getMarket() {
        return market;
    }

    public Channel getChannel() {
        return channel;
    }
}

