package controller.commands.addportfolio;

import java.util.Scanner;

import controller.commands.AbstractCommand;
import controller.commands.Command;
import controller.commands.stock.AddStock;
import controller.commands.stock.CostAnalysis;
import controller.commands.stock.ShowAllStocks;
import user.Account;
import user.Portfolio;



/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to add a new add portfolio to a selected account.
 * Then it gives following options to for the portfolio.
 * 1. Buy Share.
 * 2. See all Stock Shares.
 * 3. Cost Basis analysis on Specific Date.
 * 4. Back.
 * Only calling to start will add a new account.
 */
public class SelectPortfolio extends AbstractCommand {

  private Readable readable;
  private Appendable appendable;
  private Account account;
  private String portfolioName;
  private Scanner scanner;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param readable - This is a readable to get input from user.
   * @param appendable - This is a appendable to show output to user.
   * @param portfolioName - This is a String for the portfolio name to be selected.
   */
  public SelectPortfolio(Readable readable, Appendable appendable,
                         Account account, String portfolioName) {
    this.account = account;
    this.appendable = appendable;
    this.portfolioName = portfolioName;
    this.readable = readable;
    this.scanner = new Scanner(readable);
  }

  @Override
  public void start() {


    try {
      Portfolio portfolio = account.getPortfolio(portfolioName);

      Command command = null;
      StringBuilder stringBuilder = new StringBuilder();

      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"SUCCESS: Portfolio " + portfolioName + ".\n");
      showMessage(appendable,"*------------------------------------------------------*\n");
      stringBuilder.append("*------------------------------------------------------*\n");
      stringBuilder.append("Choose from following options:\n");
      stringBuilder.append("1. Add Stock.\n");
      stringBuilder.append("2. See all Stocks Shares.\n");
      stringBuilder.append("3. Cost Basis analysis on Specific Date.\n");
      stringBuilder.append("4. Back\n");
      stringBuilder.append("*------------------------------------------------------*\n");

      showMessage(appendable, stringBuilder.toString());

      while (scanner.hasNext()) {
        try {
          switch (Integer.parseInt(scanner.next())) {
            case 1:
              command = new AddStock(readable, appendable, portfolio, scanner.next());
              break;
            case 2:
              command = new ShowAllStocks(appendable, portfolio);
              break;
            case 3:
              command = new CostAnalysis(appendable, portfolio, scanner);
              break;
            case 4:
              showMessage(appendable,"*--------------------------" +
                      "----------------------------*\n");
              showMessage(appendable,"Choose from following options:\n");
              showMessage(appendable,"1. Create a Portfolio.\n");
              showMessage(appendable,"2. Show all Portfolio\n");
              showMessage(appendable,"3. Select Simple Portfolio.\n");
              showMessage(appendable,"4. Create investment Portfolio.\n");
              showMessage(appendable,"5. Back\n");
              showMessage(appendable,"*------------------------" +
                      "------------------------------*\n");
              return;
            default:
              showMessage(appendable,"*------------------------------" +
                      "------------------------*\n");
              showMessage(appendable,"ERROR: Enter a Valid option\n");
              showMessage(appendable,"*--------------------------------" +
                      "----------------------*\n");
              break;
          }

          if (command != null) {
            command.start();
            command = null;
          }
        }

        catch (NumberFormatException e) {
          showMessage(appendable,"*-------------------------------" +
                  "-----------------------*\n");
          showMessage(appendable,"ERROR: Enter a Valid Number\n");
          showMessage(appendable,"*------------------------------------" +
                  "------------------*\n");
        }
      }
    }

    catch (Exception e) {
      showMessage(appendable,"*------------------------------------" +
              "------------------*\n");
      showMessage(appendable,"ERROR: Portfolio Not Found!\n");
      showMessage(appendable,"*--------------------------------------" +
              "----------------*\n");
    }



  }
}
