package controller.commands;

/**
 * This is a command interface.
 * It is used to make new commands and show messages on Appendable.
 */
public interface Command {

  /**
   * This method is used to initialize the command.
   */
  void  start();

  /**
   * This method writes message to the appendable provided as argument.
   * @param appendable - This is the appendable for output.
   * @param message - This is a message to be displayed.
   */
  void showMessage(Appendable appendable, String message);
}
