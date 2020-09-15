package graphic.stocks;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;


import javax.swing.JPanel;


import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * This class is used to see all the stocks in a new window.
 * It extends JFrame amd implements IViewStock.
 */
public class JFrameAllStocks extends JFrame implements IViewStock  {


  private JButton exitButton;


  /**
   * This is the only constructor of this class.
   * This set default window width to 1000 and hieght to 1000.
   * You cannot resize the window.
   * @param data it takes string as data.
   */
  public JFrameAllStocks(String data) {

    setSize(1000, 1000);
    setLocation(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setMinimumSize(new Dimension(500,500));


    this.setLayout(new FlowLayout());

    JPanel mainPanel = new JPanel();

    JLabel display = new JLabel("All Investments:");

    String[] investments = data.split("\n");


    JList list = new JList(investments);

    JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setPreferredSize(new Dimension(500,200));
    mainPanel.add(scrollPane);


    mainPanel.add(display);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit All");
    this.add(exitButton);

    this.add(mainPanel);
    pack();

  }

  @Override
  public void setEchoOutput(String s) {
    //empty
  }

  @Override
  public String getInputString() {
    return null;
  }

  @Override
  public void clearInputString() {
    //empty
  }

  @Override
  public void setListener(ActionListener listener) {
    exitButton.addActionListener(listener);
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void sendData(List<String> data) {
    //empty
  }

  @Override
  public void refreshState() {
    //empty
  }

  @Override
  public Object selectedListItem() {
    return null;
  }

  @Override
  public JFrameAllStocks getFrame() {
    return this;
  }
}
