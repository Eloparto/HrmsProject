package eloparto.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eloparto.hrms.business.abstracts.CandidateService;
import eloparto.hrms.core.utilities.adapters.mernis.MernisServiceAdapter;
import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.ErrorResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.core.utilities.results.SuccessDataResult;
import eloparto.hrms.core.utilities.results.SuccessResult;
import eloparto.hrms.dataAccess.abstracts.CandidateDao;
import eloparto.hrms.entities.concretes.Candidate;
import eloparto.hrms.entities.concretes.Employer;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao) {

		this.candidateDao = candidateDao;
	}

	@Override
	public Result add(Candidate candidate) {
		
		if(!firstNameChecker(candidate)) {
			return new ErrorResult("Ad alanı boş bırakılamaz");
		}else if(!firstNameCharacterLimit(candidate)) {
			return new ErrorResult("Geçersiz isim girdiniz");
		}else if(!lastNameChecker(candidate)) {
			return new ErrorResult("Soyad alanı boş bırakılamaz");
		}else if(!lastNameChecker(candidate)) {
			return new ErrorResult("Geçersiz soyisim girdiniz");
		}
		
		else if(!MernisServiceAdapter.checkIfRealPerson(candidate.getNationalityId())) {
			return new ErrorResult("TC Kimlik nuramaranız doğrulanamadı, tekrar kontrol ederek deneyiniz");
		}else if(candidate.getNationalityId().isBlank()) {
			return new ErrorResult("TC Kimlik Nuramara alanını boş bırakılamaz");
		}
		
		else if (!birthDateChecker(candidate)) {
			return new ErrorResult("Doğum tarihi alanını boş bırakılamaz");
		}
		
		else if(!emailNullChecker(candidate)) {
			return new ErrorResult("Email alanını boş bırakılamaz");
		}else if(!isRealEmail(candidate)) {
			return new ErrorResult("Girilen Email adresi geçersiz");
		}
		
		else if(!passwordNullChecker(candidate)) {
			return new ErrorResult("Şifre alanını boş bırakılamaz");
		}else if(!passwordChracterLimit(candidate)) {
			return new ErrorResult("Şifreniz minimum 6 karakterden oluşmalıdır");
		}
		
		else if(candidateDao.findAllByEmail(candidate.getEmail()).stream().count() != 0) {
			return new ErrorResult("Girdiğiniz Email adresi sistemde zaten mevcut");
		}else if(candidateDao.findAllByNationalityId(candidate.getNationalityId()).stream().count() !=0) {
			return new ErrorResult("Girdiğiniz TC Kimlik numarası sistemde zaten mevcut");
		}
				
		this.candidateDao.save(candidate);
		
		return new SuccessResult("İş arayan üye sisteme başarıyla eklendi");

	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "İş arayanlar listelendi !");
	}

	private boolean firstNameCharacterLimit(Candidate candidate) {
		if(candidate.getFirstName().length()<=2) {
			return false;
		}
		return true;
	}
	
	private boolean lastNameChracterLimit(Candidate candidate) {
		if(candidate.getLastName().length()<=1) {
			return false;
		}
		return true;
	}
	
	private boolean firstNameChecker(Candidate candidate) {
		if(candidate.getFirstName().isBlank() || candidate.getFirstName().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean lastNameChecker(Candidate candidate) {
		if(candidate.getFirstName().isBlank() || candidate.getFirstName().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean birthDateChecker(Candidate candidate) {
		if(candidate.getDateOfBirth().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean emailNullChecker(Candidate candidate) {
		if(candidate.getEmail().isBlank()|| candidate.getEmail().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean passwordChracterLimit(Candidate candidate) {
		if(candidate.getPassword().length()<6) {
			return false;
		}
		return true;
	}
	
	private boolean passwordNullChecker(Candidate candidate) {
		if(candidate.getPassword().isBlank() || candidate.getPassword().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean isRealEmail(Candidate candidate) {
		String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

		return pattern.matcher(candidate.getEmail()).find();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
