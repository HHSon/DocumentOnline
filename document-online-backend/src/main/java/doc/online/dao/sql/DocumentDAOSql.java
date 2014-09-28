package doc.online.dao.sql;

import doc.online.dao.IDocumentDAO;
import doc.online.model.Document;
import java.util.List;

public class DocumentDAOSql implements IDocumentDAO {

	@Override
	public boolean createDocument(Document newDoc) {
		throw new RuntimeException();
	}

	@Override
	public List<Document> getBriefDocumentList(String userId) {
		throw new RuntimeException();
	}

	@Override
	public Document getDocumentById(String ownerLoginId, Integer docId) {
		throw new RuntimeException();
	}

	@Override
	public boolean updateDocument(Document doc) {
		throw new RuntimeException();
	}

	@Override
	public boolean deleteDocument(String ownerLoginId, Integer docId) {
		throw new RuntimeException();
	}
}
