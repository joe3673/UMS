package UMS.Dao;


    import UMS.Exception.AlreadyExistException;
    import UMS.Model.Item;
    import UMS.Enum.Types;
    import UMS.MySqlConnection;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    public class ItemDao {

        private List<Item> itemList;

        private final Connection connection = MySqlConnection.getConnection();

        //Initializing the item-list in the constructor.
        public ItemDao() {
            this.itemList = new ArrayList<>();


        }

        // Using the check method to see if the item is in the list.
        public void addItem(Item item) {

            for (Item itemToCheck : itemList) {
                if (itemToCheck.getItemId() == item.getItemId()) {
                    throw new AlreadyExistException("The item already Exist");
                }
            }

            Item itemToAdd = new Item(item.getItemName(), item.getItemType(), item.getPrice());
            itemToAdd.setItemId(createId());
            itemList.add(itemToAdd);
        }

        public void removeItem(Item item) {
            int indexToRemove = -1;
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).equals(item)) {
                    indexToRemove = i;
                    break;
                }
            }

            // If item was found, remove it from the list
            if (indexToRemove != -1) {
                itemList.remove(indexToRemove);
            }
        }

        //loop through the list to find the item.
        public Item findItem(int itemId) {

            for (Item item1 : itemList) {
                if (itemId == item1.getItemId()) {
                    return item1;
                }

            }

            System.out.println("This item doesn't exist");
            return null;
        }

        public List<Item> listItemsByType(Types type) {
            List<Item> newList = new ArrayList<>();

            for (Item item : itemList) {
                if (item.getItemType().equals(type)) {
                    newList.add(item);
                }
            }
            return newList;


        }

        //Returns the list of items as a String.
        public String returnItemListAsString() {
            if (itemList.isEmpty()) {

                return "The list is empty";
            }
            return itemList.toString();
        }

        //creates an id that is one more than the length of the itemList
        //Each new Item will always be one more than the size of the array list.
        public int createId() {
            int id = itemList.size();

            return id;
        }


        //creating a list of items to use for display and/or testing purposes.
        public void createDummyItems() {


            Item item1 = new Item("Eggs", Types.FOOD, 2.79);
            Item item2 = new Item("Bread", Types.FOOD, 3.50);
            Item item3 = new Item("Bike", Types.CARS, 199.99);


            item1.setItemDescription("An oval or round object laid by a female bird, reptile, fish, or invertebrate," +
                    " usually containing a developing embryo.");
            item2.setItemDescription("Food made of flour, water, and yeast or another leavening agent, mixed together and baked.");
            item3.setItemDescription("A bicycle or motorcycle.");

            itemList.add(item1);
            itemList.add(item2);
            itemList.add(item3);


        }

        public void displayItems() {
            System.out.println(itemList);
        }



        public List<Item> getAllItemsFromDatabase() {

            List<Item> itemList = new ArrayList<>();
            Connection connection = MySqlConnection.getConnection();

            try {
                String sql = "SELECT * FROM items";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("item_id");
                    String itemName = resultSet.getString("item_name");
                    Types itemType = Types.valueOf(resultSet.getString("item_type"));
                    double price = resultSet.getDouble("price");
                    String itemDescription = resultSet.getString("item_description");

                    Item item = new Item(itemName, itemType, price);
                    item.setItemId(itemId);
                    item.setItemDescription(itemDescription);

                    itemList.add(item);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception
            } finally {
                MySqlConnection.closeConnection();
            }

            return itemList;
        }

        public void addItemToDatabase(Item item) {

            try {
                String sql = "INSERT INTO items (item_name, item_type, price, item_description) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, item.getItemName());
                preparedStatement.setString(2, item.getItemType().toString());
                preparedStatement.setDouble(3, item.getPrice());
                preparedStatement.setString(4, item.getItemDescription());

                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
        public Item findItemInDatabase(int itemId) {

            Item item = null;

            try {
                String sql = "SELECT * FROM items WHERE item_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, itemId);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    item = new Item(
                            resultSet.getString("item_name"),
                            Types.valueOf(resultSet.getString("item_type")),
                            resultSet.getDouble("price")
                    );
                    item.setItemId(itemId);
                    item.setItemDescription(resultSet.getString("item_description"));
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                MySqlConnection.closeConnection();
            }

            return item;
        }

        public void updateItemInDatabase(Item item) {


            try {
                String sql = "UPDATE items SET item_name = ?, item_type = ?, price = ?, item_description = ? WHERE item_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, item.getItemName());
                preparedStatement.setString(2, item.getItemType().toString());
                preparedStatement.setDouble(3, item.getPrice());
                preparedStatement.setString(4, item.getItemDescription());
                preparedStatement.setInt(5, item.getItemId());

                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception
            } finally {
                MySqlConnection.closeConnection();
            }
        }

        public void deleteItemFromDatabase(int itemId) {
            Connection connection = MySqlConnection.getConnection();

            try {
                String sql = "DELETE FROM items WHERE item_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, itemId);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                MySqlConnection.closeConnection();
            }
        }





    }



