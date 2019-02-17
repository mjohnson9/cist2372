/*
 * Copyright 2019 Michael Johnson
 */

package johnson.michael.retailitem;

import johnson.michael.retailitem.exceptions.NegativePriceException;
import johnson.michael.retailitem.exceptions.NegativeUnitsOnHandException;

/**
 * This class represents an item in a retail store.
 */
public class RetailItem {

  /**
   * A description of the item.
   */
  private String description;
  /**
   * The number of units of the item on hand.
   */
  private int unitsOnHand;
  /**
   * The price of the item.
   */
  private double price;

  // Don't allow no-arg constructor
  private RetailItem() {
  }

  /**
   * Constructs a {@code RetailItem} from the given parameters.
   *
   * @param description The initial description.
   * @param unitsOnHand The initial number of units on hand.
   * @param price The initial price.
   */
  public RetailItem(final String description, final int unitsOnHand, final double price) {
    this.setDescription(description);
    this.setUnitsOnHand(unitsOnHand);
    this.setPrice(price);
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public int getUnitsOnHand() {
    return this.unitsOnHand;
  }

  public void setUnitsOnHand(final int unitsOnHand) {
    if (unitsOnHand < 0) {
      throw new NegativeUnitsOnHandException();
    }

    this.unitsOnHand = unitsOnHand;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(final double price) {
    if (price < 0) {
      throw new NegativePriceException();
    }

    this.price = price;
  }

  @Override
  public String toString() {
    return String.format("Item: %s priced at %.2f with %d units on hand", this.getDescription(),
        this.getPrice(), this.getUnitsOnHand());
  }
}
