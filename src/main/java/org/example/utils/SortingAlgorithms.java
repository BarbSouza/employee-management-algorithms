package org.example.utils;

import org.example.model.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the Merge Sort algorithm to sort employees by their full names.
 */
public class SortingAlgorithms {
    // Merge Sort implementation to sort employees by full name
    public static List<Employee> mergeSort(List<Employee> employees) {
        if (employees.size() <= 1) {
            return employees;
        }
        // Split the list into two halves
        int mid = employees.size() / 2;
        List<Employee> left = new ArrayList<>(employees.subList(0, mid));
        List<Employee> right = new ArrayList<>(employees.subList(mid, employees.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }
    // Merge two sorted lists
    private static List<Employee> merge(List<Employee> left, List<Employee> right) {
        List<Employee> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        // Merge until one list is exhausted
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getFullName().compareToIgnoreCase(right.get(rightIndex).getFullName()) <= 0) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        // Append remaining elements
        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }

        return result;
    }
}
