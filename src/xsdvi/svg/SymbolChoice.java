package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolChoice extends AbstractSymbol {
	private String cardinality = null;
	
	/**
	 * @param cardinality
	 */
	public SymbolChoice(String cardinality) {
		this();
		this.cardinality = cardinality;
	}

	/**
	 * 
	 */
	public SymbolChoice() {
		super();
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
		print("<rect class='boxcompositor' x='0' y='8' width='"+width+"' height='"+height+"' rx='9'/>");
		print("<circle cx='"+(width/2+12)+"' cy='14' r='2'/>");
		print("<circle class='empty' cx='"+(width/2+12)+"' cy='23' r='2'/>");
		print("<circle class='empty' cx='"+(width/2+12)+"' cy='32' r='2'/>");
		print("<polyline points='"+(width/2-4)+",23 "+(width/2+4)+",23 "+(width/2+4)+",14 "+(width/2+10)+",14'/>");
		if (cardinality!=null) {
			print("<text x='5' y='52'>"+cardinality+"</text>");
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
		calc.newWidth(15, cardinality);
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