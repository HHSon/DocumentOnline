package doc.online.dao.memory;

import doc.online.dao.IClientDAO;
import doc.online.database.memory.ClientManager;
import doc.online.model.Client;

public class ClientDAOMemory implements IClientDAO {

	private final ClientManager clientManager = ClientManager.getInstance();

	@Override
	public boolean registerClient(Client newClient) {
		return clientManager.createClient(newClient);
	}

	@Override
	public Client getClientById(String clientId) {
		return clientManager.getClientById(clientId);
	}

	@Override
	public Client getClientByName(String clientName) {
		return clientManager.getClientByName(clientName);
	}

	@Override
	public boolean isClientLoginValid(String clientId, String clientSecret) {
		return clientManager.isClientLoginValid(clientId, clientSecret);
	}

	@Override
	public boolean isClientIdExist(String clientId) {
		return getClientById(clientId) != null;
	}

	@Override
	public boolean isClientNameExist(String clientName) {
		return getClientByName(clientName) != null;
	}
}
