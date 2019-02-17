package johnson.michael.employee;

public class Employee {

  private String name;
  private int idNumber;
  private String department;
  private String position;

  Employee(final String name, final int employeeId, final String department,
      final String position) {
    this.setName(name);
    this.setIdNumber(employeeId);
    this.setDepartment(department);
    this.setPosition(position);
  }

  Employee(final String name, final int employeeId) {
    this(name, employeeId, "", "");
  }

  Employee() {
    this("", 0);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(int idNumber) {
    this.idNumber = idNumber;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
