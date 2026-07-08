package com.udemy.cruddemo.dao;

import com.udemy.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="employi")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
