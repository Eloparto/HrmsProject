package eloparto.hrms.business.abstracts;

import java.util.List;

import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.entities.concretes.Candidate;
import eloparto.hrms.entities.concretes.User;

public interface UserService {

	DataResult<List<User>> getAll();
}
