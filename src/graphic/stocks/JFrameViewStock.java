package graphic.stocks;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import javax.swing.JScrollPane;
import javax.swing.JList;

import javax.swing.DefaultListModel;


/**
 * This class is used to see all the Stocks in portfolio.
 * It extends JFrame amd implements IViewStock.
 */
public class JFrameViewStock extends JFrame implements IViewStock {

  private JLabel display;
  private JButton addStockButton;
  private JButton exitButton;
  private JButton  investButton;
  private JButton  allStocksButton;



  private JList list;
  private DefaultListModel<String> model;

  private List<String> companyTickers;

  /**
   * This is the only constructor of this class.
   * This set default window width to 1000 and hieght to 1000.
   * You cannot resize the window.
   */
  public JFrameViewStock() {

    setSize(1000, 1000);
    setLocation(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setMinimumSize(new Dimension(500,500));


    this.setLayout(new FlowLayout());

    JSplitPane splitPane = new JSplitPane();

    JPanel topPanel = new JPanel();         // our top component
    JPanel bottomPanel = new JPanel();

    display = new JLabel("All Tickers Added: ");



    getContentPane().setLayout(new GridLayout());
    getContentPane().add(splitPane);

    splitPane.setDividerLocation(200);
    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPane.setTopComponent(topPanel);
    splitPane.setBottomComponent(bottomPanel);


    //echobutton
    addStockButton = new JButton("Add");
    addStockButton.setActionCommand("Add");
    bottomPanel.add(addStockButton);

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
    investButton = new JButton("Invest ");
    investButton.setActionCommand("Invest");
    bottomPanel.add(investButton);
    allStocksButton = new JButton("All Investments");
    allStocksButton.setActionCommand("All");
    bottomPanel.add(allStocksButton);

    this.add(splitPane);
    pack();
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void sendData(List<String> data) {
    this.companyTickers = data;
  }

  @Override
  public void refreshState() {
    model.removeAllElements();
    Object[] ar = companyTickers.toArray();

    for (int i = 0; i < ar.length; i++) {
      model.addElement(ar[i].toString());
    }

    list.setModel(model);
  }

  @Override
  public Object selectedListItem() {
    return null;
  }

  @Override
  public JFrameViewStock getFrame() {
    return this;
  }


  @Override
  public void setListener(ActionListener listener) {
    addStockButton.addActionListener(listener);
    exitButton.addActionListener(listener);
    investButton.addActionListener(listener);
    allStocksButton.addActionListener(listener);
  }

  @Override
  public void setEchoOutput(String s) {
    display.setText(s);
  }

  @Override
  public String getInputString() {
    return null;
  }

  @Override
  public void clearInputString() {
    //Empty
  }
}
