package controller.commands;

/**
 * This class is an abstract class which implements Command interface.
 * This class implements showMessage method.
 */
public abstract class AbstractCommand implements Command {

  @Override
  public void showMessage(Appendable appendable, String message) {

    try {
      appendable.append(message);
    }

    catch (Exception e) {
      //
    }
  }
}
