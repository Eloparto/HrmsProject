package eloparto.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import eloparto.hrms.business.abstracts.JobTitleService;
import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.ErrorDataResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.core.utilities.results.SuccessDataResult;
import eloparto.hrms.core.utilities.results.SuccessResult;
import eloparto.hrms.dataAccess.abstracts.JobTitleDao;
import eloparto.hrms.entities.concretes.Employee;
import eloparto.hrms.entities.concretes.Employer;
import eloparto.hrms.entities.concretes.JobTitle;


@Service
public class JobTitleManager implements JobTitleService {

	@Autowired
	private JobTitleDao jobTitleDao;

	
	public JobTitleManager(JobTitleDao jobTitleDao) {

		this.jobTitleDao = jobTitleDao;
		
	}

	@Override
	public List<JobTitle> getAll() {

		return this.jobTitleDao.findAll();

	}

	@Override
	public DataResult<JobTitle> add(JobTitle title) {

		if (jobTitleDao.findAllByTitleName(title.getTitleName()).stream().count() !=0) {

			return new ErrorDataResult<JobTitle>(" Bu iş pozisyonu sistemde zaten mevcut");

		}

		return new SuccessDataResult<JobTitle>(this.jobTitleDao.save(title),
				" Girilen iş pozisyonu sisteme başarıyla eklendi");

	}

	@Override
	public List<JobTitle> findJobTitles(String title) {

		return this.jobTitleDao.findAllByTitleName(title);

	}


}
