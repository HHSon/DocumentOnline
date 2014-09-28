package doc.online.net.servlet;

import doc.online.dao.ClientDAO;
import doc.online.model.Client;
import doc.online.model.LoggedInClient;
import doc.online.net.helper.RequestName;
import doc.online.net.response.ClientRegisterResponse;
import doc.online.net.response.CommonResponse;
import doc.online.net.response.ResponseCode;
import doc.online.util.Configuration;
import doc.online.util.Generator;
import doc.online.util.StringUtil;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ClientRegisterServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ClientRegisterServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		Client newClient = getClientInfo(req, out);

		if (newClient == null) {
			return;
		}

		try {
			ClientDAO clientDAO = new ClientDAO();
			if (clientDAO.registerClient(newClient) == true) {
				ClientRegisterResponse o = new ClientRegisterResponse(
					ResponseCode.OK,
					"client register success",
					newClient);
				out.println(o.toString());
				return;
			}
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
		}

		CommonResponse respObj = new CommonResponse(
			ResponseCode.ERROR,
			"cannot create new client");

		out.println(respObj.toString());
	}

	private Client getClientInfo(final HttpServletRequest req, PrintWriter out) {
		String authorization = (String) req.getHeader(RequestName.AUTHORIZATION);
		if (StringUtil.isNullOrEmpty(authorization) || !isAuthorizationValid(authorization)) {
			logger.debug("Authorization failed: " + authorization);
			ClientRegisterResponse o = new ClientRegisterResponse(
				ResponseCode.UNAUTHORIZED,
				"unauthorized");
			out.println(o.toString());
			return null;
		}

		String clientName = (String) req.getParameter(RequestName.NAME);
		String description = (String) req.getParameter(RequestName.DESC);
		String group = (String) req.getParameter(RequestName.GROUP);
		String homepage = (String) req.getParameter(RequestName.HOMEPAGE);

		List<String> missingParams = new ArrayList<String>();

		if (StringUtil.isNullOrEmpty(clientName))
			missingParams.add(RequestName.NAME);

		if (StringUtil.isNullOrEmpty(description))
			missingParams.add(RequestName.DESC);

		if (StringUtil.isNullOrEmpty(group))
			missingParams.add(RequestName.GROUP);

		if (missingParams.size() > 0) {
			ClientRegisterResponse o = new ClientRegisterResponse(
				ResponseCode.ERROR,
				"missing parameters",
				missingParams);
			out.println(o.toString());
			return null;
		}

		ClientDAO clientDAO = new ClientDAO();

		if (clientDAO.isClientNameExist(clientName)) {
			ClientRegisterResponse o = new ClientRegisterResponse(
				ResponseCode.E_NAME_IS_EXIST,	
				"client name is exist, please choose another name");
			out.println(o.toString());
			return null;
		}

		if (!Configuration.acceptClientName(clientName)) {
			ClientRegisterResponse o = new ClientRegisterResponse(
				ResponseCode.E_ILEGAL_CLIENT_NAME,
				"client name is not allowed");

			out.println(o.toString());
			return null;
		}

		Client newClient = new Client();

		newClient.setName(clientName);
		newClient.setDescription(clientName);
		newClient.setGroup(group);
		newClient.setHomepage(homepage);
		newClient.setDateRegistered(new Date());

		newClient.setClientId(Generator.generateNewClientId());
		newClient.setClientSecret(Generator.generateNewClientSecret());

		return newClient;
	}

	private boolean isAuthorizationValid(String clientAuthorization) {
		return Configuration.getClientRegisterAuthorization().equals(clientAuthorization);
	}
}
