package eloparto.hrms.business.abstracts;

import java.util.List;

import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.entities.concretes.Employer;

public interface EmployerService {

	DataResult<List<Employer>> getAll();
	
	Result add(Employer employer);
	
}
