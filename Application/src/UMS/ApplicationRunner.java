package UMS;


import UMS.Dao.ItemDao;
import UMS.Enum.Types;
import UMS.Model.Item;

import java.util.List;

public class ApplicationRunner {

    public static void main(String [] args){

      //  UserInterface userInterface = new UserInterface();

       // userInterface.userAuthentication();
        MySqlConnection.createTable();

        ItemDao itemDao = new ItemDao();
        itemDao.addItemToDatabase(new Item("loop", Types.CARS,8.90));
        itemDao.addItemToDatabase( new Item("baker", Types.FOOD,9.93));
        List<Item> items = itemDao.getAllItemsFromDatabase();
        System.out.println(items);
        MySqlConnection.closeConnection();


    }

}
