package com.example.demospring1.service;

import com.example.demospring1.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> list();
    void save(Employee employee);
    void deleteEmployee(Long id);

}
