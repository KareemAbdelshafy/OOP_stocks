package controller.commands.stock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import controller.commands.AbstractCommand;
import helper.Date;
import stocks.SharePrice;
import stocks.SharePriceFile;
import user.Portfolio;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to add a new add Stock to a Portfolio List.
 * Then it gives following options to for the portfolio.
 * 1. Enter Company name.
 * 2. Enter number of shares.
 * 3. Add.
 * 4. Back.
 * To add select this sequentially.
 * First enter company name, then select 2 and enter number of shares you want to buy.
 * then select Add.
 * Only calling to start will add a new account.
 */
public class AddStock extends AbstractCommand {

  private Appendable appendable;
  private Portfolio portfolio;
  private String dateString;
  private Scanner scanner;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param readable - This is a readable to get input from user.
   * @param appendable - This is a appendable to show output to user.
   * @param dateString - This is a String for the date to be searched.
   * @param portfolio - This is the portflolio in which we want to enter shares.
   */
  public AddStock(Readable readable, Appendable appendable,
                  Portfolio portfolio, String dateString) {
    this.appendable = appendable;
    this.portfolio = portfolio;
    this.dateString = dateString;
    this.scanner = new Scanner(readable);
  }

  @Override
  public void start() {

    Date date = new Date(dateString);

    showMessage(appendable, "Enter Company name\n");

    String companyTicker = scanner.next();

    SharePrice sharePrice;

    showMessage(appendable, "Enter Number of stocks\n");

    int numberOfStocks = scanner.nextInt();

    try {

      if (date.isWorkDay()) {
        sharePrice = new SharePriceFile(companyTicker, date);
      }

      else {

        if (date.getCalendar().get(Calendar.DAY_OF_WEEK) == 7) {

          DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
          java.util.Date utilDate = new java.util.Date(date.timeInMilliSeconds() - 86400000);

          sharePrice = new SharePriceFile(companyTicker,
                  new Date(simple.format(utilDate)));
        }

        else {
          DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
          java.util.Date utilDate = new java.util.Date(date.timeInMilliSeconds() - 172800000);

          sharePrice = new SharePriceFile(companyTicker,
                  new Date(simple.format(utilDate)));
        }
      }

      portfolio.addStockShare(sharePrice, numberOfStocks);
      showMessage(appendable, "Stock Added.\n");

    } catch (Exception e) {
      System.out.print("Data not avaiable on this date\n");
    }

    showMessage(appendable, "Choose from following options:\n");
    showMessage(appendable,"1. Add Stock.\n");
    showMessage(appendable,"2. See all Stock Shares.\n");
    showMessage(appendable,"3. Cost Basis analysis on Specific Date.\n");
    showMessage(appendable,"4. Back\n");
    showMessage(appendable,"*------------------------------------------------------*\n");


  }
}
