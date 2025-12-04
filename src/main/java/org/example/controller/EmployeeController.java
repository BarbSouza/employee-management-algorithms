package org.example.controller;

import org.example.model.Employee;
import org.example.model.EmployeeBinaryTree;
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

    private String formatName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }
        name = name.trim();
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    private void addEmployee() {
        view.displayMessage("--- Add New Employee ---");

        String firstName = formatName(view.getUserInput("Enter First Name: "));
        String lastName = formatName(view.getUserInput("Enter Last Name: "));

        // Validate gender selection
        view.displayGenderOptions();
        int genderChoice = view.getChoiceFromList("Select Gender by number: ", 3);
        String selectedGender;
        switch (genderChoice) {
            case 1:
                selectedGender = "Male";
                break;
            case 2:
                selectedGender = "Female";
                break;
            default:
                selectedGender = "Other";
        }

        double salary = view.getDoubleInput("Enter Salary: ");

        String jobTitle = formatName(view.getUserInput("Enter Job Title: "));

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

        view.displayAvailablePositions();
        int positionChoice = view.getChoiceFromList("Select Position by number: ", 4);
        String selectedPosition;
        int selectedLevel;
        switch (positionChoice) {
            case 1:
                selectedPosition = "Level 1";
                selectedLevel = 1;
                break;
            case 2:
                selectedPosition = "Level 2";
                selectedLevel = 2;
                break;
            case 3:
                selectedPosition = "Level 3";
                selectedLevel = 3;
                break;
            case 4:
                selectedPosition = "Level 4";
                selectedLevel = 4;
                break;
            default:
                selectedPosition = "Level 5";
                selectedLevel = 5;
        }

        Set<String> uniqueReportsTo = new HashSet<>();
        for (Employee emp : employees) {
            if(emp.getPosition() != null && !emp.getPosition().isEmpty()) {
                try{
                    String posStr = emp.getPosition().replace("Level ", "");
                    int empLevel = Integer.parseInt(posStr);
                    if (empLevel < selectedLevel) {
                        uniqueReportsTo.add(emp.getFullName());
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }

        if(selectedLevel == 1) {
            uniqueReportsTo.add("Owner");
        }

        if(selectedLevel == 2) {
            for(Employee emp : employees) {
                if(emp.getPosition() == null) {
                    uniqueReportsTo.add(emp.getFullName());
                }
            }
        }

        if (uniqueReportsTo.isEmpty()) {
            view.displayMessage("No available supervisors found for the selected position level.");
            return;
        }
        List<String> reportsToList = new ArrayList<>(uniqueReportsTo);

        view.displayAvailableReportsTo(reportsToList);
        int reportsToChoice = view.getChoiceFromList("Select who the employee reports to: ", reportsToList.size());
        String selectedReportsTo = reportsToList.get(reportsToChoice - 1);

        String emailDomain = "oakwood.edu";
        String email = (firstName.charAt(0) + lastName + "@" + emailDomain).toLowerCase();

        Employee newEmployee = new Employee(
                firstName,
                lastName,
                selectedGender,
                email,
                salary,
                selectedDepartment,
                selectedPosition,
                jobTitle,
                selectedReportsTo
        );
        employees.add(newEmployee);
        isSorted = false;
        view.displayMessage("New employee added successfully.");
        view.displayEmployee(newEmployee);
    }

    private void createBinaryTree() {

        view.displayMessage("--- Create Binary Search Tree of Employees ---");

        EmployeeBinaryTree tree = new EmployeeBinaryTree();

        int count = Math.min(20, employees.size());
        for (int i = 0; i < count; i++) {
            tree.insert(employees.get(i));
        }

        view.displayMessage("--- Employee Hierarchy Tree Created ---");
        tree.levelOrderTraversal(view);

        view.displayMessage("--- Tree Statistics ---");
        view.displayMessage("Tree Height: " + tree.getHeight());
        view.displayMessage("Total Nodes: " + tree.getNodeCount());

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
                        createBinaryTree();
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
