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
    
}
