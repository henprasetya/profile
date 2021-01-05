package com.app.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.profile.dto.req.EmployeeDTO;
import com.app.profile.dto.resp.ApiResponse;
import com.app.profile.services.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/{nik}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Employee.", notes = "You have to provide a valid Employee NIK.")
	public ResponseEntity<ApiResponse> getEmployeeByNik(@ApiParam(value = "The Nik of the Employee.", required = true) @PathVariable("nik") String nik) {
		return ResponseEntity.ok(new ApiResponse(employeeService.findByNik(nik)));
	}
	
	@GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get list of all Employee.", notes = "The list is all data Employee")
	public ResponseEntity<ApiResponse> getListEmployeeAll(){
		return ResponseEntity.ok(new ApiResponse(employeeService.showAll()));
	}
	
	@PostMapping("/add-or-update")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create or Update a Employee resource.", notes = "Returns the Data Employee")
	public ResponseEntity<ApiResponse> created(@RequestBody EmployeeDTO dto) throws Exception {
		try {
			return ResponseEntity.ok(new ApiResponse(employeeService.saveOrUpdate(dto)));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
		}
	}
	
	@DeleteMapping("/{nik}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a Employee resource.", notes = "You have to provide a valid Employee nik. Once deleted the resource can not be recovered.")
	public ResponseEntity<ApiResponse> deleteCompany(@ApiParam(value = "The Nik of the existing Employee resource.", required = true) @PathVariable("nik") String nik) throws Exception {
		try {
			employeeService.deleted(nik);
			return ResponseEntity.ok(new ApiResponse("Data deleted"));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
		}
	}
}
