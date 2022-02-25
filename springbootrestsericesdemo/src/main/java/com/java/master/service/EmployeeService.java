package com.java.master.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.java.master.entity.Employee;

@Service
public interface EmployeeService {

	Employee saveEmployee(@RequestBody Employee employee);
}
