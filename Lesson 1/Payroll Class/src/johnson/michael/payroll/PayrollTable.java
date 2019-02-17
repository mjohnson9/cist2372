package johnson.michael.payroll;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PayrollTable {

  private Payroll payroll;
  private JFrame frame;
  private JScrollPane pane;
  private JTable table;

  /**
   * Initializes the PayrollTable based on the given Payroll
   *
   * @param payroll The Payroll whose data to use
   */
  public PayrollTable(final Payroll payroll) {
    this.payroll = payroll;

    this.frame = new JFrame("Payroll");
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.table = new JTable(this.getTableData(), this.getColumnNames());
    this.table.setAutoCreateRowSorter(true);
    this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    this.table.setFillsViewportHeight(true);
    this.table.doLayout();
    this.table.setPreferredScrollableViewportSize(this.table.getPreferredSize());

    this.pane = new JScrollPane(this.table);
    this.frame.add(this.pane);

    this.frame.pack();
  }

  /**
   * Makes the PayrollTable visible to the user
   */
  public void show() {
    this.frame.setVisible(true);
  }

  /**
   * Constructs table data from the Payroll object
   *
   * @return Table data suitable for use in a JTable constructor
   */
  private Object[][] getTableData() {
    final Object[][] tableData = new Object[this.payroll.length][4];

    for (int i = 0; i < this.payroll.length; i++) {
      tableData[i][0] = Integer.toString(this.payroll.getEmployeeId(i));
      tableData[i][1] = String.format("%.2f", this.payroll.getPayRate(i));
      tableData[i][2] = Integer.toString(this.payroll.getHours(i));
      tableData[i][3] = String.format("%.2f", this.payroll.getWages(i));
    }

    return tableData;
  }

  /**
   * @return Column names suitable for use in a JTable constructor
   */
  private Object[] getColumnNames() {
    return new Object[]{"Employee ID", "Pay Rate", "Hours Worked", "Gross Wages"};
  }
}
