package controller;

import java.io.IOException;

/**
 * This implementation of this interface is responsible of getting the stock data of certain
 * company. The implementations are GetStockDataFile, GetStockDataURL.
 */
public interface GetStockData {

  /**
   * This method return the all the stock data of certain company in all available times.
   * @return - String with all the data of certian company.
   * @throws IOException - Throws this exception if data is not found.
   */
  String getData() throws IOException;

  /*
   * This method returns the stock ticker for the company.
   * @return - A string which is stock ticker for the company.
   */
  String getStockSymbol();
}
