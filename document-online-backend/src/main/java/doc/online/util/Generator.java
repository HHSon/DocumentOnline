package doc.online.util;

import doc.online.dao.ClientDAO;
import java.util.Random;

public final class Generator {
	public static String generateNewClientId() {
		return generateRamdomNumericString(Configuration.getClientIdStringLength());
	}

	public static String generateNewClientSecret() {
		return "12345678";
	}

	public static String generateRamdomNumericString(int len) {
		ClientDAO clientDAO = new ClientDAO();
		StringBuffer sb = new StringBuffer();
		Random r = new Random();

		do {
			sb = new StringBuffer();
			for (int i = 0; i < len; i++) {
				sb.append(r.nextInt(10));
			}
		} while (clientDAO.isClientIdExist(sb.toString()));

		return sb.toString();
	}
}