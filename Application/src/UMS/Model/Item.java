package UMS.Model;

import UMS.Enum.Types;

import java.security.Timestamp;
import java.util.Objects;

    public final class Item {

        //Identifier used for the Item Object
        private int itemId;

        private  String itemName;

        private Timestamp timeItemAdded;

        private Timestamp timeItemRemoved;

        private  String itemDescription;

        private double price;

        private final Types itemType;

        public Item(String itemName, Types itemType, double price){

            this.itemName = itemName;
            this.itemType = itemType;
            this.price = price;
            this.itemDescription= "The description on this Item wasn't set.";


        }


        public int getItemId() {
            return itemId;
        }


        public Timestamp getTimestamp(){
            return timeItemAdded;
        }


        public String getItemDescription(){
            return itemDescription;
        }


        public Types getItemType() {
            return itemType;
        }

        public void setItemDescription(String description){
            this.itemDescription = description;
        }


        public String getItemName() {
            return itemName;
        }
        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Timestamp getTimeItemAdded() {
            return timeItemAdded;
        }

        public void setTimeItemAdded(Timestamp timeItemAdded) {
            this.timeItemAdded = timeItemAdded;
        }

        public Timestamp getTimeItemRemoved() {
            return timeItemRemoved;
        }

        public void setTimeItemRemoved(Timestamp timeItemRemoved) {
            this.timeItemRemoved = timeItemRemoved;
        }

        public double getPrice() {
        return price;
        }

        public void setPrice(double price) {
        this.price = price;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "itemId=" + itemId +
                    ", itemName='" + itemName + '\'' +
                    ", timeItemAdded=" + timeItemAdded +
                    ", timeItemRemoved=" + timeItemRemoved +
                    ", itemDescription='" + itemDescription + '\'' +
                    ", price=" + price +
                    ", itemType=" + itemType +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Item otherItem = (Item) obj;
            return itemId == otherItem.itemId && Objects.equals(itemName, otherItem.itemName);
        }

        }


