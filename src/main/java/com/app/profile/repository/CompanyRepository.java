package com.app.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.profile.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>{

}
