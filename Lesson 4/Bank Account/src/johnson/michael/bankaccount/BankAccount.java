package johnson.michael.bankaccount;

public abstract class BankAccount {
  private double balance = 0d;
  private int numDeposits = 0;
  private int numWithdrawals = 0;
  private double annualInterestRate = 0d;
  private double monthlyServiceCharges = 0d;

  public void deposit(double amount) {
    this.setBalance(this.getBalance() + amount);
    this.setNumDeposits(this.getNumDeposits() + 1);
  }

  public void withdraw(double amount) throws InsufficientFundsException, AccountInactiveException {
    if (this.getBalance() < amount) {
      throw new InsufficientFundsException();
    }
    this.setBalance(this.getBalance() - amount);
    this.setNumWithdrawals(this.getNumWithdrawals() + 1);
  }

  private void calcInterest() {
    final double monthlyInterestRate = this.getAnnualInterestRate() / 12d;
    final double monthlyInterest = this.getBalance() * monthlyInterestRate;
    this.setBalance(this.getBalance() + monthlyInterest);
  }

  public void monthlyProcess() {
    this.setBalance(this.getBalance() - this.getMonthlyServiceCharges());
    this.calcInterest();
    this.setNumWithdrawals(0);
    this.setNumDeposits(0);
    this.setMonthlyServiceCharges(0d);
  }

  public double getBalance() {
    return this.balance;
  }

  protected void setBalance(double balance) {
    this.balance = balance;
  }

  public int getNumDeposits() {
    return this.numDeposits;
  }

  protected void setNumDeposits(int numDeposits) {
    this.numDeposits = numDeposits;
  }

  public int getNumWithdrawals() {
    return this.numWithdrawals;
  }

  protected void setNumWithdrawals(int numWithdrawals) {
    this.numWithdrawals = numWithdrawals;
  }

  public double getAnnualInterestRate() {
    return this.annualInterestRate;
  }

  public void setAnnualInterestRate(double annualInterestRate) {
    this.annualInterestRate = annualInterestRate;
  }

  public double getMonthlyServiceCharges() {
    return this.monthlyServiceCharges;
  }

  protected void setMonthlyServiceCharges(double monthlyServiceCharges) {
    this.monthlyServiceCharges = monthlyServiceCharges;
  }
}
