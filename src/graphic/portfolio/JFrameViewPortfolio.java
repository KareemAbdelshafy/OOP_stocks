package graphic.portfolio;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import javax.swing.JScrollPane;
import javax.swing.JList;

import javax.swing.DefaultListModel;



/**
 * This class is used to see all the portfolio.
 * It lists all the portfolios that have been saved to to account directory.
 * It extends JFrame amd implements IViewPortfolio.
 */
public class JFrameViewPortfolio extends JFrame implements IViewPortfolio {

  private JLabel display;
  private JButton addInvestorButton;
  private JButton exitButton;
  private JButton  selectButton;
  private JButton strategyButton;
  private JButton  investStategy;
  private JTextField input;

  private  List<String> portfolioAdvancedList;
  private JList list;
  private DefaultListModel<String> model;

  /**
   * This is the only constructor of this class.
   * This set default window width to 1000 and hieght to 1000.
   * You cannot resize the window.
   */
  public JFrameViewPortfolio() {

    setSize(1000, 1000);
    setLocation(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setMinimumSize(new Dimension(500,500));


    this.setLayout(new FlowLayout());

    JSplitPane splitPane = new JSplitPane();

    JPanel topPanel = new JPanel();         // our top component
    JPanel bottomPanel = new JPanel();

    display = new JLabel("All Portfolios: ");


    //the textfield
    input = new JTextField(10);

    getContentPane().setLayout(new GridLayout());
    getContentPane().add(splitPane);

    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPane.setDividerLocation(200);
    splitPane.setTopComponent(topPanel);
    splitPane.setBottomComponent(bottomPanel);



    bottomPanel.add(input);

    //echobutton
    addInvestorButton = new JButton("Add Portfolio");
    addInvestorButton.setActionCommand("Add");
    bottomPanel.add(addInvestorButton);

    list = new JList();
    model = new DefaultListModel<>();

    JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setPreferredSize(new Dimension(500,200));
    topPanel.add(scrollPane);


    topPanel.add(display);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    bottomPanel.add(exitButton);
    selectButton = new JButton("Select Portfolio");
    selectButton.setActionCommand("Select");
    bottomPanel.add(selectButton);

    strategyButton = new JButton("See all Strategies");
    strategyButton.setActionCommand("Strategy");
    bottomPanel.add(strategyButton);

    investStategy = new JButton("Invest By Strategies");
    investStategy.setActionCommand("Invest");
    bottomPanel.add(investStategy);

    this.add(splitPane);
    pack();
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void sendData(List<String> data) {
    this.portfolioAdvancedList = data;
  }

  @Override
  public void refreshState() {
    model.removeAllElements();
    Object[] ar = portfolioAdvancedList.toArray();

    for (int i = 0; i < ar.length; i++) {
      model.addElement(ar[i].toString());
    }

    list.setModel(model);
  }

  @Override
  public Object selectedListItem() {
    return list.getSelectedValue();
  }

  @Override
  public JFrameViewPortfolio getFrame() {
    return this;
  }


  @Override
  public void setListener(ActionListener listener) {
    addInvestorButton.addActionListener(listener);
    exitButton.addActionListener(listener);
    selectButton.addActionListener(listener);
    strategyButton.addActionListener(listener);
    investStategy.addActionListener(listener);
  }

  @Override
  public void setEchoOutput(String s) {
    display.setText(s);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }
}
