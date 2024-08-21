package UMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class MySqlConnection {

        private static Connection databaseLink;
        private static final String databaseUser = "root";
        private static final String databasePassword = "Bman3673$";
        private static final String databaseUrl = "jdbc:mysql://localhost:3306/ums";


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


