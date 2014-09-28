package doc.online.dao;

import doc.online.dao.ldap.ClientDAOLdap;
import doc.online.dao.memory.ClientDAOMemory;
import doc.online.dao.sql.ClientDAOSql;
import doc.online.model.Client;
import doc.online.util.Configuration;
import doc.online.util.DatabaseMode;
import doc.online.util.StringUtil;


public class ClientDAO implements IClientDAO {

	protected IClientDAO clientDAO = getSpecificClientDAO();

	public IClientDAO getSpecificClientDAO() {
		final DatabaseMode dbMode = Configuration.getDatabaseMode();

		if (dbMode == DatabaseMode.SQL_DB) {
			return new ClientDAOSql();
		}

		if (dbMode == DatabaseMode.MEMORY) {
			return new ClientDAOMemory();
		}

		if (dbMode == DatabaseMode.LDAP) {
			return new ClientDAOLdap();
		}

		throw new IllegalArgumentException("Cannot find appropriate database mode");
	}

	@Override
	public boolean registerClient(Client newClient) {
		if (newClient == null || newClient.verify() == false)
			throw new IllegalArgumentException("newClient verify failed");

		return clientDAO.registerClient(newClient);
	}

	@Override
	public Client getClientById(String clientId) {
		if (StringUtil.isNullOrEmpty(clientId))
			throw new NullPointerException("clientId cannot be empty");

		return clientDAO.getClientById(clientId);
	}

	@Override
	public Client getClientByName(String clientName) {
		if (StringUtil.isNullOrEmpty(clientName))
			throw new IllegalArgumentException("clientName is null or empty");
		return clientDAO.getClientByName(clientName);
	}

	@Override
	public boolean isClientLoginValid(String clientId, String clientSecret) {
		if (StringUtil.isNullOrEmpty(clientId) || StringUtil.isNullOrEmpty(clientSecret))
			return false;

		return clientDAO.isClientLoginValid(clientId, clientSecret);
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
