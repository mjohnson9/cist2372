/*
 * Copyright 2019 Michael Johnson
 */

package johnson.michael.retailitem.exceptions;

public class NegativeUnitsOnHandException extends IllegalArgumentException {

  /**
   * Constructs a {@code NegativeUnitsOnHandException} with a default message of "The units on hand
   * cannot be negative"
   */
  public NegativeUnitsOnHandException() {
    super("The units on hand cannot be negative");
  }
}
