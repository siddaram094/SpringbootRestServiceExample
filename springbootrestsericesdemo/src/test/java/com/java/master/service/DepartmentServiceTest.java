package com.java.master.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.java.master.entity.Department;
import com.java.master.repository.DepartmentRepository;

@SpringBootTest
class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;
	
	@MockBean
	DepartmentRepository departmentRepository;
	
	@BeforeEach
	void setUp() {
		
		Department department = new Department();
		department.setDepartmentAddress("Bangalore");
		department.setDepartmentCode("IT-001");
		department.setDepartmentName("IT");
		Mockito.when(departmentRepository.findByDepartmentName("IT")).thenReturn(department);
	}
	
	@Test
	public void fetchdepartmentByName_when_departmentNameIsValid() {
		String departmentName = "IT";
		Department dept = departmentService.getDepartmentByName(departmentName);
		assertEquals(departmentName, dept.getDepartmentName());
	}

}
