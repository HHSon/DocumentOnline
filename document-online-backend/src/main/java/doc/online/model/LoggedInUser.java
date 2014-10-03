package doc.online.model;

import java.util.Date;

public class LoggedInUser {
	private User user;
	private Date logTime;
	
	public LoggedInUser() {
	}
	
	public LoggedInUser(User user, Date logTime) {
		this.setUser(user);
		this.setLogTime(logTime);
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		if (user == null)
			throw new NullPointerException("user is null");
		this.user = user;
	}
	
	public Date getLogTime() {
		return logTime;
	}
	
	public void setLogTime(Date logTime) {
		if (logTime == null)
			throw new NullPointerException("logTime is null");
		this.logTime = logTime;
	}
}
