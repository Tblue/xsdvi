package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolSelector extends AbstractSymbol {
	private String xpath = null;
	
	/**
	 * @param xpath
	 */
	public SymbolSelector(String xpath) {
		this();
		this.xpath = xpath;
	}

	/**
	 * 
	 */
	public SymbolSelector() {
		super();
	}

	/**
	 * @return
	 */
	public String getXpath() {
		return xpath;
	}

	/**
	 * @param xpath
	 */
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#draw()
	 */
	@Override
	public void draw() {
		drawGStart();
		print("<rect class='boxselector' x='0' y='8' width='"+width+"' height='"+height+"' rx='9'/>");
		print("<circle cx='12' cy='17' r='3'/>");
		print("<circle class='empty' cx='12' cy='28' r='2'/>");
		print("<polyline points='5,22 12,17 20,17'/>");
		print("<line x1='14' y1='28' x2='20' y2='28'/>");
		if (xpath!=null) {
			print("<text x='25' y='27'>"+xpath+"</text>");
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
		calc.newWidth(35, xpath);
		return calc.getWidth();
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#getHeight()
	 */
	@Override
	public int getHeight() {
		return MID_HEIGHT;
	}
}