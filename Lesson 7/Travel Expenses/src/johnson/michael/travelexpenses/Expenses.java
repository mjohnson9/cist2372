package johnson.michael.travelexpenses;

/**
 * Expenses holds an employee's travel expenses. It can also calculate their allowance.
 */
public class Expenses {
  /**
   * The number of days spent on the trip.
   */
  private int days;

  /**
   * The cost of flying.
   */
  private double airfare;

  /**
   * The cost of a rental vehicle.
   */
  private double rentalFees;

  /**
   * The number of miles driven in a personal vehicle.
   */
  private double milesDriven;

  /**
   * The cost of parking.
   */
  private double parkingFees;

  /**
   * The cost of taxi services.
   */
  private double taxiFees;

  /**
   * The cost of conference or seminar registration.
   */
  private double conferenceFees;

  /**
   * The cost of lodging.
   */
  private double lodgingCost;

  /**
   * The Expenses constructor initializes all values to 0. Values should be set to sensible numbers
   * before using the class.
   */
  public Expenses() {
    // Initialize all of our variables
    this.setDays(0);
    this.setAirfare(0d);
    this.setRentalFees(0d);
    this.setMilesDriven(0d);
    this.setParkingFees(0d);
    this.setTaxiFees(0d);
    this.setConferenceFees(0d);
    this.setLodgingCost(0d);
  }

  /**
   * Calculates the amount owed as a result of the expenses.
   *
   * @return The amount owed as a result of the given expenses in dollars. A negative amount means
   *     that the expenses were within budget.
   */
  public double getAmountOwed() {
    return this.getTotal() - this.getAllowable();
  }

  /**
   * Calculates the total allowable expenses.
   *
   * @return The total expenses allowable, based on days and miles driven.
   */
  public double getAllowable() {
    double allowable = 0;

    // Why doesn't this company reimburse airfare, car rental, or conference fees?

    final double days = (double) this.getDays();

    allowable += this.getAirfare(); // Airfare is always covered
    allowable += this.getConferenceFees(); // Conference fees are always covered

    allowable += 37.00d * days; // $37 per day for meals
    allowable += 10.00d * days; // $10 per day for parking
    allowable += 20.00d * days; // $20 per day for taxis
    allowable += 95.00d * days; // $95 per day for lodging
    allowable += 0.27d * this.getMilesDriven(); // $0.27 per mile driven

    return allowable;
  }

  /**
   * Calculates the total expenses incurred.
   *
   * @return Total expenses incurred.
   */
  public double getTotal() {
    return this.getAirfare() + this.getRentalFees() + this.getParkingFees() + this.getTaxiFees()
        + this.getConferenceFees() + this.getLodgingCost();
  }

  /**
   * @return The number of days spent on the trip.
   */
  public int getDays() {
    return this.days;
  }

  /**
   * Sets the number of days that the trip lasted.
   *
   * @param days The number of days the trip lasted.
   */
  public void setDays(final int days) {
    this.days = days;
  }

  /**
   * @return The airfare cost.
   */
  public double getAirfare() {
    return this.airfare;
  }

  /**
   * Sets the airfare cost incurred.
   *
   * @param airfare The airfare cost.
   */
  public void setAirfare(final double airfare) {
    this.airfare = airfare;
  }

  /**
   * @return The cost of a rental vehicle.
   */
  public double getRentalFees() {
    return this.rentalFees;
  }

  /**
   * Sets the cost of a rental vehicle.
   *
   * @param rentalFees The cost of a rental vehicle.
   */
  public void setRentalFees(final double rentalFees) {
    this.rentalFees = rentalFees;
  }

  /**
   * @return The number of miles driven in a personal vehicle.
   */
  public double getMilesDriven() {
    return this.milesDriven;
  }

  /**
   * Sets the number of miles driven in a private vehicle.
   *
   * @param milesDriven The number of miles driven.
   */
  public void setMilesDriven(final double milesDriven) {
    this.milesDriven = milesDriven;
  }

  /**
   * @return The cost of parking.
   */
  public double getParkingFees() {
    return this.parkingFees;
  }

  /**
   * Sets the cost of parking fees.
   *
   * @param parkingFees The cost of parking fees.
   */
  public void setParkingFees(final double parkingFees) {
    this.parkingFees = parkingFees;
  }

  /**
   * @return The cost of taxi services.
   */
  public double getTaxiFees() {
    return this.taxiFees;
  }

  /**
   * Sets the cost of taxi services.
   *
   * @param taxiFees The cost of taxi services.
   */
  public void setTaxiFees(final double taxiFees) {
    this.taxiFees = taxiFees;
  }

  /**
   * @return The cost of conference or seminar registration.
   */
  public double getConferenceFees() {
    return this.conferenceFees;
  }

  /**
   * Sets the cost of conference fees.
   *
   * @param conferenceFees The cost of conference fees.
   */
  public void setConferenceFees(final double conferenceFees) {
    this.conferenceFees = conferenceFees;
  }

  /**
   * @return The cost of lodging.
   */
  public double getLodgingCost() {
    return this.lodgingCost;
  }

  /**
   * Sets the cost of lodging.
   *
   * @param lodgingCost The cost of lodging.
   */
  public void setLodgingCost(final double lodgingCost) {
    this.lodgingCost = lodgingCost;
  }
}
