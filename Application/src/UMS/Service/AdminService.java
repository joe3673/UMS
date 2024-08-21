package UMS.Service;

import UMS.Dao.AdminDao;
import UMS.Dao.ItemDao;
import UMS.Model.Admin;
import UMS.Model.Item;

import java.util.List;
import java.util.Objects;

public class AdminService {

    AdminDao adminDao;

    ItemDao itemDao;

    private List<Admin> admins;




    public void createAdmin(){

        Admin admin = new Admin("Admin", "Abc123");
    }
    public boolean adminAuthentication(String userName, String password){
        return Objects.equals(userName, "Admin") && Objects.equals(password, "Abc123");
    }

    public void createTable(){

        adminDao.createItemTable();

    }
    public Item createNewItem(String itemName, String itemDescription, double price, String type, int amount){
        Item item = new Item(itemName, type, price);
        item.setAmount(amount);
        item.setItemDescription(itemDescription);

        itemDao.addItem(item);
        return item;
    }




}
