package xsdvi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xerces.xs.XSImplementation;
import org.apache.xerces.xs.XSLoader;
import org.apache.xerces.xs.XSModel;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import xsdvi.svg.AbstractSymbol;
import xsdvi.svg.SvgForXsd;
import xsdvi.utils.LoggerHelper;
import xsdvi.utils.TreeBuilder;
import xsdvi.utils.WriterHelper;
import xsdvi.utils.XsdErrorHandler;

/**
 * @author Václav Slavìtínský
 * 
 */
public final class XsdVi {
	private static final Logger logger = Logger.getLogger(LoggerHelper.LOGGER_NAME);
	
	private static List<String> inputs = new ArrayList<String>();
	private static String style = null;
	private static String styleUrl = null;

	/**
	 * 
	 */
	public static final String EMBODY_STYLE = "-embodyStyle";
	
	/**
	 * 
	 */
	public static final String GENERATE_STYLE = "-generateStyle";
	
	/**
	 * 
	 */
	public static final String USE_STYLE = "-useStyle";
	
	/**
	 * 
	 */
	public static final String USAGE =
		"\n" +
		"USAGE:\n" +
		"java -jar xsdvi.jar <input1.xsd> [<input2.xsd> [<input3.xsd> ...]] [style]\n" +
		"  STYLE:\n" +
		"    " + EMBODY_STYLE + "                css style will be embodied in each svg file, this is default\n" +
		"    " + GENERATE_STYLE + " <style.css>  new css file with specified name will be generated and used by svgs\n" +
		"    " + USE_STYLE + "      <style.css>  external css file at specified url will be used by svgs\n";
	
	
	/**
	 * 
	 */
	private XsdVi() {
		// no instances
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoggerHelper.setupLogger();
		
		parseArgs(args);
		
		XSLoader schemaLoader = getSchemaLoader();
		
		TreeBuilder builder = new TreeBuilder();
		XsdHandler xsdHandler = new XsdHandler(builder);
		WriterHelper writerHelper = new WriterHelper();
		SvgForXsd svg = new SvgForXsd(writerHelper);
		
		if (style.equals(EMBODY_STYLE)) {
			logger.info("The style will be embodied");
			svg.setEmbodyStyle(true);
		}
		else {
			logger.info("Using external style " + styleUrl);
			svg.setEmbodyStyle(false);
			svg.setStyleUri(styleUrl);
		}
		if (style.equals(GENERATE_STYLE)) {
			logger.info("Generating style " + styleUrl + "...");
			svg.printExternStyle();
			logger.info("Done.");
		}
		
		for (String input : inputs) {
			String output = outputUrl(input);
			
			logger.info("Parsing " + input + "...");
			XSModel model = schemaLoader.loadURI(input);
			logger.info("Processing XML Schema model...");
			xsdHandler.processModel(model);
			logger.info("Drawing SVG " + output + "...");
			writerHelper.newWriter(output);
			svg.draw((AbstractSymbol) builder.getRoot());
			logger.info("Done.");
		}
		
		//new xsdvi.svg.SvgSymbols(writerHelper).drawSymbols();
		//logger.info("Symbols saved.");
	}

	/**
	 * @param input
	 * @return
	 */
	private static String outputUrl(String input) {
		String[] field = input.split("[/\\\\]");
		String in = field[field.length-1];
		if (in.toLowerCase().endsWith(".xsd")) {
			return in.substring(0, in.length()-4) + ".svg";
		}
		return in + ".svg";
	}

	/**
	 * @param args
	 */
	private static void parseArgs(String[] args) {
		if (args.length < 1 || args[0].equalsIgnoreCase(EMBODY_STYLE) || args[0].equalsIgnoreCase(GENERATE_STYLE) || args[0].equalsIgnoreCase(USE_STYLE)) {
			printUsage();
			System.exit(1);
		}
		for (int i=0; i<args.length; i++) {
			if (args[i].equalsIgnoreCase(EMBODY_STYLE)) {
				if (args.length != i+1) {
					printUsage();
					System.exit(1);
				}
				style = EMBODY_STYLE;
				return;
			}
			else if (args[i].equalsIgnoreCase(GENERATE_STYLE)) {
				if (args.length != i+2) {
					printUsage();
					System.exit(1);
				}
				style = GENERATE_STYLE;
				styleUrl = args[i+1];
				return;
			}
			else if (args[i].equalsIgnoreCase(USE_STYLE)) {
				if (args.length != i+2) {
					printUsage();
					System.exit(1);
				}
				style = USE_STYLE;
				styleUrl = args[i+1];
				return;
			}
			else {
				inputs.add(args[i]);
			}
		}
		style = EMBODY_STYLE;
	}

	/**
	 * 
	 */
	private static void printUsage() {
		logger.severe(USAGE);
	}

	/**
	 * @return
	 */
	private static XSLoader getSchemaLoader() {
		XSLoader schemaLoader = null;
		try {
			System.setProperty(DOMImplementationRegistry.PROPERTY, "org.apache.xerces.dom.DOMXSImplementationSourceImpl");
			DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			XSImplementation impl = (XSImplementation) registry.getDOMImplementation("XS-Loader");
			schemaLoader = impl.createXSLoader(null);
			DOMConfiguration config = schemaLoader.getConfig();
			DOMErrorHandler errorHandler = new XsdErrorHandler();
			config.setParameter("error-handler", errorHandler);
			config.setParameter("validate", Boolean.TRUE);
		} catch (ClassCastException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (InstantiationException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (IllegalAccessException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		return schemaLoader;
	}
}