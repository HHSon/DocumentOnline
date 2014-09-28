package doc.online.dao;

import doc.online.model.User;

public interface IUserDAO {
	boolean createUser(User user);
	boolean updateUser(User user);
	User getUserByLoginId(String loginId);
	User getUserByEmail(String email);
}
