package johnson.michael.theaterrevenue;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ResultsFrame extends JFrame {
  private static final double PERCENT_KEPT = 0.2d;

  private final double adultTicketPrice;
  private final int adultTicketsSold;

  private final double childTicketPrice;
  private final int childTicketsSold;

  public ResultsFrame(final double adultTicketPrice, final int adultTicketsSold,
      final double childTicketPrice, final int childTicketsSold) {
    super();

    this.adultTicketPrice = adultTicketPrice;
    this.adultTicketsSold = adultTicketsSold;
    this.childTicketPrice = childTicketPrice;
    this.childTicketsSold = childTicketsSold;

    this.setLayout(new GridLayout(6, 2, 5, 5));

    this.addAdultRevenue();
    this.addChildRevenue();
    this.addTotalRevenue();

    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
    this.setSize(packedSize.width + 20, packedSize.height + 20);
  }

  private void addAdultRevenue() {
    this.add(new JLabel("Gross revenue from adult tickets:", JLabel.RIGHT));
    this.add(new JLabel(String.format("$%,.2f", this.getAdultGrossRevenue())));

    this.add(new JLabel("Net revenue from adult tickets:", JLabel.RIGHT));
    this.add(new JLabel(String.format("$%,.2f", this.getAdultNetRevenue())));
  }

  private void addChildRevenue() {
    this.add(new JLabel("Gross revenue from child tickets:", JLabel.RIGHT));
    this.add(new JLabel(String.format("$%,.2f", this.getChildGrossRevenue())));

    this.add(new JLabel("Net revenue from child tickets:", JLabel.RIGHT));
    this.add(new JLabel(String.format("$%,.2f", this.getChildNetRevenue())));
  }

  private void addTotalRevenue() {
    this.add(new JLabel("Total revenue from tickets:", JLabel.RIGHT));
    this.add(new JLabel(String.format("$%,.2f", this.getTotalGrossRevenue())));

    this.add(new JLabel("Net revenue from all tickets:", JLabel.RIGHT));
    this.add(new JLabel(String.format("$%,.2f", this.getTotalNetRevenue())));
  }

  public double getAdultGrossRevenue() {
    return this.adultTicketPrice * (double) this.adultTicketsSold;
  }

  public double getAdultNetRevenue() {
    return this.getAdultGrossRevenue() * PERCENT_KEPT;
  }

  public double getChildGrossRevenue() {
    return this.childTicketPrice * (double) this.childTicketsSold;
  }

  public double getChildNetRevenue() {
    return this.getChildGrossRevenue() * PERCENT_KEPT;
  }

  public double getTotalGrossRevenue() {
    return this.getAdultGrossRevenue() + this.getChildGrossRevenue();
  }

  public double getTotalNetRevenue() {
    return this.getTotalGrossRevenue() * PERCENT_KEPT;
  }
}
