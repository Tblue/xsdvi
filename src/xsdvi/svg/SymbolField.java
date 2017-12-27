package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolField extends AbstractSymbol {
	private String xpath = null;
	
	/**
	 * @param xpath
	 */
	public SymbolField(String xpath) {
		this();
		this.xpath = xpath;
	}

	/**
	 * 
	 */
	public SymbolField() {
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
		print("<rect class='boxfield' x='0' y='8' width='"+width+"' height='"+height+"' rx='9'/>");
		print("<rect class='empty' x='6' y='17' width='12' height='12'/>");
		print("<rect class='empty' x='8' y='19' width='8' height='8'/>");
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