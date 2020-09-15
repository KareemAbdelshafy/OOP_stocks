package controller.commands.stock;

import java.util.List;

import controller.commands.AbstractCommand;
import user.Portfolio;
import user.StockShare;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used show individual stock in the portfolio.
 * Only calling to start will show cost.
 */
public class ShowAllStocks extends AbstractCommand {

  private Appendable appendable;
  private Portfolio portfolio;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param appendable - This is a appendable to show output to user.
   * @param portfolio - This is the portflolio in which we want to enter shares.
   */
  public ShowAllStocks(Appendable appendable, Portfolio portfolio) {
    this.appendable = appendable;
    this.portfolio = portfolio;
  }

  @Override
  public void start() {

    List<StockShare> stockShares = portfolio.getAllStocks();

    if (stockShares.size() != 0) {
      for (StockShare stockShare: stockShares) {
        showMessage(appendable, stockShare.toString() + "\n");
      }
    }

    else {
      showMessage(appendable, "No stocks added!");
    }

    showMessage(appendable, "Choose from following options:\n");
    showMessage(appendable,"1. Add Stock.\n");
    showMessage(appendable,"2. See all Stock Shares.\n");
    showMessage(appendable,"3. Cost Basis analysis on Specific Date.\n");
    showMessage(appendable,"4. Back\n");
    showMessage(appendable,"*---------------------------------" +
            "---------------------*\n");

  }
}
