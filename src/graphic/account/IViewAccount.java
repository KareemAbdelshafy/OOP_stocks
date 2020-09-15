package graphic.account;

import java.awt.event.ActionListener;
import java.util.List;

import user.Account;

/**
 * The interface for our view class.
 */
public interface IViewAccount {
  /**
   * Set the label that is showing what the model stores.
   */
  void setEchoOutput(String s);

  /**
   * Get the string from the text field and return it.
   */
  String getInputString();

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */
  void clearInputString();

  /**
   * Set the listener for any actions.
   */
  void setListener(ActionListener listener);

  /**
   * Display this view.
   */
  void display();

  /**
   * This method sends data as list of Accounts to the view.
   * @param data as List of Account.
   */
  void sendData(List<Account> data);

  /**
   * This method is used to refresh the list if the new data has been passed.
   * List here is JList.
   */
  void refreshState();

  /*
   * This method returns Object of selected item in the list.
   * List here is JList.
   * @return Object which is actually a string
   */
  Object selectedListItem();

  /**
   * This is a getter method for JFrame.
   * @return JFrameViewAccount which is extended class of JFrame
   */
  JFrameViewAccount getFrame();
}
