package com.app.profile.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.profile.model.Company;
import com.app.profile.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository repository;

	public Company saveOrUpdate(Company company) throws Exception {
		try {
			company = repository.save(company);
		} catch (Exception e) {
			throw new Exception("Cant procced data");
		}
		return company;
	}

	public Company findById(String code) {
		Optional<Company> optComp = repository.findById(code);
		if (optComp.isPresent())
			return optComp.get();
		return null;
	}

	public List<Company> showAll() {
		return repository.findAll();
	}

	public void deleted(String code) throws Exception {
		Optional<Company> optComp = repository.findById(code);
		if (optComp.isPresent()) {
			repository.delete(optComp.get());
		} else {
			throw new Exception("Your data is not exist");
		}
	}
}
