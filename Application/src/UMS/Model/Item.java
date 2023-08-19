package UMS.Model;

import java.security.Timestamp;
import java.util.Objects;

//Java object that will act as the item.
public final class Item {

        //Identifier used for the Item Object
        private int itemId;


        //
        private  String itemName;

        private Timestamp timeItemAdded;

        private Timestamp timeItemRemoved;

        private  String itemDescription;



        private final String itemType;

        public Item(String itemName, String itemType){

            this.itemName = itemName;
            this.itemType = itemType;
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


        public String getItemType() {
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

        @Override
        public String toString() {
            return " Id : " + itemId +
                    " / Item Name = " + itemName + "\n \n";
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


