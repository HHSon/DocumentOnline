package doc.online.net.servlet;

import doc.online.dao.UserDAO;
import doc.online.model.LoggedInUser;
import doc.online.model.User;
import doc.online.net.helper.RequestName;
import doc.online.net.helper.SessionElementName;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;


public class UserRegisterServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		User newUser = getRegisterUser(req, out);

		if (newUser != null) {
		}

		UserDAO userDAO = new UserDAO();
		if (userDAO.createUser(newUser) == true) {
			
		}

		
	}

	private User getRegisterUser(HttpServletRequest req, PrintWriter out)
			throws ServletException, IOException {
		throw new RuntimeException("Unsupported");
	}
}
