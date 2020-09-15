package graphic.portfolio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import graphic.stocks.ControllerStock;
import graphic.stocks.IViewStock;
import graphic.stocks.JFrameViewStock;
import user.Account;
import user.PortfolioAdvanced;


/**
 * This is the controller of the Portfolio.
 * This class implements ActionListener which listens to the clicks.
 */
public class ControllerPortfolio implements ActionListener {
  private Account model;
  private IViewPortfolio view;
  private IViewPortfolio allView;
  private List<String> portfolioAdvancedList;
  private StringBuilder fileNames;

  /**
   * This is the only constructor of this class.
   * It takes Account as argument.
   * @param m Account as parameter.
   */
  public ControllerPortfolio(Account m) {
    model = m;
  }

  /**
   * This is setter method of the View.
   * It has to be called if you want to run the Graphical user interface.
   * @param view View as IViewPortfolio object.
   */
  public void setView(IViewPortfolio view) {
    this.view = view;
    view.setListener(this);
    portfolioAdvancedList = new ArrayList<>();

    for (PortfolioAdvanced portfolioAdvanced: model.getAllPortfolioAdvanced()) {
      portfolioAdvancedList.add(portfolioAdvanced.getName());
    }

    view.sendData(portfolioAdvancedList);
    view.display();
    loadPortfolio();
  }


  private void loadPortfolio() {


    File file = new File(model.getCurrentDir().trim());
    File [] files = file.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith("_prt.txt");
      }
    });

    for (File f: files) {
      PortfolioAdvanced portfolioAdvanced = model.loadPortfolio(  model.getCurrentDir()
              + "/" + f.getName());
      portfolioAdvancedList.add(portfolioAdvanced.getName());
    }

    view.sendData(portfolioAdvancedList);
    view.refreshState();

  }

  private void loadStragy() {

    fileNames = new StringBuilder();

    File file = new File(model.getCurrentDir().trim());
    File [] files = file.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith("_str.txt");
      }
    });

    for (File file1: files) {
      String str = file1.getName().replace("_str.txt","");
      System.out.println( "file ane" + str);
      fileNames.append(str).append("\n");
    }
  }



  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {

      case "Add":
        String text = view.getInputString();
        model.createProfolioAdvanced(text);
        JOptionPane.showMessageDialog(new JFrame(), "Portfolio Added!", "Dialog",
                JOptionPane.INFORMATION_MESSAGE);
        portfolioAdvancedList.add(text);
        view.sendData(portfolioAdvancedList);
        view.refreshState();
        view.display();
        break;

      case "Exit Button":
        System.exit(0);
        break;

      case "Exit All":
        allView.getFrame().dispose();
        break;

      case "Strategy":
        loadStragy();
        allView = new JFrameAllStrategies(fileNames.toString());
        allView.setListener(this);
        allView.display();
        break;

      case "Invest":
        String name = JOptionPane.showInputDialog(new JFrame(),
                "Enter the strategy name");
        String portfolioName = JOptionPane.showInputDialog(new JFrame(),
                "Enter the Portfolio name");

        System.out.println(model.getCurrentDir().trim()
                + name + "_str.txt");

        PortfolioAdvanced portfolio =
                model.addPortfolioByStrategy(model.getCurrentDir().trim() + "/"
                        + name + "_str.txt", portfolioName);




        if (portfolio != null) {
          view.getFrame().dispose();
          ControllerStock controller = new ControllerStock(portfolio);
          IViewStock view = new JFrameViewStock();
          controller.setView(view);
        }

        break;

      case "Select":
        String selectedPortfolio = (String) view.selectedListItem();

        try {
          String portfolioAdvacnedString = selectedPortfolio.replace("PortfolioImpl Name:",
                  "").trim();
          PortfolioAdvanced portfolioAdvanced = model.getPortfolioAdvanced(portfolioAdvacnedString);
          if (portfolioAdvanced != null) {
            view.getFrame().dispose();
            ControllerStock controller = new ControllerStock(portfolioAdvanced);
            IViewStock view = new JFrameViewStock();
            controller.setView(view);
          }
          else {
            throw new NoSuchFieldException("Not found!");
          }
        }
        catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Portfolio not found", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
        break;

      default:
        break;
    }
  }
}
