package com.app.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.profile.dto.resp.ApiResponse;
import com.app.profile.services.ActivityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/log")
public class LogActivityController {

	@Autowired
	ActivityService activityService;

	@GetMapping("/{pageIndex}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get list of all Activity.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 10)")
	public ResponseEntity<ApiResponse> getListActivity(
			@ApiParam(value = "The page number ", required = true) @PathVariable("pageIndex") int pageIndex,
			@ApiParam(value = "The page size", required = false) @RequestParam(value = "size", defaultValue = "10") Integer size,
			@ApiParam(value = "The page sort by", required = false) @RequestParam(required = false, name = "sort_by") String sortBy,
			@ApiParam(value = "The page sort direction", required = false) @RequestParam(required = false, name = "sort_dir") String sortDirection) {

		Sort sortz = null;
		if (StringUtils.hasText(sortBy) && StringUtils.hasText(sortDirection)) {
			sortz = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		} else if (StringUtils.hasText(sortBy) && !StringUtils.hasText(sortDirection)) {
			sortz = Sort.by(sortBy).ascending();
		} else {
			sortz = Sort.by("activityDate").descending();
		}
		Pageable paging = PageRequest.of(pageIndex, size, sortz);
		return ResponseEntity.ok(new ApiResponse(activityService.showAll(paging)));
	}
}
