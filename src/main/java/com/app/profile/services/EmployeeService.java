package com.app.profile.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.profile.dto.req.EmployeeDTO;
import com.app.profile.model.Company;
import com.app.profile.model.Employee;
import com.app.profile.repository.CompanyRepository;
import com.app.profile.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CompanyRepository companyRepository;

	public Employee saveOrUpdate(EmployeeDTO dto) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Employee emp = mapper.convertValue(dto, Employee.class);
			Company comp = companyRepository.findById(dto.getCompanyCode()).get();
			emp.setCompany(comp);
			emp = employeeRepository.save(emp);
			return emp;
		} catch (Exception e) {
			throw new Exception("Data cant procced");
		}
	}

	public Employee findByNik(String nik) {
		return employeeRepository.findById(nik).get();
	}

	public List<Employee> showAll() {
		return employeeRepository.findAll();
	}

	public void deleted(String nik) throws Exception {
		Optional<Employee> optEmp = employeeRepository.findById(nik);
		if (optEmp.isPresent()) {
			employeeRepository.delete(optEmp.get());
		} else {
			throw new Exception("Data is not Exist");
		}
	}
}
