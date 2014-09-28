package doc.online.dao.memory;

import doc.online.dao.IDocumentDAO;
import doc.online.database.memory.DocumentManager;
import doc.online.model.Document;
import doc.online.model.User;
import java.util.List;
import java.util.ArrayList;

public class DocumentDAOMemory implements IDocumentDAO {
	private DocumentManager documentManager = DocumentManager.getInstance();
	
	@Override
	public boolean createDocument(Document newDoc) {
		return documentManager.createDocument(newDoc);
	}

	@Override
	public List<Document> getBriefDocumentList(String userId) {
		List<Document> docs = documentManager.getAllDocuments(userId);
		List<Document> briefList = new ArrayList<Document>(docs.size());

		for (Document doc : docs)
			briefList.add(doc.createBriefInfo());

		return briefList;
	}

	@Override
	public Document getDocumentById(String ownerLoginId, Integer docId) {
		return documentManager.getDocumentById(ownerLoginId, docId);
	}

	@Override
	public boolean updateDocument(Document doc) {
		// because doc is store on memory, so
		// it doesn't require to update doc
		return true;
	}

	@Override
	public boolean deleteDocument(String ownerLoginId, Integer docId) {
		return documentManager.deleteDocument(ownerLoginId, docId);
	}
}
