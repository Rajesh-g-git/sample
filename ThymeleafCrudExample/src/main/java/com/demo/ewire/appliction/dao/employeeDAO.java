package com.demo.ewire.appliction.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.demo.ewire.appliction.model.Employee;
import com.demo.ewire.appliction.repository.EmployeeRepository;

@Service
public class employeeDAO {
	@Autowired
	EmployeeRepository employeeRepository;

	/* to save an employee */

	public Employee save(Employee std) {
		return employeeRepository.save(std);
	}

	/* search all employees */

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	/* get an employee by id */
	public Employee findOne(Integer id) {
		return employeeRepository.findOne(id);
	}

	/* delete an employee */

	public void delete(Employee std) {
		employeeRepository.delete(std);
	}

	/* delete an All employee */

	@Modifying
	@Query("delete from ?1")
	public void deleteAllFromTable(String table) {
	}

}
