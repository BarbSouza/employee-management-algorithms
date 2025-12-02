package org.example.controller;

import org.example.model.Employee;
import org.example.utils.MenuOption;
import org.example.utils.SearchAlgorithms;
import org.example.utils.SortingAlgorithms;
import org.example.view.ConsoleView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeController {
    private ConsoleView view;
    private List<Employee> employees;

    public EmployeeController(List<Employee> employees) {
        this.view = new ConsoleView();
        this.employees = employees;
    }

    private boolean isSorted = false;

    private void sortEmployees() {
        view.displayMessage("--- Sorting Employees by Name ---");
        employees = SortingAlgorithms.mergeSort(new ArrayList<>(employees));
        isSorted = true;
        view.displayMessage("--- Displaying the first 20 employees ---");
        view.displayEmployees(employees, 20);
    }

    private void searchEmployee() {
        view.displayMessage("--- Search Employee by Full Name ---");
        String searchName = view.getUserInput("Enter full name to search: ");

        if (!isSorted) {
            view.displayMessage("Sorting list before search...");
            employees = SortingAlgorithms.mergeSort(new ArrayList<>(employees));
            isSorted = true;
        }

        Employee foundEmployee = SearchAlgorithms.binarySearchHelper(employees, searchName);
        if (foundEmployee != null) {
            view.displayMessage("Employee found:");
            view.displayEmployeeDetails(foundEmployee);
        } else {
            view.displayMessage("Employee with name '" + searchName + "' not found.");
        }
    }

    private void addEmployee() {
        view.displayMessage("--- Add New Employee ---");

        String firstName = view.getUserInput("Enter First Name: ");
        String lastName = view.getUserInput("Enter Last Name: ");

        view.displayGenderOptions();
        int genderChoice = view.getChoiceFromList("Select Gender by number: ", 3);
        String selectedGender = genderChoice == 1 ? "Male" : "Female";

        double salary = view.getDoubleInput("Enter Salary: ");
        String company = view.getUserInput("Enter Company: ");
        String position = view.getUserInput("Enter Position: ");

        Set<String> uniqueJobTitles = new HashSet<>();
        for (Employee emp : employees) {
            if(emp.getJobTitle() != null && !emp.getJobTitle().isEmpty()) {
                uniqueJobTitles.add(emp.getJobTitle());
            }
        }
        List<String> jobTitleList = new ArrayList<>(uniqueJobTitles);

        view.displayAvailableJobs(jobTitleList);
        int jobChoice = view.getChoiceFromList("Select Job Title by number: ", jobTitleList.size());
        String selectedJobTitle = jobTitleList.get(jobChoice - 1);

        Set<String> uniqueDepartments = new HashSet<>();
        for (Employee emp : employees) {
            if(emp.getDepartment() != null && !emp.getDepartment().isEmpty()) {
                uniqueDepartments.add(emp.getDepartment());
            }
        }
        List<String> departmentList = new ArrayList<>(uniqueDepartments);

        view.displayAvailableDepartments(departmentList);
        int deptChoice = view.getChoiceFromList("Select Department by number: ", departmentList.size());
        String selectedDepartment = departmentList.get(deptChoice - 1);

        Employee newEmployee = new Employee(
                firstName,
                lastName,
                selectedGender,
                firstName.toLowerCase().charAt(0) + lastName.toLowerCase() + "@" + company.toLowerCase() + ".com",
                salary,
                selectedDepartment,
                position,
                selectedJobTitle,
                company
        );
        employees.add(newEmployee);
        isSorted = false;
        view.displayMessage("New employee added successfully.");
        view.displayEmployee(newEmployee);
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
                        addEmployee();
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
