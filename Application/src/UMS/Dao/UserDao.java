package UMS.Dao;

import UMS.Model.User;
import UMS.MySqlConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDao {

    Connection MySQLConnection;

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());





    public boolean checkUserExists(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE user_name = ?";
        try (Connection connection = MySqlConnection.getConnection();
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

    public boolean authenticateUser(String username, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE user_name = ? AND password = ?";
        try (Connection connection = MySqlConnection.getConnection();
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

    public void addUser(int userID, String username, String password, int tokens, double cash, double accountBalance) {
        String insertSQL = "INSERT INTO users (user_ID, user_name, password, tokens, cash, account_balance) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, tokens);
            preparedStatement.setDouble(5, cash);
            preparedStatement.setDouble(6, accountBalance);

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

    public List<User> getUsersByUsername(String username) {
        String query = "SELECT user_id, user_name, password, tokens, cash, account_balance FROM users WHERE user_name LIKE ?";
        List<User> users = new ArrayList<>();

        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + username + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("user_id");
                    String retrievedUsername = resultSet.getString("user_name");
                    String password = resultSet.getString("password");
                    int tokens = resultSet.getInt("tokens");
                    double cash = resultSet.getDouble("cash");
                    double accountBalance = resultSet.getDouble("account_balance");

                    users.add(new User(id, retrievedUsername, password, tokens, cash, accountBalance));
                }

                if (users.isEmpty()) {
                    System.out.println("No users found with username containing '" + username + "'");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving users: " + e.getMessage());
        }
        return users;
    }

    public Optional<User> getUserById(int userId) {
        String query = "SELECT user_id, user_name, password, tokens, cash, account_balance FROM users WHERE user_id = ?";
        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("user_id");
                    String username = resultSet.getString("user_name");
                    String password = resultSet.getString("password");
                    int tokens = resultSet.getInt("tokens");
                    double cash = resultSet.getDouble("cash");
                    double accountBalance = resultSet.getDouble("account_balance");

                    return Optional.of(new User(id, username, password, tokens, cash, accountBalance));
                } else {
                    System.out.println("No user found with id {}");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user: {}");
        }
        return Optional.empty();
    }



    public void updateUserProfile(int userId, String newUserName, String newPassword) {
        String updateSQL = "UPDATE users SET user_name = ?, password = ? WHERE user_id = ?";

        try (Connection connection = MySqlConnection.getConnection();
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

    public void updateUserGameCashAndTokens(int userId, BigDecimal newCash, int newTokens) {
        String updateSQL = "UPDATE users SET cash = ?, tokens = ? WHERE user_id = ?";

        try (Connection connection = MySqlConnection.getConnection();
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




    public void updateUserCash(int userId, double newCash) {
        String updateSQL = "UPDATE users SET cash = ? WHERE user_id = ?";

        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setDouble(1, newCash);
            preparedStatement.setInt(2, userId);

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

    public void updateUserAccountBalance(int userId, double accountBalance) {
        String updateSQL = "UPDATE users SET account_balance  = ? WHERE user_id = ?";

        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setDouble(1, accountBalance);
            preparedStatement.setInt(2, userId);
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
    public int getUserCount() {
        String countQuery = "SELECT COUNT(*) FROM users";
        int userCount = 0;

        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(countQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                userCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error counting users: " + e.getMessage());
        }

        return userCount;
    }



}
