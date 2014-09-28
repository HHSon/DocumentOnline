package doc.online.model;

import doc.online.model.Client;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClientTest {
	String[] empty = { null, "", " ", "   ", "\t", "\t\t", "\n", "\n\n", " \t\t  \n\n  " };

	public Client createTestClient() {
		Client c = new Client();
		c.setClientId("test-app");
		c.setClientSecret("12345678");
		c.setDateRegistered(new Date());
		c.setName("Unit Test Application");
		c.setDescription("application for unit test");
		c.setGroup("unit test");
		c.setHomepage("www.example.com");

		assertTrue(c.verify());
		return c;
	}

	@Test
	public void testVerifyEmptyId() {
		Client c = createTestClient();

		for (String s : empty) {
			try {
				c.setClientId(s);
				assertTrue(false);
			} catch (IllegalArgumentException ex) {
			}
		}
	}

	@Test
	public void testVerifyEmptyClientSecret() {
		Client c = createTestClient();
		
		for (String s : empty) {
			try {
				c.setClientSecret(s);
				assertTrue(false);
			} catch (IllegalArgumentException ex) {
			}
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void testVerifyNullDate() {
		Client c = createTestClient();
		c.setDateRegistered(null);
	}

	@Test
	public void testVerifyEmptyName() {
		Client c = createTestClient();
	
		for (String s : empty) {
			try {
				c.setName(s);
				assertTrue(false);
			} catch (IllegalArgumentException ex) {
			}
		}
	}

	@Test
	public void testVerifyEmptyDescription() {
		Client c = createTestClient();

		for (String s : empty) {
			try {
				c.setDescription(s);
				assertTrue(false);
			} catch (IllegalArgumentException ex) {
			}
		}
	}

	@Test
	public void testVerifyEmptyGroup() {
		Client c = createTestClient();
		
		for (String s : empty) {
			try {
				c.setGroup(s);
				assertTrue(false);
			} catch (IllegalArgumentException ex) {
			}
		}
	}
}
