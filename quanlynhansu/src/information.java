import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.Date;
public class information {
    protected String host = "jdbc:mysql://localhost:3306/quanlynhansu";
    protected String username = "root";
    protected String password = "";

    protected Connection connection;
    protected Scanner scanner = new Scanner(System.in);

    public information() {
        try{
            connection = DriverManager.getConnection(host, username, password);
        } catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
        }
    }
    protected void insert(int id, String employeecode, String firstname, String lastname, String gender, String address, String email, String department, Date date_of_birth, String status, Double salary_coefficient, Double basic_salary) {
        String sqlcheck = "SELECT * FROM employees WHERE id = ?";
        try{
            PreparedStatement checkStatement = connection.prepareStatement(sqlcheck);
            checkStatement.setInt(1, id);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("Employee already exists");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        String sql = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, employeecode);
            statement.setString(3, firstname);
            statement.setString(4, lastname);
            statement.setString(5, gender);
            statement.setString(6, address);
            statement.setString(7, email);
            statement.setString(8, department);
            statement.setDate(9, (java.sql.Date) date_of_birth);
            statement.setString(10, status);
            statement.setDouble(11, salary_coefficient);
            statement.setDouble(12, basic_salary);

            int check = statement.executeUpdate();
            if (check == 1){
                System.out.println("Employee added successfully");
            } else {
                System.out.println("Failed to add employee");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void inputEmployeeInfo() {
        System.out.println("Enter employee id: ");
        int id = scanner.nextInt();
        System.out.println("Enter employee code: ");
        String employeecode = scanner.nextLine();
        System.out.println("Enter firstname: ");
        String firstname = scanner.nextLine();
        System.out.println("Enter lastname: ");
        String lastname = scanner.nextLine();
        System.out.println("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter department: ");
        String department = scanner.nextLine();
        System.out.println("Enter date of birth: ");
        String dob = scanner.nextLine();
        Date date_of_birth = Date.valueOf(dob);
        System.out.println("Enter status: ");
        String status = scanner.nextLine();
        System.out.println("Enter salary coefficient: ");
        Double salary_coefficient = scanner.nextDouble();
        System.out.println("Enter basic salry: ");
        Double basic_salary = scanner.nextDouble();

        insert(id, employeecode, firstname, lastname, gender, address, email, department, date_of_birth, status, salary_coefficient, basic_salary);
    }

    protected 
}
