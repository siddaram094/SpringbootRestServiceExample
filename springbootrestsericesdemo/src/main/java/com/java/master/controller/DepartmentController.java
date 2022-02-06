package com.java.master.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.master.entity.Department;
import com.java.master.error.DepartmentNotFoundException;
import com.java.master.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Value("${welcome.message}")
	private String welcomeMessage;
	@Autowired
	DepartmentService departmentService;
	
	@PostMapping("/saveDepartment")
	public Department saveDepartment(@Valid @RequestBody Department department) {
		logger.info("Department to be saved"+department);
	Department dept = departmentService.saveDepartment(department);
		return dept;
	}
	
	
	@GetMapping("/getDepartments")
	public List<Department> getDepartments(){
		List<Department> departmentList = departmentService.getDepartments();
		return departmentList;
	}
	
	@GetMapping("/getDepartmentsById/{departmentId}")
	public Department getDepartmentById(@PathVariable("departmentId") Long departmentId) throws DepartmentNotFoundException {
		Department dept = departmentService.getDepartmentById(departmentId);
		return dept;
	}
	
	@DeleteMapping("/deleteDepartment/{departmentId}")
	public String deleteDepartmentById(@PathVariable("departmentId") Long departmentId) {
	
		boolean deleteFlag = departmentService.deleteDepartmentById(departmentId);
		if(deleteFlag) {
		return "department deleted successfully";
		}else {
			return "department doesn't exist";	
		}
	}
	
	@PutMapping("updateDepartment/{departmentId}")
	public Department updateDepartmentById(@PathVariable("departmentId") Long departmentId,@RequestBody Department department) {
		return departmentService.updateDepartmentById(departmentId,department);
		 
	}
	
	@GetMapping("/getDepartments/{departmentName}")
	public Department getDepartmentByName(@PathVariable("departmentName") String departmentName) {
		Department dept = departmentService.getDepartmentByName(departmentName);
		return dept;
	}
}
