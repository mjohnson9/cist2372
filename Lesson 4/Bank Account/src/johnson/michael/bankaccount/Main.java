package johnson.michael.bankaccount;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] main) throws InterruptedException {
    final SavingsAccount account = setupSavingsAccount();
    mainMenu(account);
  }

  public static void printHeader(final SavingsAccount account) {
    System.out.println("========= SAVINGS ACCOUNT ==========");
    System.out.println(String.format("Balance: $%,.2f", account.getBalance()));
    System.out.println("Active: " + (account.isActive() ? "Yes" : "No"));
    System.out.println(String.format(
        "Pending monthly service charges: $%,.2f", account.getMonthlyServiceCharges()));
    System.out.println("====================================");
  }

  public static void mainMenu(final SavingsAccount account) throws InterruptedException {
    while (true) { // Run an infinite loop; we'll exit by returning or breaking
      clearScreen();
      printHeader(account);
      System.out.println();
      System.out.println("What would you like to do?");
      System.out.println("D: Deposit");
      if (account.isActive()) {
        System.out.println("W: Withdraw");
      }
      System.out.println("M: Run monthly processing");
      System.out.println();
      System.out.println("Q: Quit");

      final char input = Character.toLowerCase(promptChar("Please select an option: ", value -> {
        final char lowerValue =
            Character.toLowerCase(value); // lowercase the value for easier comparison

        switch (lowerValue) {
          case 'd':
          case 'w':
          case 'm':
          case 'q':
            return true;
          default:
            System.out.println(String.format("%s is not a valid option.", value));
            System.out.println();
            return false;
        }
      }));

      switch (input) {
        case 'd':
          depositMenu(account);
          break;
        case 'w':
          withdrawMenu(account);
          break;
        case 'm':
          monthlyProcessing(account);
          break;
        case 'q':
          return; // Return out of mainMenu to end the program
      }
    }
  }

  private static void depositMenu(final SavingsAccount account) {
    clearScreen();
    printHeader(account);
    final double toDeposit = promptDouble("How much would you like to deposit? $", value -> {
      if (value < 0.0d) {
        System.out.println(
            "You may not deposit a negative amount. To remove money from the account, use the withdrawal functionality.");
        System.out.println();
        return false;
      }

      return true;
    });

    account.deposit(toDeposit);
  }

  private static void withdrawMenu(final SavingsAccount account) throws InterruptedException {
    if (!account.isActive()) {
      System.out.println();
      System.out.println(
          "You may not withdraw from this account, as it is inactive. Make the account active again by raising the balance above $25.");
      Thread.sleep(5000);
      return;
    }

    clearScreen();
    printHeader(account);
    final double toWithdraw = promptDouble("How much would you like to withdraw? $", value -> {
      if (value < 0.0d) {
        System.out.println(
            "You may not withdraw a negative amount. To add money to the account, use the deposit functionality.");
        System.out.println();
        return false;
      }

      return true;
    });

    try {
      account.withdraw(toWithdraw);
    } catch (InsufficientFundsException e) {
      System.out.println(
          String.format("You do not have sufficient funds to withdraw $%,.2f.", toWithdraw));
      Thread.sleep(5000);
    } catch (AccountInactiveException e) {
      System.out.println(
          "You may not withdraw from this account, as it is inactive. Make the account active again by raising the balance above $25.");
      Thread.sleep(5000);
    }
  }

  private static void monthlyProcessing(final SavingsAccount account) throws InterruptedException {
    clearScreen();
    printHeader(account);
    System.out.println();
    System.out.println("Running monthly processing...");
    account.monthlyProcess();
    System.out.println();
    printHeader(account);
    Thread.sleep(5000);
  }

  public static SavingsAccount setupSavingsAccount() {
    clearScreen();
    System.out.println("========= SAVINGS ACCOUNT CREATION ==========");

    final double initialBalance =
        promptDouble("Please enter the initial balance of the savings account: $", value -> {
          if (value < 0) {
            System.out.println("The initial balance may not be less than zero.");
            return false;
          }

          return true;
        });
    final double annualInterestRate =
        promptDouble("Please enter the annual interest rate: ", value -> {
          if (value < 0) {
            System.out.println("The annual interest rate may not be less than zero.");
            return false;
          }

          return true;
        });

    return new SavingsAccount(initialBalance, annualInterestRate / 100);
  }

  public static char promptChar(final String prompt, final InputValidator<Character> validator) {
    while (true) { // Do an infinite loop; we'll return to get out of it
      System.out.print(prompt);

      final String token = scanner.next();
      final char value = token.charAt(0);
      if (!validator.validateInput(value)) {
        // Don't print anything because the validator function should have done that
        continue; // The input was invalid; try again
      }

      return value;
    }
  }

  public static double promptDouble(final String prompt, final InputValidator<Double> validator) {
    while (true) { // Do an infinite loop; we'll return to get out of it
      System.out.print(prompt);

      double value;
      try {
        value = scanner.nextDouble();
      } catch (InputMismatchException e) {
        scanner.next(); // Skip the invalid token
        System.out.println("The given input is not a number.");
        System.out.println();
        continue; // Skip the rest of the code in this iteration and try again
      }

      if (!validator.validateInput(value)) {
        // Don't print anything because the validator function should have done that
        continue; // The input was invalid; try again
      }

      return value;
    }
  }

  private static void clearScreen() {
    if (System.getProperty("os.name").contains("Windows")) {
      clearScreenWindows();
    } else {
      clearScreenOther();
    }
  }

  private static void clearScreenWindows() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (IOException | InterruptedException e) {
    }
  }

  private static void clearScreenOther() {
    try {
      new ProcessBuilder("clear").inheritIO().start().waitFor();
    } catch (IOException | InterruptedException e) {
    }
  }
}
