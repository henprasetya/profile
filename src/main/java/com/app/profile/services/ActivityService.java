package com.app.profile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.profile.model.Activity;
import com.app.profile.repository.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	ActivityRepository activityRepository;
	
	public Page<Activity> showAll(Pageable paging){
		return activityRepository.findAll(paging);
	}
}
