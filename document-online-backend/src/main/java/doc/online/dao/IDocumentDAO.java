package doc.online.dao;

import doc.online.model.Document;
import java.util.List;

public interface IDocumentDAO {
	boolean createDocument(Document newDoc);
	List<Document> getBriefDocumentList(String userId);
	Document getDocumentById(String ownerLoginId, Integer docId);
	boolean updateDocument(Document doc);
	boolean deleteDocument(String ownerLoginId, Integer docId);
}
