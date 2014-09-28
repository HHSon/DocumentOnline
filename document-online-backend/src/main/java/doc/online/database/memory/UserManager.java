package doc.online.database.memory;

import doc.online.model.User;
import doc.online.util.StringUtil;
import java.util.List;
import java.util.ArrayList;

/**
 * Manage all user in memory
 */
public final class UserManager {
	private List<User> users = new ArrayList<User>();
	private static UserManager _instance = new UserManager();

	/**
	 * Private constructor (singleton)
	 */
	private UserManager() {
	}

	public static UserManager getInstance() {
		return _instance;
	}

	public boolean createUser(User user) {
		if (user == null ||
				StringUtil.isNullOrEmpty(user.getLoginId()) ||
				isUserLoginIdExist(user.getLoginId()) ||
				StringUtil.isNullOrEmpty(user.getPassword()) ||
				StringUtil.isNullOrEmpty(user.getName()) ||
				StringUtil.isNullOrEmpty(user.getEmail()) ||
				isUserEmailExist(user.getEmail()) ||
				user.getBirthday() == null) {
			return false;
		}

		return users.add(user);
	}

	public boolean containsUser(User user) {
		if (user == null) {
			return false;
		} else {
			return users.contains(user);
		}
	}

	public boolean deleteUser(User user) {
		if (user == null) {
			return false;
		}
		return users.remove(user);
	}

	public User getUserByLoginId(String userLoginId) {
		if (StringUtil.isNullOrEmpty(userLoginId)) {
			return null;
		}

		for (User user : users) {
			if (user.getLoginId().equalsIgnoreCase(userLoginId))
				return user;
		}

		return null;
	}

	public User getUserByEmail(String email) {
		if (StringUtil.isNullOrEmpty(email)) {
			return null;
		}

		for (User user : users) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				return user;
			}
		}

		return null;
	}

	public boolean isUserLoginIdExist(String userLoginId) {
		return getUserByLoginId(userLoginId) != null;
	}

	public boolean isUserEmailExist(String email) {
		return getUserByEmail(email) != null;
	}
}
