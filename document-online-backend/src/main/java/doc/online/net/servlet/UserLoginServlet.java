package doc.online.net.servlet;

import doc.online.util.StringUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet handle user login request
 */
public class UserLoginServlet extends HttpServlet {

	protected static final String REQ_LOGIN_ID = "login_id";
	protected static final String REQ_PASSWORD = "password";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse reps)
			throws IOException, ServletException {
		String userLoginId = req.getParameter(REQ_LOGIN_ID);
		String password = req.getParameter(REQ_PASSWORD);

		if (StringUtil.isNullOrEmpty(userLoginId) ||
				StringUtil.isNullOrEmpty(password)) {
			
		}
	}
}
