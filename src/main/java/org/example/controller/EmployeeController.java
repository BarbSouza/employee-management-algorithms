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
                    //Sort employees by name logic
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
