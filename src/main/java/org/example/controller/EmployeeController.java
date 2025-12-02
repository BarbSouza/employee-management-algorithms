package org.example.controller;

import org.example.model.Employee;
import org.example.utils.MenuOption;
import org.example.utils.SortingAlgorithms;
import org.example.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private ConsoleView view;
    private List<Employee> employees;

    public EmployeeController(List<Employee> employees) {
        this.view = new ConsoleView();
        this.employees = employees;
    }

    private void sortEmployees() {
        view.displayMessage("--- Sorting Employees by Name ---");

        List<Employee> sortedEmployees = SortingAlgorithms.mergeSort(new ArrayList<>(employees));

        view.displayMessage("--- Displaying the first 20 employees ---");
        view.displayEmployees(sortedEmployees, 20);

        employees = sortedEmployees;
    }

    private void searchEmployee() {
        view.displayMessage("--- Search Employee by Full Name ---");
        String searchName = view.getSearchName();

        employees = SortingAlgorithms.mergeSort(new ArrayList<>(employees));

        Employee foundEmployee = org.example.utils.SearchAlgorithms.binarySearchHelper(employees, searchName);
        if (foundEmployee != null) {
            view.displayMessage("Employee found:");
            view.displayEmployee(foundEmployee);
        } else {
            view.displayMessage("Employee with name '" + searchName + "' not found.");
        }
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            //view.displayEmployees(employees, employees.size());
            view.displayMenu();
            int choice = view.getUserChoice();
            try {
                MenuOption option = MenuOption.fromNumber(choice);
                switch (option) {
                    case SORT:
                        sortEmployees();
                        break;
                    case SEARCH:
                        searchEmployee();
                        break;
                    case ADD_RECORDS:
                        //addEmployee();
                        break;
                    case CREATE_BINARY_TREE:
                        //createBinaryTree();
                        break;
                    case EXIT:
                        exit = true;
                        break;
                }
            } catch (IllegalArgumentException e) {
                view.displayError("Invalid choice. Please try again.");
            }
        }
    }
}
