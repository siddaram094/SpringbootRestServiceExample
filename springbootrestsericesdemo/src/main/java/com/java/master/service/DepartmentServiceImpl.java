package com.java.master.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.master.entity.Department;
import com.java.master.error.DepartmentNotFoundException;
import com.java.master.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Override
	public Department saveDepartment(Department department) {
		departmentRepository.save(department);
		return department;
	}
	@Override
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}
	@Override
	public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> department =  departmentRepository.findById(departmentId);
		if(!department.isPresent()) {
			throw new DepartmentNotFoundException("department Not Available");
		}
		return department.get();
	}
	@Override
	public boolean deleteDepartmentById(Long departmentId) {
		if(departmentRepository.existsById(departmentId)) {
		departmentRepository.deleteById(departmentId);
		return true;
		}else {
			return false;
		}
	}
	@Override
	public Department updateDepartmentById(Long departmentId, Department department) {

		Department deptdDB = departmentRepository.findById(departmentId).get();
		if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
			deptdDB.setDepartmentName(department.getDepartmentName());
		}
		if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
			deptdDB.setDepartmentAddress(department.getDepartmentAddress());
		}
		if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
			deptdDB.setDepartmentCode(department.getDepartmentCode());
		}
		
		return departmentRepository.save(deptdDB);
		
	}
	@Override
	public Department getDepartmentByName(String departmentName) {
		return departmentRepository.findByDepartmentName(departmentName);
	}

}
