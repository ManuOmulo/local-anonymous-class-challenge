import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

record Employee(String firstName, String lastName, int hireDate) {};

public class Main {
  public static void main(String[] args) {
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee("Emmanuel", "Omulo", 2015));
    employeeList.add(new Employee("Shadrack", "Ongondo", 2019));
    employeeList.add(new Employee("Philip", "Marx", 2012));
    employeeList.add(new Employee("Catherine", "Nekesa", 2023));
    employeeList.add(new Employee("Patrick", "Mahomes", 2017));

    sortList(employeeList);

  }

  public static void sortList(List<Employee> list) {
    class DetailedEmployee {
      private String fullName;
      private int yearsWorked;
      private Employee employee;

      public DetailedEmployee(Employee employee) {
        this.employee = employee;
        this.fullName = getFullName();
        this.yearsWorked = getYearsWorked();
      }

      public String getFullName() {
        return "%s %s".formatted(employee.firstName(), employee.lastName());
      }

      public int getYearsWorked() {
        int currentYear = LocalDate.now().getYear();
        return currentYear - employee.hireDate();
      }

      @Override
      public String toString() {
        return "%s - %d".formatted(fullName, yearsWorked);
      }
    }

    List<DetailedEmployee> employees = new ArrayList<>(list.size());

    var comparator = new Comparator<DetailedEmployee>() {
      @Override
      public int compare(DetailedEmployee e1, DetailedEmployee e2) {
        return e1.fullName.compareTo(e2.fullName);
      }
    };

    for (var employee : list) {
      employees.add(new DetailedEmployee(employee));
    }

    employees.sort(comparator);

    for (var employee : employees) {
      System.out.println(employee);
    }
  }
}
