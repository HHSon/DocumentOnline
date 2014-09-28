package doc.online.model;

import doc.online.util.StringUtil;
import java.util.Date;

public class Client {
	private String clientId;
	private String clientSecret;
	private String name;
	private String description;
	private Date dateRegistered;
	private String group;
	private String homepage;


	public Client() {
	}


	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		if (StringUtil.isNullOrEmpty(clientId))
			throw new IllegalArgumentException("clientId is null or empty");
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		if (StringUtil.isNullOrEmpty(clientSecret))
			throw new IllegalArgumentException("clientSecret is null or empty");
		this.clientSecret = clientSecret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (StringUtil.isNullOrEmpty(name))
			throw new IllegalArgumentException("client name is null or empty");
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		if (StringUtil.isNullOrEmpty(desc))
			throw new IllegalArgumentException("description is null or empty");
		this.description = desc;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered) {
		if (dateRegistered == null)
			throw new IllegalArgumentException("dateRegistered is null");

		this.dateRegistered = dateRegistered;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		if (StringUtil.isNullOrEmpty(group))
			throw new IllegalArgumentException("group is null");
		this.group = group;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public boolean verify() {
		if (StringUtil.isNullOrEmpty(clientId))
			return false;

		if (StringUtil.isNullOrEmpty(clientSecret))
			return false;

		if (StringUtil.isNullOrEmpty(name))
			return false;

		if (StringUtil.isNullOrEmpty(description))
			return false;

		if (dateRegistered == null)
			return false;

		if (StringUtil.isNullOrEmpty(group))
			return false;

		return true;
	}
}
