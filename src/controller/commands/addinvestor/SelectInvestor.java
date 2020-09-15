package controller.commands.addinvestor;


import java.util.Scanner;

import controller.commands.AbstractCommand;
import controller.commands.Command;
import controller.commands.addaccount.AddAccount;
import controller.commands.addaccount.SelectAccount;
import controller.commands.addaccount.ShowAllAccounts;

import user.Investor;
import user.InvestorList;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to add a new Investor profile to a investor List.
 * Then it gives following options to for the portfolio.
 * 1. Create an Investor Account.
 * 2. Show all Accounts.
 * 3. Select an Account.
 * 4. Back.
 * Only calling to start will add a new account.
 */
public class SelectInvestor extends AbstractCommand {

  private String investorName;
  private Readable readable;
  private Appendable appendable;
  private Scanner scanner;
  private InvestorList investorList;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param readable - This is a readable to get input from user.
   * @param appendable - This is a appendable to show output to user.
   * @param investorName - This is a String for the Investor name to be selected.
   */
  public SelectInvestor(Readable readable, Appendable appendable, String investorName) {

    this.readable = readable;
    this.appendable = appendable;
    this.scanner = new Scanner(readable);
    this.investorName = investorName;
    this.investorList = InvestorList.getInstance();
  }

  @Override
  public void start() {
    Investor investor = investorList.getInvestor(investorName);

    if (investor != null) {
      showMessage(appendable, "\n\n");

      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"MESSAGE: Welcome " + investorName + "\n");
      showMessage(appendable,"*------------------------------------------------------*\n");

      StringBuilder stringBuilder = new StringBuilder();

      Command command = null;

      stringBuilder.append("Please select from the following options:\n");
      stringBuilder.append("1. Create an Investor Account.\n");
      stringBuilder.append("2. Show all Accounts\n");
      stringBuilder.append("3. Select an Accounts\n");
      stringBuilder.append("4. Back\n");
      showMessage(appendable, stringBuilder.toString());


      while (scanner.hasNext()) {

        try {
          switch (Integer.parseInt(scanner.next())) {
            case 1:
              showMessage(appendable,"*------------------------------------------------------*\n");
              showMessage(appendable,"INPUT: Enter Account name to be added: \n");
              showMessage(appendable,"*------------------------------------------------------*\n");
              command = new AddAccount(readable, appendable, investor, scanner.next());
              break;
            case 2:
              command = new ShowAllAccounts(appendable, investor);
              break;
            case 3:
              showMessage(appendable,"*------------------------------------------------------*\n");
              showMessage(appendable,"INPUT: Enter Account name to Search: \n");
              showMessage(appendable,"*------------------------------------------------------*\n");
              command = new SelectAccount(readable, appendable, investor, scanner.next());
              break;
            case 4:
              showMessage(appendable, "*------------------------------------------------------*\n");
              showMessage(appendable, " Select from the following options:\n");
              showMessage(appendable, "1. Add new Investor Profile\n");
              showMessage(appendable, "2. Show all Investors\n");
              showMessage(appendable, "3. Select an Investor\n");
              showMessage(appendable, "4. Quit\n");
              showMessage(appendable, "*------------------------------------------------------*\n");
              return;
            default:
              showMessage(appendable,"*------------------------------------------------------*\n");
              showMessage(appendable,"ERROR: Enter a Valid option\n");
              showMessage(appendable,"*------------------------------------------------------*\n");
              break;
          }

          if (command != null) {
            command.start();
            command = null;
          }
        }

        catch (NumberFormatException e) {
          showMessage(appendable,"*------------------------------------------------------*\n");
          showMessage(appendable,"ERROR: Enter a Valid Number\n");
          showMessage(appendable,"*------------------------------------------------------*\n");
        }
      }
    }

    else {
      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"ERROR: Investor Not Found\n");
      showMessage(appendable,"*------------------------------------------------------*\n");
    }

  }

}
