package com.collect.collection.Service;

import com.collect.collection.Employee;
import com.collect.collection.EmployeeExceptions.EmployeeAlreadyAddedException;
import com.collect.collection.EmployeeExceptions.EmployeeNotFoundException;
import com.collect.collection.EmployeeExceptions.EmployeeStorageIsFullException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int limit = 20;
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Kara", "Hunter"),
            new Employee("Sara", "Fisher"),
            new Employee("Nick", "Radclyffe"),
            new Employee("Lidia", "Sinn"),
            new Employee("Andy", "Garcia"),
            new Employee("Anna", "Boil"),
            new Employee("Kaila", "Harlan"),
            new Employee("Nadia", "Song"),
            new Employee("Lana", "Fillips"),
            new Employee("Jack", "Becket"),
            new Employee("Wilfred", "Tasker")
    ));


    private void checkLimit() throws EmployeeStorageIsFullException {
        if (employees.size() >= limit)
            throw new EmployeeStorageIsFullException("Can not hire more employees");
    }

    private void checkAlreadyEmployed(Employee employee) throws EmployeeAlreadyAddedException {
        int hired = 0;
        for (Employee empl : employees) {
            if (employee.getFullName().equals(empl.getFullName())) {
                hired = 1;
            }
        }
        if (hired == 1)
            throw new EmployeeAlreadyAddedException("Employee is already hired");
    }

    private void find(Employee employee) throws EmployeeNotFoundException {
        boolean check = false;
        for (Employee empl : employees) {
            if (employee.getFullName().equals(empl.getFullName())) {
                check = true;
            }
        }
        if (!check) {
            throw new EmployeeNotFoundException("Employee is not found");
        }
    }

    @Override
    public void addNewEmployee(Employee employee) {
        try {
            checkLimit();
            checkAlreadyEmployed(employee);
            employees.add(employee);
        } catch (EmployeeStorageIsFullException e) {
            System.out.println("Can not hire more employees");
            e.printStackTrace();
        } catch (EmployeeAlreadyAddedException y) {
            System.out.println("Already employed");
            y.printStackTrace();
        }
    }

    @Override
    public void removeEmployee(Employee employee) {
        try {
            find(employee);
            employees.removeIf(empl -> employee.getFullName().equals(empl.getFullName()));
        } catch (EmployeeNotFoundException e) {
            System.out.println("Employee is not found");
            e.printStackTrace();
        }
    }

    @Override
    public int findEmployee(Employee employee) {
        int num = 0;
        try {
            find(employee);
            num = employee.hashCode();
        } catch (EmployeeNotFoundException e) {
            System.out.println("Employee is not found");
            e.printStackTrace();
        }
        return num;
    }

    public List<Employee> printAll(){
        return employees;
    }


}
