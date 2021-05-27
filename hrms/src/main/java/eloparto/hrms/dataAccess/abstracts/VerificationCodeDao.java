package eloparto.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import eloparto.hrms.entities.concretes.VerificationCode;

public interface VerificationCodeDao extends JpaRepository<VerificationCode,Integer> {

	VerificationCode findByUserIdAndVerificationCode(int userId, String verificationCode); 
}
