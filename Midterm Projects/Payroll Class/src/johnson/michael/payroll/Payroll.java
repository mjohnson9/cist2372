package johnson.michael.payroll;

import johnson.michael.payroll.exceptions.EmptyNameException;
import johnson.michael.payroll.exceptions.InvalidEmployeeIdException;
import johnson.michael.payroll.exceptions.InvalidHourlyPayRateException;
import johnson.michael.payroll.exceptions.InvalidHoursWorkedException;

public class Payroll {
  /**
   * The employee's ID number.
   */
  private int id;

  /**
   * The employee's first name.
   */
  private String firstName;

  /**
   * The employee's last name.
   */
  private String lastName;

  /**
   * The employee's hourly pay rate in dollars.
   */
  private double hourlyPayRate;

  /**
   * The number of hours that the employee has worked.
   */
  private double hoursWorked;

  public Payroll(final int id, final String firstName, final String lastName) {
    this.setId(id);
    this.setFirstName(firstName);
    this.setLastName(lastName);
  }

  public int getId() {
    return this.id;
  }

  public void setId(final int id) throws InvalidEmployeeIdException {
    if (id <= 0) {
      throw new InvalidEmployeeIdException(
          "The employee's ID cannot be less than or equal to zero");
    }
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) throws EmptyNameException {
    firstName = firstName.trim(); // Trim any whitespace off of the ends
    if (firstName.length() == 0) {
      throw new EmptyNameException("The employee's first name cannot be empty");
    }

    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) throws EmptyNameException {
    lastName = lastName.trim(); // Trim any whitespace off of the ends
    if (lastName.length() == 0) {
      throw new EmptyNameException("The employee's last name cannot be empty");
    }

    this.lastName = lastName;
  }

  public double getHourlyPayRate() {
    return this.hourlyPayRate;
  }

  public void setHourlyPayRate(final double hourlyPayRate) throws InvalidHourlyPayRateException {
    if (hourlyPayRate < 0) {
      throw new InvalidHourlyPayRateException("The employee's hourly pay rate cannot be negative");
    } else if (hourlyPayRate > 25) {
      throw new InvalidHourlyPayRateException(
          "The employee's hourly pay rate cannot be greater than 25");
    }
    this.hourlyPayRate = hourlyPayRate;
  }

  public double getHoursWorked() {
    return this.hoursWorked;
  }

  public void setHoursWorked(final double hoursWorked) throws InvalidHoursWorkedException {
    if (hoursWorked < 0) {
      throw new InvalidHoursWorkedException(
          "The employee's number of hours worked cannot be negative");
    } else if (hoursWorked > 84) {
      throw new InvalidHoursWorkedException(
          "The employee's number of hours worked cannot be greater than 84");
    }
    this.hoursWorked = hoursWorked;
  }

  public double getGrossPay() {
    return this.getHoursWorked() * this.getHourlyPayRate();
  }
}
