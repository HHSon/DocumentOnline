package doc.online.dao;

import doc.online.dao.ldap.UserDAOLdap;
import doc.online.dao.memory.UserDAOMemory;
import doc.online.dao.sql.UserDAOSql;
import doc.online.util.DatabaseMode;
import doc.online.model.User;
import doc.online.util.Configuration;

public class UserDAO implements IUserDAO {

	/**
	 * Get specific UserDAO depends on database mode.
	 */
	public IUserDAO getSpecificUserDAO() {
		DatabaseMode databaseMode = Configuration.getDatabaseMode();

		if (databaseMode == DatabaseMode.SQL_DB)
			return new UserDAOSql();

		if (databaseMode == DatabaseMode.MEMORY)
			return new UserDAOMemory();

		if (databaseMode == DatabaseMode.LDAP)
			return new UserDAOLdap();

		throw new IllegalArgumentException("Cannot find appropriate database mode");
	}

	@Override
	public boolean createUser(User user) {
		return getSpecificUserDAO().createUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return getSpecificUserDAO().updateUser(user);
	}

	@Override
	public User getUserByLoginId(String loginId) {
		return getSpecificUserDAO().getUserByLoginId(loginId);
	}

	@Override
	public User getUserByEmail(String email) {
		return getSpecificUserDAO().getUserByEmail(email);
	}
}
