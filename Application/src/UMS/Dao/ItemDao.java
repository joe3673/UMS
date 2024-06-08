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
            item1.setItemDescription("An oval or round object laid by a female bird, reptile, fish, or invertebrate, usually containing a developing embryo.");
            itemList.add(item1);

            Item item2 = new Item("Bread", Types.FOOD, 3.50);
            item2.setItemDescription("Food made of flour, water, and yeast or another leavening agent, mixed together and baked.");
            itemList.add(item2);

            Item item3 = new Item("Bike", Types.CARS, 199.99);
            item3.setItemDescription("A bicycle or motorcycle.");
            itemList.add(item3);

            Item item4 = new Item("Milk", Types.FOOD, 1.99);
            item4.setItemDescription("A white liquid produced by the mammary glands of mammals.");
            itemList.add(item4);

            Item item5 = new Item("Cheese", Types.FOOD, 4.79);
            item5.setItemDescription("A dairy product made from curdled or cultured milk.");
            itemList.add(item5);

            Item item6 = new Item("T-shirt", Types.CLOTHES, 14.99);
            item6.setItemDescription("A casual top, generally made of cotton, having the shape of a T when spread out flat.");
            itemList.add(item6);

            Item item7 = new Item("Jeans", Types.CLOTHES, 39.99);
            item7.setItemDescription("Denim trousers that are typically blue and durable.");
            itemList.add(item7);

            Item item8 = new Item("Chess", Types.GAMES, 29.99);
            item8.setItemDescription("A two-player strategy board game played on an 8x8 grid.");
            itemList.add(item8);

            Item item9 = new Item("Monopoly", Types.GAMES, 19.99);
            item9.setItemDescription("A board game where players trade, buy, and sell properties to win.");
            itemList.add(item9);

            Item item10 = new Item("Car", Types.CARS, 24999.99);
            item10.setItemDescription("A road vehicle, typically with four wheels, powered by an internal combustion engine.");
            itemList.add(item10);

            Item item11 = new Item("Truck", Types.CARS, 34999.99);
            item11.setItemDescription("A large, heavy motor vehicle for transporting goods or troops.");
            itemList.add(item11);

            Item item12 = new Item("Apple", Types.FOOD, 0.99);
            item12.setItemDescription("A round fruit with red or green skin and a whitish interior.");
            itemList.add(item12);

            Item item13 = new Item("Orange", Types.FOOD, 1.29);
            item13.setItemDescription("A citrus fruit with a tough, bright orange rind.");
            itemList.add(item13);

            Item item14 = new Item("Sweater", Types.CLOTHES, 24.99);
            item14.setItemDescription("A knitted garment worn on the upper body.");
            itemList.add(item14);

            Item item15 = new Item("Hat", Types.CLOTHES, 19.99);
            item15.setItemDescription("A shaped covering for the head worn for warmth, as a fashion item, or as part of a uniform.");
            itemList.add(item15);

            Item item16 = new Item("Soccer Ball", Types.GAMES, 25.99);
            item16.setItemDescription("A spherical ball used in the game of soccer.");
            itemList.add(item16);

            Item item17 = new Item("Basketball", Types.GAMES, 29.99);
            item17.setItemDescription("A spherical ball used in the game of basketball.");
            itemList.add(item17);

            Item item18 = new Item("Motorcycle", Types.CARS, 7999.99);
            item18.setItemDescription("A two-wheeled vehicle that is powered by a motor and has no pedals.");
            itemList.add(item18);

            Item item19 = new Item("Scooter", Types.CARS, 499.99);
            item19.setItemDescription("A two-wheeled vehicle that is powered by a small engine.");
            itemList.add(item19);

            Item item20 = new Item("Banana", Types.FOOD, 0.69);
            item20.setItemDescription("A long, curved fruit with a yellow skin and soft, sweet, white flesh inside.");
            itemList.add(item20);

            Item item21 = new Item("Lettuce", Types.FOOD, 1.49);
            item21.setItemDescription("A leafy green vegetable often used in salads.");
            itemList.add(item21);

            Item item22 = new Item("Yogurt", Types.FOOD, 3.29);
            item22.setItemDescription("A dairy product produced by bacterial fermentation of milk.");
            itemList.add(item22);

            Item item23 = new Item("Pizza", Types.FOOD, 5.99);
            item23.setItemDescription("A dish of Italian origin consisting of a flat, round base of dough baked with a topping of tomatoes and cheese.");
            itemList.add(item23);

            Item item24 = new Item("Pasta", Types.FOOD, 1.79);
            item24.setItemDescription("An Italian food typically made from an unleavened dough of wheat flour mixed with water and formed into sheets or other shapes.");
            itemList.add(item24);

            Item item25 = new Item("Rice", Types.FOOD, 2.49);
            item25.setItemDescription("A cereal grain that is a staple food for a large part of the world's human population.");
            itemList.add(item25);

            Item item26 = new Item("Juice", Types.FOOD, 3.99);
            item26.setItemDescription("A drink made from the extraction or pressing of the natural liquid contained in fruit and vegetables.");
            itemList.add(item26);

            Item item27 = new Item("Water", Types.FOOD, 0.99);
            item27.setItemDescription("A transparent, tasteless, odorless, and nearly colorless chemical substance, which is the main constituent of Earth's streams, lakes, and oceans.");
            itemList.add(item27);

            Item item28 = new Item("Wine", Types.FOOD, 9.99);
            item28.setItemDescription("An alcoholic drink made from fermented grapes or other fruits.");
            itemList.add(item28);

            Item item29 = new Item("Beer", Types.FOOD, 6.99);
            item29.setItemDescription("An alcoholic drink made from yeast-fermented malt flavored with hops.");
            itemList.add(item29);

            Item item30 = new Item("Cereal", Types.FOOD, 3.59);
            item30.setItemDescription("A breakfast food made from processed grains.");
            itemList.add(item30);

            Item item31 = new Item("Butter", Types.FOOD, 2.89);
            item31.setItemDescription("A solid dairy product made by churning fresh or fermented cream or milk.");
            itemList.add(item31);

            Item item32 = new Item("Fish", Types.FOOD, 7.49);
            item32.setItemDescription("A limbless cold-blooded vertebrate animal with gills and fins living wholly in water.");
            itemList.add(item32);


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

            String sql = "SELECT * FROM items WHERE item_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, itemId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        item = new Item(
                                resultSet.getString("item_name"),
                                Types.valueOf(resultSet.getString("item_type")),
                                resultSet.getDouble("price")
                        );
                        item.setItemId(itemId);
                        item.setItemDescription(resultSet.getString("item_description"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid item type in database: " + e.getMessage());
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



