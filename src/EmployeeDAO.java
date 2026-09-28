import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
public void addEmployee(Employee employee) {

    String sql =
            "INSERT INTO employees (name, email, salary) VALUES (?, ?, ?)";

    try (
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, employee.getName());
        ps.setString(2, employee.getEmail());
        ps.setDouble(3, employee.getSalary());

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Employee added successfully!");
        }

    } catch (Exception e) {

        e.printStackTrace();
    }
}


    public List<Employee> getAllEmployees() {

    List<Employee> employees = new ArrayList<>();

    String sql = "SELECT * FROM employees";

    try (
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            double salary = rs.getDouble("salary");

            Employee employee =
                    new Employee(id, name, email, salary);

            employees.add(employee);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return employees;
}
public Employee findEmployeeById(int id) {

    String sql = "SELECT * FROM employees WHERE id = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Employee employee = new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getDouble("salary")
            );

            rs.close();
            ps.close();
            con.close();

            return employee;
        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();
    }

    return null;
}
public boolean updateEmployeeSalary(int id, double salary) {

    String sql = "UPDATE employees SET salary = ? WHERE id = ?";

    try (
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setDouble(1, salary);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();

        return rows > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
}
public boolean deleteEmployee(int id) {

    String sql = "DELETE FROM employees WHERE id = ?";

    try (
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        return rows > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
}
}