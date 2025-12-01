package org.example.utils;

import org.example.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Employee> readEmployeesFromFile(String fileName) {
        List<Employee> employees = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header line
                    continue;
                }
                
                String[] values = line.split(",");
                if (values.length >= 9) {
                    Employee employee = getEmployee(values);
                    employees.add(employee);
                }

            }

            System.out.println("File read successfully with " + employees.size() + " employees.");

        } catch (IOException e) {
            System.out.println("Error reading file");;
        }

        return  employees;
    }

    private static Employee getEmployee(String[] values) {
        Employee employee = new Employee();
        employee.setFirstName(values[0].trim());
        employee.setLastName(values[1].trim());
        employee.setGender(values[2].trim());
        employee.setEmail(values[3].trim());
        employee.setSalary(Double.parseDouble(values[4].trim()));
        employee.setDepartment(values[5].trim());
        String position = values[6].trim();
        employee.setPosition(position.isEmpty() ? "Manager" : position);
        employee.setJobTitle(values[7].trim());
        employee.setCompany(values[8].trim());
        return employee;
    }
}
