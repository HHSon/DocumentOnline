package doc.online.util;

import doc.online.util.DatabaseMode;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
 * Global properties utility
 */
public final class Configuration {
	private static final String CONFIG_FILE 				= "/home/pij/production/config/document-online.properties";

	private static final String CFG_PROJECT_NAME 				= "project.name";
	private static final String CFG_PROJECT_VERSION 			= "project.version";
	private static final String CFG_DATABASE_MODE 				= "database.mode";
	private static final String CFG_CLIENT_REGISTER_AUTHORIZATION 		= "client.register.authorization";
	private static final String CFG_CLIENT_SESSION_TIMEOUT_IN_SECONDS 	= "client.session.timeoutInSeconds";
	private static final String CFG_AVOID_CLIENT_NAME 			= "client.name.avoid";
	private static final String CFG_CLIENT_ID_LENGTH 			= "clientId.length";

	private static DatabaseMode databaseMode 		= null;
	private static int clientSessionTimeoutInSeconds 	= 600;
	private static String[] avoidClientNames 		= new String[0];

	private static int DEFAULT_CLIENT_ID_LENGTH 		= 10;
	private static int clientIdLength 			= DEFAULT_CLIENT_ID_LENGTH;
	private static Properties properties;

	
	public static void init()
			throws IOException, IllegalArgumentException {
		properties = new Properties();
		properties.load(new FileReader(CONFIG_FILE));

		// init database mode
		initDatabaseMode();

		// init clientSessionTimeOut
		try {
			clientSessionTimeoutInSeconds = Integer.parseInt(
				properties.getProperty(CFG_CLIENT_SESSION_TIMEOUT_IN_SECONDS));
		} catch (RuntimeException ex) {
			throw ex;
		}

		// init avoid client names
		String avoidClientNamesStr = properties.getProperty(CFG_AVOID_CLIENT_NAME);
		if (avoidClientNamesStr == null)
			avoidClientNamesStr = "";

		avoidClientNames = avoidClientNamesStr.split(",");

		// init clientId length
		String clientIdLengthStr = properties.getProperty(CFG_CLIENT_ID_LENGTH);
		if (!StringUtil.isNullOrEmpty(clientIdLengthStr)) {
			try {
				clientIdLength = Integer.parseInt(clientIdLengthStr);
			} catch (RuntimeException ex) {
				clientIdLength = DEFAULT_CLIENT_ID_LENGTH;
			}
		}
	}

	private static void initDatabaseMode()
			throws IllegalArgumentException {
		String databaseModeConfig = getDatabaseModeConfig();

		if (databaseModeConfig == null || databaseModeConfig.trim().isEmpty()) {
			throw new IllegalArgumentException("cannot read database config value");
		}

		for (DatabaseMode mode : DatabaseMode.values()) {
			if (mode.getConfigName().equalsIgnoreCase(databaseModeConfig)) {
				databaseMode = mode;
				break;
			}
		}

		if (databaseMode == null) {
			throw new IllegalArgumentException("cannot realize database config: " + databaseModeConfig);
		}
	}

	private static String getDatabaseModeConfig() {
		return properties.getProperty(CFG_DATABASE_MODE);
	}


	public static String getProjectName() {
		return properties.getProperty(CFG_PROJECT_NAME);
	}

	public static String getProjectVersion() {
		return properties.getProperty(CFG_PROJECT_VERSION);
	}

	public static DatabaseMode getDatabaseMode() {
		return databaseMode;
	}

	public static int getClientSessionTimeoutInSeconds() {
		return clientSessionTimeoutInSeconds;
	}

	public static String getClientRegisterAuthorization() {
		return properties.getProperty(CFG_CLIENT_REGISTER_AUTHORIZATION);
	}

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

	public static int getClientIdLength() {
		return clientIdLength;
	}
}
