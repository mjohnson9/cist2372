/*
 * Copyright 2019 Michael Johnson
 */

package johnson.michael.retailitem;

import java.util.Random;
import johnson.michael.retailitem.exceptions.NegativePriceException;
import johnson.michael.retailitem.exceptions.NegativeUnitsOnHandException;

public final class Main {
  private static final String[] DESCRIPTIONS = {
      "Eggs", "Bacon", "Bread", "Butter", "Sugar", "Ramen"};

  // Don't allow Main to be instantiated
  private Main() {}

  public static void main(final String[] args) {
    final Random r = new Random();

    final int numToCreate = 10 + r.nextInt(30);
    for (int i = 0; i < numToCreate; i++) {
      final String description = getRandomDescription(r);
      int unitsOnHand = 1 + r.nextInt(100);
      double price = 0.5d + (r.nextDouble() * 20d);

      if (r.nextBoolean()) {
        price = -price;
      } else if (r.nextBoolean()) {
        unitsOnHand = -unitsOnHand;
      }

      final RetailItem item;

      try {
        item = new RetailItem(description, unitsOnHand, price);
      } catch (final NegativePriceException e) {
        System.out.printf(
            "Attempted to create a retail item with a negative price (%.2f) and got an exception: %s\n",
            price, e);
        continue; // Go through the next iteration of the loop
      } catch (final NegativeUnitsOnHandException e) {
        System.out.printf(
            "Attempted to create a retail item with a negative number of units on hand (%d) and got an exception: %s\n",
            unitsOnHand, e);
        continue; // Go through the next iteration of the loop
      }

      System.out.println(item);
    }
  }

  private static String getRandomDescription(final Random r) {
    return DESCRIPTIONS[r.nextInt(DESCRIPTIONS.length)];
  }
}
