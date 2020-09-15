package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The class takes the company name as a string and its stock Symbol It searches this url for stock
 * data https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&outputsize=full&symbol
 * stockSymbol + "&apikey=" + apiKey + "&datatype=csv" Then it returns all stock prices in all times
 * as a string as provided.
 */
class GetStockDataURL implements GetStockData {

  private URL url;
  private String stockSymbol;

  /**
   * The constructor takes the stock Symbol of this company.
   */
  public GetStockDataURL(String stockSymbol) {

    this.stockSymbol = stockSymbol;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + "EU5W0IME68VHFS73" + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

  }



  /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.
      This is printed below.
       */

  @Override
  public String getData() {
    InputStream in = null;
    StringBuilder output = new StringBuilder();


    try {
      in = url.openStream();
      int b;
      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found using " + url);
    }

    String result = output.toString();

    if (result.contains("Error Message")) {
      throw new IllegalArgumentException("Invalid URL" + url);
    }

    return result;
  }

  /*
   * This method returns the stock ticker for the company.
   * @return - A string which is stock ticker for the company.
   */
  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }


}

