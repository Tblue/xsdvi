package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolAnyAttribute extends AbstractSymbol{
	private String namespace = null;
	private int processContents = PC_STRICT;
	
	/**
	 * @param namespace
	 * @param processContents
	 */
	public SymbolAnyAttribute(String namespace, int processContents) {
		this();
		this.namespace = namespace;
		this.processContents = processContents;
	}

	/**
	 * 
	 */
	public SymbolAnyAttribute() {
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
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#draw()
	 */
	@Override
	public void draw() {
		drawGStart();
		print("<rect class='shadow' x='3' y='3' width='"+width+"' height='"+height+"' rx='9'/>");
		print("<rect class='boxanyattribute' x='0' y='0' width='"+width+"' height='"+height+"' rx='9'/>");
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
		print("<text class='strong' x='5' y='27'>@</text>");
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