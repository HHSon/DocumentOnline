package doc.online.net.response;

import org.json.JSONObject;

/**
 * The base class of all response classes.
 * Every response object has 3 elements:
 * <ul>
 * 	<li>status: indicates status of the response</li>
 *	<li>message: the information of the response</li>
 *	<li>info: a JSONObject that store specific information of the response</li>
 * </ul>
 */
public abstract class BaseResponse {
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final String INFO = "info";
	
	protected String status;
	protected String message;
	protected JSONObject info = new JSONObject();
	protected final JSONObject responseJSON = new JSONObject();

	public BaseResponse() {
	}

	public BaseResponse(String status, String message) {
		this.setStatus(status);
		this.setMessage(message);
	}

	public BaseResponse(String status, String message, JSONObject info) {
		this(status, message);
		this.setInfo(info);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public JSONObject getInfo() {
		return info;
	}

	public void setInfo(JSONObject info) {
		if (info == null)
			throw new NullPointerException("info cannot be null");
		this.info = info;
	}

	protected void setup() {
		responseJSON.put(STATUS, status == null ? JSONObject.NULL : status);
		responseJSON.put(MESSAGE, message == null ? JSONObject.NULL : message);
		responseJSON.put(INFO, info);
	}

	@Override
	public String toString() {
		setup();
		return responseJSON.toString();
	}
}
