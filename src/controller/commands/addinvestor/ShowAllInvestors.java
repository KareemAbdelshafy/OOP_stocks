package controller.commands.addinvestor;

import java.util.List;

import controller.commands.AbstractCommand;

import user.InvestorList;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to show all investors.
 * It does not provide any interaction.
 * Only calling to start show the list.
 */
public class ShowAllInvestors extends AbstractCommand {

  private InvestorList investorList;
  private Appendable appendable;

  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param appendable - This is a appendable to get input from user.
   */
  public ShowAllInvestors(Appendable appendable) {
    investorList = InvestorList.getInstance();
    this.appendable = appendable;
  }

  @Override
  public void start() {

    List<String> list = investorList.allInvestors();
    if (list.size() != 0) {
      showMessage(appendable,"*----------------------------------" +
              "--------------------*\n");
      showMessage(appendable,"MESSAGE: All Investors are: \n");
      showMessage(appendable,"*------------------------------------" +
              "------------------*\n");
      for (String s: list) {
        showMessage(appendable, s + "\n");
      }

      showMessage(appendable, "*-------------------------------------" +
              "-----------------*\n");
      showMessage(appendable, "Please select from the following options:\n");
      showMessage(appendable, "1. Add new Investor Profile\n");
      showMessage(appendable, "2. Show all Investors\n");
      showMessage(appendable, "3. Select an Investor\n");
      showMessage(appendable, "4. Quit\n");
      showMessage(appendable, "*----------------------------------------" +
              "--------------*\n");

    }
    else {
      showMessage(appendable,"*---------------------------------------" +
              "---------------*\n");
      showMessage(appendable,"ERROR: No investors Added yet\n");
      showMessage(appendable,"*---------------------------------------" +
              "---------------*\n");
    }

  }
}
