package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolAny extends AbstractSymbol{
	private String namespace = null;
	private int processContents = PC_STRICT;
	private String cardinality = null;
	
	/**
	 * @param namespace
	 * @param processContents
	 * @param cardinality
	 */
	public SymbolAny(String namespace, int processContents, String cardinality) {
		this();
		this.namespace = namespace;
		this.processContents = processContents;
		this.cardinality = cardinality;
	}

	/**
	 * 
	 */
	public SymbolAny() {
		super();
	}
	
	/**
	 * @return
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * @param namespace
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * @return
	 */
	public int getProcessContents() {
		return processContents;
	}

	/**
	 * @param processContents
	 */
	public void setProcessContents(int processContents) {
		this.processContents = processContents;
	}

	/**
	 * @return
	 */
	public String getCardinality() {
		return cardinality;
	}

	/**
	 * @param cardinality
	 */
	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#draw()
	 */
	@Override
	public void draw() {
		drawGStart();
		print("<rect class='shadow' x='3' y='3' width='"+width+"' height='"+height+"'/>");
		print("<rect class='boxany' x='0' y='0' width='"+width+"' height='"+height+"'/>");
		String cls = "strict";
		if (processContents==PC_LAX) {
			cls = "lax";
		}
		else if (processContents==PC_SKIP) {
			cls = "skip";
		}
		print("<rect class='"+cls+"' x='6' y='34' width='6' height='6'/>");
		print("<rect class='"+cls+"' x='16' y='34' width='6' height='6'/>");
		print("<rect class='"+cls+"' x='26' y='34' width='6' height='6'/>");
		if (namespace!=null) {
			print("<text x='5' y='13'>"+namespace+"</text>");
		}
		print("<text class='strong' x='5' y='27'>&lt;&gt;</text>");
		if (cardinality!=null) {
			print("<text x='5' y='59'>"+cardinality+"</text>");
		}
		drawConnection();
		drawGEnd();
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#getWidth()
	 */
	@Override
	public int getWidth() {
		WidthCalculator calc = new WidthCalculator(MIN_WIDTH);
		calc.newWidth(15, namespace);
		calc.newWidth(15, cardinality);
		return calc.getWidth();
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#getHeight()
	 */
	@Override
	public int getHeight() {
		return MAX_HEIGHT;
	}
}