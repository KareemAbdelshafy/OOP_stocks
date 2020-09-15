import java.io.BufferedReader;
import java.io.InputStreamReader;

import controller.InvestmentController;
import graphic.investor.Controller;
import graphic.investor.IView;
import graphic.investor.JFrameView;
import user.InvestorList;

/**
 * This is the main class of the program.
 */
public class MainClass {

  /**
   * This is the main method of the program.
   * @param args - These are command line arguments which is a string array.
   */
  public static void main(String[] args) {
    // InvestorList model = InvestorList.getInstance();




      InvestorList model = InvestorList.getInstance();
      Controller controller = new Controller(model);
      IView view = new JFrameView();
      controller.setView(view);


  }

}
