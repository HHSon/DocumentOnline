package doc.online.net.response;

import doc.online.model.Client;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClientRegisterResponse extends ClientInfoResponse {
	public static final String CLIENT_SECRET = "client_secret";
	public static final String MISSING_PARAMETERS = "missing";

	protected List<String> missingParameters = new ArrayList<String>();

	public ClientRegisterResponse() {
	}

	public ClientRegisterResponse(String status, String message) {
		super(status, message);
	}

	public ClientRegisterResponse(String status, String message, Client client) {
		super(status, message, client);
	}

	public ClientRegisterResponse(String status, String message, List<String> missingParameters) {
		super(status, message);
		setMissingParameters(missingParameters);
	}

	public List<String> getMissingParameters() {
		return missingParameters;
	}

	public void setMissingParameters(List<String> missingParameters) {
		this.missingParameters = missingParameters;
	}

	@Override
	protected void setup() {
		if (client != null) {
			info.put(CLIENT_SECRET, client.getClientSecret() == null ?
						JSONObject.NULL :
						client.getClientSecret());
		}

		if (missingParameters != null && !missingParameters.isEmpty()) {
			JSONArray arr = new JSONArray();
			for (String s : missingParameters) {
				arr.put(s);
			}
			info.put(MISSING_PARAMETERS, arr);
		}

		super.setup();
	}
}
