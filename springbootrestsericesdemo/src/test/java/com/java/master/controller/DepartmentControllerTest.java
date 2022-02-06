package com.java.master.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
	
	@Test
	public void testGetDepartments() throws Exception {
		Department Dept1 = new Department();
		Dept1.setDepartmentAddress("Hyderabad");
		Dept1.setDepartmentCode("IT-001");
		Dept1.setDepartmentName("Engineering");
		Dept1.setDepartmentId(1l);
		
		Department Dept2 = new Department();
		Dept2.setDepartmentAddress("Bangalore");
		Dept2.setDepartmentCode("IT-002");
		Dept2.setDepartmentName("IT");
		Dept2.setDepartmentId(2l);
		
		List<Department> listDepartment = new ArrayList<Department>();
		listDepartment.add(Dept1);
		listDepartment.add(Dept2);
		
		Mockito.when(departmentService.getDepartments()).thenReturn(listDepartment);
		mockMvc.perform(get("/department/getDepartments").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteDepartmentById_IdExist() throws Exception {
		Department Dept1 = new Department();
		Dept1.setDepartmentAddress("Hyderabad");
		Dept1.setDepartmentCode("IT-001");
		Dept1.setDepartmentName("Engineering");
		Dept1.setDepartmentId(1l);
		
		Department inputDept = new Department();
		inputDept.setDepartmentAddress("Hyderabad");
		inputDept.setDepartmentCode("IT-001");
		inputDept.setDepartmentName("Engineering");
		
		Mockito.when(departmentService.deleteDepartmentById((long) 1)).thenReturn(true);
		mockMvc.perform(delete("/department/deleteDepartment/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateDepartmentById() throws Exception
	{
		Department Dept1 = new Department(); 
		Dept1.setDepartmentAddress("Hyderabad");
		Dept1.setDepartmentCode("IT-001");
		Dept1.setDepartmentName("Engineering");
		
		Department Dept2 = new Department(); 
		Dept2.setDepartmentAddress("Hyderabad");
		Dept2.setDepartmentCode("IT-001");
		Dept2.setDepartmentName("Engineering");
		Dept2.setDepartmentId(1l);
		
		Mockito.when(departmentService.updateDepartmentById((long) 1, Dept1)).thenReturn(Dept2);
		mockMvc.perform(put("/department/updateDepartment/1").contentType(MediaType.APPLICATION_JSON).content("{\r\n" + 
				"        \"departmentId\": 1,\r\n" + 
				"        \"departmentName\": \"Engineering\",\r\n" + 
				"        \"departmentAddress\": \"Hyderabad\",\r\n" + 
				"        \"departmentCode\": \"IT-001\"\r\n" + 
				"    }"))
		.andExpect(status().isOk());
	}

}
