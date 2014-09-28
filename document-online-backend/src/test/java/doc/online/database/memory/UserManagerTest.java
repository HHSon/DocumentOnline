package doc.online.database.memory;

import doc.online.database.memory.UserManager;
import doc.online.model.User;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;


public class UserManagerTest {
	UserManager userManager = UserManager.getInstance();

	public User createTestUser() {
		User u = new User();

		u.setLoginId("0001");
		u.setPassword("12345678");
		u.setName("test");
		u.setEmail("test@example.com");
		u.setBirthday(new Date());

		return u;
	}

	@Test
	public void testGetInstance() {
		assertNotNull(userManager);
		assertTrue(UserManager.getInstance() == userManager);
	}

	@Test
	public void testCreateNullUser() {
		assertFalse(userManager.createUser(null));
		assertFalse(userManager.createUser(new User()));
	}

	@Test
	public void testCreateUserWithNullOrEmptyLoginId() {
		User u = createTestUser();
		u.setLoginId(null);
		assertFalse(userManager.createUser(u));

		u.setLoginId("");
		assertFalse(userManager.createUser(u));

		u.setLoginId("       ");
		assertFalse(userManager.createUser(u));
	}

	@Test
	public void testCreateUserWithNullOrEmptyPassword() {
		User u = createTestUser();
		u.setPassword(null);
		assertFalse(userManager.createUser(u));

		u.setPassword("");
		assertFalse(userManager.createUser(u));
	}

	@Test
	public void testCreateUserWithNullOrEmptyName() {
		User u = createTestUser();

		u.setName(null);
		assertFalse(userManager.createUser(u));

		u.setName("");
		assertFalse(userManager.createUser(u));

		u.setName("    ");
		assertFalse(userManager.createUser(u));
	}

	@Test
	public void testCreateUserWithNullOrEmptyEmail() {
		User u = createTestUser();

		u.setEmail(null);
		assertFalse(userManager.createUser(u));

		u.setEmail("");
		assertFalse(userManager.createUser(u));

		u.setEmail("      ");
		assertFalse(userManager.createUser(u));
	}

	@Test
	public void testCreateUserWithNullBirthday() {
		User u = createTestUser();
		u.setBirthday(null);
		assertFalse(userManager.createUser(u));
	}

	@Test
	public void testContainsNullUser() {
		assertFalse(userManager.containsUser(null));
		assertFalse(userManager.containsUser(new User()));
	}

	@Test
	public void testCreateAndDeleteUserSuccessful() {
		User u = createTestUser();
		assertTrue(userManager.createUser(u));
		assertTrue(userManager.containsUser(u));
		assertTrue(userManager.getUserByLoginId(u.getLoginId()) == u);
		assertTrue(userManager.getUserByEmail(u.getEmail()) == u);
	
		assertTrue(userManager.deleteUser(u));
		assertFalse(userManager.containsUser(u));
		assertNull(userManager.getUserByLoginId(u.getLoginId()));
	}

	@Test
	public void testDeleteUser() {
		assertFalse(userManager.deleteUser(null));
		assertFalse(userManager.deleteUser(new User()));
		
		User u = createTestUser();
		assertFalse(userManager.deleteUser(u));
		assertTrue(userManager.createUser(u));

		assertTrue(userManager.deleteUser(u));
		assertFalse(userManager.deleteUser(u));

		assertNull(userManager.getUserByLoginId(u.getLoginId()));
	}

	@Test
	public void testIsUserLoginIdExist() {
		assertFalse(userManager.isUserLoginIdExist(null));
		assertFalse(userManager.isUserLoginIdExist(""));
		assertFalse(userManager.isUserLoginIdExist("   "));
		
		User u = createTestUser();
		assertTrue(userManager.createUser(u));
		assertTrue(userManager.isUserLoginIdExist(u.getLoginId()));

		assertTrue(userManager.deleteUser(u));
		assertFalse(userManager.isUserLoginIdExist(u.getLoginId()));
	}

	@Test
	public void testIsUserEmailExist() {
		assertFalse(userManager.isUserEmailExist(null));
		assertFalse(userManager.isUserEmailExist(""));
		assertFalse(userManager.isUserEmailExist("   "));

		User u = createTestUser();
		assertTrue(userManager.createUser(u));
		assertTrue(userManager.isUserEmailExist(u.getEmail()));

		assertTrue(userManager.deleteUser(u));
		assertFalse(userManager.isUserEmailExist(u.getEmail()));
	}
}
