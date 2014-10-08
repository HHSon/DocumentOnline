package doc.online.model;

import doc.online.util.StringUtil;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class User {
	private String loginId;
	private String password;
	private String name;
	private String email;
	private Date   birthday;
	private String address;
	private List<Document> documents = new ArrayList<Document>();


	/**
	 * Constructor
	 */
	public User() {
	}


	/* ----------------------------------------------
	 * GET & SET
	 * ---------------------------------------------*/

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	/**
	 * Set document list for user.
	 * @param documents user's documents (can be empty but cannot be null)
	 */
	public void setDocuments(List<Document> documents) {
		if (documents == null) {
			throw new NullPointerException("documents cannot be null");
		}
		
		for (Document doc : documents) {
			final String userLoginId = doc.getOwnerLoginId();
			if (userLoginId == null || !userLoginId.equalsIgnoreCase(this.loginId))
			throw new IllegalArgumentException("user is not the owner of document");
		}

		this.documents = documents;
	}

	/*------------------------------------------------
	 * END GET & SET
	 *-----------------------------------------------*/
}
