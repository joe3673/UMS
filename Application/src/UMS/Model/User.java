package UMS.Model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


 public class User {


        private String userId;
        private String userName;


        private String password;

        private String Email;


        //Each user list of personal items in the app.
        //Made accessible after login
        private List<Item> personalItems;


        //Keeps track of the last time a user has logged out of the app.
        //Info that stays in the system.
        // For database purposes.
        private ZonedDateTime userLoginTime;


        //Keeps track of the last time a user has logged out of the app.
        //Info that stays in the system.

        private ZonedDateTime userLastLogoutTime;


        //Constructor for creating a new user.
        //The basic info you would need to create a profile

        public User(String userName, String password, String email) {

            this.userName = userName;
            this.password = password;
            this.Email = email;
        }

        //This constructor is to be used for user login.
        //We create a dummy user with these credentials and see if it matches with a user in our database

        public User(String userName, String password) {

            this.userName = userName;
            this.password = password;
        }

        public boolean checkPassword(String password) {

            if (password.equals(getPassword())) {
                return true;
            } else {
                return false;
            }
        }

        public void addItemToList(Item item) {

            Item itemToAdd = new Item(item.getItemName(), item.getItemType());
            personalItems.add(itemToAdd);

        }

        public void removeItemFromList(Item item) {
            int indexToRemove = -1;
            for (int i = 0; i < personalItems.size(); i++) {
                if (personalItems.get(i).equals(item)) {
                    indexToRemove = i;
                    break;
                }
            }

            // If item was found, remove it from the list
            if (indexToRemove != -1) {
                personalItems.remove(indexToRemove);
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

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
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

