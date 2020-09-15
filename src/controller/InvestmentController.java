package controller;

import java.util.Scanner;

import controller.commands.Command;
import controller.commands.addinvestor.AddInvestor;
import controller.commands.addinvestor.SelectInvestor;
import controller.commands.addinvestor.ShowAllInvestors;


/**
 * This is the Controller of this application.
 * It takes an Readabel and appendable to run the application.
 */
public class InvestmentController {

  private Readable readable;
  private Appendable appendable;
  private StringBuilder stringBuilder;


  /**
   * This constructor takes readable to take input from the user and
   * appends output to the appendable.
   * @param readable - This is the Readable for user input.
   * @param appendable - This is the Appendable for user output.
   */
  public InvestmentController(Readable readable, Appendable appendable) {

    this.readable = readable;
    this.appendable = appendable;
    stringBuilder = new StringBuilder();
  }

  /**
   * This method is used to start the application after
   * creating an object with readable and appendable.
   */
  public void start() {

    showMessage("Welcome to Stock Application\n");

    stringBuilder.append("*------------------------------------------------------*\n");
    stringBuilder.append("Please select from the following options:\n");
    stringBuilder.append("1. Add new Investor Profile\n");
    stringBuilder.append("2. Show all Investors\n");
    stringBuilder.append("3. Select an Investor\n");
    stringBuilder.append("4. Quit\n");
    stringBuilder.append("*------------------------------------------------------*\n");

    showMessage(stringBuilder.toString());
    Scanner  scanner = new Scanner(readable);

    Command command  = null;

    while (scanner.hasNext()) {

      try {
        switch (Integer.parseInt(scanner.next())) {
          case 1:
            showMessage("*------------------------------------------------------*\n");
            showMessage("INPUT: Enter New Investor name:\n");
            showMessage("*------------------------------------------------------*\n");
            command = new AddInvestor(readable, appendable, scanner.next());
            break;
          case 2:
            command = new ShowAllInvestors(appendable);
            break;
          case 3:
            showMessage("*------------------------------------------------------*\n");
            showMessage("INPUT: Enter New Investor to Search:\n");
            showMessage("*------------------------------------------------------*\n");
            command = new SelectInvestor(readable, appendable, scanner.next());
            break;
          case 4:
            showMessage("*------------------------------------------------------*\n");
            showMessage("QUITTING: Thanks for using this application.\n");
            showMessage("*------------------------------------------------------*\n");
            return;
          default:
            showMessage("*------------------------------------------------------*\n");
            showMessage("ERROR: Enter a Valid Option\n");
            showMessage("*------------------------------------------------------*\n");
            break;
        }
        if (command != null) {
          command.start();
          command = null;
        }
      }
      catch (NumberFormatException e) {
        showMessage("*------------------------------------------------------*\n");
        showMessage("ERROR: Enter a valid Integer");
        showMessage("*------------------------------------------------------*\n");
      }

    }

  }

  private void showMessage(String message) {
    try {
      appendable.append(message);
    }

    catch (Exception e) {
      //
    }
  }



}
