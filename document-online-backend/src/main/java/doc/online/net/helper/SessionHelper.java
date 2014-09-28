package doc.online.net.helper;

import javax.servlet.http.HttpSession;

/**
 * Providing operation on session for entirely application.
 */
public final class SessionHelper {
	public static final boolean removeAllUserInfoFromSession(final HttpSession session) {
		if (session.getAttribute(SessionElementName.LOGGED_IN_USER) == null)
			return false;

		session.removeAttribute(SessionElementName.LOGGED_IN_USER);
		return true;
	}

	public static final boolean removeAllClientInfoFromSession(final HttpSession session) {
		if (session.getAttribute(SessionElementName.LOGGED_IN_CLIENT) == null)
			return false;

		session.removeAttribute(SessionElementName.LOGGED_IN_CLIENT);
		return true;
	}
}
