package UMS.Dao;

import UMS.Model.Item;
import UMS.MySqlConnection;

import java.sql.*;

public class UserItemDao {

    public void addUserItem(Item item) {
        String insertSQL = "INSERT INTO items (item_name, item_type, price, item_description, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = MySqlConnection.getConnection();
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


    public void updateItemTableAmount(int itemId, int newAmount) {
        String updateSQL = "UPDATE items SET amount = ? WHERE item_id = ?";

        try (Connection connection = MySqlConnection.getConnection();
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

}

}
