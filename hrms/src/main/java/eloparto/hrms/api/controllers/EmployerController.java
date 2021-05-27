package eloparto.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eloparto.hrms.business.abstracts.EmployerService;
import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

	EmployerService employerService;
	
	@Autowired
	public EmployerController(EmployerService employerService) {
		
		this.employerService = employerService;
		
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Employer>> getAll(){
		
		return this.employerService.getAll();
		
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Employer employer) {
		
		return this.employerService.add(employer);
		
	}
	
}
