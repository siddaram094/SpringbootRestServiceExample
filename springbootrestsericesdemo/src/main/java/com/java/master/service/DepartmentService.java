package com.java.master.service;

import java.util.List;

import com.java.master.entity.Department;
import com.java.master.error.DepartmentNotFoundException;

public interface DepartmentService {

	Department saveDepartment(Department department);

	List<Department> getDepartments();

	Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

	boolean deleteDepartmentById(Long departmentId);

	Department updateDepartmentById(Long departmentId, Department department);

	Department getDepartmentByName(String departmentName);
}
