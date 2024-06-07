package UMS.Games;

import UMS.Model.User;

public abstract class Games {

    private String title;

    private int level;

    private double cashRewarded;

    private int timesGamePlayed;



    public boolean gameInterface(User user){

        return false;
    }

    public void playGame(){

    }

    public double rewardUser(double cashRewarded){
        return 0;
    }
}
