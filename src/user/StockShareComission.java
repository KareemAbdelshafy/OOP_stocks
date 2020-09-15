package user;

/**
 * This is a stock with commmission. This extends StockShare class.
 */
public interface StockShareComission extends StockShare {

  /**
   * This is a getter method for buying commission.
   *
   * @return - double as buying commmison.
   */
  double getBuyingComission();

  /**
   * This is a setter method for buying commission.
   *
   * @param buyingComission - double as buying commission.
   */
  void setBuyingComission(double buyingComission);

  /**
   * This is a getter method for selling commission.
   *
   * @return - double as selling commmison.
   */
  double getSellingComission();

  /**
   * This is a setter method for selling commission.
   *
   * @param sellingComission - double as selling commission.
   */
  void setSellingComission(double sellingComission);

}
