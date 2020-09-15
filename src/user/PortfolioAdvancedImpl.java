package user;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.LinkedList;
import java.util.List;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import helper.Date;
import stocks.SharePrice;
import stocks.SharePriceFile;

public class PortfolioAdvancedImpl implements PortfolioAdvanced {

  private Date startDate;
  private Date endDate;
  private int periodicity;
  private double buyingComission;
  private double sellingComission;
  private String portfolioName;
  private List<StockShareComission> stockes;
  private List<String> stockSymbols;
  private double investAmount;
  private List<Double> ratios;
  private Account account;


  /**
   * A protfolio Constructor who can be called from inside an account object only.
   *
   * @param portfoliName is the portfolio name
   */
  PortfolioAdvancedImpl(String portfoliName, Account account) {
    this.portfolioName = portfoliName;
    stockes = new LinkedList<>();
    stockSymbols = new LinkedList<>();
    ratios = new LinkedList<>();
    buyingComission = 0;
    sellingComission = 0;
    periodicity = 0;
    endDate = null;
    startDate = null;
    this.account = account;
  }


  /**
   * This is the only method that can create a Stock Share object. It create it and add it to the
   * list of stockShares
   *
   * @param sharePrice     : is an object that represent the price of one Share. It contains also
   *                       the date of the price and company.
   * @param numberOfShares is the number of shares that the investor want to buy.
   */
  public void addStockShare(SharePrice sharePrice, double numberOfShares) {
    StockShareComission stockShare = new StockShareCommisionImp(sharePrice, numberOfShares);
    stockShare.setBuyingComission(buyingComission);
    stockes.add(stockShare);
  }

  @Override
  public double getTotalCost() {
    double totalCost = 0;

    for (StockShareComission stocke : stockes) {
      totalCost = totalCost + stocke.getShareCost();
    }

    return totalCost;
  }

  @Override
  public List<StockShare> getAllStocks() {
    return null;
  }


  @Override
  public void addStockSymbol(String stockSymbol) {
    if (stockSymbols.contains(stockSymbol)) {
      return;
    }

    stockSymbols.add(stockSymbol);
    ratios = new LinkedList<>();
    for (String s : stockSymbols) {
      ratios.add(1.0 / stockSymbols.size());
    }
  }


  @Override
  public boolean saveStrategy(String dir) {
    return saving(dir + "/" + portfolioName + "_str.txt", strategytoString());
  }

  private boolean saving(String dir, String data) {
    try {
      PrintWriter out = new PrintWriter(dir);
      out.print(data);
      out.close();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public List<String> getstockSymbols() {
    return stockSymbols;
  }

  @Override
  public void loadPortfolio(String dir) {
    investByStrategy(dir);
  }

  private boolean loadStrategy(String dir) {


    File file = new File(dir);

    String line = "";
    try {
      Scanner sc = new Scanner(file);
      sc.nextLine();

      line = sc.nextLine();
      Scanner sc2 = new Scanner(line);
      sc2.next();
      while (sc2.hasNext()) {
        stockSymbols.add(sc2.next());
      }

      line = sc.nextLine();
      String[] pieces = line.split("\\s+");

      for (int ii = 1; ii < pieces.length; ii++) {
        ratios.add(Double.parseDouble(pieces[ii]));
      }
      investAmount = Double.parseDouble(sc.nextLine().split("\\s+")[1]);
      sellingComission = Double.parseDouble(sc.nextLine().split("\\s+")[1]);

      buyingComission = Double.parseDouble(sc.nextLine().split("\\s+")[1]);

      line = sc.nextLine();
      line = line.split("\\s+")[1];

      if (line.length() != 0 && !line.contains("null")) {
        startDate = new Date(line);
      }
      line = sc.nextLine();
      line = line.split("\\s+")[1];

      if (line.length() != 0 && !line.contains("null")) {
        endDate = new Date(line);
      }

      line = sc.nextLine();
      line = line.split("\\s+")[1];

      periodicity = Integer.parseInt(line);

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public void investByStrategy(String dir) {
    loadStrategy(dir);
    if (periodicity == 0) {
      invest(startDate);
    } else {
      investPeriodically(startDate, periodicity);
    }

  }

  @Override
  public boolean savePortfolio(String dir) {
    String data = strategytoString();
    data = data + "\n";
    data = data + this.toString();
    return saving(dir + "/" + portfolioName + "_prt.txt", data);
  }


  @Override
  public void invest(Date date) throws IllegalArgumentException {

    startDate = date;

    if (ratios == null) {
      int size = stockSymbols.size();
      for (String symbol : stockSymbols) {
        ratios.add(1.0 / size);
      }
    } else {
      checkratios();
    }

    for (int i = 0; i < stockSymbols.size(); i++) {
      SharePrice sharePrice;
      try {

        if (date.isWorkDay()) {
          sharePrice = new SharePriceFile(stockSymbols.get(i), date);
        } else {
          if (date.getCalendar().get(Calendar.DAY_OF_WEEK) == 7) {

            DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = new java.util.Date(date.timeInMilliSeconds() - 86400000);

            sharePrice = new SharePriceFile(stockSymbols.get(i),
                    new Date(simple.format(utilDate)));
          } else {
            DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = new java.util.Date(date.timeInMilliSeconds() - 172800000);

            sharePrice = new SharePriceFile(stockSymbols.get(i),
                    new Date(simple.format(utilDate)));
          }
        }
        addStockShare(sharePrice,
                investAmount * ratios.get(i) / sharePrice.getPrice());

      } catch (Exception e) {
        System.out.print(e.getMessage());
        //sharePrice = new SharePriceConsol(stockSymbols.get(i), date);
      }

    }
  }

  @Override
  public void investPeriodically(Date date, int periodicity) throws IllegalArgumentException {

    this.periodicity = periodicity;
    this.startDate = date;

    long periodicityMills = TimeUnit.DAYS.toMillis(this.periodicity);

    long startDateMills = startDate.timeInMilliSeconds();

    if (endDate != null) {
      long endDateMills = endDate.timeInMilliSeconds();

      for (long i = startDateMills; i <= endDateMills; i = i + periodicityMills) {
        DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date(i);
        invest(new Date(simple.format(utilDate)));
      }
    } else {
      long endDateMills = System.currentTimeMillis();
      for (long i = startDateMills; i <= endDateMills; i = i + periodicityMills) {
        DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date(i);
        invest(new Date(simple.format(utilDate)));
      }
    }

  }


  private void checkratios() {
    int shareSize = stockSymbols.size();
    int ratioSize = ratios.size();
    double sum = ratios.stream().mapToDouble(Double::doubleValue).sum();
    if (shareSize != ratioSize) {
      throw new IllegalArgumentException("share size is not equal to ratio size");
    }
    double tol = 0.001;
    if ((sum + tol) < 1 || (sum - tol) > 1) {
      throw new IllegalArgumentException("sum of ratios is not 1" + ratios);
    }
  }

  @Override
  public Date getStartDate() {
    return startDate;
  }

  @Override
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  @Override
  public Date getEndDate() {
    return endDate;
  }

  @Override
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Override
  public int getPeriodicity() {
    return periodicity;
  }

  @Override
  public void setPeriodicity(int periodicity) {
    this.periodicity = periodicity;
  }

  @Override
  public double getBuyingComission() {
    return buyingComission;
  }

  @Override
  public void setBuyingComission(double buyingComission) {
    this.buyingComission = buyingComission;
  }

  @Override
  public double getSellingComission() {
    return sellingComission;
  }

  @Override
  public void setSellingComission(double sellingComission) {
    this.sellingComission = sellingComission;
  }


  @Override
  public String getPortfolioName() {
    return portfolioName;
  }

  @Override
  public double calculatePrice(Date date) {
    double price = 0;
    for (StockShareComission share : stockes) {
      share.setSellingComission(sellingComission);
      price = price + share.calculatePrice(date);
    }
    return price;
  }

  @Override
  public StockShare getStockShare(int stockShareID) {
    for (StockShare s : stockes) {
      if (s.getID() == stockShareID) {
        return s;
      }
    }
    throw new IllegalArgumentException("Invalid Stock Share ID");
  }

  public double getInvestAmount() {
    return investAmount;
  }

  public void setInvestAmount(double investAmount) {
    this.investAmount = investAmount;
  }

  public List<Double> getRatios() {
    return ratios;
  }

  public void setRatios(List<Double> ratios) {
    this.ratios = ratios;
  }


  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PortfolioImpl Name: ").append(portfolioName).append("\n");
    for (StockShare stockShare : stockes) {
      stringBuilder.append(stockShare.toString()).append("\n");
    }
    return stringBuilder.toString();
  }


  private String strategytoString() {
    StringBuilder s = new StringBuilder("");
    s.append("Name ").append(portfolioName);
    s.append("\n");
    s.append("tickerSymbols ");
    for (String stock : stockSymbols) {
      s.append(stock).append(" ");
    }
    s.append("\n");
    s.append("InvestmentRatios ");

    for (double ratio : ratios) {
      s.append(ratio).append(" ");
    }
    s.append("\n");
    s.append("investAmount ");

    s.append(investAmount);
    s.append("\n");
    s.append("sellingComission ");

    s.append(sellingComission);
    s.append("\n");
    s.append("buyingComission ");

    s.append(buyingComission);
    s.append("\n");
    s.append("startDate ");

    s.append(startDate);
    s.append("\n");
    s.append("endDate ");

    s.append(endDate);
    s.append("\n");
    s.append("InvestmentPeriodicity ");

    s.append(periodicity);
    return s.toString();
  }

  public Account getAccount() {
    return account;
  }

  @Override
  public String getName() {
    return portfolioName;
  }

}
