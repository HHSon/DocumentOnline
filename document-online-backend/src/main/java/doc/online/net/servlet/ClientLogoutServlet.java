package doc.online.net.servlet;

import doc.online.net.helper.SessionHelper;
import doc.online.net.response.CommonResponse;
import doc.online.net.response.ResponseCode;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * Handles client logout requests
 */
public class ClientLogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SessionHelper.removeAllClientInfoFromSession(req.getSession());
		resp.setContentType("application/json");
		resp.getWriter().println(new CommonResponse(ResponseCode.OK, "logout success").toString());
	}
}
