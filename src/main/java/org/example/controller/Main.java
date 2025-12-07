package org.example.controller;

import org.example.model.Employee;
import org.example.utils.FileHandler;

import java.util.List;
import java.util.Scanner;

/**
 * Main class to run the Employee Management System application.
 * It handles user input for the filename and initializes the controller.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = null;

        System.out.println("Welcome to the Employee Management System!");

        // Keep asking for filename until valid file is loaded or user exits
        while (employees == null || employees.isEmpty()) {
            System.out.print("Please enter the filename to read (or type 'exit' to quit): ");
            String filename = scanner.nextLine().trim();

            // Allow user to exit
            if (filename.equalsIgnoreCase("exit")) {
                System.out.println("Exiting application. Goodbye!");
                scanner.close();
                return;
            }

            // Attempt to read employees from the specified file
            try {
                employees = FileHandler.readEmployeesFromFile(filename);

                if (employees.isEmpty()) {
                    System.out.println("No employees found in file. Please try again.\n");
                    employees = null;
                }
            } catch (Exception e) {
                System.out.println("Error: Could not read file '" + filename + "'. Please check the filename and try again.\n");
                employees = null;
            }
        }

        // Initialize and run the controller
        EmployeeController controller = new EmployeeController(employees);
        controller.run();

        scanner.close();
        System.out.println("Thank you for using the Employee Management System!");
    }
}