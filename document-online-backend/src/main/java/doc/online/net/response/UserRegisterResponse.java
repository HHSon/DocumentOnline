package doc.online.net.response;

import doc.online.model.User;

public class UserRegisterResponse extends BaseResponse {
	protected User user;
	
	public UserRegisterResponse() {
	}

	public UserRegisterResponse(User user) {
		this.setUser(user);
	}

	public void setUser(User user) {
		if (user == null)
			throw new NullPointerException("user is null");
		this.user = user;
	}
}
