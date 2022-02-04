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
	
	private Department department;
	
	@BeforeEach
	void setUp() {
		
		department = new Department();
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
	
	@Test
	public void testSaveDepartment() {
		Department deptDb = new Department();
		deptDb.setDepartmentAddress("Bangalore");
		deptDb.setDepartmentCode("IT-001");
		deptDb.setDepartmentName("IT");
		deptDb.setDepartmentId(1l);
		Mockito.when(departmentService.saveDepartment(department)).thenReturn(deptDb);
		assertEquals(deptDb.getDepartmentAddress(), department.getDepartmentAddress());
	}
	
	

}
