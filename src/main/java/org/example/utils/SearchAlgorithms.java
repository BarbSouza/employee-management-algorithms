package org.example.utils;

import org.example.model.Employee;

import java.util.List;

public class SearchAlgorithms {
    public static Employee binarySearchHelper(List<Employee> employees, String searchName) {
        return binarySearchHelper(employees, searchName, 0, employees.size() - 1);
    }

    private static Employee binarySearchHelper(List<Employee> employees, String searchName, int left, int right) {
        if (left > right) {
            return null; // Not found
        }

        int mid = left + (right - left) / 2;
        Employee midEmployee = employees.get(mid);
        int comparison = midEmployee.getFullName().compareToIgnoreCase(searchName);

        if (comparison == 0) {
            return midEmployee; // Found
        } else if (comparison < 0) {
            return binarySearchHelper(employees, searchName, mid + 1, right); // Search right half
        } else {
            return binarySearchHelper(employees, searchName, left, mid - 1); // Search left half
        }

    }
}
