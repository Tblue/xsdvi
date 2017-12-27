package xsdvi.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Václav Slavìtínský
 *
 */
public class WriterHelper {
	private static final Logger logger = Logger.getLogger(LoggerHelper.LOGGER_NAME);
	private Writer writer;
	
	/**
	 * 
	 */
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 
	 */
	public WriterHelper() {
		//
	}
	
	/**
	 * @param uri
	 */
	public WriterHelper(String uri) {
		newWriter(uri, DEFAULT_CHARSET);
	}
	
	/**
	 * @param uri
	 * @param charsetName
	 */
	public WriterHelper(String uri, String charsetName) {
		newWriter(uri, charsetName);
	}
	
	/**
	 * @param oStream
	 * @param charsetName
	 */
	public WriterHelper(OutputStream oStream, String charsetName) {
		newWriter(oStream, charsetName);
	}

	/**
	 * @param w
	 */
	public WriterHelper(Writer w) {
		this.writer = w;
	}
	
	/**
	 * 
	 */
	public void close() {
	    try {
	    	writer.close();
	    } catch (IOException e) {
	    	logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
	    }
	}

	/**
	 * @param csq
	 * @return
	 */
	public Writer append(CharSequence csq) {
		try {
			writer.append(csq);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		return writer;
	}

	/**
	 * @return
	 */
	public Writer getWriter() {
		return writer;
	}

	/**
	 * @param w
	 */
	public void setWriter(Writer w) {
		this.writer = w;
	}

	/**
	 * @param uri
	 */
	public final void newWriter(String uri) {
		newWriter(uri, DEFAULT_CHARSET);
	}
	
	/**
	 * @param uri
	 * @param charsetName
	 */
	public final void newWriter(String uri, String charsetName) {
		try {
			OutputStream foStream = new FileOutputStream(uri);
			writer = new OutputStreamWriter(foStream, charsetName);
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}
	
	/**
	 * @param oStream
	 * @param charsetName
	 */
	public final void newWriter(OutputStream oStream, String charsetName) {
		try {
			writer = new OutputStreamWriter(oStream, charsetName);
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}
}