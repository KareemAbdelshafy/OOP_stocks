package user;


import java.util.ArrayList;
import java.util.List;

import helper.Date;

/**
 * This class represent an account. It contains a list of protfolios. and it contains its use as
 * inverstor object.
 */
public class Account {

  private String name;
  private List<PortfolioAdvanced> portfolioImpls;
  private Investor investor;

  /**
   * A package private constructor. The only object that can create an account object is investor
   * object.
   *
   * @param name     the name of the account.
   * @param investor the holder of the account as an investor object.
   */
  Account(String name, Investor investor) {
    this.name = name;
    this.investor = investor;
    portfolioImpls = new ArrayList<>();
  }

  /**
   * All the accounts are saved as a directory. Inside the directory there will be the portfolios.
   *
   * @return the directory of this account.
   */

  public String getCurrentDir() {
    return "res/" + investor.getUsername() + "/" + name;
  }

  /**
   * This method add a portfolio to the current account by loading a strategy and making the
   * investment.
   *
   * @param dir           is the directory of the strategy.
   * @param portfolioName is the portfolio name.
   * @return an object of the new PortfolioAdvanced created.
   */
  public PortfolioAdvanced addPortfolioByStrategy(String dir, String portfolioName) {
    PortfolioAdvanced p = createProfolio(portfolioName);
    p.investByStrategy(dir);
    return p;
  }

  /**
   * This method will_returns all the portfolios insided this account.
   *
   * @return List of portfolios.
   */

  public List<PortfolioAdvanced> getAllPortfolioAdvanced() {
    List<PortfolioAdvanced> portfolioImplList = new ArrayList<>();
    portfolioImplList.addAll(portfolioImpls);
    return portfolioImplList;
  }

  /**
   * This method will_returns a PortfolioAdvanced objcet if it exists. Otherwise, it throw
   * an exception.
   * @param porfoliName is the name of the portfolio needed.
   * @return portfolio object.
   * @throws IllegalArgumentException if it doesnot contain that portflio name.
   */
  public PortfolioAdvanced getPortfolioAdvanced(String porfoliName)
          throws IllegalArgumentException {
    for (PortfolioAdvanced p : portfolioImpls) {

      if (p.getPortfolioName().equals(porfoliName)) {
        return p;
      }
    }
    throw new IllegalArgumentException("No porfolio with name " + porfoliName);
  }

  /**
   * This method load a saved portfolio and add it to the current account.
   *
   * @param dir is the location of the portfolio file.
   * @return a reference of the new portfolio loaded.
   */
  public PortfolioAdvanced loadPortfolio(String dir) {
    String[] pieces = dir.split("/");
    String pName = pieces[pieces.length - 1].split("_")[0];
    PortfolioAdvanced p = createProfolio(pieces[pieces.length - 1].split("_")[0]);
    p.loadPortfolio(dir);
    return p;
  }

  /**
   * Calculate price of portfolio on a certain date.
   *
   * @param date - This is a Date object for which the price is to be calculated.
   * @return - value of whole porfolio as a double.
   */
  public double calculatePrice(Date date) {
    double price = 0;
    for (Portfolio p : portfolioImpls) {
      price = price + p.calculatePrice(date);
    }
    return price;
  }

  /**
   * The only method that can create an PortfolioImpl object that belongs to this account. This is a
   * simple portfolio which cannot be used for investment.
   *
   * @param portfolioName the new portfolio name.
   * @return an portfolio object.
   */
  public PortfolioAdvanced createProfolio(String portfolioName) {
    PortfolioAdvanced p = new PortfolioAdvancedImpl(portfolioName, this);
    portfolioImpls.add(p);
    return p;
  }


  /**
   * The only method that can create an PortfolioAdvancedImpl object that belongs to this account.
   * This can be used to make investment portfolio.
   *
   * @param portfolioName the new portfolio name.
   * @return an portfolio object.
   */
  public PortfolioAdvanced createProfolioAdvanced(String portfolioName) {
    PortfolioAdvancedImpl p = new PortfolioAdvancedImpl(portfolioName, this);
    portfolioImpls.add(p);
    return p;
  }

  /**
   * The method returns the account holder as investor object.
   *
   * @return inverstor object.
   */
  public Investor getInvestor() {
    return investor;
  }

  /**
   * returns the name of the profolio.
   *
   * @return the name of the portfolio.
   */
  public String getName() {
    return name;
  }

  /**
   * getPortfolio returns a copy of all portfolioImpls belongs to this account as a list.
   *
   * @return list of the account portfolioImpls.
   */
  public List<Portfolio> getPortiofolios() {
    List<Portfolio> portfolioImplList = new ArrayList<>();
    portfolioImplList.addAll(portfolioImpls);
    return portfolioImplList;
  }


  /**
   * This method will_returns the total cost as double.
   *
   * @return - value of all simple portfolios as double.
   */
  public double getTotalCost() {
    double totalCost = 0;

    for (Portfolio p : portfolioImpls) {
      totalCost = totalCost + p.getTotalCost();
    }
    return totalCost;

  }

  /**
   * This method will_returns protfolio for a given string.
   *
   * @param porfoliName - name for certain portfolio.
   * @return - Portfolio object.
   * @throws IllegalArgumentException - of portfolio not found.
   */
  public Portfolio getPortfolio(String porfoliName) throws IllegalArgumentException {
    for (Portfolio p : portfolioImpls) {

      if (p.getPortfolioName().equals(porfoliName)) {
        return p;
      }
    }
    throw new IllegalArgumentException("No porfolio with name " + porfoliName);
  }


  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Account Name: ");
    stringBuilder.append(name + "\n");

    for (Portfolio portfolioImpl : portfolioImpls) {
      stringBuilder.append(portfolioImpl.toString());
    }
    return stringBuilder.toString();
  }


}
