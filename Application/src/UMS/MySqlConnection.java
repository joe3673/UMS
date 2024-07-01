package UMS;

import UMS.Model.Item;

import java.math.BigDecimal;
import java.sql.*;

public  class MySqlConnection {

        private static Connection databaseLink;
        private static final String databaseUser = "root";
        private static final String databasePassword = "Bman3673$";
        private static final String databaseUrl = "jdbc:mysql://localhost:3306/ums";


    public static void createItemTable() {
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

    public static void addItem(Item item) {
        String insertSQL = "INSERT INTO items (item_name, item_type, price, item_description, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, item.getItemName());
            preparedStatement.setString(2, item.getItemType());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setString(4, item.getItemDescription());
            preparedStatement.setInt(5, item.getAmount());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item added successfully.");

                // Retrieve the auto-generated item_id
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int itemId = generatedKeys.getInt(1);
                        item.setItemId(itemId); // Set the generated item_id in the Item object
                    }
                } catch (SQLException e) {
                    System.err.println("Failed to retrieve auto-generated key: " + e.getMessage());
                }

            } else {
                System.out.println("Failed to add item.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding item: " + e.getMessage());
        }
    }
    public static boolean checkUserExists(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE user_name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // User exists if count is greater than 0
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error checking user existence: " + e.getMessage());
        }
        return false;
    }

    public static boolean authenticateUser(String username, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE user_name = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Authentication successful if count is greater than 0
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        return false;
    }


    public static void updateItemTableAmount(int itemId, int newAmount) {
        String updateSQL = "UPDATE items SET amount = ? WHERE item_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setInt(1, newAmount);
            preparedStatement.setInt(2, itemId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item amount updated successfully.");
            } else {
                System.out.println("No item found with id " + itemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error updating item amount: " + e.getMessage());
        }
    }

    public static void deleteItemTable() {
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

    public static void createUserTable() {
        // SQL statement to create the table
        String createTableSQL = "CREATE TABLE users ("
                + "user_id INT AUTO_INCREMENT PRIMARY KEY,"
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

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
             Statement statement = connection.createStatement()) {
            // Create the table
            statement.execute(createTableSQL);
            System.out.println("Table 'users' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void addUser(String username, String password, int tokens, Double cash, Double accountBalance) {
        String insertSQL = "INSERT INTO users (user_name, password, tokens, cash, account_balance) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, tokens);
            preparedStatement.setDouble(4, cash);
            preparedStatement.setDouble(5, accountBalance);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    public static void updateUserProfile(int userId, String newUserName, String newPassword) {
            String updateSQL = "UPDATE users SET user_name = ?, password = ? WHERE user_id = ?";

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
                preparedStatement.setString(1, newUserName);
                preparedStatement.setString(2, newPassword);
                preparedStatement.setInt(3, userId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User profile updated successfully.");
                } else {
                    System.out.println("No user found with id " + userId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error updating user profile: " + e.getMessage());
            }
        }

    public static void updateUserGameCashAndTokens(int userId, BigDecimal newCash, int newTokens) {
        String updateSQL = "UPDATE users SET cash = ?, tokens = ? WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setBigDecimal(1, newCash);
            preparedStatement.setInt(2, newTokens);
            preparedStatement.setInt(3, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User game cash and tokens updated successfully.");
            } else {
                System.out.println("No user found with id " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error updating user game cash and tokens: " + e.getMessage());
        }
    }




    public static void updateUserItemsAndCash(int userId, String newPersonalItems, BigDecimal newCash) {
        String updateSQL = "UPDATE users SET personal_items = ?, cash = ? WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newPersonalItems);
            preparedStatement.setBigDecimal(2, newCash);
            preparedStatement.setInt(3, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User items and cash updated successfully.");
            } else {
                System.out.println("No user found with id " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error updating user items and cash: " + e.getMessage());
        }
    }

    public static void deleteUserTable() {
        // SQL statement to drop (delete) the table
        String dropTableSQL = "DROP TABLE IF EXISTS users";

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
             Statement statement = connection.createStatement()) {
            // Drop (delete) the table
            statement.execute(dropTableSQL);
            System.out.println("Table 'users' deleted successfully.");
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


