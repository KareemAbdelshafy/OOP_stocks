package user;

import helper.Date;

/**
 * This is the basic unit of the stock.
 */
public interface StockShare {

  /**
   * This mehtod is used to calculate the price of the stock.
   *
   * @param date - It takes a Date object for which the price is to be calculated.
   * @return - double as a value of stock.
   */
  double calculatePrice(Date date);

  /**
   * This returns the cost of the share.
   *
   * @return - This is a double as value of the share.
   */
  double getShareCost();

  /**
   * This is a getter method for the id.
   *
   * @return - Int as id of stock.
   */
  int getID();

}
