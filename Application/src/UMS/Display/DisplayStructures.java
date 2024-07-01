package UMS.Display;

import UMS.Dao.ItemDao;
import UMS.Dao.UserService;
import UMS.Model.Item;
import UMS.Model.User;

import java.util.List;

public class DisplayStructures {

    ItemDao itemDao;

    UserService userDao;


    public DisplayStructures(){
        itemDao = new ItemDao();
        userDao = new UserService();
    }

    public void displayUserAsTable(User user){

        List<Item> itemList = user.getPersonalItems();
        System.out.println("User: " + user.getUserName());
        System.out.println("User Tokens: T" + user.getTokens());
        System.out.println("User cash: $" + user.getCash());
        System.out.println("User Account Balance: $" + user.getAccountBalance());
        System.out.println("User Items: " );
        System.out.println("User Item 1:" + itemList.get(0).getItemName() + " Price: $" + itemList.get(0).getPrice());
        System.out.println("User Item 2:" + itemList.get(1).getItemName() + " Price: $" + itemList.get(1).getPrice());
        System.out.println("User Item 3:" + itemList.get(2).getItemName() + " Price: $" + itemList.get(2).getPrice());
        System.out.println("User Item 4:" + itemList.get(3).getItemName() + " Price: $" + itemList.get(3).getPrice());
        System.out.println("User Item 5:" + itemList.get(4).getItemName() + " Price: $" + itemList.get(4).getPrice());
        System.out.println("More items....");
    }
    public void displayStoreAsTable(){
        List<Item> itemList = itemDao.getAllItemsFromDatabase();
        System.out.println("Store Items: ");
        System.out.println("Item 1: " + itemList.get(0).getItemName() + " Price: $" + itemList.get(0).getPrice());
        System.out.println("Item 2: " + itemList.get(1).getItemName() + " Price: $" + itemList.get(1).getPrice());
        System.out.println("Item 3: " +  itemList.get(2).getItemName() + " Price: $" + itemList.get(2).getPrice());
        System.out.println("Item 4: " + itemList.get(3).getItemName() + " Price: $" + itemList.get(3).getPrice());
        System.out.println("Item 5: " + itemList.get(4).getItemName() + " Price: $" + itemList.get(4).getPrice());
        System.out.println("More items....");
    }


}
