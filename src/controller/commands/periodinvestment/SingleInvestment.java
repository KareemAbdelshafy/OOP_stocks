package controller.commands.periodinvestment;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



import controller.commands.AbstractCommand;
import helper.Date;
import user.PortfolioAdvanced;

/**
 * This class extends AbstractCommand class. It implements start() method.
 * This is used to add a new add Stock to a Portfolio List.
 * Then it gives following options to for the portfolio.
 * 1. Enter Company name.
 * 2. Enter number of shares.
 * 3. Add.
 * 4. Back.
 * To add select this sequentially.
 * First enter company name, then select 2 and enter number of shares you want to buy.
 * then select Add.
 * Only calling to start will add a new account.
 */
public class SingleInvestment extends AbstractCommand {

  private Appendable appendable;
  private PortfolioAdvanced portfolio;
  private String dateString;
  private Scanner scanner;


  /**
   * This constructor intializes Readable, Appendable, Investor, String.
   * This is the only constructor of this class.
   * @param readable - This is a readable to get input from user.
   * @param appendable - This is a appendable to show output to user.
   * @param dateString - This is a String for the date to be searched.
   * @param portfolio - This is the portflolio in which we want to enter shares.
   */
  public SingleInvestment(Readable readable, Appendable appendable,
                          PortfolioAdvanced portfolio, String dateString) {
    this.appendable = appendable;
    this.portfolio = portfolio;
    this.dateString = dateString;
    this.scanner = new Scanner(readable);
  }

  @Override
  public void start() {

    try {

      showMessage(appendable, "Enter Number of Companies to add.\n");

      int totalComapies = scanner.nextInt();

      int i = 0;
      while ( i < totalComapies) {
        int number = i + 1;
        showMessage(appendable, "Enter " + number + " company's Ticker:\n");
        String newCompany = scanner.next();
        portfolio.addStockSymbol(newCompany);
        i++;
      }

      showMessage(appendable, "Enter Commission.\n");

      int commission = scanner.nextInt();

      showMessage(appendable, "Enter Amount to invest.\n");

      int investment = scanner.nextInt();



      showMessage(appendable, "Do you want to set a ratio to investment?\n");
      showMessage(appendable, "Enter Y or N\n");

      char yesNo = scanner.next().charAt(0);

      if (yesNo == 'Y' || yesNo == 'y') {
        int j = 0;

        showMessage(appendable, "Format: 0.1 for 1st company, 0.9 for 2nd company.\n" );
        showMessage(appendable, "Ratios should add upto 1.\n" );

        List<Double> ratios = new LinkedList<>();

        while ( j < totalComapies) {
          int num = j + 1;
          showMessage(appendable, "Enter ratio " + num + " company in double:\n" );
          ratios.add(scanner.nextDouble());
          j++;
        }

        portfolio.setBuyingComission(commission);
        portfolio.setInvestAmount(investment);
        portfolio.setRatios(ratios);
        portfolio.invest(new Date(dateString));
        showMessage(appendable, "Investment Complete.\n");
      }

      else {
        portfolio.setBuyingComission(commission);
        portfolio.setInvestAmount(investment);
        portfolio.invest(new Date(dateString));
        showMessage(appendable, "Investment Complete.\n");
      }
    }

    catch (Exception e) {
      showMessage(appendable, "Please enter valid options.\n");
      showMessage(appendable, "Date should be in format YYYY-MM-DD\n");
      showMessage(appendable, "Ratios should sum upto 1.\n");
    }


    showMessage(appendable, "*------------------------------------------------------*\n");
    showMessage(appendable, "Choose from following options:\n");
    showMessage(appendable, "1. Invest one time.\n");
    showMessage(appendable, "2. Invest periodically.\n");
    showMessage(appendable, "3  Back.\n");
    showMessage(appendable, "*------------------------------------------------------*\n");
  }
}
