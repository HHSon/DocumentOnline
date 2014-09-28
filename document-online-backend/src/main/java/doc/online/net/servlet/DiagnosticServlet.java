package doc.online.net.servlet;

import doc.online.util.Configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.json.JSONObject;

/**
 * Utility for server diagnostic
 */
public class DiagnosticServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pathInfo = req.getPathInfo();

		if (pathInfo.contains("health")) {
			health(req, resp);
			return;
		}

		// no mapping found: 404
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	/**
	 * Path="/health/*"
	 */
	private void health(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject responseObj = new JSONObject();
		responseObj.put("name", Configuration.getProjectName());
		responseObj.put("version", Configuration.getProjectVersion());

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.println(responseObj.toString());
	}

	public static JSONObject getHttpServletRequestInfo(final HttpServletRequest req) {
		if (req == null) {
			return null;
		}

		JSONObject requestInfoJSON = new JSONObject();
		
		// Common info
		JSONObject commonJSON = new JSONObject();
		
		commonJSON.put("characterEncoding", req.getCharacterEncoding());
		commonJSON.put("contentLength", req.getContentLength());
		commonJSON.put("contentType", req.getContentType());
		commonJSON.put("localAddr", req.getLocalAddr());
		commonJSON.put("locale", req.getLocale().toString());
		commonJSON.put("localName", req.getLocalName());
		commonJSON.put("localPort", req.getLocalPort());
		commonJSON.put("protocol", req.getProtocol());
		//commonJSON.put("realPath", req.getRealPath());
		commonJSON.put("remoteAddr", req.getRemoteAddr());
		commonJSON.put("remoteHost", req.getRemoteHost());
		commonJSON.put("remotePort", req.getRemotePort());
		commonJSON.put("scheme", req.getScheme());
		commonJSON.put("serverName", req.getServerName());
		commonJSON.put("serverPort", req.getServerPort());
		//commonJSON.put("isAsyncStarted", req.isAsyncStarted());
		//commonJSON.put("isAsyncSupported", req.isAsyncSupported());
		commonJSON.put("isSecure", req.isSecure());
		

		// get attributes
		Enumeration<String> attrsEnum = req.getAttributeNames();
		JSONObject attrsJSON = new JSONObject();

		while (attrsEnum.hasMoreElements()) {
			String attrName = attrsEnum.nextElement();
			attrsJSON.put(attrName, req.getAttribute(attrName));
		}
		
		commonJSON.put("attributes", attrsJSON);

		// get parameters
		Enumeration<String> paramsEnum = req.getParameterNames();
		JSONObject paramsJSON = new JSONObject();

		while (paramsEnum.hasMoreElements()) {
			String paramName = paramsEnum.nextElement();
			paramsJSON.put(paramName, req.getParameterValues(paramName));
		}
		
		commonJSON.put("parameters", paramsJSON);
		requestInfoJSON.put("servletRequest", commonJSON);
		
		// http servlet info
		JSONObject httpJSON = new JSONObject();
		
		// get header names
		Enumeration<String> headersEnum = req.getHeaderNames();
		JSONObject headersJSON = new JSONObject();
		
		while (headersEnum.hasMoreElements()) {
			String headerName = headersEnum.nextElement();
			headersJSON.put(headerName, req.getHeader(headerName));
		}
		
		httpJSON.put("headers", headersJSON);
		httpJSON.put("authType", req.getAuthType());
		httpJSON.put("contextPath", req.getContextPath());
		httpJSON.put("pathInfo", req.getPathInfo());
		httpJSON.put("pathTranslated", req.getPathTranslated());
		httpJSON.put("queryString", req.getQueryString());
		httpJSON.put("requestURI", req.getRequestURI());
		httpJSON.put("requestURL", req.getRequestURL());
		httpJSON.put("servletPath", req.getServletPath());
		httpJSON.put("remoteUser", req.getRemoteUser());
		httpJSON.put("requestedSessionId", req.getRequestedSessionId());
		httpJSON.put("isRequestedSessionIdFromCookie", req.isRequestedSessionIdFromCookie());
		httpJSON.put("isRequestedSessionIdFromURL", req.isRequestedSessionIdFromURL());
		httpJSON.put("isRequestedSessionIdValid", req.isRequestedSessionIdValid());
		
		
		requestInfoJSON.put("httpServletRequest", httpJSON);

		return requestInfoJSON;
	}
}
