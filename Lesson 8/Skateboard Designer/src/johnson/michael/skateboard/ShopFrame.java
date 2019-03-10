package johnson.michael.skateboard;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ShopFrame displays a frame for a Skateboard Shop. It lists several items that can be purchased.
 * It updates its prices as item selections change.
 */
@SuppressWarnings({"ClassWithTooManyFields", "CyclicClassDependency"})
public class ShopFrame extends JFrame {
  /**
   * The tax rate to be charged on items purchased here.
   */
  public static final double TAX_RATE = 0.06d;

  /**
   * The combo box for selecting the deck.
   */
  private final JComboBox<PurchasableItem> deckBox;
  /**
   * The label showing the cost of the deck.
   */
  private final JLabel deckCostLabel;
  /**
   * The combo box for selecting the trucks.
   */
  private final JComboBox<PurchasableItem> truckBox;
  /**
   * The label showing the cost of the trucks.
   */
  private final JLabel truckCostLabel;
  /**
   * The combo box for selecting the wheels.
   */
  private final JComboBox<PurchasableItem> wheelBox;
  /**
   * The label showing the cost of the wheels.
   */
  private final JLabel wheelCostLabel;
  /**
   * The list allowing selecting of miscellaneous items.
   */
  private final JList<PurchasableItem> miscellaneousList;
  /**
   * The label showing the total cost of the miscellaneous items.
   */
  private final JLabel miscellaneousCostLabel;

  /**
   * The label showing the subtotal of all items.
   */
  private final JLabel subtotalLabel;
  /**
   * The label showing the sale tax.
   */
  private final JLabel taxLabel;
  /**
   * The label showing the grand total.
   */
  private final JLabel totalLabel;

  /**
   * The list of purchasable decks.
   */
  private static final PurchasableItem[] DECKS = {new PurchasableItem("The Dictator", 45.00d),
      new PurchasableItem("The Street King", 50.00d),
      new PurchasableItem("The Master Thrasher", 60.00d)};
  /**
   * The list of purchasable trucks.
   */
  private static final PurchasableItem[] TRUCKS = {new PurchasableItem("7.75 inch axle", 35.00d),
      new PurchasableItem("8 inch axle", 40.00d), new PurchasableItem("8.5 inch axle", 45.00d)};
  /**
   * The list of purchasable wheels.
   */
  private static final PurchasableItem[] WHEELS = {new PurchasableItem("51 mm", 20.00d),
      new PurchasableItem("55 mm", 22.00d), new PurchasableItem("58 mm", 24.00d),
      new PurchasableItem("61 mm", 28.00d)};
  /**
   * The list of purchasable miscellaneous items.
   */
  private static final PurchasableItem[] MISCELLANEOUS_ITEMS = {
      new PurchasableItem("Grip tape", 10.00d), new PurchasableItem("Bearings", 30.00d),
      new PurchasableItem("Riser pads", 2.00d), new PurchasableItem("Nuts & bolts kit", 3.00d)};

  /**
   * Constructs a new ShopFrame with a pre-defined layout and contents.
   */
  public ShopFrame() {
    super();

    this.setTitle("Skateboard Designer");
    this.setLayout(new GridLayout(7, 3, 5, 5));

    final ActionListener ourListener = new ItemSelectionUpdated();

    this.add(new JLabel("Deck:", JLabel.RIGHT));
    this.deckBox = new JComboBox<>(DECKS);
    this.deckBox.addActionListener(ourListener);
    this.add(this.deckBox);
    this.deckCostLabel = new JLabel("$-.--");
    this.add(this.deckCostLabel);

    this.add(new JLabel("Truck assembly:", JLabel.RIGHT));
    this.truckBox = new JComboBox<>(TRUCKS);
    this.truckBox.addActionListener(ourListener);
    this.add(this.truckBox);
    this.truckCostLabel = new JLabel("$-.--");
    this.add(this.truckCostLabel);

    this.add(new JLabel("Wheel set:", JLabel.RIGHT));
    this.wheelBox = new JComboBox<>(WHEELS);
    this.wheelBox.addActionListener(ourListener);
    this.add(this.wheelBox);
    this.wheelCostLabel = new JLabel("$-.--");
    this.add(this.wheelCostLabel);

    this.add(new JLabel("Other items:", JLabel.RIGHT));
    this.miscellaneousList = new JList<>(MISCELLANEOUS_ITEMS);
    this.miscellaneousList.addListSelectionListener((ListSelectionListener) ourListener);
    this.miscellaneousList.setVisibleRowCount(4);
    final JScrollPane miscellaneousScrollPane = new JScrollPane(this.miscellaneousList);
    this.add(miscellaneousScrollPane);
    this.miscellaneousCostLabel = new JLabel("$-.--");
    this.add(this.miscellaneousCostLabel);

    this.add(new JLabel());
    this.add(new JLabel("Subtotal:", JLabel.RIGHT));
    this.subtotalLabel = new JLabel("$-.--");
    this.add(this.subtotalLabel);

    this.add(new JLabel());
    this.add(new JLabel("Sales tax:", JLabel.RIGHT));
    this.taxLabel = new JLabel("$-.--");
    this.add(this.taxLabel);

    this.add(new JLabel());
    this.add(new JLabel("Total:", JLabel.RIGHT));
    this.totalLabel = new JLabel("$-.--");
    this.add(this.totalLabel);

    this.updatePrices();

    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
  }

  /**
   * Updates all price labels, subtotal, taxes, and grand total.
   */
  private void updatePrices() {
    this.updatePrice(this.getSelectedDeck(), this.deckCostLabel);
    this.updatePrice(this.getSelectedTruck(), this.truckCostLabel);
    this.updatePrice(this.getSelectedWheels(), this.wheelCostLabel);
    this.updateMiscellaneousPrice();
    this.updateTotals();
  }

  /**
   * Updates the price listed in a single {@code costLabel} based on the given {@code item}.
   * @param item The item to get the cost from.
   * @param costLabel The label to update with the price.
   */
  private void updatePrice(final PurchasableItem item, final JLabel costLabel) {
    if (item == null) {
      // Either no item is selected or an item that isn't an instance of PurchasableItem is
      // selected.
      costLabel.setText("$-.--");
      return;
    }

    costLabel.setText(String.format("$%,.2f", item.getCost()));
  }

  /**
   * Updates the miscellaneous price label.
   */
  private void updateMiscellaneousPrice() {
    final PurchasableItem[] selected = this.getSelectedMiscellaneous();

    double total = 0.00d;
    for (final PurchasableItem item : selected) {
      total += item.getCost();
    }

    this.miscellaneousCostLabel.setText(String.format("$%,.2f", total));
  }

  /**
   * Updates the subtotal, taxes, and grand total labels.
   */
  private void updateTotals() {
    final double subtotal = this.getSubtotal();
    this.subtotalLabel.setText(String.format("$%,.2f", subtotal));

    double taxes = subtotal * TAX_RATE; // Calculate tax rate on subtotal
    // noinspection MagicNumber
    taxes = Math.round(taxes * 100.0d) / 100.0d; // Round taxes to the nearest penny
    this.taxLabel.setText(String.format("$%,.2f", taxes));

    final double total = subtotal + taxes;
    this.totalLabel.setText(String.format("$%,.2f", total));
  }

  /**
   * Calculates the subtotal based on the cost of all items.
   * @return The subtotal of all selected items.
   */
  @SuppressWarnings("FeatureEnvy")
  private double getSubtotal() {
    double subtotal = 0d;

    PurchasableItem deck = this.getSelectedDeck();
    if (deck != null) {
      subtotal += deck.getCost();
    }

    PurchasableItem trucks = this.getSelectedTruck();
    if (trucks != null) {
      subtotal += trucks.getCost();
    }

    PurchasableItem wheels = this.getSelectedWheels();
    if (wheels != null) {
      subtotal += wheels.getCost();
    }

    final PurchasableItem[] miscellaneousItems = this.getSelectedMiscellaneous();
    for (final PurchasableItem miscellaneousItem : miscellaneousItems) {
      subtotal += miscellaneousItem.getCost();
    }

    return subtotal;
  }

  /**
   * @return The currently selected deck's PurchasableItem, or null if nothing is selected or
   *     something other than a PurchasableItem is selected.
   */
  private PurchasableItem getSelectedDeck() {
    final Object obj = this.deckBox.getSelectedItem();
    if (!(obj instanceof PurchasableItem)) { // Make sure this is a PurchasableItem before
                                             // attempting to cast it
      return null;
    }

    return (PurchasableItem) obj;
  }

  /**
   * @return The currently selected truck's PurchasableItem, or null if nothing is selected or
   *     something other than a PurchasableItem is selected.
   */
  private PurchasableItem getSelectedTruck() {
    final Object obj = this.truckBox.getSelectedItem();
    if (!(obj instanceof PurchasableItem)) { // Make sure this is a PurchasableItem before
                                             // attempting to cast it
      return null;
    }

    return (PurchasableItem) obj;
  }

  /**
   * @return The currently selected wheels' PurchasableItem, or null if nothing is selected or
   *     something other than a PurchasableItem is selected.
   */
  private PurchasableItem getSelectedWheels() {
    final Object obj = this.wheelBox.getSelectedItem();
    if (!(obj instanceof PurchasableItem)) { // Make sure this is a PurchasableItem before
                                             // attempting to cast it
      return null;
    }

    return (PurchasableItem) obj;
  }

  /**
   * @return An array of the currently selected miscellaneous items. The array can be of 0 length.
   */
  private PurchasableItem[] getSelectedMiscellaneous() {
    final int[] selected = this.miscellaneousList.getSelectedIndices();
    final PurchasableItem[] items = new PurchasableItem[selected.length];

    for (int i = 0; i < selected.length; i++) {
      final int selectedIndex = selected[i];
      items[i] = MISCELLANEOUS_ITEMS[selectedIndex];
    }

    return items;
  }

  /**
   * An action handling class that simply calls {@code updatePrices}.
   */
  private class ItemSelectionUpdated implements ActionListener, ListSelectionListener {
    @Override
    public void actionPerformed(final ActionEvent e) {
      // A combo box selection changed; update all of the prices
      ShopFrame.this.updatePrices();
    }

    @Override
    public void valueChanged(final ListSelectionEvent e) {
      // A list selection changed; update all of the prices
      ShopFrame.this.updatePrices();
    }
  }
}
