package johnson.michael.payroll;

public class Payroll {

  public final int length = 7;
  private int[] employeeId = new int[]{5658845, 4520125, 7895122, 8777541, 8451277, 1302850,
      7580489};
  private int[] hours = new int[length];
  private double[] payRate = new double[length];
  private double[] wages = new double[length];

  /**
   * setEmployeeId sets the employee ID at the given index
   *
   * @param index The index to set the employee ID for
   * @param id The new employee ID
   */
  public void setEmployeeId(int index, int id) {
    this.employeeId[index] = id;
  }

  /**
   * getEmployeeId returns the employee ID at the given index
   *
   * @param index The index to get the employee ID from
   * @return The employee ID at the given index
   */
  public int getEmployeeId(int index) {
    return this.employeeId[index];
  }

  /**
   * setHours sets the number of hours worked by the employee at index
   *
   * @param index The index to set the hours for
   * @param hours The number of hours worked
   */
  public void setHours(int index, int hours) {
    this.hours[index] = hours;
    this.updateWages(index);
  }

  /**
   * getHours returns the hours worked by the employee at the given index
   *
   * @param index The index to get the hours from
   * @return The hours worked by the employee at the given index
   */
  public int getHours(int index) {
    return this.hours[index];
  }

  /**
   * setPayRate sets the pay rate of the employee at index
   *
   * @param index The index to set the pay rate for
   * @param payRate the pay rate of the employee
   */
  public void setPayRate(int index, double payRate) {
    this.payRate[index] = payRate;
    this.updateWages(index);
  }

  /**
   * getPayRate returns the pay rate of the employee at the given index
   *
   * @param index The index to get the pay rate from
   * @return The pay rate of the employee at the given index
   */
  public double getPayRate(int index) {
    return this.payRate[index];
  }

  /**
   * getWages returns the gross wages of the employee at the given index
   *
   * @param index The index to get the wages from
   * @return The gross wages of the employee at the given index
   */
  public double getWages(int index) {
    return this.wages[index];
  }

  /**
   * getWagesById retrieves an employee's wages based on their employee ID
   *
   * @param employeeId The employee ID to retrieve wages for
   * @return The wages of the employee with the given ID
   * @throws NullPointerException Thrown when no employee with the given ID exists
   */
  public double getWagesById(int employeeId) throws NullPointerException {
    int targetIndex = -1;
    for (int i = 0; i < this.length; i++) {
      final int currentId = this.employeeId[i];
      if (currentId == employeeId) {
        targetIndex = i;
        break;
      }
    }

    if (targetIndex == -1) {
      throw new NullPointerException(); // throw an exception if that employee doesn't exist
    }

    return this.wages[targetIndex];
  }

  /**
   * updateWages updates an employee's wages after a change is made to their information
   *
   * @param index The index of the employee whose wages should be updated
   */
  private void updateWages(int index) {
    this.wages[index] = this.getHours(index) * this.getPayRate(index);
  }
}
