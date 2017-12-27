package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolUnique extends AbstractSymbol {
	private String name = null;
	private String namespace = null;
	
	/**
	 * @param name
	 * @param namespace
	 */
	public SymbolUnique(String name, String namespace) {
		this();
		this.name = name;
		this.namespace = namespace;
	}

	/**
	 * 
	 */
	public SymbolUnique() {
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
		if (namespace!=null) {
			print("<text x='20' y='13'>"+namespace+"</text>");
		}
		if (name!=null) {
			print("<text class='strong' x='5' y='27'><tspan class='big'>U </tspan>"+name+"</text>");
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
		calc.newWidth(15, name, 2);
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