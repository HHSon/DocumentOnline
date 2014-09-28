package doc.online.model;

import doc.online.model.User;
import java.util.Date;

/**
 * User's document
 */
public class Document {
	private static final int MAX_DISPLAY_CHARACTERS_NUMBER = 1000;

	private User owner;
	private Integer documentId;
	private String title; 		/* can be null or empty*/
	private String content; 	/* can be null or empty*/
	private Date dateCreated;
	private Date lastModified;

	/**
	 * Constructor
	 */
	public Document() {
	}


	/*---------------------------------------------------
	 * GET & SET
	 *---------------------------------------------------*/	

	public User getOwner() {
		return owner;
	}

	public void setOwner(User ownerUser) {
		this.owner = owner;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int id) {
		documentId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}


	public Document createBriefInfo() {
		final Document brief = new Document();

		brief.setOwner(this.owner);
		brief.setDocumentId(this.documentId);
		brief.setTitle(this.title);
		brief.setContent(this.getFirstParagraphOfContent());
		brief.setDateCreated(this.dateCreated);
		brief.setLastModified(this.lastModified);

		return brief;
	}

	public String getFirstParagraphOfContent() {
		if (content == null || content.isEmpty()) {
			return content;
		}

		int dotPos = content.indexOf('.');
		if (dotPos < 0) {
			dotPos = content.length();
		}
		dotPos = Math.max(dotPos, MAX_DISPLAY_CHARACTERS_NUMBER);

		return content.substring(0, dotPos);
	}

	/* -----------------------------------------------------
	 * END GET & SET
	 * -----------------------------------------------------*/

	public String getOwnerLoginId() {
		if (owner != null)
			return owner.getLoginId();
		else
			return null;
	}
}
