package com.collect.collection.controller;

import com.collect.collection.Employee;
import com.collect.collection.Service.EmployeeService;
import com.collect.collection.Service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeServiceImpl = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam(required = true) String firstName,
                              @RequestParam(required = true) String lastName) {
        Employee employee = new Employee(firstName, lastName);

        employeeServiceImpl.addNewEmployee(employee);
        return employee.toString();

    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam(required = true) String firstName,
                                 @RequestParam(required = true) String lastName) {
        Employee employee = new Employee(firstName, lastName);

        employeeServiceImpl.removeEmployee(employee);
        return employee.toString();
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam(required = true) String firstName,
                               @RequestParam(required = true) String lastName) {
        Employee employee = new Employee(firstName, lastName);

        return employee.toString() + ", id " + employeeServiceImpl.findEmployee(employee);
    }

    @GetMapping(path = "/printAll")
    public Collection<Employee> printEmployees() {

        return this.employeeServiceImpl.printAll();
    }


}
