package user;

import java.util.List;

import helper.Date;
import stocks.SharePrice;

/**
 * This is an interface which is used to create a portfolio. This portfolio is just used to add
 * stocks in it.
 */
public interface Portfolio {

  /*
   * This method returns portfolio name.
   * @return - This is a String which is portfolio name.
   */
  String getPortfolioName();

  /*
   * This method returns price of portfolio on a certain date.
   * @param date - Date object for which portfolio price is to be calculated.
   * @return - double as a value of portfolio.
   */
  double calculatePrice(Date date);

  /**
   * This returns a StockShare object by id.
   *
   * @param stockShareID - id of StockShare to be fetched.
   * @return - StockShare object for a certain id.
   */
  StockShare getStockShare(int stockShareID);

  /**
   * This is used to add stocks to the portfolio.
   *
   * @param sharePrice     - This is SharePrice object.
   * @param numberOfShares - Number of shares to be added.
   */
  void addStockShare(SharePrice sharePrice, double numberOfShares);

  /**
   * This returns total cost of the portfolio.
   *
   * @return - double as total cost of portfolio.
   */
  double getTotalCost();

  /**
   * List of StockShares in a certain portfolio.
   *
   * @return - List of StockShares.
   */
  List<StockShare> getAllStocks();

}
