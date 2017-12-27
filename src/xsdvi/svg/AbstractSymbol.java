package xsdvi.svg;

import xsdvi.utils.TreeElement;

/**
 * @author Václav Slavìtínský
 *
 */
public abstract class AbstractSymbol extends TreeElement {
	private SvgForXsd svg;
	
	protected int xPosition;
	protected int yPosition;
	protected int width;
	protected int height;
	
	private static int highestYPosition;

	/**
	 * 
	 */
	public static final int PC_STRICT	= 1;
    /**
     * 
     */
    public static final int PC_SKIP		= 2;
    /**
     * 
     */
    public static final int PC_LAX		= 3;
    
    /**
     * 
     */
    public static final int X_INDENT	= 45;
	/**
	 * 
	 */
	public static final int Y_INDENT	= 25;

	/**
	 * 
	 */
	public static final int MIN_WIDTH	= 60;
	/**
	 * 
	 */
	public static final int MAX_HEIGHT	= 46;
	/**
	 * 
	 */
	public static final int MID_HEIGHT	= 31;
	/**
	 * 
	 */
	public static final int MIN_HEIGHT	= 21;

	
	/**
	 * @return
	 */
	public int getXEnd() {
		return xPosition + width;
	}
	
	/**
	 * @return
	 */
	public int getYEnd() {
		return yPosition + MAX_HEIGHT;
	}
	
	/**
	 * @return
	 */
	public int getXPosition() {
		return xPosition;
	}
	
	/**
	 * @return
	 */
	public int getYPosition() {
		return yPosition;
	}

	/**
	 * @param xPos
	 */
	public void setXPosition(int xPos) {
		this.xPosition = xPos;
	}

	/**
	 * @param yPos
	 */
	public void setYPosition(int yPos) {
		this.yPosition = yPos;
	}

	/**
	 * @param svgForXsd
	 */
	public void setSvg(SvgForXsd svgForXsd) {
		this.svg = svgForXsd;
	}
	
	/**
	 * @return
	 */
	public SvgForXsd getSvg() {
		return svg;
	}
	
	/**
	 * @param w
	 */
	public void setWidth(int w) {
		this.width = w;
	}

	/**
	 * @param h
	 */
	public void setHeight(int h) {
		this.height = h;
	}
	
	/**
	 * @param string
	 */
	protected void print(String string) {
		svg.print(string);
	}
	
	/**
	 * 
	 */
	protected void drawGStart() {
		print("<g id='"+code()+"' class='box' transform='translate("+xPosition+","+yPosition+")'>");
	}
	
	/**
	 * 
	 */
	protected void drawGEnd() {
		print("</g>\n");
	}
	
	/**
	 * 
	 */
	protected void drawConnection() {
		if (isLastChild() && !isFirstChild()) {
			print("<line class='connection' id='p"+code()+"' x1='"+(10-X_INDENT)+"' y1='"+(((AbstractSymbol) getParent()).yPosition-yPosition+MAX_HEIGHT/2)+"' x2='"+(10-X_INDENT)+"' y2='"+(-15-Y_INDENT)+"'/>");
			print("<path class='connection' d='M"+(10-X_INDENT)+","+(-15-Y_INDENT)+" Q"+(10-X_INDENT)+",15 0,"+MAX_HEIGHT/2+"'/>");
		}
		else {
			print("<line class='connection' x1='"+(10-X_INDENT)+"' y1='"+MAX_HEIGHT/2+"' x2='0' y2='"+MAX_HEIGHT/2+"'/>");
		}
	}

	/**
	 * 
	 */
	protected void drawUse() {
		if (hasChildren()) {
			String code = code();
			print("<use x='"+(width-1)+"' y='"+(MAX_HEIGHT/2-6)+"' xlink:href='#minus' id='s"+code+"' onclick='show(\""+code+"\")'/>");
		}
	}

	/**
	 * 
	 */
	protected void drawMouseover() {
		print("onmouseover='makeVisible(\""+code()+"\")' onmouseout='makeHidden(\""+code()+"\")'/>");
	}

	/**
	 * 
	 */
	public void prepareBox() {
		if (hasParent()) {
			xPosition = ((AbstractSymbol) getParent()).getXEnd() + X_INDENT;
			if (isFirstChild()) {
				yPosition = highestYPosition;
			}
			else {
				yPosition = highestYPosition + MAX_HEIGHT + Y_INDENT;
			}
		}
		else {
			xPosition = 20;
			yPosition = 50;
		}
		width = getWidth();
		height = getHeight();
		highestYPosition = yPosition;
	}

	/**
	 * 
	 */
	public abstract void draw();
	
	/**
	 * @return
	 */
	public abstract int getWidth();
	
	/**
	 * @return
	 */
	public abstract int getHeight();

}