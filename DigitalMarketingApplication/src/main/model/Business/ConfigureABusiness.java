package main.model.Business;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import main.model.CustomerManagement.CustomerDirectory;
import main.model.CustomerManagement.CustomerProfile;
import main.model.MarketModel.Channel;
import main.model.MarketModel.Market;
import main.model.MarketModel.MarketChannelAssignment;
import main.model.OrderManagement.Order;
import main.model.OrderManagement.OrderItem;
import main.model.Personnel.Person;
import main.model.ProductManagement.Product;
import main.model.ProductManagement.SolutionOffer;

public class ConfigureABusiness {

    public static  Business initialize() {
      Business business = new Business("Amazon");
      // create a few markets
      createMarket(business);
      System.out.println("-------------------------------------------------------------");
      // create a few channels
      createChannels(business);
      System.out.println("-------------------------------------------------------------");
      // create MarketChannelAssignments
      ArrayList<MarketChannelAssignment> allMarketChannelAssignments = createMarketChannelAssignments(business);
      System.out.println("-------------Created " + allMarketChannelAssignments.size() + "MarketChannelAssignments");
      System.out.println("-------------------------------------------------------------");
      // create all products
      ArrayList<Product> allProducts = createAllProducts(business);
      System.out.println("-------------------------------------------------------------");
      // create solution offers from products and assign to marketChannelAssignments
      CreateSolutionOffers(business, allProducts);
      System.out.println("-------------------------------------------------------------");
      // Add 20 customers
      ArrayList<String> allCustomerIds = addCustomers(business);
      // For each customer, add 3 Orders with 1-5 Items (SolutionOffer) to each
      ArrayList<Order> allOrders = addOrders(business, allCustomerIds, allMarketChannelAssignments);
      // Customers search for products and return with SolutionOffers + Ads
      CustomersSearchForProducts(business);
      // Generate reports
      generateReportByMarket(business, allOrders);
      generateReportByChannel(business, allOrders);
      generateReportByAdsAndSolutionOffers(business, allOrders);
      return business;
    }
  
    private static void CustomersSearchForProducts(Business business) {
      System.out.println("===============Customer Searches for Products===================");
      // 2 examples of customer searching
      customerSearch(business, "Adults", "Electronics", "MacBook Air", 1000);
      customerSearch(business, "Teens", "School and Office Supplies", "Ballpoint Pens", 15);
    }
  
    private static void customerSearch(Business business, String customerMarket, String customerChannel, String productName, int budget) {
      MarketChannelAssignment msa = findMarketChannelAssignment(business, customerMarket, customerChannel);
      ArrayList<SolutionOffer> solutionOffers = findSolutionOffersContainingProductName(msa, productName, budget);
      ArrayList<SolutionOffer> solutionOffersByAds = findSolutionOffersByAds(msa, solutionOffers, budget);
      System.out.println("Customer is looking for " + productName + " at Market " + customerMarket + " and Channel " + customerChannel + " with budget of $" + budget);
      System.out.println("Searching Results: ");
      for (SolutionOffer so : solutionOffers) {
        System.out.println("Solution Offer : " + so.getProducts().toString() + " at unit price : " + so.getPrice());
      }
      System.out.println("\n(Ads) Come and See Our Latest Offers!");
      for (SolutionOffer so : solutionOffersByAds) {
        System.out.println("Solution Offer : " + so.getProducts().toString() + " at unit price : " + so.getPrice());
      }
      System.out.println("---------------------------------------------------------------------");
    }
  
    // find all the solution offers thru ads
    private static ArrayList<SolutionOffer> findSolutionOffersByAds(MarketChannelAssignment msa,
    ArrayList<SolutionOffer> solutionOffers, int budget) {

  ArrayList<SolutionOffer> solutionOffersByAds = new ArrayList<>();
  for (SolutionOffer so : msa.getSolutionOfferCatalog().getSolutionoffers()) {
    boolean isExisted = false;
    for (SolutionOffer sso: solutionOffers) {
      if (so == sso) {
        isExisted = true;
        break;
      }
    }
        // make sure the found solution offer won't exceed customer budget and it's not in the solution offers in the last step
        if (isExisted == false && so.getPrice() <= budget) {
          solutionOffersByAds.add(so);
        }
      }
      return solutionOffersByAds;
  
    }
  
    // for all solution offers, if the solution offer contains that product, it will be added to the return list
    private static ArrayList<SolutionOffer> findSolutionOffersContainingProductName(MarketChannelAssignment msa, String productName, int budget) {
      ArrayList<SolutionOffer> solutionOffers = new ArrayList<>();
      for (SolutionOffer so : msa.getSolutionOfferCatalog().getSolutionoffers()) {
        for (Product product : so.getProducts()) {
          if (product.getName().equals(productName) && so.getPrice() <= budget) {
            solutionOffers.add(so);
          }
        }
      }
      return solutionOffers;
    }
  
  
    // For each order, we calculate the sum of all order item sales under this order.
    // order item sales = price of solution offer under this order item * quantity
    // each order contains the marketChannelAssignment information, where we can find the market information
    // Then we'll be able to calculate the total sales for each market by going over all orders
    private static void generateReportByMarket(Business business, ArrayList<Order> allOrders) {
      // Create a hashmap to record {market : sales under this market}
      System.out.println("=======================Sales by Market================================");
      HashMap<String, Integer> nameToMarket = new HashMap<>();
  
      for (Market market : business.getMarketcatalog().getMarkets()) {
        nameToMarket.put(market.getName(), 0);
      }
      // if the order belongs to this market, the sales value will be added to that market thru the hashmap
      for (Order order : allOrders) {
        Market market = order.getMca().getMarket();
        nameToMarket.put(market.getName(), nameToMarket.get(market.getName()) + order.getOrderTotalSales());
      }
      System.out.println("Market  |  TotalSales($)");
      for (String marketName : nameToMarket.keySet()) {
        System.out.println(marketName + " | " + nameToMarket.get(marketName));
      }
      // calculate the total sales of all
      int totalSales = 0;
      for (String marketName : nameToMarket.keySet()) {
        int val = nameToMarket.get(marketName);
        totalSales += val;
      }
      System.out.println("totalSales" + " | " + totalSales);
    }
  
    private static void generateReportByChannel(Business business, ArrayList<Order> allOrders) {
      System.out.println("=======================Sales by Channel================================");
      // Create a hashmap to record {channel : sales under this channel}
      HashMap<String, Integer> nameToChannel = new HashMap<>();
  
      for (Channel channel : business.getChannelcatalog().getChannelList()) {
        nameToChannel.put(channel.getName(), 0);
      }
      // if the order belongs to this channel, the sales value will be added to that channel thru the hashmap
      for (Order order : allOrders) {
        Channel channel = order.getMca().getChannel();
        nameToChannel.put(channel.getName(), nameToChannel.get(channel.getName()) + order.getOrderTotalSales());
      }
      System.out.println("Channel  |  TotalSales($)");
  
      for (String channel : nameToChannel.keySet()) {
        System.out.println(channel + " | " + nameToChannel.get(channel));
      }
  
      int totalSales = 0;
      for (String channelName : nameToChannel.keySet()) {
        int val = nameToChannel.get(channelName);
        totalSales += val;
      }
      System.out.println("totalSales" + " | " + totalSales);
    }
  
    private static void generateReportByAdsAndSolutionOffers(Business business, ArrayList<Order> allOrders) {
      System.out.println("=======================Sales by SolutionOffers and Ads================================");
      // Need a HashMap of {solutionOffer : total sales}
      HashMap<String, Integer> soToSales = new HashMap<>();
      // Need a HashMap of {solutionOffer : sales by ads}
      HashMap<String, Integer> soToSalesByAds = new HashMap<>();
      // Calculate the total sales for each solution offer
      for (Order order : allOrders) {
        for (OrderItem oi : order.getOrderitems()) {
          SolutionOffer so = oi.getSolutionOffer();
          // Initiate the HashMaps 
          if (!soToSales.containsKey(so.getProducts().toString())) {
            soToSales.put(so.getProducts().toString(), 0);
            soToSalesByAds.put(so.getProducts().toString(), 0);
          }
          soToSales.put(so.getProducts().toString(), soToSales.get(so.getProducts().toString()) + so.getPrice() * oi.getQuantity());
          if (oi.isByAd()) {
            soToSalesByAds.put(so.getProducts().toString(), soToSalesByAds.get(so.getProducts().toString()) + so.getPrice() * oi.getQuantity());
          }
        }
      }
      // Print out SolutionOffer  vs  TotalSales($) vs TotalSalesByAds($) vs TotalSalesNotByAds($) 
      System.out.println("SolutionOffer  |  TotalSales($) | TotalSalesByAds($) | TotalSalesNotByAds($)");
      int allSales = 0;
      int allSalesbyAds = 0;
      int allSalesNotbyAds = 0;
      for (String so : soToSales.keySet()) {
        int totalSales = soToSales.get(so);
        int salesByAds = soToSalesByAds.get(so);
        int salesNotByAds = totalSales - salesByAds;
        System.out.println(so + " | " + totalSales + " | " + salesByAds + " | " + salesNotByAds);
        allSales += totalSales;
        allSalesbyAds += salesByAds;
        allSalesNotbyAds += salesNotByAds;
      }
      System.out.println("Total: " + allSales + " | " + allSalesbyAds + " | " + allSalesNotbyAds);
  
    }
  
    
  
    public static ArrayList<Order> addOrders(Business business, ArrayList<String> allCustomerIds, ArrayList<MarketChannelAssignment> allMarketChannelAssignments) {
      ArrayList<Order> allOrders = new ArrayList<>();
      Random rand = new Random();
  
      // get customers based on their ID
      ArrayList<CustomerProfile> customerProfiles = new ArrayList<CustomerProfile>();
      for (String customerId : allCustomerIds) {
        for (CustomerProfile customerProfile : business.getCustomerDirectory().getCustomerlist()) {
          if (customerId.equals(customerProfile.getCustomerId()) == true) {
            customerProfiles.add(customerProfile);
          }
        }
      }
      System.out.println("=======================Customer Orders================================");
      
      // For each customer add 3 Orders with 1-5 OrderItems to each; each orderItem has 1 solutionOffer, quantity will be 1-3
      // Randomly assign to a MarketChannelAssigment
      // Randomly make an order with 1-5 orderItems under that MarketChannelAssigment
      for (CustomerProfile customerProfile : customerProfiles) {
        // generate a random MarketChannelAssignment for this customer
        MarketChannelAssignment mca = getRandomMarketChannelAssignment(allMarketChannelAssignments);
        // make 3 orders
        for (int i = 0; i < 3; i ++) {
          Order order = new Order(customerProfile);
          order.setMca(mca);
          allOrders.add(order);
          System.out.println("--------------------New Order------------------------------");
          // each order has 1 - 5 order items (SolutionOffer)
          for (int j = 0; j < 1 + rand.nextInt(5); j++) {
            SolutionOffer so = getRandomSolutionOffer(mca.getSolutionOfferCatalog().getSolutionoffers());
            // Each order item has 1 to 3 pieces of a solution offer
            int numberOfSolutionOffers = 1 + rand.nextInt(3);
            OrderItem oi = order.newOrderItem(so, numberOfSolutionOffers);
            // Use random true and false to OrderItem
            oi.setByAd(getRandomTrueFalse());
  
            System.out.println(customerProfile.getCustomerId() + " ordered " + numberOfSolutionOffers + " "
              + so.getProducts().toString() + " at price $" + so.getPrice() + ". Order by ad? " + oi.isByAd());
          }
        }
      }
      return allOrders;
    }
  
    private static boolean getRandomTrueFalse() {
      Random rand = new Random();
      int i = rand.nextInt(10000);
      if (i % 2 == 1){
        return true;
      } else{
        return false;
      }
    }
  
    private static SolutionOffer getRandomSolutionOffer(ArrayList<SolutionOffer> solutionoffers) {
      Random rand = new Random();
      int i = rand.nextInt(solutionoffers.size());
      return solutionoffers.get(i);
    }
  
    private static MarketChannelAssignment getRandomMarketChannelAssignment(
        ArrayList<MarketChannelAssignment> allMarketChannelAssignments) {
      Random rand = new Random();
      int i = rand.nextInt(allMarketChannelAssignments.size());
      return allMarketChannelAssignments.get(i);
    }
  
    public static ArrayList<String> addCustomers(Business business) {
      CustomerDirectory cd = business.getCustomerDirectory();
      ArrayList<String> allCustomerIds = new ArrayList<String>();
      for (int i = 0; i < 20; i++) {
        allCustomerIds.add("Customer" + i);
        System.out.println("Successfully created Customer" + i);
      }
  
      for (String id : allCustomerIds) {
        cd.newCustomerProfile(new Person(id));
      }
      return allCustomerIds;
    }
  
    private static ArrayList<SolutionOffer> CreateSolutionOffers(Business business, ArrayList<Product> allProducts) {
      ArrayList<String> solutionOffersRaw = new ArrayList<>(List.of(
        "MacBook Air&AirPods,899,Teens&Electronics",
        "MacBook Air&AirPods,959,Students&Electronics",
        "MacBook Air&AirPods,999,Adults&Electronics",
        "AirPods,89,Teens&Electronics",
        "AirPods,99,Students&Electronics",
        "AirPods,100,Adults&Electronics",
        "Ipad Pro&Apple Pensil,499,Teens&Electronics",
        "Ipad Pro&Apple Pensil,599,Students&Electronics",
        "Ipad Pro&Apple Pensil,699,Adults&Electronics",
        "Apple Pensil,199,Teens&Electronics",
        "Apple Pensil,200,Students&Electronics",
        "Apple Pensil,205,Adults&Electronics",
        "Ballpoint Pens&Gen Pens,15,Teens&School and Office Supplies",
        "Ballpoint Pens&Gen Pens,18,Adults&School and Office Supplies",
        "Ballpoint Pens,5,Teens&School and Office Supplies",
        "Ballpoint Pens,6,Adults&School and Office Supplies",
        "Reusable Notebook,10,Teens&School and Office Supplies",
        "Reusable Notebook,11,Adults&School and Office Supplies",
        "4 Person Dining Table&Wooden chairs (4 piece),1200,Adults&Furnitures",
        "Yaheetech Convertible Sofa,1500,Adults&Furnitures",
        "Costway Queen Size Metal Bed Frame&Signature 9.5 inches Mattress,1000,Adults&Furnitures",
        "Signature 9.5 inches Mattress,300,Adults&Furnitures"
      ));
  
      ArrayList<SolutionOffer> allSolutionOffers = new ArrayList<>();
      for (String solutionOfferRaw : solutionOffersRaw) {
        String[] splits = solutionOfferRaw.split(",", 0);
        String[] productNameList = splits[0].split("&", 0); // e.g. ["MacBook Air", "AirPods"]
        int price = Integer.parseInt(splits[1]); // e.g. price = 899;
        String marketName = splits[2].split("&", 0)[0]; // e.g. marketName = "Adults"
        String channelName = splits[2].split("&", 0)[1]; // e.g. channelName = "Furnitures"
        ArrayList<Product> products = findProducts(productNameList , allProducts); // find product objects by product names
        MarketChannelAssignment marketChannelAssignment = findMarketChannelAssignment(business, marketName, channelName);
        // create solution offer in marketChannelAssignment.SolutionOfferCatalog
        SolutionOffer so = marketChannelAssignment.getSolutionOfferCatalog().newSolutionOffer(marketChannelAssignment);
        for (Product product : products) {
          so.addProduct(product);
          so.setPrice(price);
        }
        System.out.println("Successfully Created Solution Offer: " + so.getProducts().toString() + 
          " at price: $" + so.getPrice() + " under MarketChannelAssignment " + marketName + "&" + channelName);
        allSolutionOffers.add(so);
      }
      return allSolutionOffers;
    }
  
    private static MarketChannelAssignment findMarketChannelAssignment(Business business, String marketName,
        String channelName) {
      for (Market market : business.getMarketcatalog().getMarkets()) {
        if (market.getName().equals(marketName)) {
          for (MarketChannelAssignment mca : market.getMarketChannelAssignments()) {
            if (mca.getChannel().getName().equals(channelName)) {
              return mca;
            }
          }
        }
      }
      System.out.println("Cannot find MarketChannelAssignment: " + marketName + "&" + channelName);
      return null;
    }
  
    private static ArrayList<Product> findProducts(String[] productNameList, ArrayList<Product> allProducts) {
      ArrayList<Product> products = new ArrayList<>();
      for (String productName : productNameList) {
        for (Product product : allProducts) {
          if (product.getName().equals(productName)) {
            products.add(product);
            break;
          }
        }
      }
      if (productNameList.length != products.size()) {
        System.out.println("Cannot find product: " + productNameList.toString());
        return null;
      }
      return products;
    }
  
    private static ArrayList<Product> createAllProducts(Business business) {
      ArrayList<String> productNames = new ArrayList<>(List.of("MacBook Air", "AirPods", "Ipad Pro", "Apple Pensil", 
        "Ballpoint Pens", "Gen Pens", "Reusable Notebook",
        "4 Person Dining Table", "Wooden chairs (4 piece)", "Yaheetech Convertible Sofa", "Costway Queen Size Metal Bed Frame", "Signature 9.5 inches Mattress"));
      
      ArrayList<Product> allProducts = new ArrayList<>();
      for (String productName : productNames) {
        Product product = new Product(productName);
        allProducts.add(product);
        System.out.println("Successfully created Product: " + productName);
      }
      return allProducts;
    }
  
    private static ArrayList<MarketChannelAssignment> createMarketChannelAssignments(Business business) {
      ArrayList<MarketChannelAssignment> allMarketChannelAssignments = new ArrayList<>();
      // Do not create these unreasonable MarketChannelAssignments
      ArrayList<String> marketblocklist = new ArrayList<>(List.of("Students", "Students", "Teens"));
      ArrayList<String> channelblocklist = new ArrayList<>(List.of("School and Office Supplies", "Furnitures", "Furnitures"));
      
      // Create a combination of market and channel for all markets/ channels
      for (Market market : business.getMarketcatalog().getMarkets()) {
        for (Channel channel : business.getChannelcatalog().getChannelList()) {
          // Only create MarketChannelAssignments if the market and channel combinations are not in the block list; 
          boolean shouldCreate = true;
          for (int i=0; i<marketblocklist.size(); i++) {
            if (marketblocklist.get(i).equals(market.getName()) && (channelblocklist.get(i).equals(channel.getName()))) {
              shouldCreate = false;
              break;
            }
          }
          if (shouldCreate == true) {
            MarketChannelAssignment mca = new MarketChannelAssignment(market, channel);
            market.addMarketChannelAssignments(mca);
            allMarketChannelAssignments.add(mca);
            System.out.println("Successfully created MarketChannelAssignment of " + market.getName() + "&" + channel.getName());
          }
          
          }
        }
        return allMarketChannelAssignments;
    }
  
    private static void createChannels(Business business) {
      ArrayList<String> channels = new ArrayList<>(List.of("Electronics", "School and Office Supplies", "Furnitures"));
      for (String channel : channels) {
        business.getChannelcatalog().addChannel(new Channel(channel));
        System.out.println("Successfully created Channel " + channel);
      }
    }
  
    private static void createMarket(Business business) {
      ArrayList<String> marketNames = new ArrayList<>(List.of("Teens", "Students", "Adults"));
      for (String marketName : marketNames) {
        business.getMarketcatalog().addMarket(new Market(marketName));
        System.out.println("Successfully created Market " + marketName);
      }
    }
  }