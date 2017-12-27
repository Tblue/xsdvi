package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolSequence extends AbstractSymbol {
	private String cardinality = null;
	
	/**
	 * @param cardinality
	 */
	public SymbolSequence(String cardinality) {
		this();
		this.cardinality = cardinality;
	}
	
	/**
	 * 
	 */
	public SymbolSequence() {
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
		print("<circle cx='"+(width/2+12)+"' cy='23' r='2'/>");
		print("<circle cx='"+(width/2+12)+"' cy='32' r='2'/>");
		print("<text class='small' x='"+(width/2)+"' y='17'>1</text>");
		print("<text class='small' x='"+(width/2)+"' y='26'>2</text>");
		print("<text class='small' x='"+(width/2)+"' y='35'>3</text>");
		print("<line x1='"+(width/2+12)+"' y1='14' x2='"+(width/2+12)+"' y2='32'/>");
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