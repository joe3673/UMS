package UMS.Model;

import java.time.ZonedDateTime;
import java.util.Objects;


 public class User {


     private int userId;
     private String userName;

     private String password;

     private int tokens;

     private ZonedDateTime userLoginTime;


     private ZonedDateTime userLastLogoutTime;

     private double cash;

     private double bankAccount;

     public User(int userID, String userName, String password, int tokens, double cash, double bankAccount) {

         this.userName = userName;
         this.password = password;
         this.tokens = tokens;
         this.cash = cash;
         this.bankAccount = bankAccount;
     }

     public boolean checkPassword(String password) {

         return password.equals(getPassword());
     }



     //This how we check to make sure the password is stored inside the server with the following commands on the app
     //If false will return an error when applied to a method in the Dao

     public boolean isValidPassword(String inputPassword) {
         return this.password.equals(inputPassword);
     }




     public boolean isValidUser(String userName) {
         return this.userName.equals(userName);
     }


     public String getUserName() {
         return userName;
     }

     public void setUserName(String userName) {
         this.userName = userName;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }


     public int getUserId() {
         return userId;
     }

     public void setUserId(int userId) {
         this.userId = userId;
     }


     public ZonedDateTime getUserLoginTime() {
         return userLoginTime;
     }

     public void setUserLoginTime(ZonedDateTime userLoginTime) {
         this.userLoginTime = userLoginTime;
     }

     public ZonedDateTime getUserLastLogoutTime() {
         return userLastLogoutTime;
     }

     public void setUserLastLogoutTime(ZonedDateTime userLastLogoutTime) {
         this.userLastLogoutTime = userLastLogoutTime;
     }

     public int getTokens() {
         return tokens;
     }

     public void setTokens(int tokens) {
         this.tokens = tokens;
     }

     public double getCash() {
         return cash;
     }

     public void setCash(double cash) {
         this.cash = cash;
     }

     public double getAccountBalance() {
         return bankAccount;
     }

     public void setAccountBalance(double bankAccount) {
         this.bankAccount = bankAccount;
     }

     @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return Objects.equals(userName, user.userName) && Objects.equals(password, user.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userName, password);
        }


    }

