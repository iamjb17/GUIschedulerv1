package Scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    public static void getConnection() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306")) {
            System.out.println("connection successful");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
