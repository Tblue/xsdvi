package xsdvi.utils;

import java.util.logging.Logger;

import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;

/**
 * @author Václav Slavìtínský
 *
 */
public class XsdErrorHandler implements DOMErrorHandler {
	private static final Logger logger = Logger.getLogger(LoggerHelper.LOGGER_NAME);

	/* (non-Javadoc)
	 * @see org.w3c.dom.DOMErrorHandler#handleError(org.w3c.dom.DOMError)
	 */
	@Override
	public boolean handleError(DOMError error) {
		int severity = error.getSeverity();
		if (severity == DOMError.SEVERITY_FATAL_ERROR) {
			logger.severe("[xs-fatal-error]: " + errorMessage(error));
			System.exit(1);
		} else if (severity == DOMError.SEVERITY_ERROR) {
			logger.severe("[xs-error]: " + errorMessage(error));
		} else if (severity == DOMError.SEVERITY_WARNING) {
			logger.warning("[xs-warning]: " + errorMessage(error));
		}
		return true;
	}

	/**
	 * @param error
	 * @return
	 */
	private String errorMessage(DOMError error) {
		return error.getMessage() + " (line: " + error.getLocation().getLineNumber() + ")";
	}
}