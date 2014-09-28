package doc.online.database.memory;

import doc.online.model.Client;
import doc.online.util.StringUtil;
import java.util.List;
import java.util.ArrayList;

public final class ClientManager {
	private static final ClientManager instance = new ClientManager();
	private final List<Client> clients = new ArrayList<Client>();


	private ClientManager() {
	}

	public static ClientManager getInstance() {
		return instance;
	}

	public boolean createClient(Client client) {
		if (client == null)
			throw new NullPointerException("client cannot be null");

		return clients.add(client);
	}

	public boolean isClientIdExist(String clientId) {
		return getClientById(clientId) != null;
	}

	public Client getClientById(String clientId) {
		if (StringUtil.isNullOrEmpty(clientId))
			return null;

		for (Client c : clients)
			if (c.getClientId().equalsIgnoreCase(clientId))
				return c;

		return null;
	}

	public Client getClientByName(String clientName) {
		if (StringUtil.isNullOrEmpty(clientName))
			return null;

		for (Client c : clients)
			if (c.getName().equalsIgnoreCase(clientName))
				return c;

		return null;
	}

	public boolean isClientLoginValid(String clientId, String clientSecret) {
		Client c = getClientById(clientId);

		if (c == null || clientSecret == null)
			return false;

		return c.getClientSecret().equals(clientSecret);
	}

	public void deleteClientById(String clientId) {
		if (clientId == null)
			throw new NullPointerException("clientId cannot be null");

		Client c = getClientById(clientId);
		if (c != null)
			clients.remove(c);		
	}
}
