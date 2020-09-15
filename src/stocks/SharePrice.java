package stocks;

import helper.Date;

public interface SharePrice {

  double getPrice();

  String getStockSymbol();

  Date getDate();

  double calculatePrice(Date date);
}
