package doc.online.model;

import java.util.Calendar;
import java.util.Date;

public class LoggedInClient {
	private Client client;
	private Date loginTime;
	private Date expiredTime;

	public LoggedInClient() {
	}

	public LoggedInClient(Client client, Date loginTime, int timeoutInSeconds) {
		if (client == null || loginTime == null) {
			throw new NullPointerException("create LoggedInClientFailed");
		}

		this.client = client;
		this.loginTime = loginTime;

		final Calendar cal = Calendar.getInstance();
		cal.setTime(loginTime);
		cal.add(Calendar.SECOND, timeoutInSeconds);
		this.expiredTime = cal.getTime();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		if (loginTime == null)
			throw new NullPointerException("loginTime cannot be null");

		this.loginTime = loginTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		if (expiredTime == null)
			throw new NullPointerException("expiredTime cannot be null");

		this.expiredTime = expiredTime;
	}

	public boolean isExpired() {
		final Date now = new Date();
		return now.after(expiredTime);
	}
}
