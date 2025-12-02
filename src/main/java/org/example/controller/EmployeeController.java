package org.example.controller;

import org.example.model.Employee;
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

    public void run() {
        boolean exit = false;
        while (!exit) {
            //view.displayEmployees(employees, employees.size());
            view.displayMenu();
            int choice = view.getUserChoice();
            switch (choice) {
                case 1:
                    sortEmployees();
                    break;
                case 2:
                    // Search employee logic
                    break;
                case 3:
                    // Add employee records logic
                    break;
                case 4:
                    // Binary tree creation logic
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
