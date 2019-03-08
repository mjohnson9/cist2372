package johnson.michael.bankaccount;

public class SavingsAccount extends BankAccount {
  public SavingsAccount(final double initialBalance, final double annualInterestRate) {
    this.setBalance(initialBalance);
    this.setAnnualInterestRate(annualInterestRate);
  }

  public boolean isActive() {
    return this.getBalance() >= 25d;
  }

  @Override
  public void withdraw(double amount) throws InsufficientFundsException, AccountInactiveException {
    if (!this.isActive()) {
      throw new AccountInactiveException();
    }

    super.withdraw(amount);
  }

  public void monthlyProcess() {
    final int numWithdrawals = this.getNumWithdrawals();
    if (numWithdrawals > 4) {
      this.setMonthlyServiceCharges(
          this.getMonthlyServiceCharges() + (((double) numWithdrawals) - 4d));
    }

    super.monthlyProcess();
  }
}
