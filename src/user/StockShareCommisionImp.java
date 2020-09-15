package user;

import helper.Date;
import stocks.SharePrice;

/**
 * This class extends StockShareFree and implements StockShareComission. This maintains the record
 * of buying commission and selling commision of the stock.
 */
public class StockShareCommisionImp extends StockShareFree implements StockShareComission {

  private double buyingComission;
  private double sellingComission;

  /**
   * The only constructor that can only be called from a portfolioOneTime object that obtain this
   * share.
   *
   * @param price          is a SharePrice object that contain the price of one share, the company
   *                       name and the date of this price
   * @param numberOfShares is the number of shares that user want to buy.
   */
  StockShareCommisionImp(SharePrice price, double numberOfShares) {
    super(price, numberOfShares);
    sellingComission = 0;
    buyingComission = 0;
  }


  @Override
  public double calculatePrice(Date date) {
    double price = super.calculatePrice(date);
    price = price - sellingComission;
    return price;
  }

  @Override
  public double getShareCost() {
    double cost = super.getShareCost();
    cost = cost + buyingComission;
    return cost;
  }

  @Override
  public int getID() {
    return id;
  }

  @Override
  public double getBuyingComission() {
    return buyingComission;
  }

  @Override
  public void setBuyingComission(double buyingComission) {
    this.buyingComission = buyingComission;
  }

  @Override
  public double getSellingComission() {
    return sellingComission;
  }

  @Override
  public void setSellingComission(double sellingComission) {
    this.sellingComission = sellingComission;
  }


}
