package doc.online.net.response;

public final class ResponseCode {
	public static final String ERROR = "error";
	public static final String OK = "ok";
	public static final String UNAUTHORIZED = "unauthorized";
	public static final String E_NAME_IS_EXIST = "name_existed";

	// client
	public static final String E_CLIENT_LOGIN_FAILED 	= "login_failed";
	public static final String I_CLIENT_ALREADY_LOGIN 	= "already_login";
	public static final String E_CLIENT_NOT_REGISTERED 	= "not_register";
	public static final String E_ILEGAL_CLIENT_NAME		= "ilegal_client_name";

	// user
	public static final String E_USER_LOGIN_FAILED		= "login_failed";
	public static final String I_USER_ALREADY_LOGIN 	= "already_login";
	public static final String E_USER_NOT_REGISTERED 	= "not_register";
}
