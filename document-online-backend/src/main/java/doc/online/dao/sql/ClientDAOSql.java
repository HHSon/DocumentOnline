package doc.online.dao.sql;

import doc.online.dao.IClientDAO;
import doc.online.model.Client;

public class ClientDAOSql implements IClientDAO {

	@Override
	public boolean registerClient(Client newClient) {
		throw new RuntimeException("This method is not implemented");
	}

	@Override
	public Client getClientByName(String clientName) {
		throw new RuntimeException("This method is not implemented");
	}

	@Override
	public Client getClientById(String clientId) {
		throw new RuntimeException("This method is not implemented");
	}

	@Override
	public boolean isClientLoginValid(String clientId, String clientSecret) {
		throw new RuntimeException("This method is not implemented");
	}

	@Override
	public boolean isClientIdExist(String clientId) {
		throw new RuntimeException("This method is not implemented");
	}

	@Override
	public boolean isClientNameExist(String clientName) {
		throw new RuntimeException("This method is not implemented");
	}
}
