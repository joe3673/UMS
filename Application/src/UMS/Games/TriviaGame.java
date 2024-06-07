package UMS.Games;

import UMS.Model.User;

import java.util.Scanner;

public class TriviaGame extends Games{

    private String title;

    private int level;

    private double cashRewarded;

    private int timesGamePlayed;


    private Scanner scanner = new Scanner(System.in);


    public boolean gameInterface(User user){

        System.out.println("Welcome to the Trivia Game are your ready?");
        System.out.println("Y or N ");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("n")){
            System.out.println("Sorry. Back to the main menu we go lol.");
            return false;
        }
        if (answer.equalsIgnoreCase("y")){
            System.out.println("Welcome " + user.getUserName() + " you will be answering 5 questions.");
            System.out.println(" Get them all right for a point ");
            return true;
        }
        System.out.println("Invalid input. Please try again.");
        return gameInterface(user);
    }

    public void playGame(){


    }




}
