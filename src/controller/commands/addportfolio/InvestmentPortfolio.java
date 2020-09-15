package controller.commands.addportfolio;

import java.util.Scanner;

import controller.commands.AbstractCommand;

import controller.commands.Command;
import controller.commands.investmentstock.AddStockInvestment;
import controller.commands.investmentstock.CostAnalysisInvestment;
import controller.commands.investmentstock.ShowAllStocksInvestment;
import user.Account;
import user.PortfolioAdvanced;


/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to show all portfolio.
 * It does not provide any interaction.
 * Only calling to start show the list.
 */
public class  InvestmentPortfolio extends AbstractCommand {

  private Appendable appendable;
  private Account account;
  private Readable readable;
  private Scanner scanner;
  private String portfolioName;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param appendable - This is a appendable to get input from user.
   * @param account - This is the account for which it shows all portfolio.
   */
  public InvestmentPortfolio(Readable readable, Appendable appendable,
                             Account account, String name, Scanner scanner) {
    this.account = account;
    this.appendable = appendable;
    this.readable = readable;
    this.scanner = scanner;
    this.portfolioName = name;
  }

  @Override
  public void start() {

    PortfolioAdvanced portfolio = account.createProfolioAdvanced(portfolioName);

    Command command = null;
    StringBuilder stringBuilder = new StringBuilder();

    showMessage(appendable,"*------------------------------------------------------*\n");
    showMessage(appendable,"SUCCESS: Portfolio Added.\n");
    showMessage(appendable,"*------------------------------------------------------*\n\n");
    stringBuilder.append("*------------------------------------------------------*\n");
    stringBuilder.append("Choose from following options:\n");
    stringBuilder.append("1. Add Stock.\n");
    stringBuilder.append("2. See all Stock Shares.\n");
    stringBuilder.append("3. Cost Basis analysis on Specific Date.\n");
    stringBuilder.append("4. Back\n");
    stringBuilder.append("*------------------------------------------------------*\n");

    showMessage(appendable, stringBuilder.toString());

    while (scanner.hasNext()) {
      try {
        switch (Integer.parseInt(scanner.next())) {
          case 1:
            showMessage(appendable,"*--------------------------" +
                    "----------------------------*\n");
            showMessage(appendable,"INPUT: Enter Stock date to invest: \n");
            showMessage(appendable,"*-----------------------------" +
                    "-------------------------*\n");
            command = new AddStockInvestment(readable, appendable, portfolio, scanner.next());
            break;

          case 2:
            command = new ShowAllStocksInvestment(appendable, portfolio, account);
            break;

          case 3:
            command = new CostAnalysisInvestment(appendable, portfolio, scanner);
            break;

          case 4:
            showMessage(appendable,"*--------------------------------" +
                    "----------------------*\n");
            showMessage(appendable,"Choose from following options:\n");
            showMessage(appendable,"1. Create a Portfolio.\n");
            showMessage(appendable,"2. Show all Portfolio\n");
            showMessage(appendable,"3. Select Portfolio.\n");
            showMessage(appendable,"4. Back\n");
            showMessage(appendable,"*---------------------------------" +
                    "---------------------*\n");
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
