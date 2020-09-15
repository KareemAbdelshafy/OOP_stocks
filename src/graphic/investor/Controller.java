package graphic.investor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JOptionPane;
import javax.swing.JFrame;
import graphic.account.ControllerAccount;
import graphic.account.IViewAccount;
import graphic.account.JFrameViewAccount;
import user.Investor;
import user.InvestorList;


/**
 * This is the main UI controller of the application.
 * This class implements ActionListener which listens to the clicks.
 */
public class Controller implements ActionListener {
  private InvestorList model;
  private IView view;
  private List<String> investors;

  /**
   * This is the only constructor of this class.
   * It takes InvestorList as argument which is a singleton class.
   * @param m InvestorList as parameter.
   */
  public Controller(InvestorList m) {
    model = m;
  }

  /**
   * This is setter method of the View.
   * It has to be called if you want to run the Graphical user interface.
   * @param view View as IView object.
   */
  public void setView(IView view) {
    this.view = view;
    view.setListener(this);
    view.sendData(model.allInvestors());
    view.display();
    loadInvestors();
  }


  private void loadInvestors() {
    investors = new ArrayList<>();
    File file = new File("res/");
    String[] directories = file.list(new FilenameFilter() {
      @Override
      public boolean accept(File current, String name) {
        return new File(current, name).isDirectory();
      }
    });

    if (directories.length > 0) {
      for (String a: directories) {
        investors.add(a.trim());
      }
    }
    view.sendData(investors);
    view.refreshState();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      //read from the input textfield
      case "Add Investor":
        String text = view.getInputString();

        for (String s: investors) {
          model.addInvestor(s);
        }

        model.addInvestor(text);
        JOptionPane.showMessageDialog(new JFrame(), "Investor Added!", "Dialog",
                JOptionPane.INFORMATION_MESSAGE);
        view.sendData(model.allInvestors());
        view.refreshState();
        view.display();
        break;

      case "Exit Button":
        System.exit(0);
        break;

      case "Refresh":
        view.refreshState();
        break;

      case "Select":
        String selectedInvestor = (String) view.selectedListItem();

        try {
          Investor investor = model.getInvestor(selectedInvestor);
          if (investor != null) {
            view.getFrame().getFrame().dispose();
            ControllerAccount controller = new ControllerAccount(investor);
            IViewAccount view = new JFrameViewAccount();
            controller.setView(view);
          }

          else {
            model.addInvestor(selectedInvestor);
            Investor investorNew = model.getInvestor(selectedInvestor);
            view.getFrame().getFrame().dispose();
            ControllerAccount controller = new ControllerAccount(investorNew);
            IViewAccount view = new JFrameViewAccount();
            controller.setView(view);
          }
        }
        catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Investor not found", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
        break;

      default:
        break;
    }
  }
}
