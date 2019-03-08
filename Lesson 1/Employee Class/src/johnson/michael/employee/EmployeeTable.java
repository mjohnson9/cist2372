package johnson.michael.employee;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmployeeTable {
  private Employee[] employees;
  private JFrame frame;
  private JScrollPane pane;
  private JTable table;

  /**
   * Initializes the PayrollTable based on the given employees
   *
   * @param employees An array of Employee objects to use as data
   */
  public EmployeeTable(final Employee[] employees) {
    this.employees = employees;

    this.frame = new JFrame("Employees");
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
   * Makes the EmployeeTable visible to the user
   */
  public void show() {
    this.frame.setVisible(true);
  }

  /**
   * Constructs table data from the Employee objects
   *
   * @return Table data suitable for use in a JTable constructor
   */
  private Object[][] getTableData() {
    final Object[][] tableData = new Object[this.employees.length][4];

    for (int i = 0; i < this.employees.length; i++) {
      final Employee employee = this.employees[i];
      tableData[i][0] = employee.getIdNumber();
      tableData[i][1] = employee.getName();
      tableData[i][2] = employee.getDepartment();
      tableData[i][3] = employee.getPosition();
    }

    return tableData;
  }

  /**
   * @return Column names suitable for use in a JTable constructor
   */
  private Object[] getColumnNames() {
    return new Object[] {"Employee ID", "Name", "Department", "Position"};
  }
}