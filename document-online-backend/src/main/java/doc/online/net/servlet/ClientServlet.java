package doc.online.net.servlet;

import doc.online.model.Client;
import doc.online.model.LoggedInClient;
import doc.online.net.helper.RequestName;
import doc.online.net.helper.SessionElementName;
import doc.online.net.response.ClientInfoResponse;
import doc.online.net.response.CommonResponse;
import doc.online.net.response.ResponseCode;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handle client request such as update, delete, get information
 */
public class ClientServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		final PrintWriter out = resp.getWriter();

		String operation = (String) req.getParameter(RequestName.OPERATION);
		if (operation == null)
			operation = "";

		if (operation.equals(RequestName.GET_CLIENT_INFO)) {
			executeGetClientInfo(req, out);
		} else {
			out.println(new CommonResponse(
				ResponseCode.ERROR, "request operation not found").toString());
		}
	}

	protected void executeGetClientInfo(final HttpServletRequest req, final PrintWriter out) {
		HttpSession sess = req.getSession();
		LoggedInClient loggedInClient = (LoggedInClient) sess.getAttribute(
					SessionElementName.LOGGED_IN_CLIENT);

		if (loggedInClient == null) {
			out.println(new CommonResponse(
				ResponseCode.ERROR, "cannot get client information").toString());
			return;
		}

		ClientInfoResponse respObj = new ClientInfoResponse(
			ResponseCode.OK,
			"public client information",
			loggedInClient.getClient());

		out.println(respObj.toString());
	}
}
