package UMS.Service;

import UMS.Dao.UserDao;
import UMS.Dao.UserItemDao;
import UMS.Model.User;

public class UserService {

    UserDao userDao;

    UserItemDao userItemDao;


    private UserService(UserDao userDao, UserItemDao userItemDao){
        this.userDao = userDao;
        this.userItemDao = userItemDao;
    }



    public void createDummyUsers(){


        userDao.addUser(1, "JoeyMula303","Hoolahoops",0, 0.0,10.0 );
        userDao.addUser(2, "Hellokitkat", "rawchicken67",0, 0.0,10.0);
        userDao.addUser(3,"klionLits09","TooDangerous45",0, 0.0,10.0);

    }

    public User createNewUser(String userName, String password){
        if(userName == null || password == null){
            throw new RuntimeException("Username and/or Password Can not be empty!");
        }
        if(!userDao.checkUserExists(userName)){
            System.out.println("Incorrect Username or password.");
            return null;
        }
        User user = new User(userDao.getUserCount(), userName, password, 0, 0.0, 10.0);
        userDao.addUser(userDao.getUserCount(), userName, password, 0, 0.0, 10.0);
        return user;
    }

    public Boolean authenticateUser(String userName, String password){
        return userDao.authenticateUser(userName, password);
    }

    public User updateUserBankAccount(User user, double newAmount){
        userDao.updateUserAccountBalance(user.getUserId(), newAmount);
        return user;
    }

    public User updateUserCash(User user){
        userDao.updateUserCash(user.getUserId(),user.getCash());
        return user;
    }

    public User updateTokens(User user){
        userDao.
    }

}
