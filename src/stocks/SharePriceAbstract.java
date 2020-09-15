package stocks;

import controller.GetStockData;
import helper.Date;

public class SharePriceAbstract implements SharePrice {
  protected double price;
  protected GetStockData getStockData;
  protected Date date;
  protected String stockSymbol;

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public double calculatePrice(Date date) throws IllegalArgumentException {

    SharePrice sharePrice;
    try {
      sharePrice = new SharePriceFile(stockSymbol, date);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new IllegalArgumentException("Date not found");
    }

    return sharePrice.getPrice();
  }
}
