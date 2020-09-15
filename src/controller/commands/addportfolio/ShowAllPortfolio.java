package controller.commands.addportfolio;

import java.util.List;

import controller.commands.AbstractCommand;

import user.Account;
import user.Portfolio;


/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to show all portfolio.
 * It does not provide any interaction.
 * Only calling to start show the list.
 */
public class ShowAllPortfolio extends AbstractCommand {

  private Appendable appendable;
  private Account account;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param appendable - This is a appendable to get input from user.
   * @param account - This is the account for which it shows all portfolio.
   */
  public ShowAllPortfolio(Appendable appendable, Account account) {
    this.account = account;
    this.appendable = appendable;
  }

  @Override
  public void start() {


    List<Portfolio> portfolios = account.getPortiofolios();

    if (portfolios.size() != 0) {
      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"MESSAGE: All Portfolios are:\n");
      showMessage(appendable,"*------------------------------------------------------*\n");
      for (Portfolio portfolio: portfolios) {
        showMessage(appendable, portfolio.toString());
      }
      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"Choose from following options:\n");
      showMessage(appendable,"1. Create a Portfolio.\n");
      showMessage(appendable,"2. Show all Portfolio\n");
      showMessage(appendable,"3. Select Simple Portfolio.\n");
      showMessage(appendable,"4. Create investment Portfolio.\n");
      showMessage(appendable,"5. Back\n");
      showMessage(appendable,"*------------------------------------------------------*\n");
    }
    else  {
      showMessage(appendable,"*------------------------------------------------------*\n");
      showMessage(appendable,"ERROR: No portfolio Found!\n");
      showMessage(appendable,"*------------------------------------------------------*\n");
    }

  }

}
