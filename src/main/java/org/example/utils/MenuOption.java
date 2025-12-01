package org.example.utils;

public enum MenuOption {
    SORT("1. Sort Employees"),
    SEARCH("2. Search Employees"),
    ADD_RECORDS("3. Add Employee Records"),
    CREATE_BINARY_TREE("4. Create Binary Tree from Employees"),
    EXIT("5. Exit Application");

    private final String displayName;

    MenuOption(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static MenuOption fromNumber(int number) {
        switch (number) {
            case 1:
                return SORT;
            case 2:
                return SEARCH;
            case 3:
                return ADD_RECORDS;
            case 4:
                return CREATE_BINARY_TREE;
            case 5:
                return EXIT;
            default:
                throw new IllegalArgumentException("Invalid menu option number: " + number);
        }
    }

    public static void displayMenuOptions() {
        for (MenuOption menuOption : MenuOption.values()) {
            System.out.println(menuOption.getDisplayName());
        }
    }
}
