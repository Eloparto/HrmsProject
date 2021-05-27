package eloparto.hrms.business.abstracts;

import java.util.List;

import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.entities.concretes.JobTitle;

public interface JobTitleService {

	DataResult<JobTitle> add(JobTitle title);

	List<JobTitle> getAll();

	List<JobTitle> findJobTitles(String title);

}
