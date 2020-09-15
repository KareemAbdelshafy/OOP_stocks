package controller.commands.addaccount;

import java.util.Scanner;

import controller.commands.AbstractCommand;
import controller.commands.Command;
import controller.commands.addportfolio.AddPortfolio;
import controller.commands.addportfolio.SelectPortfolio;
import controller.commands.addportfolio.ShowAllPortfolio;
import user.Account;
import user.Investor;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to select an account of a investor.
 * Then it gives following options to for the portfolio.
 * 1. Add an Portfoilio
 * 2. Show all Portfolio.
 * 3. Select an Portfolio.
 * 4. Back.
 * Only calling to start will add a new account.
 */
public class SelectAccount extends AbstractCommand {

  private Readable readable;
  private Appendable appendable;
  private Investor investor;
  private Scanner scanner;
  private String accountName;


  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param readable - This is a readable to get input from user.
   * @param appendable - This is a appendable to show output to user.
   * @param investor - This is the Investor being passed as an argument.
   * @param accountName - This is a String for the account name to be selected.
   */
  public SelectAccount(Readable readable, Appendable appendable,
                       Investor investor, String accountName) {
    this.readable = readable;
    this.appendable = appendable;
    this.investor = investor;
    this.scanner = new Scanner(readable);
    this.accountName = accountName;
  }

  @Override
  public void start() {
    Account account = investor.getAccount(accountName);

    if (account != null) {


      showMessage(appendable,"\n\n");

      StringBuilder stringBuilder = new StringBuilder();

      Command command = null;

      stringBuilder.append("Please select from the following options:\n");
      stringBuilder.append("1. Add an Portfoilio\n");
      stringBuilder.append("2. Show all Portfoilios\n");
      stringBuilder.append("3. Select an Portfolio\n");
      stringBuilder.append("4. Back\n");
      showMessage(appendable, stringBuilder.toString());


      while (scanner.hasNext()) {

        try {
          switch (Integer.parseInt(scanner.next())) {
            case 1:
              showMessage(appendable,"*----------------------------" +
                      "--------------------------*\n");
              showMessage(appendable,"INPUT: Enter New Portfolio name: \n");
              showMessage(appendable,"*--------------------------------" +
                      "----------------------*\n");
              command = new AddPortfolio(readable, appendable, account, scanner.next());
              break;
            case 2:
              command = new ShowAllPortfolio(appendable, account);
              break;
            case 3:
              showMessage(appendable,"*---------------------------" +
                      "---------------------------*\n");
              showMessage(appendable,"INPUT: Enter Portfolio name to Search: \n");
              showMessage(appendable,"*-----------------------------" +
                      "-------------------------*\n");
              command = new SelectPortfolio(readable, appendable, account, scanner.next());
              break;
            case 4:
              showMessage(appendable,"*---------------------------" +
                      "---------------------------*\n");
              showMessage(appendable,"Please select from the following options:\n");
              showMessage(appendable,"1. Create an Investor Account.\n");
              showMessage(appendable,"2. Show all Accounts\n");
              showMessage(appendable,"3. Select an Account\n");
              showMessage(appendable,"4. Back\n");
              showMessage(appendable,"*-----------------------------" +
                      "-------------------------*\n");
              return;
            default:
              showMessage(appendable,"*------------------------------" +
                      "------------------------*\n");
              showMessage(appendable,"ERROR: Enter a Valid option\n");
              showMessage(appendable,"*------------------------------" +
                      "------------------------*\n");
              break;
          }

          if (command != null) {
            command.start();
            command = null;
          }
        }

        catch (NumberFormatException e) {
          showMessage(appendable,"*--------------------------------" +
                  "----------------------*\n");
          showMessage(appendable,"ERROR: Enter a Valid Number\n");
          showMessage(appendable,"*--------------------------------" +
                  "----------------------*\n");
        }
      }
    }

    else {
      showMessage(appendable,"*-------------------------------------" +
              "-----------------*\n");
      showMessage(appendable,"ERROR: Account Not Found!\n");
      showMessage(appendable,"*-------------------------------------" +
              "-----------------*\n");
    }


  }

}
