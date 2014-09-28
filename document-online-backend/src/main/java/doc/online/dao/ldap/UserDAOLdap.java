package doc.online.dao.ldap;

import doc.online.dao.IUserDAO;
import doc.online.model.User;

public class UserDAOLdap implements IUserDAO {

	@Override
	public boolean createUser(User user) {
		throw new RuntimeException();
	}

	@Override
	public boolean updateUser(User user) {
		throw new RuntimeException();
	}

	@Override
	public User getUserByLoginId(String loginId) {
		throw new RuntimeException();
	}

	@Override
	public User getUserByEmail(String email) {
		throw new RuntimeException();
	}
}
