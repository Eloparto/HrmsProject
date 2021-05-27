package eloparto.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eloparto.hrms.entities.concretes.Candidate;


public interface CandidateDao extends JpaRepository<Candidate,Integer> {

	List<Candidate> findAllByEmail(String email);

	List<Candidate> findAllByNationalityId(String identificationNumber);

}