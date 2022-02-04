package com.java.master.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.java.master.entity.Department;
import com.java.master.service.DepartmentService;

@WebMvcTest
class DepartmentControllerTest {

	@MockBean
	private DepartmentService departmentService;
	
	private Department department;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		department  = new Department();
		department.setDepartmentAddress("Hyderabad");
		department.setDepartmentCode("IT-001");
		department.setDepartmentName("Engineering");
		department.setDepartmentId(1l); 
	}

	@Test
	void testSaveDepartment() throws Exception {
		Department inputDept = new Department();
		inputDept.setDepartmentAddress("Hyderabad");
		inputDept.setDepartmentCode("IT-001");
		inputDept.setDepartmentName("Engineering");
		Mockito.when(departmentService.saveDepartment(inputDept)).thenReturn(department);
		
		mockMvc.perform(post("/department/saveDepartment").contentType(MediaType.APPLICATION_JSON).content("{\r\n" + 
				"   \"departmentName\": \"Engineering\",\r\n" + 
				"    \"departmentAddress\":\"Hyderabad\",\r\n" + 
				"    \"departmentCode\":\"IT-001\"\r\n" + 
				"}"))
		.andExpect(status().isOk());
	}
	
	@Test
	void testGetDepartmentById() throws Exception {
		Department inputDept = new Department();
		inputDept.setDepartmentAddress("Hyderabad");
		inputDept.setDepartmentCode("IT-001");
		inputDept.setDepartmentName("Engineering");
		Mockito.when(departmentService.getDepartmentById(1l)).thenReturn(department);
		
		mockMvc.perform(get("/department/getDepartmentsById/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
	}

}
