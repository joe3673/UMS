package UMS.Dao;

import UMS.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDao {

    Connection MySQLConnection;


    public void createItemTable() {
        // SQL statement to create the table
        String createTableSQL = "CREATE TABLE items ("
                + "item_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "item_name VARCHAR(20) NOT NULL,"
                + "item_type VARCHAR(20) NOT NULL,"
                + "price DECIMAL(10, 2) NOT NULL,"
                + "item_description TEXT,"
                + "time_item_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "amount INT NOT NULL DEFAULT 0"
                + ")";

        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            // Create the table
            statement.execute(createTableSQL);
            System.out.println("Table 'items' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public void deleteItemTable() {
        // SQL statement to drop (delete) the table
        String dropTableSQL = "DROP TABLE IF EXISTS items";

        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            // Drop (delete) the table
            statement.execute(dropTableSQL);
            System.out.println("Table 'items' deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting table: " + e.getMessage());
        }
    }


    public static void createUserTable() {
        // SQL statement to create the table
        String createTableSQL = "CREATE TABLE users ("
                + "user_id INT PRIMARY KEY,"
                + "user_name VARCHAR(16) NOT NULL,"
                + "password VARCHAR(16) NOT NULL,"
                + "tokens INT NOT NULL,"
                + "personal_items TEXT,"
                + "item_description TEXT,"
                + "time_user_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "time_user_lastLogin TIMESTAMP,"
                + "time_user_logout TIMESTAMP,"
                + "cash DECIMAL(10, 2) NOT NULL,"
                + "account_balance DECIMAL(10, 2) NOT NULL"
                + ")";

        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            // Create the table
            statement.execute(createTableSQL);
            System.out.println("Table 'users' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void deleteUserTable() {
        // SQL statement to drop (delete) the table
        String dropTableSQL = "DROP TABLE IF EXISTS users";

        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            // Drop (delete) the table
            statement.execute(dropTableSQL);
            System.out.println("Table 'users' deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting table: " + e.getMessage());
        }
    }

    public void createUserItemTable() {

        String createTableSQL = "CREATE TABLE items ("
                + "user_id INT PRIMARY KEY,"
                + "item_id INT NOT NULL,"
                + "item_name VARCHAR(20) NOT NULL,"
                + "item_type VARCHAR(20) NOT NULL,"
                + "price DECIMAL(10, 2) NOT NULL,"
                + "item_description TEXT,"
                + "time_item_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "amount INT NOT NULL DEFAULT 0"
                + ")";

        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            // Create the table
            statement.execute(createTableSQL);
            System.out.println("Table 'items' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public void deleteUserItemTable() {
        // SQL statement to drop (delete) the table
        String dropTableSQL = "DROP TABLE IF EXISTS items";

        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            // Drop (delete) the table
            statement.execute(dropTableSQL);
            System.out.println("Table 'items' deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting table: " + e.getMessage());
        }
    }
}
