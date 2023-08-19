package UMS;

import UMS.Dao.ItemDao;
import UMS.Dao.UserDao;
import UMS.Exception.AlreadyExistException;
import UMS.Model.User;

import java.util.Scanner;

public class UserInterface {

    private UserDao userDao;

    private ItemDao itemDao;

    Scanner scanner = new Scanner(System.in);


    public UserInterface(){
        userDao = new UserDao();
        itemDao = new ItemDao();
    }




    //1. Have users authenticate with Username and Password. If new user create account.
    // Populates User List with dummy users
    // Checks the User Database for matching for any matching information before creating a new user
    public boolean userAuthentication(){

        userDao.createDummyUsers();



        System.out.println(" Welcome to the User Item Management System. \n \n ");
        System.out.println("Do you have an UMS account? \n Input: y or n  \n");

        String answer1 = scanner.next();
        scanner.nextLine();

        if(answer1.equals("N") || answer1.equals("n")){
            System.out.println("You need to create an account to access the UMS. \n " +
                    "We'll need an email first:  ");
            String userEmail = scanner.nextLine();

            System.out.println("Choose a username. ");
            String username =  scanner.nextLine();

            System.out.println("Choose a password.");
            String userPassword = scanner.nextLine();
            try{
            userDao.createNewUser(username,userPassword,userEmail);}
            catch (AlreadyExistException e){
                System.out.println(e.getMessage());
                System.out.println("Please try again. ");
                return userAuthentication();
            }

            System.out.println("Congratulations. You have successfully created a user.");
            return userAuthentication();
        }
        else {
            System.out.println("You need to login.");
            System.out.println("Please enter Your Username: ");
            String userName = scanner.nextLine();
            System.out.println("Please enter your password:");
            String password = scanner.nextLine();
            if (!userDao.verifyUser(userName, password)){
                System.out.println("The username or Password entered was incorrect. Please try again");
                return userAuthentication();
            }
            return false;
        }


    }


    //2. If user can't authenticate, offer the option to create a user or close the application.
    //3. Allow the user to access the main menu.
    //4. The main menu will consist of a list of options available for the user:
    // Populates General Item List with dummy items
    //  - Display Personal Items  - Display General Items
    //  - Add Personal item to List  - Add General item to List
    //  - Delete Personal Item from list - Delete Item from List
    //  - Clear List
    // Sign out and end program





}
