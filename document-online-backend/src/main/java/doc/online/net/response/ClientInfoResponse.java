package doc.online.net.response;

import doc.online.model.Client;
import org.json.JSONObject;

public class ClientInfoResponse extends BaseResponse {
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String DATE_REGISTERED = "date_registered";
	public static final String GROUP = "group";
	public static final String HOMEPAGE = "homepage";

	protected Client client;

	public ClientInfoResponse() {
	}

	public ClientInfoResponse(String status, String msg) {
		super(status, msg);
	}

	public ClientInfoResponse(String status, String msg, Client client) {
		super(status, msg);
		this.setClient(client);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		if (client == null)
			throw new NullPointerException("client cannot be null");
		this.client = client;
	}

	@Override
	protected void setup() {
		if (client != null) {
			info.put(CLIENT_ID, client.getClientId());
			info.put(CLIENT_NAME, client.getName());
			info.put(DESCRIPTION, client.getDescription());
			info.put(DATE_REGISTERED, client.getDateRegistered());
			info.put(GROUP, client.getGroup());
			info.put(HOMEPAGE, client.getHomepage() == null ?
						JSONObject.NULL :
						client.getHomepage());
		}
		super.setup();
	}
}
