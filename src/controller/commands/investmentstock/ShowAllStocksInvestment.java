package controller.commands.investmentstock;

import controller.commands.AbstractCommand;
import user.Account;
import user.PortfolioAdvanced;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used show individual stock in the portfolio.
 * Only calling to start will show cost.
 */
public class ShowAllStocksInvestment extends AbstractCommand {

  private Appendable appendable;
  private Account account;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param appendable - This is a appendable to show output to user.
   * @param portfolio - This is the portflolio in which we want to enter shares.
   */
  public ShowAllStocksInvestment(Appendable appendable,
                                 PortfolioAdvanced portfolio, Account account) {
    this.appendable = appendable;
    this.account = account;
  }

  @Override
  public void start() {

    showMessage(appendable, account.getInvestor().toString());

    showMessage(appendable,"*---------------------------------" +
            "---------------------*\n");

    showMessage(appendable, "Choose from following options:\n");
    showMessage(appendable,"1. Add Stock.\n");
    showMessage(appendable,"2. See all Stock Shares.\n");
    showMessage(appendable,"3. Cost Basis analysis on Specific Date.\n");
    showMessage(appendable,"4. Back\n");
    showMessage(appendable,"*---------------------------------" +
            "---------------------*\n");
  }
}
