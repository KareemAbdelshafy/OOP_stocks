package user;

import java.util.LinkedList;
import java.util.List;

import helper.Date;
import stocks.SharePrice;

/**
 * This class represent an PortfolioImpl. It contain a list of Stock Shares inside this protfolio,
 * the account and the investor who obtains this protfolio.
 */
public class PortfolioImpl implements Portfolio {

  private String portfolioName;
  private List<StockShare> stockes;

  /**
   * A protfolio Constructor who can be called from inside an account object only.
   *
   * @param portfoliName is the portfolio name
   */
  PortfolioImpl(String portfoliName) {
    this.portfolioName = portfoliName;
    stockes = new LinkedList<>();
  }

  @Override
  public double calculatePrice(Date date) {
    double price = 0;
    for (StockShare share : stockes) {
      price = price + share.calculatePrice(date);
    }
    return price;
  }


  /**
   * This Method return the stock Share with the given ID.
   *
   * @param stockShareID is int of the unique stockShare ID.
   * @return stock Share Object.
   */
  public StockShare getStockShare(int stockShareID) {
    for (StockShare s : stockes) {
      if (s.getID() == stockShareID) {
        return s;
      }
    }
    throw new IllegalArgumentException("Invalid Stock Share ID");
  }

  /**
   * returns this protfolio Name.
   *
   * @return String of portfolio Name.
   */
  public String getPortfolioName() {
    return portfolioName;
  }


  /**
   * returns the total cost of shares inside this protfolio.
   *
   * @return total cost.
   */
  public double getTotalCost() {
    double totalCost = 0;

    for (StockShare stocke : stockes) {
      totalCost = totalCost + stocke.getShareCost();
    }

    return totalCost;
  }


  @Override
  public List<StockShare> getAllStocks() {
    return stockes;
  }

  /**
   * This is the only method that can create a Stock Share object. It create it and add it to the
   * list of stockShares
   *
   * @param sharePrice     is an object that represent the price of one Share. It contains also the
   *                       date of the price and company.
   * @param numberOfShares is the number of shares that the investor want to buy.
   */
  public void addStockShare(SharePrice sharePrice, double numberOfShares) {

    StockShare stockShare = new StockShareFree(sharePrice, numberOfShares);

    stockes.add(stockShare);
  }

  /**
   * return the name of the protfolio.
   *
   * @return a string as a name of the profolio.
   */
  public String getName() {
    return this.portfolioName;
  }


  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PortfolioImpl Name: ").append(portfolioName).append("\n");
    for (StockShare stockShare : stockes) {
      stringBuilder.append(stockShare.toString()).append("\n");
    }
    return stringBuilder.toString();
  }


}
