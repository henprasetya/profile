package com.app.profile.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.profile.model.Activity;
import com.app.profile.model.Employee;
import com.app.profile.repository.ActivityRepository;
import com.app.profile.repository.EmployeeRepository;

@Component
public class HandlerRequestFilter extends OncePerRequestFilter {

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Employee emp = null;
		try {
			if (request.getHeader("session_id") != null) {
				emp = employeeRepository.findById(request.getHeader("session_id").toString()).get();
			}
		} catch (Exception e) {
		}
		Activity act = new Activity();
		act.setActivity(request.getServletPath());
		act.setActivityDate(LocalDateTime.now());
		act.setEmployee(emp);
		activityRepository.save(act);
		filterChain.doFilter(request, response);

	}

}
