package graphic.investor;

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
 * This is the main application view.
 * This extends JFrame and implements IView.
 */
public class JFrameView extends JFrame implements IView {

  private JLabel display;
  private JButton addInvestorButton;
  private JButton  exitButton;
  private JButton  selectButton;
  private JTextField input;

  private  List<String> investorList;
  private JList list;
  private DefaultListModel<String> model;

  /**
   * This is the only constructor of this class.
   * This set default window width to 1000 and hieght to 1000.
   * You cannot resize the window.
   */
  public JFrameView() {

    setSize(1000, 1000);
    setLocation(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setMinimumSize(new Dimension(500,500));


    this.setLayout(new FlowLayout());

    JSplitPane splitPane = new JSplitPane();

    JPanel topPanel = new JPanel();         // our top component
    JPanel bottomPanel = new JPanel();

    display = new JLabel("All Investors: ");


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
    addInvestorButton = new JButton("Add Investor");
    addInvestorButton.setActionCommand("Add Investor");
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
    selectButton = new JButton("Select Investor");
    selectButton.setActionCommand("Select");
    bottomPanel.add(selectButton);

    this.add(splitPane);
    pack();
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void sendData(List<String> data) {
    this.investorList = data;
    System.out.println(investorList);
  }

  @Override
  public void refreshState() {
    model.removeAllElements();
    Object[] ar = investorList.toArray();

    for (int i = 0; i < ar.length; i++) {
      model.addElement(ar[i].toString());
    }
    list.setModel(model);
    System.out.println(this.investorList);
  }

  @Override
  public Object selectedListItem() {
    return list.getSelectedValue();
  }

  @Override
  public JFrameView getFrame() {
    return this;
  }


  @Override
  public void setListener(ActionListener listener) {
    addInvestorButton.addActionListener(listener);
    exitButton.addActionListener(listener);
    selectButton.addActionListener(listener);
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
