package doc.online.net.response;

import org.json.JSONObject;

public class ClientLoginResponse extends BaseResponse {
	private static final String SESSION_TIMEOUT_IN_SECONDS = "sessionTimeoutInSeconds";

	protected int sessionTimeoutInSeconds;

	public void setSessionTimeoutInSeconds(int timeoutInSeconds) {
		this.sessionTimeoutInSeconds = timeoutInSeconds;
	}

	@Override
	protected void setup() {
		info.put(SESSION_TIMEOUT_IN_SECONDS, sessionTimeoutInSeconds);
		super.setup();
	}
}
