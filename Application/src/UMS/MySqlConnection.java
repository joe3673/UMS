package UMS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class MySqlConnection {

        private static Connection databaseLink;
        private static final String databaseUser = "root";
        private static final String databasePassword = "Bman3673$";
        private static final String databaseUrl = "jdbc:mysql://localhost:3306/ums";

    public static void createTable() {
        // SQL statement to create the table
        String createTableSQL = "CREATE TABLE items ("
                + "item_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "item_name VARCHAR(255) NOT NULL,"
                + "item_type VARCHAR(50) NOT NULL,"
                + "price DECIMAL(10, 2) NOT NULL,"
                + "item_description TEXT,"
                + "time_item_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "time_item_removed TIMESTAMP"
                + ")";

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
             Statement statement = connection.createStatement()) {
            // Create the table
            statement.execute(createTableSQL);
            System.out.println("Table 'items' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
    public static void deleteTable() {
        // SQL statement to drop (delete) the table
        String dropTableSQL = "DROP TABLE IF EXISTS items";

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
             Statement statement = connection.createStatement()) {
            // Drop (delete) the table
            statement.execute(dropTableSQL);
            System.out.println("Table 'items' deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting table: " + e.getMessage());
        }
    }

        public static Connection getConnection() {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    databaseLink = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Could not open database.");
                    System.exit(1);
            }
            return databaseLink;
        }
        public static void closeConnection() {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error closing database connection.");
                }
            }
        }
    }

