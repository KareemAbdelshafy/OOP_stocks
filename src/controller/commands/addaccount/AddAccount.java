package controller.commands.addaccount;

import java.util.Scanner;

import controller.commands.AbstractCommand;
import controller.commands.Command;
import controller.commands.addportfolio.AddPortfolio;
import controller.commands.addportfolio.InvestmentPortfolio;
import controller.commands.addportfolio.SelectPortfolio;
import controller.commands.addportfolio.ShowAllPortfolio;
import user.Account;
import user.Investor;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to add a new account to a investor.
 * Then it gives following options to for the portfolio.
 * 1. Create a Portfolio.
 * 2. Show all Portfolio.
 * 3. Select Portfolio.
 * 4. Back.
 * Only calling to start will add a new account.
 */
public class AddAccount extends AbstractCommand {

  private Investor investor;
  private Readable readable;
  private Appendable appendable;
  private Scanner scanner;
  private String accountName;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param readable - This is a readable to get input from user.
   * @param appendable - This is a appendable to show output to user.
   * @param investor - This is the Investor being passed as an argument.
   * @param accountName - This is a String for the account name to be added.
   */
  public AddAccount(Readable readable, Appendable appendable,
                    Investor investor, String accountName) {
    this.investor = investor;
    this.appendable = appendable;
    this.readable = readable;
    this.accountName = accountName;
    scanner = new Scanner(readable);
  }

  @Override
  public void start() {

    Account account = investor.createAccount(accountName);

    Command command = null;

    StringBuilder stringBuilder = new StringBuilder();
    showMessage(appendable,"*------------------------------------------------------*\n");
    showMessage(appendable,"SUCCESS: Account added.\n");
    showMessage(appendable,"*------------------------------------------------------*\n");

    stringBuilder.append("Choose from following options:\n");
    stringBuilder.append("1. Create a simple Portfolio.\n");
    stringBuilder.append("2. Show all Portfolio\n");
    stringBuilder.append("3. Select Simple Portfolio.\n");
    stringBuilder.append("4. Create a Investment Portfolio.\n");
    stringBuilder.append("5. Back.\n");


    showMessage(appendable, stringBuilder.toString());

    while (scanner.hasNext()) {

      try {
        switch (Integer.parseInt(scanner.next())) {
          case 1:
            showMessage(appendable,"*--------------------------" +
                    "----------------------------*\n");
            showMessage(appendable,"INPUT: Enter New Portfolio name:\n");
            showMessage(appendable,"*--------------------------" +
                    "----------------------------*\n");
            command = new AddPortfolio(readable, appendable, account, scanner.next());
            break;
          case 2:
            command = new ShowAllPortfolio(appendable, account);
            break;
          case 3:
            showMessage(appendable,"*----------------------------" +
                    "--------------------------*\n");
            showMessage(appendable,"INPUT: Enter Simple Portfolio name to Search: \n");
            showMessage(appendable,"*---------------------------" +
                    "---------------------------*\n");
            command = new SelectPortfolio(readable, appendable, account, scanner.next());
            break;

          case 4:
            showMessage(appendable,"*--------------------------" +
                    "----------------------------*\n");
            showMessage(appendable,"INPUT: Enter New Investment Portfolio name:\n");
            showMessage(appendable,"*--------------------------" +
                    "----------------------------*\n");
            command = new InvestmentPortfolio(readable, appendable,
                    account, scanner.next(), scanner);
            break;
          case 5:
            showMessage(appendable,"*------------------------" +
                    "------------------------------*\n");
            showMessage(appendable,"Please select from the following options:\n");
            showMessage(appendable,"1. Create an Investor Account.\n");
            showMessage(appendable,"2. Show all Accounts\n");
            showMessage(appendable,"3. Select an Account\n");
            showMessage(appendable,"4. Back\n");
            showMessage(appendable,"*--------------------------" +
                    "----------------------------*\n");
            return;
          default:
            showMessage(appendable,"*----------------------------" +
                    "--------------------------*\n");
            showMessage(appendable,"ERROR: Enter a Valid option\n");
            showMessage(appendable,"*-----------------------------" +
                    "-------------------------*\n");
            break;
        }

        if (command != null) {
          command.start();
          command = null;
        }
      }

      catch (NumberFormatException e) {
        showMessage(appendable,"*---------------------------------" +
                "---------------------*\n");
        showMessage(appendable,"ERROR: Enter a Valid Number\n");
        showMessage(appendable,"*-------------------------------" +
                "-----------------------*\n");
      }
    }
  }

}
