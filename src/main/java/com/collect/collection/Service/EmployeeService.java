package com.collect.collection.Service;

import com.collect.collection.Employee;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    List<Employee> employees = new ArrayList<>(List.of());

    public void addNewEmployee(Employee employee);

    public void removeEmployee(Employee employee);

    public int findEmployee(Employee employee);
}
