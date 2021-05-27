package eloparto.hrms.business.abstracts;

import java.util.List;

import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.entities.concretes.Candidate;

public interface CandidateService {
	
	Result add(Candidate candidate);
	
	DataResult<List<Candidate>> getAll();
}
