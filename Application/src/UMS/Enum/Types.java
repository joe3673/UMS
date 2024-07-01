package UMS.Enum;




public enum Types {
    FOOD("Food"),
    CLOTHES("Clothes"),
    GAMES("Games"),
    CARS("Cars"),
    OTHER("Other");

    private final String displayName;

    Types(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


