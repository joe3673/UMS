package UMS.Display;

import UMS.Dao.AdminDao;
import UMS.Dao.ItemDao;
import UMS.Dao.UserDao;
import UMS.Exception.AlreadyExistException;
import UMS.Games.DailyPick;
import UMS.Games.NumberPickGame;
import UMS.Games.SlotsGame;
import UMS.Games.TriviaGame;
import UMS.Model.User;
import UMS.MySqlConnection;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class UserInterface {

    private UserDao userDao;

    private ItemDao itemDao;

    private AdminDao adminDao;

    private DailyPick dailyPick;

    private NumberPickGame numberPickGame;

    private SlotsGame slotsGame;

    private TriviaGame triviaGame;

    Scanner scanner = new Scanner(System.in);



    public UserInterface(){
        userDao = new UserDao();
        itemDao = new ItemDao();
        userDao.createDummyUsers();
        adminDao.createAdmin();
        MySqlConnection.getConnection();

    }




    //1. Have users authenticate with Username and Password. If new user create account.
    // Populates User List with dummy users
    // Checks the User Database for matching for any matching information before creating a new user
    //2. If user can't authenticate, offer the option to create a user or close the application.
    public boolean userAuthentication(){

        System.out.println("Welcome to the User Item Management System. \n \n ");
        System.out.println("Do you have a UMS account? \n  Y or N  \n Type 'End' to end the application\n");

        String answer1 = scanner.next();
        scanner.nextLine();
        try {
            if (answer1.equals("N") || answer1.equals("n")) {
                System.out.println("You need to create an account to access the UMS. \n ");
                System.out.println("Choose a username. ");
                String username = scanner.nextLine();
                if (!userDao.userExistCheck(username)) {
                    throw new AlreadyExistException("The username already exist");
                }

                System.out.println("Choose a password.");
                String userPassword = scanner.nextLine();
                userDao.createNewUser(username, userPassword);
                System.out.println("Congratulations. You have successfully created a user.");
                return userAuthentication();
            }

            else if (answer1.equalsIgnoreCase("End")) {
                System.out.println("Thank you for using the User Item Management System. Have a good day!");
                return false;

            } else if (answer1.equalsIgnoreCase("Y")) {

                System.out.println("You need to login.");
                System.out.println("Please enter Your Username: ");
                String userName = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();
                if (userName.equals("Admin") && password.equals("Password")){
                    return adminMenu();
                }
                if (!userDao.verifyUser(userName, password)) {
                    System.out.println("The username or Password entered was incorrect. Please try again");
                    return userAuthentication();
                }
              User user = userDao.createNewUser(userName, password);
                System.out.println("You have successfully logged in!");
                user.setUserLoginTime(ZonedDateTime.now());

                return mainMenu(user);


            }
        }catch (AlreadyExistException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Invalid input please try again");
        return userAuthentication();
    }



    //add logout option!!!!
    public boolean mainMenu(User loggedInUser){

        itemDao.createDummyItems();

        System.out.println("Hello and welcome to the Main Menu " + loggedInUser.getUserName() +" Please choose a numeric option from our list: ");
        System.out.println("\n \n");
        System.out.println("""
                1. Go to Shop menu\s
                2. Access Items Menu
                3. Access profile Menu
                4. Access User Bank Account Balance
                5. Access Mini Game Menu
                6. Access Admin Menu
                6. Quit the program""");

        int menuOption = 0;

        menuOption = scanner.nextInt();

        switch (menuOption) {
            case 1 -> {
                System.out.println("These are all the items in the store");
                itemDao.displayItems();
            }
            case 2 -> shopMenu(loggedInUser);
            case 3 -> profileMenu(loggedInUser);
            case 4 -> {
                System.out.println("You have " + loggedInUser.getAccountBalance() + " in your account. Would you like to add more?");
                System.out.println("Y or N");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("Y")) {
                    System.out.println("How much would you like to add?");
                    double cash = scanner.nextDouble();
                    System.out.println("You have successfully added " + cash + " to your account.");
                }}
            case 5 -> {
                System.out.println("Returning to Main Menu");
                return mainMenu(loggedInUser);
                }
            case 6 -> {
                System.out.println("Thank you for using the user-item management system. ");
                return false;
            }


            }
            System.out.println("Invalid input. Please try again.");

        return mainMenu(loggedInUser);
    }
    //4. The main menu will consist of a list of options available for the user:
    // Populates General Item List with dummy items
    //  - Display Personal Items  - Display General Items
    //  - Add Personal item to List  - Add General item to List
    //  - Delete Personal Item from list - Delete Item from List
    //  - Clear List
    // Sign out and end program


    public boolean shopMenu(User user){
        System.out.println("Welcome to the user shop menu");

        return false;
    }
    public boolean profileMenu(User user){

       return false;
    }
    public boolean gameMenu(User user){

        return false;
    }


    public Boolean adminMenu(){

        return null;
    }



}
