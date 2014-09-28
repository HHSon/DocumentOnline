package doc.online.net.listener;

import doc.online.util.Configuration;
import java.io.IOException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DocOnlineContextListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger(DocOnlineContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		logger.error("Initalizing context");
		logger.error("Init configuration");

		try {
			Configuration.init();
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage());
		} catch (IllegalArgumentException ex) {
			throw ex;
		}

		logger.debug("Init configuration done");
	}

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		logger.error("destroy context");
	}
}
