/*
 * Copyright 2019 Michael Johnson
 */

package johnson.michael.retailitem.exceptions;

public class NegativePriceException extends IllegalArgumentException {

  /**
   * Constructs a {@code NegativePriceException} with a default message of "The price cannot be
   * negative"
   */
  public NegativePriceException() {
    super("The price cannot be negative");
  }
}
