package doc.online.util;

public enum DatabaseMode {

	MEMORY("memory"),
	SQL_DB("sql_db"),
	LDAP("ldap");
	
	private String configName;

	DatabaseMode(String configName) {
		this.configName = configName;
	}

	public String getConfigName() {
		return configName;
	}
}
