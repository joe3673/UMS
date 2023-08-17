package UMS.Dao;


    import UMS.Exception.AlreadyExistException;
    import UMS.Model.Item;
    import UMS.Enum.Types;

    import java.util.ArrayList;
import java.util.List;

    public class ItemDao {



        private List<Item>itemList;

        //Initializing the item-list in the constructor.
        public ItemDao() {
            this.itemList = new ArrayList<>(itemList);


        }

        // Using the check method to see if the item is in the list.
        public void addItem(Item item) {

            for(Item itemToCheck: itemList){
                if (itemToCheck.getItemId()==item.getItemId()){
                    throw new AlreadyExistException("The item already Exist");
                }
            }

            Item itemToAdd = new Item(item.getItemName(), item.getItemType());
            itemToAdd.setItemId(createId());
            itemList.add(itemToAdd);
        }
        public void removeItem(Item item){
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
        public List<Item> listItemsByType(String type){
            List<Item> newList = new ArrayList<>();

            for (Item item: itemList) {
                if (item.getItemType().equals(type)) {
                    newList.add(item);
                }
            }
            return newList;


        }

        //Returns the list of items as a String.
        public String returnItemListAsString() {
            if (itemList.isEmpty()){

                return  "The list is empty";
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


            Item item1 = new Item("Eggs", Types.FOOD);
            Item item2 = new Item("Bread",Types.FOOD);
            Item item3 = new Item("Bike",Types.CARS);

            item1.setItemDescription("An oval or round object laid by a female bird, reptile, fish, or invertebrate," +
                    " usually containing a developing embryo.");
            item2.setItemDescription("Food made of flour, water, and yeast or another leavening agent, mixed together and baked.");
            item3.setItemDescription("A bicycle or motorcycle.");

            itemList.add(item1);
            itemList.add(item2);
            itemList.add(item3);


        }
    }


