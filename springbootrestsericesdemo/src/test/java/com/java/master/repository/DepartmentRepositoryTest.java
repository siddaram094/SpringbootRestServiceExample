package com.java.master.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.java.master.entity.Department;

@DataJpaTest
class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	TestEntityManager entityManager;
	@BeforeEach
	void setUp(){
		Department department = new Department();
		department.setDepartmentAddress("Hyderabad");
		department.setDepartmentCode("IT-001");
		department.setDepartmentName("IT");
		entityManager.persist(department);
	}

	
	@Test
	public void findByDepartmentNameTest() {
		Department dept = departmentRepository.findByDepartmentName("IT");
		assertEquals(dept.getDepartmentName(), "IT");
	}

}
