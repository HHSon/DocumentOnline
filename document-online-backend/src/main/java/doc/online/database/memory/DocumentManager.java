package doc.online.database.memory;

import doc.online.database.memory.UserManager;
import doc.online.model.Document;
import doc.online.model.User;
import doc.online.util.StringUtil;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * The class manages all documents of all users.
 */
public class DocumentManager {
	private UserManager userManager = UserManager.getInstance();

	private static DocumentManager _instance = new DocumentManager();

	/**
	 * Private constructor (singleton)
	 */
	private DocumentManager() {
	}

	public static DocumentManager getInstance() {	
		return _instance;
	}

	/**
	 * create new user document.
	 */
	public boolean createDocument(Document doc) {
		if (doc == null || !userManager.containsUser(doc.getOwner())) {
			return false;
		}

		User docOwner = doc.getOwner();

		if (doc.getDateCreated() == null) {
			doc.setDateCreated(new Date());
		}

		if (doc.getLastModified() == null) {
			doc.setLastModified(new Date());
		}

		return docOwner.getDocuments().add(doc);
	}

	public Document getDocumentById(String ownerLoginId, Integer docId) {
		if (docId == null) {
			return null;
		}

		User docOwner = userManager.getUserByLoginId(ownerLoginId);

		if (docOwner != null) {
			List<Document> documents = docOwner.getDocuments();

			for (Document doc : documents) {
				if (docId == doc.getDocumentId())
					return doc;
			}
		}

		return null;
	}

	public List<Document> getAllDocuments(String userLoginId) {
		User docOwner = userManager.getUserByLoginId(userLoginId);

		if (docOwner != null) {
			return docOwner.getDocuments();
		}
		
		return null;
	}

	public boolean deleteDocument(String ownerLoginId, Integer docId) {
		Document doc = getDocumentById(ownerLoginId, docId);

		if (doc != null) {
			return doc.getOwner().getDocuments().remove(doc);
		}

		return false;
	}
}
