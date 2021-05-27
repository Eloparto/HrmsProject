package eloparto.hrms.business.abstracts;

import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.Result;
import eloparto.hrms.entities.concretes.VerificationCode;

public interface VerificationCodeService {

	Result add(VerificationCode code);
	
	DataResult<VerificationCode> getByUserIdAndVerificationCode(int userId, String verificationCode);
	
	Result update(VerificationCode code);
}