package stocks;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import controller.GetStockData;
import controller.GetStockDataController;
import helper.Date;


/**
 * This class is responsible of parsing the stock data to get the price of one share. It gets the
 * stock price of certain company at all times available. It get the date of buying.Then getPrice
 * Method is searching the data to find the price at the given time.
 */

public class SharePriceFile extends SharePriceAbstract {

  /**
   * The constructor get all the stock data of certain company as a
   * GetStockData. It also take the date of
   * buying the stock as Date object.
   * @param getStockData - This is a GetStockData object.
   * @param date - This is a Date object.
   * @throws IOException - Throws this exception if data not found.
   */
  public SharePriceFile(GetStockData getStockData, Date date) throws IOException {
    this.getStockData = getStockData;
    this.date = date;
    this.stockSymbol = getStockData.getStockSymbol();
    getPrice(date.getTime());

  }

  /**
   * The constructor get all the stock ticker of certain company as a String.
   * It also take the date of
   * buying the stock as Date object.
   * @param stockSymbol - This is a ticker for the company.
   * @param date - This is a Date object.
   * @throws IOException - Throws this exception if data not found.
   */
  public SharePriceFile(String stockSymbol, Date date) throws IOException {
    this.getStockData = new GetStockDataController(stockSymbol);
    this.date = date;
    getPrice(date.getTime());
    this.stockSymbol = stockSymbol;
  }


  private void getPrice(String type) throws IOException {

    StringBuilder dateS = new StringBuilder("");

    constructDate(dateS);
    String line = getLine(dateS);
    String[] ss = line.split(",");
    List<Double> prices = new LinkedList<>();
    for (int i = 1; i < ss.length - 1; i++) {
      prices.add(Double.valueOf(ss[i]));
    }
    double price;
    switch (type) {
      case "open":
        price = prices.get(0);
        break;
      case ("high"):
        price = prices.get(1);
        break;
      case "low":
        price = prices.get(2);
        break;
      case "close":
        price = prices.get(3);
        break;
      default:
        throw new IllegalArgumentException("Invalid time please eneter open,high,low or close");
    }

    this.price = price;

  }

  private void constructDate(StringBuilder dateS) {
    Calendar calendar = date.getCalendar();
    int year = calendar.get(Calendar.YEAR);
    dateS.append(year);

    int month = calendar.get(Calendar.MONTH) + 1;
    if (month < 10) {
      dateS.append("-0").append(month);
    } else {
      dateS.append("-").append(month);
    }
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    if (day < 10) {
      dateS.append("-0").append(day);
    } else {
      dateS.append("-").append(day);
    }

  }

  private String getLine(StringBuilder dateS) throws IOException  {
    Scanner scanner = new Scanner(getStockData.getData());
    String line = "";
    boolean found = false;

    while (scanner.hasNext()) {
      String l = scanner.nextLine();
      if (l.contains(dateS.toString())) {
        line = l;
        found = true;
        break;
      }
    }

    if (!found) {
      throw new IllegalArgumentException("Data not found " + dateS.toString() + ". \n");
    }

    return line;
  }

}
