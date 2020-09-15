package user;

import helper.Date;
import stocks.SharePrice;

/**
 * This class represent a Stock Share that the user bought. Every time a share is bough is assign it
 * a unique ID number as integer. The first buy gets ID 1. The second get ID 2 , ..etc.
 */
public class StockShareFree implements StockShare {

  protected String stockSymbol;
  protected double numberOfShares;
  protected static int counting;
  protected int id;
  protected SharePrice price;

  /**
   * The only constructor that can only be called from a portfolioOneTime object that obtain this
   * share.
   *
   * @param price          is a SharePrice object that contain the price of one share, the company
   *                       name and the date of this price
   * @param numberOfShares is the number of shares that user want to buy.
   */
  StockShareFree(SharePrice price, double numberOfShares) {
    this.price = price;
    this.stockSymbol = price.getStockSymbol();
    this.numberOfShares = numberOfShares;
    counting = counting + 1;
    id = counting;
  }

  public double calculatePrice(Date date) {
    return price.calculatePrice(date) * numberOfShares;
  }

  /**
   * return the cost of this share by multiplying the cost of one share * number of share.
   *
   * @return double of the cost of this share.
   */
  public double getShareCost() {
    return this.price.getPrice() * numberOfShares;
  }

  public SharePrice getSharePrice() {
    return price;
  }

  /**
   * returns the unique ID of this stock Share.
   *
   * @return integer ID.
   */
  public int getID() {
    return id;
  }

  @Override
  public String toString() {

    StringBuilder string = new StringBuilder("");
    string.append("ID ").append(id).append(" company ").append(stockSymbol);
    string.append("  numberOfShares ").append(numberOfShares).append(" unit price ");
    string.append(price.getPrice()).append(" totalPrice ")
            .append(price.getPrice() * numberOfShares);
    string.append(" Date ").append(price.getDate().toString());

    return string.toString();
  }


  @Override
  public boolean equals(Object obj) {
    return toString().equals(((StockShareFree) obj).toString());
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }


}
