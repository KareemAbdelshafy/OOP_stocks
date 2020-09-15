package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class gets data from the url for a stock Symbol which is an sompany ticker.
 * It first fetches data from a file in res directory.
 * File name is same as Ticker symbol. e.g FB for facebook.
 * If the file does not exist then it fethces the file from the internet and saves it
 * then gets the data from it.
 */
public class GetStockDataController implements GetStockData {

  private String stockSymbol;
  private String defaultDirectory;

  /**
   * This is the only constructor for this class.
   * It takes a string which is the ticker for the company.
   * @param stockSymbol - This is a ticker symbol for the company.
   */
  public GetStockDataController(String stockSymbol) {
    this.stockSymbol = stockSymbol;
    defaultDirectory = "res/";
  }

  /**
   * This method sets the default directory for the files to be saved.
   * @param defaultDirectory - String for the path of default directory.
   */
  public void setDefaultDirectory(String defaultDirectory) {
    this.defaultDirectory = defaultDirectory;
  }

  /**
   * This method gets the default directory of the saved files.
   * @return - String which is the path of the default directory.
   */
  public String getDefaultDirectory() {
    return defaultDirectory;
  }


  @Override
  public String getData() throws IOException {

    String stockData = "";

    try {
      GetStockData getStockData = new GetStockDataFile(stockSymbol);
      stockData = getStockData.getData();
    } catch (Exception e) {
      System.out.println("No data found in cache\n"
              + "Getting data from the internet." + "\nPlease wait...........");
      stockData = getDataURL();
    }

    return stockData;
  }

  /*
   * This method returns the stock ticker for the company.
   * @return - A string which is stock ticker for the company.
   */
  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }


  private String getDataURL() {
    String stockData = "";
    try {
      GetStockData getStockData = new GetStockDataURL(stockSymbol);
      stockData = getStockData.getData();
      saveStockData(stockData);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new IllegalArgumentException(e.getMessage());
    }
    return stockData;
  }



  private void saveStockData(String stockData) {
    try {
      saveFile(stockSymbol, stockData);
    } catch (Exception e) {
      System.out.println("Could not save the data to file");
    }
  }


  /**
   * Save file in res folderr.
   *
   * @param companyName is the company name.
   * @throws FileNotFoundException if it can not save the folder.
   */
  private void saveFile(String companyName, String data) throws FileNotFoundException {
    PrintWriter pw = new PrintWriter(new File(defaultDirectory + companyName));
    pw.write(data);
    pw.close();
  }

}
