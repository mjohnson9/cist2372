package johnson.michael.travelexpenses;

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

  public Expenses() {
  }

  public Expenses(final int days, final double airfare, final double rentalFees,
      final double milesDriven,
      final double parkingFees, final double taxiFees, final double conferenceFees,
      final double lodgingCost) {
    this.setDays(days);
    this.setAirfare(airfare);
    this.setRentalFees(rentalFees);
    this.setMilesDriven(milesDriven);
    this.setParkingFees(parkingFees);
    this.setTaxiFees(taxiFees);
    this.setConferenceFees(conferenceFees);
    this.setLodgingCost(lodgingCost);
  }

  /**
   * Calculates the amount owed as a result of the expenses.
   *
   * @return The amount owed as a result of the given expenses in dollars. A negative amount means
   * that the expenses were within budget.
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
   * The number of days spent on the trip.
   */
  public int getDays() {
    return days;
  }

  public void setDays(int days) {
    this.days = days;
  }

  /**
   * The cost of flying.
   */
  public double getAirfare() {
    return airfare;
  }

  public void setAirfare(double airfare) {
    this.airfare = airfare;
  }

  /**
   * The cost of a rental vehicle.
   */
  public double getRentalFees() {
    return rentalFees;
  }

  public void setRentalFees(double rentalFees) {
    this.rentalFees = rentalFees;
  }

  /**
   * The number of miles driven in a personal vehicle.
   */
  public double getMilesDriven() {
    return milesDriven;
  }

  public void setMilesDriven(double milesDriven) {
    this.milesDriven = milesDriven;
  }

  /**
   * The cost of parking.
   */
  public double getParkingFees() {
    return parkingFees;
  }

  public void setParkingFees(double parkingFees) {
    this.parkingFees = parkingFees;
  }

  /**
   * The cost of taxi services.
   */
  public double getTaxiFees() {
    return taxiFees;
  }

  public void setTaxiFees(double taxiFees) {
    this.taxiFees = taxiFees;
  }

  /**
   * The cost of conference or seminar registration.
   */
  public double getConferenceFees() {
    return conferenceFees;
  }

  public void setConferenceFees(double conferenceFees) {
    this.conferenceFees = conferenceFees;
  }

  /**
   * The cost of lodging.
   */
  public double getLodgingCost() {
    return lodgingCost;
  }

  public void setLodgingCost(double lodgingCost) {
    this.lodgingCost = lodgingCost;
  }
}
