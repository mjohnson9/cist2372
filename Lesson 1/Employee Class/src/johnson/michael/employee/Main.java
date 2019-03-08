package johnson.michael.employee;

public class Main {
  public static void main(String[] args) {
    final Employee[] employees =
        new Employee[] {new Employee("Susan Meyers", 47899, "Accounting", "Vice President"),
            new Employee("Mark Jones", 39119, "IT", "Programmer"),
            new Employee("Joy Rogers", 81774, "Manufacturing", "Engineer")};

    displayEmployees(employees);
  }

  public static void displayEmployees(final Employee[] employees) {
    final EmployeeTable table = new EmployeeTable(employees);
    table.show();
  }
}
