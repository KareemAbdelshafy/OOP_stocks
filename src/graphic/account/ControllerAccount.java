package graphic.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JOptionPane;
import javax.swing.JFrame;

import graphic.portfolio.ControllerPortfolio;
import graphic.portfolio.IViewPortfolio;
import graphic.portfolio.JFrameViewPortfolio;
import user.Account;
import user.Investor;

/**
 * This is the main UI controller of the for Account page.
 * This class implements ActionListener which listens to the clicks.
 */
public class ControllerAccount implements ActionListener {
  private Investor model;
  private IViewAccount view;

  /**
   * This is the only constructor of this class.
   * It takes Investor as argument.
   * @param m Investor as parameter.
   */
  public ControllerAccount(Investor m) {
    model = m;
  }

  /**
   * This is setter method of the View.
   * It has to be called if you want to run the Graphical user interface.
   * @param view View as IViewAccount object.
   */
  public void setView(IViewAccount view) {
    this.view = view;
    view.setListener(this);
    view.sendData(model.getAllAccounts());
    view.display();
    loadAccounts();
  }


  private void loadAccounts() {
    List<Account> accounts = new ArrayList<>();
    File file = new File("res/" + model.getUsername());
    String[] directories = file.list(new FilenameFilter() {
      @Override
      public boolean accept(File current, String name) {
        return new File(current, name).isDirectory();
      }
    });

    if (directories.length > 0) {
      for (String a: directories) {
        model.createAccount(a.trim());
      }
    }

    accounts.addAll(model.getAllAccounts());
    view.sendData(accounts);
    view.refreshState();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {

      case "Add Account":
        String text = view.getInputString();


        model.createAccount(text);
        JOptionPane.showMessageDialog(new JFrame(), "Account Created!", "Dialog",
                JOptionPane.INFORMATION_MESSAGE);
        view.sendData(model.getAllAccounts());
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
          String investor = selectedInvestor.replace("Account Name: ",
                  "").trim();
          Account account = model.getAccount(investor);
          if (account != null) {
            view.getFrame().getFrame().dispose();
            ControllerPortfolio controller = new ControllerPortfolio(account);
            IViewPortfolio view = new JFrameViewPortfolio();
            controller.setView(view);
          }
          else {
            throw new NoSuchFieldException("Not found!");
          }
        }
        catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Account not found", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
        break;
      default:
        break;
    }
  }
}
