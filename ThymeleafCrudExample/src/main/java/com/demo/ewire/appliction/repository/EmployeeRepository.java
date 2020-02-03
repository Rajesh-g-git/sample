package com.demo.ewire.appliction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.ewire.appliction.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
