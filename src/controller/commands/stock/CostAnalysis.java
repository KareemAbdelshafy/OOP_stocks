package controller.commands.stock;

import java.util.Scanner;

import controller.commands.AbstractCommand;

import helper.Date;
import user.Portfolio;




/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used show sum of cost of all stocks in the portfolio.
 * Only calling to start will show cost.
 */
public class CostAnalysis extends AbstractCommand {

  private Appendable appendable;
  private Portfolio portfolio;
  private Scanner scanner;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param appendable - This is a appendable to show output to user.
   * @param portfolio - This is the portflolio in which we want to enter shares.
   */
  public CostAnalysis(Appendable appendable,
                      Portfolio portfolio, Scanner scanner) {
    this.appendable = appendable;
    this.portfolio = portfolio;
    this.scanner = scanner;
  }

  @Override
  public void start() {

    showMessage(appendable, "Enter Date for Analysis.\n");

    String date = scanner.next();
    double cost = 0.0;
    try {
      cost = portfolio.calculatePrice(new Date(date));
      if (cost != 0.0) {
        showMessage(appendable, "Total cost of your portfolio on " + date + " is: ");
        showMessage(appendable, String.valueOf(cost) + "\n");
      }

      else {
        showMessage(appendable, "No stock added!\n");
      }
    }

    catch (Exception e) {
      showMessage(appendable, "Please try again.\n");
    }

    showMessage(appendable, "Choose from following options:\n");
    showMessage(appendable,"1. Add Stock.\n");
    showMessage(appendable,"2. See all Stock Shares.\n");
    showMessage(appendable,"3. Cost Basis analysis on Specific Date.\n");
    showMessage(appendable,"4. Back\n");
    showMessage(appendable,"*------------------------------------------------------*\n");
  }
}






