package doc.online.net.servlet;

import doc.online.dao.ClientDAO;
import doc.online.model.Client;
import doc.online.model.LoggedInClient;
import doc.online.net.helper.RequestName;
import doc.online.net.helper.SessionElementName;
import doc.online.net.helper.SessionHelper;
import doc.online.net.response.ClientLoginResponse;
import doc.online.net.response.CommonResponse;
import doc.online.net.response.ResponseCode;
import doc.online.util.Configuration;
import doc.online.util.StringUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

public class ClientLoginServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		final PrintWriter out = resp.getWriter();
		final HttpSession session = req.getSession();

		final String clientId = (String) req.getParameter(RequestName.CLIENT_ID);
		final String clientSecret = (String) req.getParameter(RequestName.CLIENT_SECRET);
		final Client client = checkLogin(clientId, clientSecret, session, out);
		
		if (client == null) {
			return;
		}

		final LoggedInClient logged = new LoggedInClient(
				client,
				new Date(),
				Configuration.getClientSessionTimeoutInSeconds());

		SessionHelper.removeAllClientInfoFromSession(session);
		session.setAttribute(SessionElementName.LOGGED_IN_CLIENT, logged);

		final ClientLoginResponse response = new ClientLoginResponse();
		response.setStatus(ResponseCode.OK);
		response.setMessage("login successfully");
		response.setSessionTimeoutInSeconds(Configuration.getClientSessionTimeoutInSeconds());

		out.println(response.toString());
	}

	private Client checkLogin(String clientId, String clientSecret, HttpSession session,
			PrintWriter out) throws IOException {

		if (StringUtil.isNullOrEmpty(clientId)) {
			CommonResponse response = new CommonResponse (
				ResponseCode.E_CLIENT_LOGIN_FAILED,
				"id is empty");
			out.println(response.toString());
			return null;
		}

		if (StringUtil.isNullOrEmpty(clientSecret)) {
			CommonResponse response = new CommonResponse (
				ResponseCode.E_CLIENT_LOGIN_FAILED,
				"password is empty");
			out.println(response.toString());
			return null;
		}

		final LoggedInClient logged = (LoggedInClient)session.getAttribute(
							SessionElementName.LOGGED_IN_CLIENT);

		if (logged != null && !logged.isExpired() &&
				clientId.equalsIgnoreCase(logged.getClient().getClientId())) {
			CommonResponse response = new CommonResponse (
				ResponseCode.I_CLIENT_ALREADY_LOGIN,
				"client already login");
			out.println(response.toString());
			return null;
		}

		final ClientDAO clientDAO = new ClientDAO();
		final Client client = clientDAO.getClientById(clientId);

		if (client == null) {
			CommonResponse response = new CommonResponse (
				ResponseCode.E_CLIENT_NOT_REGISTERED,
				"client has not registered");
			out.println(response.toString());
			return null;
		}

		if (!clientDAO.isClientLoginValid(clientId, clientSecret)) {
			CommonResponse response = new CommonResponse (
				ResponseCode.E_CLIENT_LOGIN_FAILED,
				"id or password is invalid");
			out.println(response.toString());
			return null;
		}

		return client;
	}
}
