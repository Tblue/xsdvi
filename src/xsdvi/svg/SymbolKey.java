package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolKey extends AbstractSymbol {
	private String name = null;
	private String namespace = null;
	
	/**
	 * @param name
	 * @param namespace
	 */
	public SymbolKey(String name, String namespace) {
		this();
		this.name = name;
		this.namespace = namespace;
	}

	/**
	 * 
	 */
	public SymbolKey() {
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
		if (namespace!=null) {
			print("<text x='20' y='13'>"+namespace+"</text>");
		}
		if (name!=null) {
			print("<text class='strong' x='20' y='27'>"+name+"</text>");
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