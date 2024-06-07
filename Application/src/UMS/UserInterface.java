package UMS;

import UMS.Dao.AdminDao;
import UMS.Dao.ItemDao;
import UMS.Dao.UserDao;
import UMS.Exception.AlreadyExistException;
import UMS.Model.Admin;
import UMS.Model.User;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class UserInterface {



    private UserDao userDao;

    private User loggedInUser;

    private ItemDao itemDao;

    private AdminDao adminDao;

    Scanner scanner = new Scanner(System.in);


    public UserInterface(){
        userDao = new UserDao();
        itemDao = new ItemDao();
        userDao.createDummyUsers();
        adminDao.createAdmin();

    }




    //1. Have users authenticate with Username and Password. If new user create account.
    // Populates User List with dummy users
    // Checks the User Database for matching for any matching information before creating a new user
    //2. If user can't authenticate, offer the option to create a user or close the application.
    public Boolean userAuthentication(){

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
              loggedInUser = userDao.createNewUser(userName, password);
                System.out.println("You have successfully logged in!");
                loggedInUser.setUserLoginTime(ZonedDateTime.now());

                return mainMenu();


            }
        }catch (AlreadyExistException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Invalid input please try again");
        return userAuthentication();
    }



    //add logout option!!!!
    public boolean mainMenu(){

        itemDao.createDummyItems();

        System.out.println("Hello and welcome to the Main Menu " + loggedInUser.getUserName() +" Please choose a numeric option from our list: ");
        System.out.println("\n \n");
        System.out.println("""
                1. Go to Shop Items\s
                2. View personal Items
                3. View profile details
                4. Questions for cash
                5. Return to Main menu""");

        int menuOption = 0;

        menuOption = scanner.nextInt();

        switch (menuOption){
            case 1:
                System.out.println("These are all the items in the store");
                itemDao.displayItems();
                break;

            case 2:
                loggedInUser.getPersonalItems();
                break;

            case 3:
                System.out.println(" This isn't done yet");
                break;


        }
        return false;

    }
    //4. The main menu will consist of a list of options available for the user:
    // Populates General Item List with dummy items
    //  - Display Personal Items  - Display General Items
    //  - Add Personal item to List  - Add General item to List
    //  - Delete Personal Item from list - Delete Item from List
    //  - Clear List
    // Sign out and end program

    public boolean adminMenu(){

        return false;
    }



}
