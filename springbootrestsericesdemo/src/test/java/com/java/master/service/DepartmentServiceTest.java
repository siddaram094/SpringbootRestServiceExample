package com.java.master.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.java.master.entity.Department;
import com.java.master.error.DepartmentNotFoundException;
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
	
	@Test
	public void testGetDepartmentById_departmentIdPresentInDB() throws DepartmentNotFoundException {
		
		Department department = new Department();
		department.setDepartmentAddress("Bangalore");
		department.setDepartmentCode("IT-001"); 
		department.setDepartmentName("IT");
		department.setDepartmentId(1l);
		
		Department deptDb = new Department();
		deptDb.setDepartmentAddress("Bangalore");
		deptDb.setDepartmentCode("IT-001");
		deptDb.setDepartmentName("IT");
		deptDb.setDepartmentId(1l);
		
		Optional<Department> optDept = Optional.of(department);
		Mockito.when(departmentRepository.findById(1l)).thenReturn(optDept);
		Department deptFromService = departmentService.getDepartmentById(1l);
		assertEquals(deptFromService.getDepartmentName(), deptDb.getDepartmentName());
	}
	
	/*
	 * @Test public void
	 * testGetDepartmentById_ThrowsError_IFDepartmentIdNotPresentInDB() throws
	 * DepartmentNotFoundException {
	 * 
	 * Department department = new Department();
	 * 
	 * 
	 * Department deptDb = new Department();
	 * deptDb.setDepartmentAddress("Bangalore"); deptDb.setDepartmentCode("IT-001");
	 * deptDb.setDepartmentName("IT"); deptDb.setDepartmentId(1l);
	 * 
	 * Optional<Department> optDept = Optional.of(department);
	 * Mockito.when(departmentRepository.findById(1l)).thenReturn(optDept);
	 * Department deptFromService = departmentService.getDepartmentById(1l);
	 * //assertEquals(deptFromService.getDepartmentName(),
	 * deptDb.getDepartmentName()); assertThrows(DepartmentNotFoundException.class,
	 * () -> departmentRepository.getById(1l), "department Not Available"); }
	 */
	
	@Test
	public void testGetDepartments() {
		
		Department department1 = new Department();
		department1.setDepartmentAddress("Bangalore");
		department1.setDepartmentCode("IT-001");
		department1.setDepartmentName("IT");
		department1.setDepartmentId(1l);
		
		Department department2 = new Department();
		department2.setDepartmentAddress("Bangalore");
		department2.setDepartmentCode("IT-002");
		department2.setDepartmentName("IS");
		department2.setDepartmentId(2l); 
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(department1);
		departmentList.add(department2);
		
		Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);
		
		List<Department> mockedDeptListfromService = departmentService.getDepartments();
		
		assertEquals(departmentList.size(), mockedDeptListfromService.size());
		
	}
	
	@Test
	public void testDeleteDepartmentByID_IDExists() {
		Boolean actualFlag = true;
		Department department = new Department();
		department.setDepartmentAddress("Bangalore");
		department.setDepartmentCode("IT-001");
		department.setDepartmentName("IT");
		department.setDepartmentId(1l);
		
		Mockito.when(departmentRepository.existsById(1l)).thenReturn(true);
		
		Boolean flag = departmentService.deleteDepartmentById(1l);
		 Mockito.verify(departmentRepository, times(1)).deleteById(1l);
		assertTrue(actualFlag==flag);
	}
	
	@Test
	public void testDeleteDepartmentByID_IDNotExists() {
		Boolean actualFlag = true;
		Department department = new Department();
		department.setDepartmentAddress("Bangalore");
		department.setDepartmentCode("IT-001");
		department.setDepartmentName("IT");
		department.setDepartmentId(1l);
		
		Mockito.when(departmentRepository.existsById(1l)).thenReturn(false);
		
		Boolean flag = departmentService.deleteDepartmentById(1l);
		
		assertTrue(actualFlag!=flag);
	}
	
	@Test
	public void testUpdateDepartment() {
		Department department = new Department();
		department.setDepartmentAddress("Bangalore");
		department.setDepartmentCode("IT-001");
		department.setDepartmentName("IT");
		
		Department savedDepartment = new Department();
		savedDepartment.setDepartmentAddress("Bangalore");
		savedDepartment.setDepartmentCode("IT-001");
		savedDepartment.setDepartmentName("IT");
		savedDepartment.setDepartmentId(0l);
		
		Department updatedDepartmentToUpdate = new Department();
		updatedDepartmentToUpdate.setDepartmentAddress("Hyderabad");
		updatedDepartmentToUpdate.setDepartmentCode("IT-001");
		updatedDepartmentToUpdate.setDepartmentName("IT");
		updatedDepartmentToUpdate.setDepartmentId(0l);
		
		//Mockito.when(departmentRepository.save(department)).thenReturn(savedDepartment);
		
		Optional<Department> optDept = Optional.of(savedDepartment);
		Mockito.when(departmentRepository.findById(0l)).thenReturn(optDept);
		department.setDepartmentAddress("Hyderabad");
		savedDepartment.setDepartmentAddress("Hyderabad"); 
		System.out.println("saved dept"+department);
		
		System.out.println("savedDepartment"+savedDepartment);
		 
		System.out.println("updatedDepartmentToUpdate"+updatedDepartmentToUpdate);
		Mockito.when(departmentRepository.save(department)).thenReturn(updatedDepartmentToUpdate);
		
		Department savedDepartment1 = departmentService.updateDepartmentById(0l, department);
		
		System.out.println("updatedDepartment"+savedDepartment1);
		assertEquals(savedDepartment1.getDepartmentName(), savedDepartment.getDepartmentName());
		//Mockito.verify(departmentRepository,times(1)).save(department);
		
	}

}
