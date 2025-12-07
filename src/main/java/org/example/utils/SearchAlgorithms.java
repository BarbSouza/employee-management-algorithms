package org.example.utils;

import org.example.model.Employee;

import java.util.List;

/**
 * This class provides the Binary Search algorithm to search for an employee by full name.
 */
public class SearchAlgorithms {
    // Binary Search implementation to find an employee by full name
    public static Employee binarySearchHelper(List<Employee> employees, String searchName) {
        return binarySearchHelper(employees, searchName, 0, employees.size() - 1);
    }
    // Recursive binary search method
    private static Employee binarySearchHelper(List<Employee> employees, String searchName, int left, int right) {
        if (left > right) {
            return null; // Not found
        }
        // Calculate mid index
        int mid = left + (right - left) / 2;
        Employee midEmployee = employees.get(mid);
        int comparison = midEmployee.getFullName().compareToIgnoreCase(searchName);

        // Check if the mid employee matches the search name
        if (comparison == 0) {
            return midEmployee; // Found
        } else if (comparison < 0) {
            return binarySearchHelper(employees, searchName, mid + 1, right); // Search right half
        } else {
            return binarySearchHelper(employees, searchName, left, mid - 1); // Search left half
        }

    }
}
