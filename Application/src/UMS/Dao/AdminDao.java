package UMS.Dao;

import UMS.Model.Admin;

import java.util.List;

public class AdminDao {


    private List<Admin> admins;




    public void createAdmin(){

        Admin admin = new Admin("Admin", "Abc123");
    }

}
