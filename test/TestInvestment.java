import org.junit.Assert;
import org.junit.Test;


import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import helper.Date;
import user.Account;
import user.Investor;
import user.InvestorList;
import user.PortfolioAdvanced;


public class TestInvestment {



  @Test
  public void testSavingandLoadStrategy() {
    InvestorList investorList = InvestorList.getInstance();
    Investor kareem = investorList.addInvestor("Kareem");
    Account saving = kareem.createAccount("Saving");
    PortfolioAdvanced portfolioImpl = saving.createProfolioAdvanced("foo");
    portfolioImpl.setInvestAmount(1000);
    portfolioImpl.setBuyingComission(5);
    portfolioImpl.addStockSymbol("FB");
    portfolioImpl.addStockSymbol("GOOGL");
    portfolioImpl.addStockSymbol("AMZN");
    portfolioImpl.invest(new Date("2018-01-10"));
    portfolioImpl.saveStrategy(saving.getCurrentDir());
    PortfolioAdvanced p = saving.createProfolioAdvanced("foo2");
    p.investByStrategy(saving.getCurrentDir() + "/foo_str.txt");
    Assert.assertEquals(p.toString(), "PortfolioImpl Name: foo2\n" +
            "ID 4 company FB  numberOfShares 1.7745599091425326 unit price 187.84" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 5 company GOOGL  numberOfShares 0.30026242936326347 unit price 1110.14" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 6 company AMZN  numberOfShares 0.26574612209971327 unit price 1254.33" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n");

    Assert.assertEquals(portfolioImpl.toString(), "PortfolioImpl Name: foo\n" +
            "ID 1 company FB  numberOfShares 1.7745599091425326 unit price 187.84" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 2 company GOOGL  numberOfShares 0.30026242936326347 unit price 1110.14 " +
            "totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 3 company AMZN  numberOfShares 0.26574612209971327 unit price 1254.33 " +
            "totalPrice 333.3333333333333 Date 2018-1-10\n");
    p.savePortfolio(saving.getCurrentDir());
  }


  @Test
  public void testLoadingStrategyAndPortfolio() {
    InvestorList investorList = InvestorList.getInstance();
    Investor kareem = investorList.addInvestor("Kareem");
    Account checking = kareem.createAccount("Checking");

    String dir = "res/Kareem/Saving/foo2_str.txt";

    PortfolioAdvanced p3 = checking.addPortfolioByStrategy(dir, "foo3");

    Assert.assertEquals(p3.toString(), "PortfolioImpl Name: foo3\n" +
            "ID 1 company FB  numberOfShares 1.7745599091425326 unit price" +
            " 187.84 totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 2 company GOOGL  numberOfShares 0.30026242936326347" +
            " unit price 1110.14 totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 3 company AMZN  numberOfShares 0.26574612209971327 unit price " +
            "1254.33 totalPrice 333.3333333333333 Date 2018-1-10\n");
    p3.savePortfolio(checking.getCurrentDir());
    PortfolioAdvanced p4 = checking.loadPortfolio("res/Kareem/Saving/foo2_prt.txt");
    Assert.assertEquals(p4.toString(), "PortfolioImpl Name: foo2\n" +
            "ID 4 company FB  numberOfShares 1.7745599091425326 unit price 187.84" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 5 company GOOGL  numberOfShares 0.30026242936326347 unit price 1110.14 " +
            "totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 6 company AMZN  numberOfShares 0.26574612209971327 unit price 1254.33 " +
            "totalPrice 333.3333333333333 Date 2018-1-10\n");
    p4.saveStrategy(checking.getCurrentDir());

    Account account = p4.getAccount();
    System.out.println(account.getCurrentDir());
  }

  @Test
  public void testSavingPortfolio() {

    String line = "Name foo2\n" +
            "tickerSymbols FB GOOGL AMZN \n" +
            "InvestmentRatios 0.3333333333333333 0.3333333333333333 0.3333333333333333 \n" +
            "investAmount 1000.0\n" +
            "sellingComission 0.0\n" +
            "buyingComission 5.0\n" +
            "startDate 2018-1-10\n" +
            "endDate null\n" +
            "InvestmentPeriodicity 0\n" +
            "PortfolioImpl Name: foo2\n" +
            "ID 4 company FB  numberOfShares 1.7745599091425326 unit price 187.84" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 5 company GOOGL  numberOfShares 0.30026242936326347 unit price 1110.14" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 6 company AMZN  numberOfShares 0.26574612209971327 unit price 1254.33" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n";

    StringBuilder s = new StringBuilder("");
    try {
      FileReader fr = new FileReader("res/Kareem/Saving/foo2_prt.txt");
      int i;
      while ((i = fr.read()) != -1) {
        s.append((char) i);
      }

    } catch (Exception e) {
      Assert.fail();
    }

    Assert.assertEquals(s.toString(), line);
  }

  @Test
  public void testSavingStrategy() {

    String line = "Name foo\n" +
            "tickerSymbols FB GOOGL AMZN \n" +
            "InvestmentRatios 0.3333333333333333 0.3333333333333333 0.3333333333333333 \n" +
            "investAmount 1000.0\n" +
            "sellingComission 0.0\n" +
            "buyingComission 5.0\n" +
            "startDate 2018-1-10\n" +
            "endDate null\n" +
            "InvestmentPeriodicity 0";

    StringBuilder s = new StringBuilder("");
    try {
      FileReader fr = new FileReader("res/Kareem/Saving/foo_str.txt");
      int i;
      while ((i = fr.read()) != -1) {
        s.append((char) i);
      }

    } catch (Exception e) {
      Assert.fail();
    }

    Assert.assertEquals(s.toString(), line);
  }


  @Test
  public void firstTest() {

    InvestorList investorList = InvestorList.getInstance();
    Investor kareem = investorList.addInvestor("Kareem");
    Account saving = kareem.createAccount("Saving");
    PortfolioAdvanced portfolioImpl = saving.createProfolioAdvanced("foo");
    portfolioImpl.setInvestAmount(1000);
    portfolioImpl.setBuyingComission(5);
    portfolioImpl.addStockSymbol("FB");
    portfolioImpl.addStockSymbol("GOOGL");
    portfolioImpl.addStockSymbol("AMZN");

    portfolioImpl.invest(new Date("2018-01-10"));

    String expected = "username Kareem\n" +
            "Account Name: Saving\n" +
            "PortfolioImpl Name: foo\n" +
            "ID 1 company FB  numberOfShares 1.7745599091425326 unit" +
            " price 187.84 totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 2 company GOOGL  numberOfShares 0.30026242936326347" +
            " unit price 1110.14 totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 3 company AMZN  numberOfShares 0.26574612209971327" +
            " unit price 1254.33 totalPrice 333.3333333333333 Date 2018-1-10\n";
    assertEquals(expected, kareem.toString());
  }

  @Test
  public void singleInvestmentRatios() {

    InvestorList investorList = InvestorList.getInstance();
    Investor kareem = investorList.addInvestor("Kareem");
    Account saving = kareem.createAccount("Saving");
    PortfolioAdvanced portfolioImpl = saving.createProfolioAdvanced("foo");
    List<Double> ratios = new LinkedList<>();
    ratios.add(0.8);
    ratios.add(0.1);
    ratios.add(0.1);
    portfolioImpl.setRatios(ratios);
    portfolioImpl.setInvestAmount(1000);
    portfolioImpl.setBuyingComission(5);
    portfolioImpl.addStockSymbol("FB");
    portfolioImpl.addStockSymbol("GOOGL");
    portfolioImpl.addStockSymbol("AMZN");

    portfolioImpl.invest(new Date("2018-01-10"));

    String expected = "username Kareem\n" +
            "Account Name: Saving\n" +
            "PortfolioImpl Name: foo\n" +
            "ID 1 company FB  numberOfShares 1.7745599091425326" +
            " unit price 187.84 totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 2 company GOOGL  numberOfShares 0.30026242936326347" +
            " unit price 1110.14 totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 3 company AMZN  numberOfShares 0.26574612209971327" +
            " unit price 1254.33 totalPrice 333.3333333333333 Date 2018-1-10\n";
    assertEquals(expected, kareem.toString());
  }

  @Test
  public void multipleInvestment() {

    InvestorList investorList = InvestorList.getInstance();
    Investor kareem = investorList.addInvestor("Kareem");
    Account saving = kareem.createAccount("Saving");
    PortfolioAdvanced portfolioImpl = saving.createProfolioAdvanced("foo");

    portfolioImpl.setInvestAmount(1000);
    portfolioImpl.setBuyingComission(5);
    portfolioImpl.addStockSymbol("FB");
    portfolioImpl.addStockSymbol("GOOGL");
    portfolioImpl.addStockSymbol("AMZN");

    portfolioImpl.investPeriodically(new Date("2018-01-10"), 30);

    String expected = "username Kareem\n" +
            "Account Name: Saving\n" +
            "PortfolioImpl Name: foo\n" +
            "ID 1 company FB  numberOfShares 1.7745599091425326 unit price 187.84" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 2 company GOOGL  numberOfShares 0.30026242936326347 unit price 1110.14" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 3 company AMZN  numberOfShares 0.26574612209971327 unit price 1254.33" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 4 company FB  numberOfShares 1.8927564211761585 unit price 176.11" +
            " totalPrice 333.3333333333333 Date 2018-2-9\n" +
            "ID 5 company GOOGL  numberOfShares 0.3185920778893912 unit price 1046." +
            "27 totalPrice 333.3333333333333 Date 2018-2-9\n" +
            "ID 6 company AMZN  numberOfShares 0.24883049666567134 unit price 1339.6 " +
            "totalPrice 333.3333333333333 Date 2018-2-9\n" +
            "ID 7 company FB  numberOfShares 1.7995645053896956 unit price 185.23 " +
            "totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 8 company GOOGL  numberOfShares 0.287148386800363 unit price 1160.84 " +
            "totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 9 company AMZN  numberOfShares 0.2111187817601817 unit price 1578.89 " +
            "totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 10 company FB  numberOfShares 2.019712392955243 unit price 165.04 " +
            "totalPrice 333.3333333333333 Date 2018-4-10\n" +
            "ID 11 company GOOGL  numberOfShares 0.32159511175430133 unit price 1036.5 t" +
            "otalPrice 333.3333333333333 Date 2018-4-10\n" +
            "ID 12 company AMZN  numberOfShares 0.23209071962048525 unit price 1436.22" +
            " totalPrice 333.3333333333333 Date 2018-4-10\n" +
            "ID 13 company FB  numberOfShares 1.7966546290806518 unit price 185.53 " +
            "totalPrice 333.3333333333333 Date 2018-5-10\n" +
            "ID 14 company GOOGL  numberOfShares 0.3015308722383541 unit price 1105.47" +
            " totalPrice 333.3333333333333 Date 2018-5-10\n" +
            "ID 15 company AMZN  numberOfShares 0.2071577133102974 unit price 1609.08 " +
            "totalPrice 333.3333333333333 Date 2018-5-10\n" +
            "ID 16 company FB  numberOfShares 1.7627357659086902 unit price 189.1" +
            " totalPrice 333.3333333333333 Date 2018-6-8\n" +
            "ID 17 company GOOGL  numberOfShares 0.29427950078425485 unit price 1132.71" +
            " totalPrice 333.3333333333333 Date 2018-6-8\n" +
            "ID 18 company AMZN  numberOfShares 0.19794258477386048 unit price 1683.99" +
            " totalPrice 333.3333333333333 Date 2018-6-8\n" +
            "ID 19 company FB  numberOfShares 1.628081143564195 unit price 204.74 " +
            "totalPrice 333.3333333333333 Date 2018-7-9\n" +
            "ID 20 company GOOGL  numberOfShares 0.28556416055559364 unit price 1167.28" +
            " totalPrice 333.3333333333333 Date 2018-7-9\n" +
            "ID 21 company AMZN  numberOfShares 0.19167883827289697 unit price 1739.02" +
            " totalPrice 333.3333333333333 Date 2018-7-9\n" +
            "ID 22 company FB  numberOfShares 1.8000504014112393 unit price 185.18" +
            " totalPrice 333.3333333333333 Date 2018-8-8\n" +
            "ID 23 company GOOGL  numberOfShares 0.2642713114992376 unit price 1261.33" +
            " totalPrice 333.33333333333337 Date 2018-8-8\n" +
            "ID 24 company AMZN  numberOfShares 0.1766921810176056 unit price 1886.52 " +
            "totalPrice 333.3333333333333 Date 2018-8-8\n" +
            "ID 25 company FB  numberOfShares 2.0444880601897286 unit price 163.04" +
            " totalPrice 333.3333333333333 Date 2018-9-7\n" +
            "ID 26 company GOOGL  numberOfShares 0.2830639979392941 unit price 1177.59 " +
            "totalPrice 333.3333333333333 Date 2018-9-7\n" +
            "ID 27 company AMZN  numberOfShares 0.1707589037961412 unit price 1952.07" +
            " totalPrice 333.3333333333333 Date 2018-9-7\n" +
            "ID 28 company FB  numberOfShares 2.1186889552744756 unit price 157.33" +
            " totalPrice 333.33333333333326 Date 2018-10-5\n" +
            "ID 29 company GOOGL  numberOfShares 0.28542967155607696 unit price 1167.83" +
            " totalPrice 333.3333333333333 Date 2018-10-5\n" +
            "ID 30 company AMZN  numberOfShares 0.17639950960936326 unit price 1889.65 " +
            "totalPrice 333.3333333333333 Date 2018-10-5\n" +
            "ID 31 company FB  numberOfShares 2.2231114668089456 unit price 149.94 " +
            "totalPrice 333.3333333333333 Date 2018-11-6\n" +
            "ID 32 company GOOGL  numberOfShares 0.3116517229665504 unit price 1069.57" +
            " totalPrice 333.3333333333333 Date 2018-11-6\n" +
            "ID 33 company AMZN  numberOfShares 0.20290437319795554 unit price 1642.81" +
            " totalPrice 333.3333333333333 Date 2018-11-6\n";
    assertEquals(expected, kareem.toString());
  }

  @Test
  public void multipleInvestmentRatios() {

    InvestorList investorList = InvestorList.getInstance();
    Investor kareem = investorList.addInvestor("Kareem");
    Account saving = kareem.createAccount("Saving");
    PortfolioAdvanced portfolioImpl = saving.createProfolioAdvanced("foo");
    List<Double> ratios = new LinkedList<>();
    ratios.add(0.8);
    ratios.add(0.1);
    ratios.add(0.1);
    portfolioImpl.setRatios(ratios);
    portfolioImpl.setInvestAmount(1000);
    portfolioImpl.setBuyingComission(5);
    portfolioImpl.addStockSymbol("FB");
    portfolioImpl.addStockSymbol("GOOGL");
    portfolioImpl.addStockSymbol("AMZN");

    portfolioImpl.investPeriodically(new Date("2018-01-10"), 30);

    String expected = "username Kareem\n" +
            "Account Name: Saving\n" +
            "PortfolioImpl Name: foo\n" +
            "ID 1 company FB  numberOfShares 1.7745599091425326 unit price 187.84" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 2 company GOOGL  numberOfShares 0.30026242936326347 unit price 1110.14" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 3 company AMZN  numberOfShares 0.26574612209971327 unit price 1254.33" +
            " totalPrice 333.3333333333333 Date 2018-1-10\n" +
            "ID 4 company FB  numberOfShares 1.8927564211761585 unit price 176.11" +
            " totalPrice 333.3333333333333 Date 2018-2-9\n" +
            "ID 5 company GOOGL  numberOfShares 0.3185920778893912 unit price 1046." +
            "27 totalPrice 333.3333333333333 Date 2018-2-9\n" +
            "ID 6 company AMZN  numberOfShares 0.24883049666567134 unit price 1339.6 " +
            "totalPrice 333.3333333333333 Date 2018-2-9\n" +
            "ID 7 company FB  numberOfShares 1.7995645053896956 unit price 185.23 " +
            "totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 8 company GOOGL  numberOfShares 0.287148386800363 unit price 1160.84 " +
            "totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 9 company AMZN  numberOfShares 0.2111187817601817 unit price 1578.89 " +
            "totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 10 company FB  numberOfShares 2.019712392955243 unit price 165.04 " +
            "totalPrice 333.3333333333333 Date 2018-4-10\n" +
            "ID 11 company GOOGL  numberOfShares 0.32159511175430133 unit price 1036.5 t" +
            "otalPrice 333.3333333333333 Date 2018-4-10\n" +
            "ID 12 company AMZN  numberOfShares 0.23209071962048525 unit price 1436.22" +
            " totalPrice 333.3333333333333 Date 2018-4-10\n" +
            "ID 13 company FB  numberOfShares 1.7966546290806518 unit price 185.53 " +
            "totalPrice 333.3333333333333 Date 2018-5-10\n" +
            "ID 14 company GOOGL  numberOfShares 0.3015308722383541 unit price 1105.47" +
            " totalPrice 333.3333333333333 Date 2018-5-10\n" +
            "ID 15 company AMZN  numberOfShares 0.2071577133102974 unit price 1609.08 " +
            "totalPrice 333.3333333333333 Date 2018-5-10\n" +
            "ID 16 company FB  numberOfShares 1.7627357659086902 unit price 189.1" +
            " totalPrice 333.3333333333333 Date 2018-6-8\n" +
            "ID 17 company GOOGL  numberOfShares 0.29427950078425485 unit price 1132.71" +
            " totalPrice 333.3333333333333 Date 2018-6-8\n" +
            "ID 18 company AMZN  numberOfShares 0.19794258477386048 unit price 1683.99" +
            " totalPrice 333.3333333333333 Date 2018-6-8\n" +
            "ID 19 company FB  numberOfShares 1.628081143564195 unit price 204.74 " +
            "totalPrice 333.3333333333333 Date 2018-7-9\n" +
            "ID 20 company GOOGL  numberOfShares 0.28556416055559364 unit price 1167.28" +
            " totalPrice 333.3333333333333 Date 2018-7-9\n" +
            "ID 21 company AMZN  numberOfShares 0.19167883827289697 unit price 1739.02" +
            " totalPrice 333.3333333333333 Date 2018-7-9\n" +
            "ID 22 company FB  numberOfShares 1.8000504014112393 unit price 185.18" +
            " totalPrice 333.3333333333333 Date 2018-8-8\n" +
            "ID 23 company GOOGL  numberOfShares 0.2642713114992376 unit price 1261.33" +
            " totalPrice 333.33333333333337 Date 2018-8-8\n" +
            "ID 24 company AMZN  numberOfShares 0.1766921810176056 unit price 1886.52 " +
            "totalPrice 333.3333333333333 Date 2018-8-8\n" +
            "ID 25 company FB  numberOfShares 2.0444880601897286 unit price 163.04" +
            " totalPrice 333.3333333333333 Date 2018-9-7\n" +
            "ID 26 company GOOGL  numberOfShares 0.2830639979392941 unit price 1177.59 " +
            "totalPrice 333.3333333333333 Date 2018-9-7\n" +
            "ID 27 company AMZN  numberOfShares 0.1707589037961412 unit price 1952.07" +
            " totalPrice 333.3333333333333 Date 2018-9-7\n" +
            "ID 28 company FB  numberOfShares 2.1186889552744756 unit price 157.33" +
            " totalPrice 333.33333333333326 Date 2018-10-5\n" +
            "ID 29 company GOOGL  numberOfShares 0.28542967155607696 unit price 1167.83" +
            " totalPrice 333.3333333333333 Date 2018-10-5\n" +
            "ID 30 company AMZN  numberOfShares 0.17639950960936326 unit price 1889.65 " +
            "totalPrice 333.3333333333333 Date 2018-10-5\n" +
            "ID 31 company FB  numberOfShares 2.2231114668089456 unit price 149.94 " +
            "totalPrice 333.3333333333333 Date 2018-11-6\n" +
            "ID 32 company GOOGL  numberOfShares 0.3116517229665504 unit price 1069.57" +
            " totalPrice 333.3333333333333 Date 2018-11-6\n" +
            "ID 33 company AMZN  numberOfShares 0.20290437319795554 unit price 1642.81" +
            " totalPrice 333.3333333333333 Date 2018-11-6\n";
    assertEquals(expected, kareem.toString());
  }


  @Test
  public void multipleInvestmentEndDate() {

    InvestorList investorList = InvestorList.getInstance();
    Investor kareem = investorList.addInvestor("Kareem");
    Account saving = kareem.createAccount("Saving");
    PortfolioAdvanced portfolioImpl = saving.createProfolioAdvanced("foo");
    portfolioImpl.setEndDate(new Date("2018-10-08"));
    portfolioImpl.setInvestAmount(1000);
    portfolioImpl.setBuyingComission(5);
    portfolioImpl.addStockSymbol("FB");
    portfolioImpl.addStockSymbol("GOOGL");
    portfolioImpl.addStockSymbol("AMZN");

    portfolioImpl.investPeriodically(new Date("2017-10-10"), 30);

    String expected = "username Kareem\n" +
            "Account Name: Saving\n" +
            "PortfolioImpl Name: foo\n" +
            "ID 1 company FB  numberOfShares 1.942615148512928 unit price" +
            " 171.59 totalPrice 333.3333333333333 Date 2017-10-10\n" +
            "ID 2 company GOOGL  numberOfShares 0.3374502260916515 unit" +
            " price 987.8 totalPrice 333.3333333333333 Date 2017-10-10\n" +
            "ID 3 company AMZN  numberOfShares 0.337655321447866 unit price" +
            " 987.2 totalPrice 333.3333333333333 Date 2017-10-10\n" +
            "ID 4 company FB  numberOfShares 1.8590816136828405 unit price" +
            " 179.3 totalPrice 333.3333333333333 Date 2017-11-9\n" +
            "ID 5 company GOOGL  numberOfShares 0.31815115997912924 unit " +
            "price 1047.72 totalPrice 333.3333333333333 Date 2017-11-9\n" +
            "ID 6 company AMZN  numberOfShares 0.29521253826692523 unit" +
            " price 1129.13 totalPrice 333.3333333333333 Date 2017-11-9\n" +
            "ID 7 company FB  numberOfShares 1.8621973929236497 unit price" +
            " 179.0 totalPrice 333.3333333333333 Date 2017-12-8\n" +
            "ID 8 company GOOGL  numberOfShares 0.317647880970986 unit" +
            " price 1049.38 totalPrice 333.3333333333333 Date 2017-12-8\n" +
            "ID 9 company AMZN  numberOfShares 0.28686173264486514 unit " +
            "price 1162.0 totalPrice 333.3333333333333 Date 2017-12-8\n" +
            "ID 10 company FB  numberOfShares 1.770412860279017 unit price" +
            " 188.28 totalPrice 333.3333333333333 Date 2018-1-8\n" +
            "ID 11 company GOOGL  numberOfShares 0.2991656270661126 unit " +
            "price 1114.21 totalPrice 333.3333333333333 Date 2018-1-8\n" +
            "ID 12 company AMZN  numberOfShares 0.26733607620147515 unit" +
            " price 1246.87 totalPrice 333.3333333333333 Date 2018-1-8\n" +
            "ID 13 company FB  numberOfShares 1.8500018500018498 unit price" +
            " 180.18 totalPrice 333.3333333333333 Date 2018-2-7\n" +
            "ID 14 company GOOGL  numberOfShares 0.3158330253961335 unit " +
            "price 1055.41 totalPrice 333.3333333333333 Date 2018-2-7\n" +
            "ID 15 company AMZN  numberOfShares 0.23527529562340893 unit " +
            "price 1416.78 totalPrice 333.3333333333333 Date 2018-2-7\n" +
            "ID 16 company FB  numberOfShares 1.7995645053896956 unit pr" +
            "ice 185.23 totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 17 company GOOGL  numberOfShares 0.287148386800363 unit " +
            "price 1160.84 totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 18 company AMZN  numberOfShares 0.2111187817601817 unit " +
            "price 1578.89 totalPrice 333.3333333333333 Date 2018-3-9\n" +
            "ID 19 company FB  numberOfShares 2.1204410517387617 unit price " +
            "157.2 totalPrice 333.3333333333333 Date 2018-4-6\n" +
            "ID 20 company GOOGL  numberOfShares 0.33004934237668526 unit " +
            "price 1009.95 totalPrice 333.3333333333333 Date 2018-4-6\n" +
            "ID 21 company AMZN  numberOfShares 0.23720909269894133 unit pr" +
            "ice 1405.23 totalPrice 333.3333333333333 Date 2018-4-6\n" +
            "ID 22 company FB  numberOfShares 1.8630300320441167 unit pric" +
            "e 178.92 totalPrice 333.3333333333333 Date 2018-5-8\n" +
            "ID 23 company GOOGL  numberOfShares 0.31488426428866073 unit p" +
            "rice 1058.59 totalPrice 333.3333333333333 Date 2018-5-8\n" +
            "ID 24 company AMZN  numberOfShares 0.20932895417161204 unit pr" +
            "ice 1592.39 totalPrice 333.3333333333333 Date 2018-5-8\n" +
            "ID 25 company FB  numberOfShares 1.7713536684734472 unit price" +
            " 188.18 totalPrice 333.3333333333333 Date 2018-6-7\n" +
            "ID 26 company GOOGL  numberOfShares 0.2938359102742664 unit p" +
            "rice 1134.42 totalPrice 333.3333333333333 Date 2018-6-7\n" +
            "ID 27 company AMZN  numberOfShares 0.19732038911580732 unit p" +
            "rice 1689.3 totalPrice 333.3333333333333 Date 2018-6-7\n" +
            "ID 28 company FB  numberOfShares 1.6401777952730077 unit pric" +
            "e 203.23 totalPrice 333.3333333333333 Date 2018-7-6\n" +
            "ID 29 company GOOGL  numberOfShares 0.2885803003543766 unit p" +
            "rice 1155.08 totalPrice 333.3333333333333 Date 2018-7-6\n" +
            "ID 30 company AMZN  numberOfShares 0.1948599833589574 unit pr" +
            "ice 1710.63 totalPrice 333.3333333333333 Date 2018-7-6\n" +
            "ID 31 company FB  numberOfShares 1.7951065395731236 unit pric" +
            "e 185.69 totalPrice 333.3333333333333 Date 2018-8-6\n" +
            "ID 32 company GOOGL  numberOfShares 0.2693232714159132 unit p" +
            "rice 1237.67 totalPrice 333.3333333333333 Date 2018-8-6\n" +
            "ID 33 company AMZN  numberOfShares 0.1803995850809543 unit p" +
            "rice 1847.75 totalPrice 333.3333333333333 Date 2018-8-6\n" +
            "ID 34 company FB  numberOfShares 1.9938589145432066 unit pric" +
            "e 167.18 totalPrice 333.3333333333333 Date 2018-9-5\n" +
            "ID 35 company GOOGL  numberOfShares 0.27798626747838656 unit " +
            "price 1199.1 totalPrice 333.3333333333333 Date 2018-9-5\n" +
            "ID 36 company AMZN  numberOfShares 0.1670994542531824 unit pr" +
            "ice 1994.82 totalPrice 333.3333333333333 Date 2018-9-5\n" +
            "ID 37 company FB  numberOfShares 2.1186889552744756 unit pric" +
            "e 157.33 totalPrice 333.33333333333326 Date 2018-10-5\n" +
            "ID 38 company GOOGL  numberOfShares 0.28542967155607696 unit " +
            "price 1167.83 totalPrice 333.3333333333333 Date 2018-10-5\n" +
            "ID 39 company AMZN  numberOfShares 0.17639950960936326 unit pr" +
            "ice 1889.65 totalPrice 333.3333333333333 Date 2018-10-5\n";
    assertEquals(expected, kareem.toString());
  }
}
