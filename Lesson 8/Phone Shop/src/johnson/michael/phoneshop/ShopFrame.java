package johnson.michael.phoneshop;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ShopFrame displays a frame for Cell Solutions' shop. It lists several items that can be
 * purchased. It updates its prices as item selections change.
 */
@SuppressWarnings({"ClassWithTooManyFields", "CyclicClassDependency"})
public class ShopFrame extends JFrame {
  /**
   * The tax rate to be charged only on cell phones purchased here.
   */
  public static final double TAX_RATE = 0.06d;

  /**
   * The combo box for selecting the service package.
   */
  private final JComboBox<PurchasableItem> servicePackageBox;
  /**
   * The label showing the cost of the selected service package.
   */
  private final JLabel servicePackageLabel;
  /**
   * The combo box for selecting the cell phone model.
   */
  private final JComboBox<PurchasableItem> phoneBox;
  /**
   * The label showing the cost of the selected phone model.
   */
  private final JLabel phoneLabel;
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
   * The list of purchasable service packages.
   */
  private static final PurchasableItem[] PACKAGES = {
      new PurchasableItem("300 minutes per month", 45.00d),
      new PurchasableItem("800 minutes per month", 65.00d),
      new PurchasableItem("1,500 minutes per month", 99.00d)};
  /**
   * The list of purchasable phone models.
   */
  private static final PurchasableItem[] PHONES = {new PurchasableItem("Model 100", 29.95d),
      new PurchasableItem("Model 110", 49.95d), new PurchasableItem("Model 200", 99.95d)};
  /**
   * The list of purchasable miscellaneous items.
   */
  private static final PurchasableItem[] MISCELLANEOUS_ITEMS = {
      new PurchasableItem("Voice mail", 5.00d), new PurchasableItem("Text messaging", 10.00d)};

  /**
   * Constructs a new ShopFrame with a pre-defined layout and contents.
   */
  public ShopFrame() {
    super();

    this.setTitle("Cell Solutions");
    this.setLayout(new GridLayout(6, 3, 5, 5));

    final ActionListener ourListener = new ItemSelectionUpdated();

    this.add(new JLabel("Service package:", JLabel.RIGHT));
    this.servicePackageBox = new JComboBox<>(PACKAGES);
    this.servicePackageBox.addActionListener(ourListener);
    this.add(this.servicePackageBox);
    this.servicePackageLabel = new JLabel("$-.--");
    this.add(this.servicePackageLabel);

    this.add(new JLabel("Phone:", JLabel.RIGHT));
    this.phoneBox = new JComboBox<>(PHONES);
    this.phoneBox.addActionListener(ourListener);
    this.add(this.phoneBox);
    this.phoneLabel = new JLabel("$-.--");
    this.add(this.phoneLabel);

    this.add(new JLabel("Options:", JLabel.RIGHT));
    this.miscellaneousList = new JList<>(MISCELLANEOUS_ITEMS);
    this.miscellaneousList.addListSelectionListener((ListSelectionListener) ourListener);
    this.miscellaneousList.setVisibleRowCount(2);
    this.add(this.miscellaneousList);
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
    this.updatePrice(this.getSelectedPackage(), this.servicePackageLabel);
    this.updatePrice(this.getSelectedPhone(), this.phoneLabel);
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

    double taxes = 0.00d; // Calculate tax rate on subtotal
    final PurchasableItem phone = this.getSelectedPhone();
    if (phone != null) {
      taxes = phone.getCost() * TAX_RATE;
      // noinspection MagicNumber
      taxes = Math.round(taxes * 100.0d) / 100.0d; // Round taxes to the nearest penny
      this.taxLabel.setText(String.format("$%,.2f", taxes));
    }

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

    PurchasableItem servicePackage = this.getSelectedPackage();
    if (servicePackage != null) {
      subtotal += servicePackage.getCost();
    }

    PurchasableItem phone = this.getSelectedPhone();
    if (phone != null) {
      subtotal += phone.getCost();
    }

    final PurchasableItem[] miscellaneousItems = this.getSelectedMiscellaneous();
    for (final PurchasableItem miscellaneousItem : miscellaneousItems) {
      subtotal += miscellaneousItem.getCost();
    }

    return subtotal;
  }

  /**
   * @return The currently selected service packages's PurchasableItem, or null if nothing is
   *     selected or something other than a PurchasableItem is selected.
   */
  private PurchasableItem getSelectedPackage() {
    final Object obj = this.servicePackageBox.getSelectedItem();
    if (!(obj instanceof PurchasableItem)) { // Make sure this is a PurchasableItem before
                                             // attempting to cast it
      return null;
    }

    return (PurchasableItem) obj;
  }

  /**
   * @return The currently selected phone's PurchasableItem, or null if nothing is selected or
   *     something other than a PurchasableItem is selected.
   */
  private PurchasableItem getSelectedPhone() {
    final Object obj = this.phoneBox.getSelectedItem();
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
