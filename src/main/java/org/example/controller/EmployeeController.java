package org.example.controller;

import org.example.model.Employee;
import org.example.view.ConsoleView;

import java.util.List;

public class EmployeeController {
    private ConsoleView view;
    private List<Employee> employees;

    public EmployeeController(List<Employee> employees) {
        this.view = new ConsoleView();
        this.employees = employees;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            view.displayMenu();
            int choice = view.getUserChoice();
            switch (choice) {
                case 1:
                    view.displayEmployees(employees, employees.size());
                    break;
                case 2:
                    view.displayEmployees(employees, 5);
                    break;
                case 3:
                    // Additional functionality can be added here
                    break;
                case 4:
                    // Additional functionality can be added here
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
