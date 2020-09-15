package stocks;

import java.util.Scanner;

import helper.Date;

/**
 * This is a helper class to get data from console if data not found for specific date.
 * This class extends SharePriceAbstract class.
 */
public class SharePriceConsol extends SharePriceAbstract {

  /**
   * This methods takes input from the user if the data is not found in the files.
   * @param stockSymbol - String as a company ticker.
   * @param date - Date object for the date.
   */
  public SharePriceConsol(String stockSymbol, Date date) {
    this.stockSymbol = stockSymbol;
    this.date = date;
    System.out.println("Please eneter stock price");
    Scanner scanner = new Scanner(System.in);
    double price;
    while (true) {
      price = Double.parseDouble(scanner.next());
      if (price < 0) {
        System.out.println("Price can not be negative. Try again");
      } else {
        break;
      }
    }
    this.price = price;
  }


}
