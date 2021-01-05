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

import com.app.profile.dto.resp.ApiResponse;
import com.app.profile.model.Company;
import com.app.profile.services.CompanyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/comp")
public class CompanyController extends AbstractRestHandler {

	@Autowired
	CompanyService companyService;
	
	@GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Company.", notes = "You have to provide a valid Company Code.")
	public ResponseEntity<ApiResponse> getCompanyById(@ApiParam(value = "The Code of the Company.", required = true) @PathVariable("code") String code) {
		return ResponseEntity.ok(new ApiResponse(companyService.findById(code)));
	}
	
	@GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get list of all Company.", notes = "The list is all data Company")
	public ResponseEntity<ApiResponse> getListCompanyAll(){
		return ResponseEntity.ok(new ApiResponse(companyService.showAll()));
	}
	
	@PostMapping("/add-or-update")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create or Update a Company resource.", notes = "Returns the Data Company")
	public ResponseEntity<ApiResponse> created(@RequestBody Company comp) throws Exception {
		try {
			return ResponseEntity.ok(new ApiResponse(companyService.saveOrUpdate(comp)));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
		}
	}
	
	@DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a Company resource.", notes = "You have to provide a valid company Code. Once deleted the resource can not be recovered.")
	public ResponseEntity<ApiResponse> deleteCompany(@ApiParam(value = "The Code of the existing Company resource.", required = true) @PathVariable("code") String code) throws Exception {
		try {
			companyService.deleted(code);
			return ResponseEntity.ok(new ApiResponse("Data deleted"));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
		}
	}
}
