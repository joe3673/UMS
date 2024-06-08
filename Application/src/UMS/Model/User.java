package UMS.Model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


 public class User {


     private String userId;
     private String userName;

     private String password;

     private int tokens;

     private List<Item> personalItems;

     private ZonedDateTime userLoginTime;


     private ZonedDateTime userLastLogoutTime;

     private double cash;

     private double bankAccount;

     public User(String userName, String password) {

         this.userName = userName;
         this.password = password;
     }

     public boolean checkPassword(String password) {

         return password.equals(getPassword());
     }

     public void addItemToList(Item item) {

         Item itemToAdd = new Item(item.getItemName(), item.getItemType(), item.getPrice());
         personalItems.add(itemToAdd);
         System.out.println("Your item has been added successfully!");

     }

     public void removeItemFromList(Item item) {
         int indexToRemove = -1;
         for (int i = 0; i < personalItems.size(); i++) {
             if (personalItems.get(i).equals(item)) {
                 indexToRemove = i;
                 System.out.println("Item was not removed. Please try again.");
                 break;

             }
         }

         // If item was found, remove it from the list
         if (indexToRemove != -1) {
             personalItems.remove(indexToRemove);
             System.out.println("Item was successfully removed");
         }
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


     public String getUserId() {
         return userId;
     }

     public void setUserId(String userId) {
         this.userId = userId;
     }

     public List<Item> getPersonalItems() {
         return new ArrayList<>(personalItems);
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

     public void setPersonalItems(List<Item> personalItems) {
         this.personalItems = personalItems;
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

