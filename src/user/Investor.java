package user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import helper.Date;


/**
 * The Investor class represent a user. It contain a list of all accounts of the user. The total
 * monery he obtain and his user name.
 */
public class Investor {

  private String username;
  private List<Account> accounts;
  private double totalCash;

  /**
   * Inverstor constructor that take the user name. It can be instaniated only by the investorList
   * class.
   *
   * @param username the inverstopr name.
   */
  Investor(String username) {
    this.username = username;
    this.accounts = new ArrayList<>();
  }

  public String getUsername() {
    return username;
  }

  /**
   * This is the only method that can create an account. It add it to the user account list. It also
   * create a folder with the name of the investor. inside this folder there will be all the
   * investor accounts.
   *
   * @param accountName is the account Name.
   * @return an account object.
   */
  public Account createAccount(String accountName) {
    Account account = new Account(accountName, this);
    accounts.add(account);
    (new File("res/" + username + "/" + accountName)).mkdir();
    return account;
  }

  /**
   * Returns all the accounts of the user as a copy.
   *
   * @return a list of accounts.
   */
  public List<Account> getAllAccounts() {
    List<Account> accountList = new ArrayList<>();
    accountList.addAll(accounts);
    return accountList;
  }

  /**
   * Returns an account object of with the specified name.
   *
   * @return an account object.
   */
  public Account getAccount(String accountName) {
    List<Account> accounts = getAllAccounts();
    Account acc = null;
    for (Account account : accounts) {
      if (account.getName().equals(accountName)) {
        acc = account;
      }
    }
    return acc;
  }

  /**
   * Calculate price of account on a certain date.
   *
   * @param date - This is a Date object for which the price is to be calculated.
   * @return - value of whole account as a double.
   */
  public double calculatePrice(Date date) {
    double price = 0;
    for (Account a : accounts) {
      price = price + a.calculatePrice(date);
    }
    return price;
  }

  /**
   * This method will_returns the total cost of all accounts as double.
   *
   * @return value of all accounts as double.
   */
  public double getTotalCost() {
    double totalCost = 0;

    for (Account account : accounts) {
      totalCost = totalCost + account.getTotalCost();
    }

    return totalCost;
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder("");

    string.append("username ").append(username).append("\n");

    List<Account> accounts = getAllAccounts();

    for (Account account : accounts) {
      string.append(account.toString());
    }

    return string.toString();
  }


}
