package user;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that contain all the User of the application as a list of inverstors. This is a Singlton
 * class. You can not create more than one object of this class.
 */
public class InvestorList {

  private static InvestorList list;

  private Map<String, Investor> investors;

  private InvestorList() {
    investors = new HashMap<>();
  }

  /**
   * The only Static Method that can create an Object of InverstorList.
   *
   * @return The only Object of InvestorList class.
   */
  public static InvestorList getInstance() {

    if (list == null) {
      list = new InvestorList();
    }
    return list;
  }

  /**
   * AllInverstor return a list of all Investors using this application.
   *
   * @return a list of string of investor names.
   */
  public List<String> allInvestors() {
    List<String> list = new ArrayList<>();
    list.addAll(investors.keySet());
    return list;
  }

  /**
   * The only method that can create an investor object and add it to investor List.
   *
   * @param name The username of the investor.
   * @return a new Investor Object.
   */
  public Investor addInvestor(String name) {
    Investor i = new Investor(name);
    investors.put(name, i);
    (new File("res/" + name)).mkdir();
    return i;
  }

  /**
   * Return an investor object of the specified username.
   *
   * @param name is the username of the investor.
   * @return investor Object.
   */
  public Investor getInvestor(String name) {
    return investors.get(name);
  }


}
