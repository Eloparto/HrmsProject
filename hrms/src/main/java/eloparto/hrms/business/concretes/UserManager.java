package eloparto.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import eloparto.hrms.business.abstracts.UserService;
import eloparto.hrms.core.utilities.results.DataResult;
import eloparto.hrms.core.utilities.results.SuccessDataResult;
import eloparto.hrms.dataAccess.abstracts.UserDao;
import eloparto.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService {


	private UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {

		this.userDao = userDao;
		
	}

	@Override
	public DataResult<List<User>> getAll() {

		return new SuccessDataResult<List<User>>(this.userDao.findAll(),null);
	}

	

}
