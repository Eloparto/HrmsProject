package eloparto.hrms.business.abstracts;

import java.util.List;

import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.entities.concretes.Employee;

public interface EmployeeService {

	DataResult<List<Employee>> getAll();
	
	Result add(Employee candidate);
	
	DataResult<Employee> getByEmail(String email);
	
}
