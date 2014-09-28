package doc.online.dao.sql;

import doc.online.dao.IUserDAO;
import doc.online.model.User;

public class UserDAOSql implements IUserDAO {
	
	@Override
	public boolean createUser(User user) {
		throw new RuntimeException("this method is not implemented");
	}

	@Override
	public boolean updateUser(User user) {
		throw new RuntimeException("this method is not implemented");
	}

	@Override
	public User getUserByLoginId(String loginId) {
		throw new RuntimeException("this method is not implemented");
	}

	@Override
	public User getUserByEmail(String email) {
		throw new RuntimeException("this method is not implemented");
	}
}
