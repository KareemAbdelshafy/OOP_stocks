package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The class takes the company name as a string. It searches the res folder for the given company
 * name. Then it returns all stock prices in all times as a string as provided in this file.
 */
class GetStockDataFile implements GetStockData {

  private FileReader fr;
  private String stockSymbol;

  /**
   * The constructor take the company name. It searched for this name inside res folder.
   *
   * @param stockSymbol is the file name which must be similar to company stockSymbol.
   * @throws IOException if it can not find the file.
   */
  GetStockDataFile(String stockSymbol) throws IOException {
    String defaultDirectory = "res/";
    File file = new File(defaultDirectory + stockSymbol);
    fr = new FileReader(file);
    this.stockSymbol = stockSymbol;
  }

  @Override
  public String getData() throws IOException {

    StringBuilder sb = new StringBuilder("");

    int i;
    while ((i = fr.read()) != -1) {
      sb.append((char) i);
    }

    return sb.toString();
  }

  /*
   * This method returns the stock ticker for the company.
   * @return - A string which is stock ticker for the company.
   */
  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }


}
