package com.java.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.master.entity.Employee;

@Repository
public interface EmployeeRepositories extends JpaRepository<Employee, Long> {

}
