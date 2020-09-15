package controller.commands.investmentstock;

import java.util.Scanner;

import controller.commands.AbstractCommand;
import controller.commands.Command;
import controller.commands.periodinvestment.PeriodicInvestment;
import controller.commands.periodinvestment.SingleInvestment;

import user.PortfolioAdvanced;

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
public class AddStockInvestment extends AbstractCommand {

  private Appendable appendable;
  private PortfolioAdvanced portfolio;
  private String dateString;
  private Scanner scanner;
  private Readable readable;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param readable - This is a readable to get input from user.
   * @param appendable - This is a appendable to show output to user.
   * @param dateString - This is a String for the date to be searched.
   * @param portfolio - This is the portflolio in which we want to enter shares.
   */
  public AddStockInvestment(Readable readable, Appendable appendable,
                  PortfolioAdvanced portfolio, String dateString) {
    this.appendable = appendable;
    this.portfolio = portfolio;
    this.dateString = dateString;
    this.scanner = new Scanner(readable);
    this.readable = readable;
  }

  @Override
  public void start() {



    Command command = null;
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("*------------------------------------------------------*\n");
    stringBuilder.append("Choose from following options:\n");
    stringBuilder.append("1. Invest one time.\n");
    stringBuilder.append("2. Invest periodically.\n");
    stringBuilder.append("3  Back.\n");
    stringBuilder.append("*------------------------------------------------------*\n");

    showMessage(appendable, stringBuilder.toString());

    while (scanner.hasNext()) {
      try {
        switch (Integer.parseInt(scanner.next())) {
          case 1:
            command = new SingleInvestment(readable, appendable, portfolio, dateString);
            break;
          case 2:
            command = new PeriodicInvestment( readable, appendable, portfolio, dateString);
            break;
          case 3:
            showMessage(appendable, "*-------------------------------" +
                    "-----------------------*\n");
            showMessage(appendable, "Choose from following options:\n");
            showMessage(appendable, "1. Add Stock.\n");
            showMessage(appendable, "2. See all Stock Shares.\n");
            showMessage(appendable, "3. Cost Basis analysis on Specific Date.\n");
            showMessage(appendable, "4. Back\n");
            showMessage(appendable, "*-----------------------------" +
                    "-------------------------*\n");
            return;
          default:
            showMessage(appendable,"*-----------------------------------" +
                    "-------------------*\n");
            showMessage(appendable,"ERROR: Enter a Valid option\n");
            showMessage(appendable,"*-----------------------------------" +
                    "-------------------*\n");
            break;
        }

        if (command != null) {
          command.start();
          command = null;
        }
      }

      catch (NumberFormatException e) {
        showMessage(appendable,"*-------------------------------------" +
                "-----------------*\n");
        showMessage(appendable,"ERROR: Enter a Valid Number\n");
        showMessage(appendable,"*---------------------------------------" +
                "---------------*\n");
      }
    }
  }
}
