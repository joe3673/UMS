package UMS.Dao;

import UMS.Exception.AlreadyExistException;
import UMS.Model.Item;
import UMS.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {

    private final List<User> userList;



    public UserService() {

        this.userList = new ArrayList<>();
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
            if (username.equalsIgnoreCase(userToFind.getUserName())) {
               return false;

            }
        return true;
    }



    public User createNewUser(String name, String password) {

        User user = new User(name, password);
        try {
            userExistCheck(user.getUserName());
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }

        user.setUserId(createNewUserId());
        userList.add(user);
        return user;

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

        return uuid + "-" + userList.size();
    }

    public List<Item> getItemList(String userId){
        User user = findUserById(userId);
        List<Item> itemList = user.getPersonalItems();


        return itemList;
    }

    public void createDummyUsers(){

        User user1 = new User("Joseph", "Joe123");
        User user2 = new User("Alex", "Alexmane123");
        User user3 = new User("Sarah", "Sarah4436");


        userList.add(user1);
        userList.add(user2);
        userList.add(user3);


    }

}
