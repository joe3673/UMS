package UMS.Dao;

import UMS.Exception.AlreadyExistException;
import UMS.Model.Item;
import UMS.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao {

    private final List<User> userList;

    private final ItemDao itemDao;


    public UserDao() {

        this.userList = new ArrayList<>();
        itemDao = new ItemDao();
    }

    //Accepts the username and password to build a new user object.
    //If the user object is not in the list of users, then it will reject the user.

    public Boolean verifyUser(String userName, String password) {

        if (userName == null || password == null) {
            return false;
        }

        User user = new User(userName, password);
        for (User userToFind : userList) {
            if (userToFind.getUserName().equals(user.getUserName())) {
                if (user.checkPassword(password)) {

                    return true;
                }
            }
        }


        return false;
    }

    //Method to find the user by username.

    public boolean userExistCheck(String username) {

        for (User userToFind : userList)
            if (username.equals(userToFind.getUserName())) {
                throw new AlreadyExistException("The user already exist.");

            }
        return true;
    }


    public void createNewUser(String name, String password, String email) {

        User user = new User(name, password, email);
        try {
            userExistCheck(user.getUserName());
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }

        user.setUserId(createNewUserId());
        userList.add(user);

    }

    public User findUserById(String userId) {

        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        System.out.println("Sorry We couldn't find the user");
        return null;
    }


    public String createNewUserId() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString() + "-" + userList.size();
    }






    public List<Item> getItemList(String userId){
        User user = findUserById(userId);
        List<Item> itemList = user.getPersonalItems();


        return itemList;
    }

    public void createDummyUsers(){

        User user1 = new User("Joseph", "Joe123", "JoesephB@myself.com");
        User user2 = new User("Alex", "Alexmane123", "Alexm@gmail.com.com");
        User user3 = new User("Sarah", "Sarah4436", "SSsarah@aol.com");


        userList.add(user1);
        userList.add(user2);
        userList.add(user3);


    }

}
