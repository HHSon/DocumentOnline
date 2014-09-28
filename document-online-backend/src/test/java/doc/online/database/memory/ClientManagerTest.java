package doc.online.database.memory;

import doc.online.database.memory.ClientManager;
import doc.online.model.Client;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/*
getInstance()
createClient(client)
isClientIdExist(clientId)
isClientLoginValid(clientId, clientSecret)
*/

public class ClientManagerTest {
	ClientManager clientManager = ClientManager.getInstance();

	Client createTestClient() {
		Client c = new Client();

		c.setClientId("test_app_id");
		c.setClientSecret("12345678");
		c.setName("test app");
		c.setDescription("app for unit test");
		c.setDateRegistered(new Date());
		c.setGroup("test group");
		c.setHomepage("www.example.com");

		return c;
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(clientManager);
		assertSame(clientManager, ClientManager.getInstance());
	}

	@Test(expected=NullPointerException.class)
	public void testCreateNullClient() {
		clientManager.createClient(null);
	}

	@Test
	public void testCreateValidClient() {
		Client newClient = createTestClient();
		assertTrue(clientManager.createClient(newClient));
		clientManager.deleteClientById(newClient.getClientId());
	}

	@Test
	public void testGetClientByEmptyId() {
		assertNull(clientManager.getClientById(null));
		assertNull(clientManager.getClientById(""));
		assertNull(clientManager.getClientById(" "));
		assertNull(clientManager.getClientById("    "));
		assertNull(clientManager.getClientById("\t"));
		assertNull(clientManager.getClientById("\n"));
		assertNull(clientManager.getClientById(" \t\t  \n\n "));
	}

	@Test
	public void testGetClientById() {
		Client c = createTestClient();
		assertTrue(clientManager.createClient(c));
		assertSame(c, clientManager.getClientById(c.getClientId()));
		assertNull(clientManager.getClientById("123"));
		
		clientManager.deleteClientById(c.getClientId());
	}

	@Test(expected=NullPointerException.class)
	public void testDeleteByNullClientId() {
		clientManager.deleteClientById(null);
	}

	@Test
	public void testDeleteClientById() {
		Client newClient = createTestClient();
		assertTrue(clientManager.createClient(newClient));
		assertNotNull(clientManager.getClientById(newClient.getClientId()));

		clientManager.deleteClientById(newClient.getClientId());
		assertNull(clientManager.getClientById(newClient.getClientId()));
	}
}
