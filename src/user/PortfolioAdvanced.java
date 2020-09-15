package user;

import java.util.List;

import helper.Date;

/**
 * This portfolio extends Portfolio. This can add stocks and invest in them. This portfolio can
 * invest periodcally or non periodically as well. If a user does not specify an end date it takes
 * current system date as end date. This has a buying and selling commission as well. User can
 * speicify ratios to be invested in the portfolio. If user does not specify any ratio it gives
 * equal weights to all.
 */
public interface PortfolioAdvanced extends Portfolio {

  String getName();

  /**
   * This is a getter method to get Start date.
   *
   * @return - Date object for the start date.
   */
  Date getStartDate();

  /**
   * This is a setter method to set startDate.
   *
   * @param startDate - Date object for the start date of investment.
   */
  void setStartDate(Date startDate);

  /**
   * This is a getter method tvao get end date.
   *
   * @return - Date object for the end date.
   */
  Date getEndDate();

  /**
   * This is a setter method to set end date.
   *
   * @param endDate - Date object for the end date of investment.
   */
  void setEndDate(Date endDate);

  /**
   * This is a getter method of periodicity. It is the number of days for periodic investment.
   *
   * @return - retuns int as period.
   */
  int getPeriodicity();

  /**
   * This is a setter method for periodicity.
   *
   * @param periodicity - It takes int as period in number of days.
   */
  void setPeriodicity(int periodicity);

  /**
   * This is getter method the buying commission which user enters.
   *
   * @return - double as buying commission.
   */
  double getBuyingComission();

  /**
   * This is a setter method to set buying commission.
   *
   * @param buyingComission - double to set a buying commission.
   */
  void setBuyingComission(double buyingComission);

  /**
   * This is a getter method to get selling commission.
   *
   * @return - double as selling commission.
   */
  double getSellingComission();

  /**
   * This is a setter method to set selling commission.
   *
   * @param sellingComission - double to set a selling commission.
   */
  void setSellingComission(double sellingComission);

  /**
   * This is a method to invest at a certain date. This method invests only once. It gives equal
   * weights for all investments if ratios are not defined.
   *
   * @param date - Date object for which the investment to be done.
   * @throws IllegalArgumentException - If ratios don't add upto 1.
   */
  void invest(Date date) throws IllegalArgumentException;

  /**
   * This is a method to invest at a certain date and prediocally. Which is taken as int in number
   * of days. It gives equal weights for all investments if ratios are not defined.
   *
   * @param date        - Date object for which the investment to be done.
   * @param periodicity - int as number of days for periodicity.
   * @throws IllegalArgumentException - If ratios don't add upto 1.
   */
  void investPeriodically(Date date, int periodicity) throws IllegalArgumentException;

  /**
   * getter method for investment amount.
   *
   * @return - double as investment amount.
   */
  double getInvestAmount();

  /**
   * This sets investment amount as double.
   *
   * @param investAmount - double which is the investment amount.
   */
  void setInvestAmount(double investAmount);

  /**
   * This is a getter method for all the ratios.
   *
   * @return - List of Double which are the ratios.
   */
  List<Double> getRatios();

  /**
   * This is a setter method which is a list of double which are the ratios. These ratios must add
   * upto 1.
   *
   * @param ratios - List of double values.
   */
  void setRatios(List<Double> ratios);

  /**
   * This is a ticker symbol to be added.
   *
   * @param stockSymbol - This is taken as a String.
   */
  void addStockSymbol(String stockSymbol);

  /**
   * This method save the strategy used in this portfolio.
   *
   * @param dir is the directory where the strategy will be saved.
   * @return true if writing succeded.
   */
  boolean saveStrategy(String dir);

  /**
   * This method load a strategy and invest using this strategy.
   *
   * @param dir is the directory where the strategy will be loaded.
   */
  void investByStrategy(String dir);

  /**
   * This method save the current portfolio with all purchases.
   *
   * @param dir is the directory where the portfolio will be saved.
   * @return true if writing succeded.
   */
  boolean savePortfolio(String dir);

  /**
   * A getter method that return the account that owns this portfolio.
   *
   * @return Account object.
   */
  Account getAccount();

  /**
   * This method will_returns all the stocks tickers that used inside this portfolio.
   *
   * @return list of string tickers
   */

  List<String> getstockSymbols();

  /**
   * This method loads a portfolio from a file and apply it to the currengt portfolio.
   *
   * @param dir is the location of portfolio.
   */
  void loadPortfolio(String dir);
}
