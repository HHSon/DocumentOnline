package doc.online.dao.memory;

import doc.online.dao.IUserDAO;
import doc.online.database.memory.UserManager;
import doc.online.model.User;

public class UserDAOMemory implements IUserDAO {

	private static UserManager userManager = UserManager.getInstance();

	@Override
	public boolean createUser(User user) {
		return userManager.createUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// because user is store in memory
		// so it doesn'n need to update
		return true;
	}

	@Override
	public User getUserByLoginId(String loginId) {
		return userManager.getUserByLoginId(loginId);
	}

	@Override
	public User getUserByEmail(String email) {
		return userManager.getUserByEmail(email);
	}
}
