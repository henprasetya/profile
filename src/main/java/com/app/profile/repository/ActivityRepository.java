package com.app.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.profile.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

}
