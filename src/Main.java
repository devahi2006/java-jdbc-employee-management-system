import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();

        try {

            System.out.println("Database connected successfully!");

            int choice;

            do {

                System.out.println("\n========== EMPLOYEE MANAGEMENT SYSTEM ==========");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Search Employee");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();

                switch (choice) {

                    case 1: {

                        scanner.nextLine();

                        System.out.print("Enter employee name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter employee email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter employee salary: ");
                        double salary = scanner.nextDouble();

                        Employee employee =
                                new Employee(0, name, email, salary);

                        employeeDAO.addEmployee(employee);

                        break;
                    }

                    case 2: {

                        List<Employee> employees =
                                employeeDAO.getAllEmployees();

                        System.out.println("\n========== EMPLOYEE LIST ==========");

                        for (Employee employee : employees) {

                            System.out.println("ID: " + employee.getId());
                            System.out.println("Name: " + employee.getName());
                            System.out.println("Email: " + employee.getEmail());
                            System.out.println("Salary: " + employee.getSalary());
                            System.out.println("----------------------------------");
                        }

                        break;
                    }

                   case 3: {

    System.out.print("Enter employee ID: ");
    int id = scanner.nextInt();

    Employee employee =
            employeeDAO.findEmployeeById(id);

    if (employee != null) {

        System.out.println("\n========== EMPLOYEE FOUND ==========");
        System.out.println("ID: " + employee.getId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Salary: " + employee.getSalary());

    } else {

        System.out.println("Employee not found!");
    }

    break;
}

                    case 4: {

    System.out.print("Enter employee ID: ");
    int id = scanner.nextInt();

    System.out.print("Enter new salary: ");
    double salary = scanner.nextDouble();

    boolean updated =
            employeeDAO.updateEmployeeSalary(id, salary);

    if (updated) {

        System.out.println("Employee updated successfully!");

    } else {

        System.out.println("Employee not found!");
    }

    break;
}
case 5: {

    System.out.print("Enter employee ID to delete: ");
    int id = scanner.nextInt();

    boolean deleted =
            employeeDAO.deleteEmployee(id);

    if (deleted) {

        System.out.println("Employee deleted successfully!");

    } else {

        System.out.println("Employee not found!");
    }

    break;
}
                    case 6:

                        System.out.println("Thank you!");
                        break;

                    default:

                        System.out.println("Invalid choice!");
                }

            } while (choice != 6);

            scanner.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}