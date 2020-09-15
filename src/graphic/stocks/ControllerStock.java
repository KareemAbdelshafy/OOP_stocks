package graphic.stocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;
import java.util.List;

import helper.Date;
import user.PortfolioAdvanced;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * This is the controller of the Stocks.
 * This class implements ActionListener which listens to the clicks.
 */
public class ControllerStock implements ActionListener {
  private PortfolioAdvanced model;
  private IViewStock view;
  private IViewStock allView;

  /**
   * This is the only constructor of this class.
   * It takes PortfolioAdvanced as argument.
   * This PortfolioAdvanced is a model.
   * @param m PortfolioAdvanced as parameter.
   */
  public ControllerStock(PortfolioAdvanced m) {
    model = m;
  }

  /**
   * This is setter method of the View.
   * It has to be called if you want to run the Graphical user interface.
   * @param view View as IViewStock object.
   */
  public void setView(IViewStock view) {
    this.view = view;
    view.setListener(this);
    view.display();
    view.sendData(model.getstockSymbols());
    view.refreshState();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {

      case "Add":

        try {

          int companies = Integer.parseInt( JOptionPane.showInputDialog(new JFrame(),
                  "Enter number of companies you want to add",
                  "Companies", JOptionPane.QUESTION_MESSAGE));

          for (int i = 0; i < companies; i++) {
            String ticker =  JOptionPane.showInputDialog(new JFrame(),
                    "Enter company Ticker",
                    "Add company", JOptionPane.PLAIN_MESSAGE);

            String str = ticker.trim();
            model.addStockSymbol(str);
          }
          view.sendData(model.getstockSymbols());
          view.refreshState();
        }

        catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Enter Valid Number of companies",
                  "Invalid!", JOptionPane.ERROR_MESSAGE);
        }

        catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Something Went wrong",
                  "Error", JOptionPane.ERROR_MESSAGE);
        }

        view.refreshState();
        view.display();
        break;

      case "Exit Button":
        System.exit(0);
        break;

      case "Exit All":
        allView.getFrame().dispose();
        break;

      case "All":
        allView = new JFrameAllStocks(model.toString());
        allView.setListener(this);
        allView.display();
        break;

      case "Invest":

        if (model.getstockSymbols().size() != 0) {

          try {
            int value = JOptionPane.showConfirmDialog(null,
                    "Do you want periodic investment?", "Investment",
                    JOptionPane.YES_NO_OPTION);

            if (value == JOptionPane.YES_OPTION) {

              int period = Integer.parseInt( JOptionPane.showInputDialog(new JFrame(),
                      "Enter Period for investment in days.",
                      "Commission", JOptionPane.QUESTION_MESSAGE));

              int commission = Integer.parseInt( JOptionPane.showInputDialog(new JFrame(),
                      "Enter commission",
                      "Commission", JOptionPane.QUESTION_MESSAGE));

              if (commission < 0) {
                throw new IllegalStateException("Not neagtive allowed");
              }


              int investment = Integer.parseInt( JOptionPane.showInputDialog(new JFrame(),
                      "Enter Investment Amount",
                      "Investment", JOptionPane.QUESTION_MESSAGE));

              if (investment < 0) {
                throw new IllegalStateException("Not neagtive allowed");
              }


              int ratios = JOptionPane.showConfirmDialog(new JFrame(),
                      "Do you want to set ratios to investment?", "Ratios",
                      JOptionPane.YES_NO_OPTION);

              if (ratios == JOptionPane.YES_OPTION) {
                //ratios
                List<Double> ratiosList = new LinkedList<>();
                int i = 0;
                while (i < model.getstockSymbols().size()) {
                  double ratioDouble = Double.parseDouble( JOptionPane.showInputDialog(new JFrame(),
                          "Enter Ratio for " + model.getstockSymbols().get(i) ,
                          "ratio", JOptionPane.QUESTION_MESSAGE));
                  i++;
                }

                String date = JOptionPane.showInputDialog(new JFrame(),
                        "Please enter date for investment in  format YYYY-MM-DD",
                        "Ratios",
                        JOptionPane.YES_NO_OPTION);

                int endDate = JOptionPane.showConfirmDialog(null,
                        "Do you want periodic investment?", "Investment",
                        JOptionPane.YES_NO_OPTION);

                if (endDate == JOptionPane.YES_OPTION) {
                  //End date yes

                  String endDateString = JOptionPane.showInputDialog(new JFrame(),
                          "Please enter end date of investment in  format YYYY-MM-DD",
                          "Ratios",
                          JOptionPane.YES_NO_OPTION);
                  model.setEndDate(new Date(endDateString));
                }

                model.setStartDate(new Date(date));
                model.setRatios(ratiosList);
                model.setBuyingComission(commission);
                model.setInvestAmount(investment);
                model.investPeriodically(new Date(date), period);
                JOptionPane.showMessageDialog(new JFrame(), "Predioc investment successful",
                        "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);

                int saveStrategy = JOptionPane.showConfirmDialog(new JFrame(),
                        "Do you want to save the strategy", "Save",
                        JOptionPane.YES_NO_OPTION);

                if (saveStrategy == JOptionPane.YES_NO_OPTION) {
                  model.saveStrategy(model.getAccount().getCurrentDir());
                }

              }

              else {
                String date = JOptionPane.showInputDialog(new JFrame(),
                        "Please enter date for investment  in format YYYY-MM-DD",
                        "Ratios",
                        JOptionPane.YES_NO_OPTION);

                model.setStartDate(new Date(date));
                model.setBuyingComission(commission);
                model.setInvestAmount(investment);
                model.investPeriodically(new Date(date), period);
                JOptionPane.showMessageDialog(new JFrame(), "Predioc investment successful",
                        "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);

                int saveStrategy = JOptionPane.showConfirmDialog(new JFrame(),
                        "Do you want to save the strategy", "Save",
                        JOptionPane.YES_NO_OPTION);

                if (saveStrategy == JOptionPane.YES_NO_OPTION) {
                  model.saveStrategy(model.getAccount().getCurrentDir());
                }
              }



            }

            else {
              int commission = Integer.parseInt( JOptionPane.showInputDialog(new JFrame(),
                      "Enter commission",
                      "Commission", JOptionPane.QUESTION_MESSAGE));

              if (commission < 0) {
                throw new IllegalStateException("Not neagtive allowed");
              }

              int investment = Integer.parseInt( JOptionPane.showInputDialog(new JFrame(),
                      "Enter Investment Amount",
                      "Investment", JOptionPane.QUESTION_MESSAGE));

              if (investment < 0) {
                throw new IllegalStateException("Not neagtive allowed");
              }

              int ratios = JOptionPane.showConfirmDialog(new JFrame(),
                      "Do you want to set ratios to investment?", "Ratios",
                      JOptionPane.YES_NO_OPTION);

              if (ratios == JOptionPane.YES_OPTION) {
                //ratios
                List<Double> ratiosList = new LinkedList<>();
                int i = 0;
                while (i < model.getstockSymbols().size()) {
                  double ratioDouble = Double.parseDouble( JOptionPane.showInputDialog(new JFrame(),
                          "Enter Ratio for " + model.getstockSymbols().get(i) ,
                          "ratio", JOptionPane.QUESTION_MESSAGE));
                  i++;
                }

                String date = JOptionPane.showInputDialog(new JFrame(),
                        "Please enter date for investment in format YYYY-MM-DD",
                        "Ratios",
                        JOptionPane.YES_NO_OPTION);

                model.setStartDate(new Date(date));
                model.setRatios(ratiosList);
                model.setBuyingComission(commission);
                model.setInvestAmount(investment);
                model.invest(new Date(date));
                JOptionPane.showMessageDialog(new JFrame(), "Single investment successful",
                        "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);

                int saveStrategy = JOptionPane.showConfirmDialog(new JFrame(),
                        "Do you want to save the strategy", "Save",
                        JOptionPane.YES_NO_OPTION);

                if (saveStrategy == JOptionPane.YES_NO_OPTION) {
                  model.saveStrategy(model.getAccount().getCurrentDir());
                }

              }

              else {
                String date = JOptionPane.showInputDialog(new JFrame(),
                        "Please enter date for investment in format YYYY-MM-DD",
                        "Ratios",
                        JOptionPane.YES_NO_OPTION);

                model.setStartDate(new Date(date));
                model.setBuyingComission(commission);
                model.setInvestAmount(investment);
                model.invest(new Date(date));
                JOptionPane.showMessageDialog(new JFrame(), "Single investment successful",
                        "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(model.toString());

                int saveStrategy = JOptionPane.showConfirmDialog(new JFrame(),
                        "Do you want to save the strategy", "Save",
                        JOptionPane.YES_NO_OPTION);

                if (saveStrategy == JOptionPane.YES_NO_OPTION) {
                  model.saveStrategy(model.getAccount().getCurrentDir());
                }
              }

            }
          }
          catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Enter Valid Number of commission",
                    "Invalid!", JOptionPane.ERROR_MESSAGE);
          }

          catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Ratios don't sum upto 1.",
                    "Invalid!", JOptionPane.ERROR_MESSAGE);
          }

          catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Commssion and investment " +
                            "cannot be negative",
                    "Invalid!", JOptionPane.ERROR_MESSAGE);
          }

          catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Something Went wrong",
                    "Error", JOptionPane.ERROR_MESSAGE);

          }

          model.savePortfolio(model.getAccount().getCurrentDir());
        }

        else {
          JOptionPane.showMessageDialog(new JFrame(), "No Company Ticker added!");
        }
        break;

      default:
        break;
    }
  }
}
