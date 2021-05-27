package eloparto.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eloparto.hrms.business.abstracts.EmployerService;
import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.ErrorResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.core.utilities.results.SuccessDataResult;
import eloparto.hrms.core.utilities.results.SuccessResult;
import eloparto.hrms.dataAccess.abstracts.EmployerDao;
import eloparto.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		
		this.employerDao = employerDao;
		
	}

	@Override
	public DataResult<List<Employer>> getAll() {

		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "İş verenler başarıyla listelendi");
	}

	@Override
	public Result add(Employer employer) {

		if(!companyNameValid(employer)) {
			return new ErrorResult("Şirket adı boş bırakılamaz");
		}else if(!webSiteValid(employer)) {
			return new ErrorResult("Website adres alanı boş bırakılamaz");
		}else if (!isRealEmployer(employer)) {
			return new ErrorResult("Geçersiz Email adresi");
		}else if(!checkIfEqualEmailAndDomain(employer.getEmail(),employer.getWebSite())) {
			return new ErrorResult("Web sitesi ile aynı domaine sahip Email kullanmanız gerekmektedir");
		}else if (!passwordNullValid(employer)) {
			return new ErrorResult("Şifre alanını boş bırakılamaz");
		}else if(!passwordChracterLimit(employer)) {
			return new ErrorResult("Şifreniz en az 6 karakterden oluşmalı");
		}else if (!isRealPhoneNumber(employer)) {
			return new ErrorResult("Geçersiz telefon numarası girdiniz");
		}else if (!isEmailAlreadyRegistered(employer)) {
			return new ErrorResult("Girdiğiniz Email adresi sistemde zaten mevcut, farklı bir Email ile tekrar deneyiniz");
		}
		
		this.employerDao.save(employer);
		
		return new SuccessResult("İş veren sisteme başarıyla eklendi !");
	}
	
	private boolean companyNameValid(Employer employer) {
		if(employer.getCompanyName().isBlank() || employer.getCompanyName().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean webSiteValid(Employer employer) {
		if(employer.getWebSite().isBlank() || employer.getWebSite().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean isRealEmployer(Employer employer) {
		String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

		return pattern.matcher(employer.getEmail()).find();
	}
	
	private boolean isEmailAlreadyRegistered(Employer employer) {
		if(employerDao.findAllByEmail(employer.getEmail()).stream().count() !=0) {
			return false;
		}
		return true;
	}
	
	private boolean checkIfEqualEmailAndDomain(String email, String website) {
		String[] emailArr = email.split("@", 2);
		String domain = website.substring(4, website.length());
		if (emailArr[1].equals(domain)) {
			return true;
		}
		return false;
		}
		
	private boolean passwordNullValid(Employer employer) {
		if(employer.getPassword().isBlank() || employer.getPassword().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean passwordChracterLimit(Employer employer) {
		if(employer.getPassword().length()<6) {
			return false;
		}
		return true;
	}
	
	private boolean isRealPhoneNumber(Employer employer) {
		String patterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

		Pattern pattern = Pattern.compile(patterns);
		Matcher matcher = pattern.matcher(employer.getPhoneNumber());
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	
}
