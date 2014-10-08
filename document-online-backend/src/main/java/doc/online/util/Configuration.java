package doc.online.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Global properties utility
 */
public final class Configuration {
	/**
	 * 1 Project name.
	 * For health check only
	 */
	private static final String CFG_PROJECT_NAME = "project.name";
	
	public static String getProjectName() {
		return properties.getProperty(CFG_PROJECT_NAME);
	}
	
	/**
	 * 2 Project version.
	 * For health check only
	 */
	private static final String CFG_PROJECT_VERSION = "project.version";
	
	public static String getProjectVersion() {
		return properties.getProperty(CFG_PROJECT_VERSION);
	}
	
	/**
	 * 3 Database mode.
	 */
	private static final String CFG_DATABASE_MODE = "database.mode";
	private static final DatabaseMode DEFAULT_DATABASE_MODE = DatabaseMode.MEMORY;
	private static DatabaseMode databaseMode = DEFAULT_DATABASE_MODE;
	
	public static DatabaseMode getDatabaseMode() {
		return databaseMode;
	}
	
	/**
	 * 4 Client authorization on header of post request.
	 */
	private static final String CFG_CLIENT_REGISTER_AUTHORIZATION = "client.register.authorization";
	private static final String DEFAULT_CLIENT_REGISTER_AUTHORIZATION = "";
	private static String clientRegisterAuthorization = DEFAULT_CLIENT_REGISTER_AUTHORIZATION;
	
	public static String getClientRegisterAuthorization() {
		return clientRegisterAuthorization;
	}
	
	/**
	 * 5 Client session timeout time in seconds.
	 * After session timeout, client must authorize again
	 */
	private static final String CFG_CLIENT_SESSION_TIMEOUT_IN_SECONDS = "client.session.timeoutInSeconds";
	private static final int DEFAULT_CLIENT_SESSION_TIMEOUT_IN_SECONDS = 3600; // 60 min
	private static int clientSessionTimeoutInSeconds = DEFAULT_CLIENT_SESSION_TIMEOUT_IN_SECONDS;
	
	public static int getClientSessionTimeoutInSeconds() {
		return clientSessionTimeoutInSeconds;
	}
	
	/**
	 * 6 Avoid client names.
	 * The words that client names cannot contains
	 */
	private static final String CFG_AVOID_CLIENT_NAMES = "client.name.avoid";
	private static final String DEAULT_VOID_CLIENT_NAMES[] = { "18+" };
	private static String avoidClientNames[] = DEAULT_VOID_CLIENT_NAMES;
	
	public static String[] getAvoidClientNames() {
		return avoidClientNames;
	}
	
	/**
	 * 7 Default client id string length for generator.
	 */
	private static final int DEFAULT_CLIENT_ID_STRING_LENGTH = 10;
	private static final String CFG_CLIENT_ID_LENGTH = "clientId.length";
	private static int clientIdStringLength = DEFAULT_CLIENT_ID_STRING_LENGTH;
	
	public static int getClientIdStringLength() {
		return clientIdStringLength;
	}

	
	
	private static final Logger log = LogManager.getLogger(Configuration.class);
	
	/**
	 * Configuration file location
	 */
	private static final String CONFIG_FILE = "~/config/document-online.properties";
	private static Properties properties;

	/**
	 * Load configuration from file
	 */
	public static void init()
			throws IOException, IllegalArgumentException {
		properties = new Properties();
		properties.load(new FileReader(CONFIG_FILE));
		log.info("loaded config file");

		// database mode
		initDatabaseMode();
		log.info("initialized database mode: " + databaseMode.name());
		
		// 

		// init clientSessionTimeOut
		try {
			clientSessionTimeoutInSeconds = Integer.parseInt(
				properties.getProperty(CFG_CLIENT_SESSION_TIMEOUT_IN_SECONDS));
		} catch (RuntimeException ex) {
			throw ex;
		}

		// init avoid client names
		String avoidClientNamesStr = properties.getProperty(CFG_AVOID_CLIENT_NAMES);
		if (avoidClientNamesStr == null)
			avoidClientNamesStr = "";

		avoidClientNames = avoidClientNamesStr.split(",");

		// init clientId length
		String clientIdLengthStr = properties.getProperty(CFG_CLIENT_ID_LENGTH);
		if (!StringUtil.isNullOrEmpty(clientIdLengthStr)) {
			try {
				clientIdStringLength = Integer.parseInt(clientIdLengthStr);
			} catch (RuntimeException ex) {
				clientIdStringLength = DEFAULT_CLIENT_ID_STRING_LENGTH;
				log.error("Get clientIdStringLen failed (used default value): " + ex.getMessage());
			}
		}
	}

	private static void initDatabaseMode()
			throws IllegalArgumentException {
		String databaseModeConfig = properties.getProperty(CFG_DATABASE_MODE);

		if (StringUtil.isNullOrEmpty(databaseModeConfig)) {
			throw new IllegalArgumentException("cannot read database config value");
		}

		for (DatabaseMode mode : DatabaseMode.values()) {
			if (mode.getConfigName().equalsIgnoreCase(databaseModeConfig)) {
				databaseMode = mode;
				break;
			}
		}

		if (databaseMode == null) {
			throw new IllegalArgumentException("cannot recognize database config: " + databaseModeConfig);
		}
	}
	
	
	/// utility

	/**
	 * Check client name is accepted or not.
	 */
	public static boolean acceptClientName(String clientName) {
		String[] words = StringUtil.getWords(clientName);

		for (String w : words) {
			for (String avoid : avoidClientNames)
				if(w.equalsIgnoreCase(avoid))
					return false;
		}

		return true;
	}
}
