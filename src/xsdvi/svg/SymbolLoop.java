package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolLoop extends AbstractSymbol {
	
	/**
	 * 
	 */
	public SymbolLoop() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#draw()
	 */
	@Override
	public void draw() {
		drawGStart();
		print("<rect class='boxloop' x='0' y='12' width='"+width+"' height='"+height+"' rx='9'/>");
		print("<polygon class='filled' points='"+(width/2+3)+",8 "+(width/2-2)+",12 "+(width/2+3)+",17'/>");
		print("<polygon class='filled' points='"+(width-5)+",24 "+(width)+",19 "+(width+5)+",24'/>");
		print("<text x='10' y='27'>LOOP</text>");
		drawConnection();
		drawGEnd();
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#getWidth()
	 */
	@Override
	public int getWidth() {
		WidthCalculator calc = new WidthCalculator(MIN_WIDTH);
		calc.newWidth(25, 4);
		return calc.getWidth();
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#getHeight()
	 */
	@Override
	public int getHeight() {
		return MIN_HEIGHT;
	}
}