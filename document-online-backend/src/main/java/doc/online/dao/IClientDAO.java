package doc.online.dao;

import doc.online.model.Client;

public interface IClientDAO {
	boolean registerClient(Client client);
	Client getClientById(String clientId);
	Client getClientByName(String clientName);
	boolean isClientLoginValid(String clientId, String clientSecret);
	boolean isClientIdExist(String clientId);
	boolean isClientNameExist(String clientName);
}
