package johnson.michael.skateboard;

/**
 * PurchasableItem represents an item that could be purchased in the skateboard shop. Its primary
 * purpose is to facilitate showing an item name and price through {@code toString} and make
 * handling the selection easy.
 *
 * PurchasableItem is immutable.
 */
public class PurchasableItem {
  /**
   * The name of the item.
   */
  private final String name;
  /**
   * The price of the item.
   */
  private final double cost;

  /**
   * Constructs a new PurchasableItem.
   * @param name The name of the item.
   * @param cost The price of the item.
   */
  public PurchasableItem(final String name, final double cost) {
    this.name = name;
    this.cost = cost;
  }

  /**
   * @return The name of the item.
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return The price of the item.
   */
  public double getCost() {
    return this.cost;
  }

  @Override
  public String toString() {
    return String.format("%s - $%,.2f", this.getName(), this.getCost());
  }
}
