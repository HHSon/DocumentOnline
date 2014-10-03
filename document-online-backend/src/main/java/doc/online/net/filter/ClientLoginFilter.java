package doc.online.net.filter;

import doc.online.model.LoggedInClient;
import doc.online.net.helper.SessionElementName;
import doc.online.net.response.ErrorResponse;
import doc.online.net.response.ResponseCode;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ClientLoginFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		// nothing to do
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LoggedInClient loggedInClient = (LoggedInClient) ((HttpServletRequest)request).
					getSession().getAttribute(SessionElementName.LOGGED_IN_CLIENT);

		if (loggedInClient == null || loggedInClient.isExpired()) {
			response.getWriter().write(
				new ErrorResponse(ResponseCode.UNAUTHORIZED, "client does not login").toString());
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// nothing to do
	}
}
