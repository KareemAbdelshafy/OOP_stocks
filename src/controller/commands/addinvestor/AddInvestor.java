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
public class AddInvestor extends AbstractCommand {

  private InvestorList investorList;
  private String investorName;
  private Readable readable;
  private Appendable appendable;
  private Scanner scanner;
  private StringBuilder stringBuilder;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param readable - This is a readable to get input from user.
   * @param appendable - This is a appendable to show output to user.
   * @param investorName - This is a String for the Investor name to be added.
   */
  public AddInvestor(Readable readable, Appendable appendable, String investorName) {
    investorList = InvestorList.getInstance();
    this.investorName = investorName;
    this.readable = readable;
    this.appendable = appendable;
    scanner = new Scanner(readable);
    stringBuilder = new StringBuilder();

  }

  @Override
  public void start() {
    Investor investor = investorList.addInvestor(investorName);

    Command command = null;
    showMessage(appendable,"*------------------------------------------------------*\n");
    showMessage(appendable,"SUCCESS: Investor added.\n");
    showMessage(appendable,"*------------------------------------------------------*\n");

    stringBuilder.append("Please select from the following options:\n");
    stringBuilder.append("1. Create an Investor Account.\n");
    stringBuilder.append("2. Show all Accounts\n");
    stringBuilder.append("3. Select an Account\n");
    stringBuilder.append("4. Back\n");


    showMessage(appendable, stringBuilder.toString());
    while (scanner.hasNext()) {
      try {
        switch (Integer.parseInt(scanner.next())) {
          case 1:
            showMessage(appendable,"*--------------------------" +
                    "----------------------------*\n");
            showMessage(appendable,"INPUT: Enter Account name to be added: \n");
            showMessage(appendable,"*-----------------------------" +
                    "-------------------------*\n");
            command = new AddAccount(readable, appendable, investor, scanner.next());
            break;
          case 2:
            command = new ShowAllAccounts(appendable, investor);
            break;
          case 3:
            showMessage(appendable,"*-----------------------------" +
                    "-------------------------*\n");
            showMessage(appendable,"INPUT: Enter Account name to Search: \n");
            showMessage(appendable,"*------------------------------" +
                    "------------------------*\n");
            command = new SelectAccount(readable, appendable, investor, scanner.next());
            break;
          case 4:
            showMessage(appendable, "*------------------------------" +
                    "------------------------*\n");
            showMessage(appendable, "Please select from the following options:\n");
            showMessage(appendable, "1. Add new Investor Profile\n");
            showMessage(appendable, "2. Show all Investors\n");
            showMessage(appendable, "3. Select an Investor\n");
            showMessage(appendable, "4. Quit\n");
            showMessage(appendable, "*-------------------------------" +
                    "-----------------------*\n");
            return;
          default:
            showMessage(appendable,"*---------------------------------" +
                    "---------------------*\n");
            showMessage(appendable,"ERROR: Enter a Valid option\n");
            showMessage(appendable,"*----------------------------------" +
                    "--------------------*\n");
            break;
        }

        if (command != null) {
          command.start();
          command = null;
        }
      }
      catch (NumberFormatException e) {
        showMessage(appendable,"*--------------------------------------" +
                "----------------*\n");
        showMessage(appendable,"ERROR: Enter a valid Number\n");
        showMessage(appendable,"*---------------------------------------" +
                "---------------*\n");
      }
    }

  }


}
