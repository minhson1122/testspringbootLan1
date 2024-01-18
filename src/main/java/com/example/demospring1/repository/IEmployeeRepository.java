package com.example.demospring1.repository;

import com.example.demospring1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
List<Employee> findAllByNameContaining(String name);
List<Employee> findAllBySalaryBetween(Double minSalary, Double maxSalary);
}
