package org.example.view;

import org.example.model.Employee;
import org.example.utils.MenuOption;

import java.util.List;
import java.util.Scanner;

import static org.example.utils.MenuOption.displayMenuOptions;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("=== Employee Management System ===");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getDisplayName());
        }
        System.out.print("Please select an option (1-5): ");
    }

    public int getUserChoice() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void displayEmployees(List<Employee> employees, int limit) {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        System.out.println("--- Employee List ---");
        int count = 0;
        for (Employee emp : employees) {
            if (count >= limit) {
                break;
            }
            System.out.println((count + 1) + ". " + emp.getFullName() +
                    " | Gender:" + emp.getGender() +
                    " | Email: " + emp.getEmail() +
                    " | Salary: " + emp.getSalary() +
                    " | Position: " + emp.getPosition() +
                    " | Department: " + emp.getDepartment() +
                    " | Job Title: " + emp.getJobTitle() +
                    " | Company: " + emp.getCompany());
            count++;
        }
        System.out.println("Total Employees Displayed: " + count);
    }

    public void displayEmployee(Employee employee) {
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.println("--- Employee Details ---");
        System.out.println("Name: " + employee.getFullName());
        System.out.println("Gender: " + employee.getGender());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Salary: " + employee.getSalary());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("Position: " + employee.getPosition());
        System.out.println("Job Title: " + employee.getJobTitle());
        System.out.println("Company: " + employee.getCompany());
        System.out.println("------------------------");
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }

    public String getSearchName() {
        return getUserInput("Enter name to search: ");
    }

    public void closeScanner() {
            scanner.close();
    }
}
