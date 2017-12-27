package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolKeyref extends AbstractSymbol {
	private String name = null;
	private String namespace = null;
	private String refer = null;
	
	/**
	 * @param name
	 * @param namespace
	 * @param refer
	 */
	public SymbolKeyref(String name, String namespace, String refer) {
		this();
		this.name = name;
		this.namespace = namespace;
		this.refer = refer;
	}

	/**
	 * 
	 */
	public SymbolKeyref() {
		super();
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
	public String getRefer() {
		return refer;
	}

	/**
	 * @param refer
	 */
	public void setRefer(String refer) {
		this.refer = refer;
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#draw()
	 */
	@Override
	public void draw() {
		drawGStart();
		print("<rect class='boxidc' x='0' y='0' width='"+width+"' height='"+height+"' rx='9'/>");
		print("<circle class='empty' cx='9' cy='8' r='3'/>");
		print("<polyline points='9,11 9,25 13,25'/>");
		print("<line x1='10' y1='21' x2='13' y2='21'/>");
		print("<line x1='10' y1='23' x2='13' y2='23'/>");
		print("<polygon points='11,42 11,32 17,37'/>");
		print("<line x1='4' y1='37' x2='11' y2='37'/>");
		if (namespace!=null) {
			print("<text x='20' y='13'>"+namespace+"</text>");
		}
		if (name!=null) {
			print("<text class='strong' x='20' y='27'>"+name+"</text>");
		}
		if (refer!=null) {
			print("<text x='20' y='41'>"+refer+"</text>");
		}
		drawConnection();
		drawUse();
		drawGEnd();
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#getWidth()
	 */
	@Override
	public int getWidth() {
		WidthCalculator calc = new WidthCalculator(MIN_WIDTH);
		calc.newWidth(30, name);
		calc.newWidth(30, namespace);
		calc.newWidth(30, refer);
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