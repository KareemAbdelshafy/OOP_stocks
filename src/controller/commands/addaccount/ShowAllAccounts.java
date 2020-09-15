package controller.commands.addaccount;

import java.util.List;

import controller.commands.AbstractCommand;

import user.Account;
import user.Investor;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to show all accounts of a investor.
 * It does not provide any interaction.
 * Only calling to start show the list.
 */
public class ShowAllAccounts extends AbstractCommand {
  private Investor investor;
  private Appendable appendable;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param appendable - This is a appendable to get input from user.
   * @param investor - This is the Investor being passed as an argument.
   */
  public ShowAllAccounts(Appendable appendable, Investor investor) {
    this.investor = investor;
    this.appendable = appendable;

  }

  @Override
  public void start() {


    List<Account> accountList = investor.getAllAccounts();

    if (accountList.size() != 0) {

      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"MESSAGE: All Accounts are: \n");
      showMessage(appendable,"*------------------------------------------------------*\n");
      StringBuilder stringBuilder = new StringBuilder();

      for (Account account: accountList) {

        stringBuilder.append(account.toString());
      }

      showMessage(appendable, stringBuilder.toString());

      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"Please select from the following options:\n");
      showMessage(appendable,"1. Create an Investor Account.\n");
      showMessage(appendable,"2. Show all Accounts\n");
      showMessage(appendable,"3. Select an Account\n");
      showMessage(appendable,"4. Back\n");
      showMessage(appendable,"*------------------------------------------------------*\n");

    }

    else {
      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"ERROR: Account Not Found!\n");
      showMessage(appendable,"*------------------------------------------------------*\n");
    }
  }
}
