package xsdvi.utils;

/**
 * @author Václav Slavìtínský
 *
 */
public class WidthCalculator {
	private int emWidth;
	private int width;
	
	/**
	 * 
	 */
	public static final int DEFAULT_EM_WIDTH = 6;
	
	/**
	 * @param minWidth
	 */
	public WidthCalculator(int minWidth) {
		this(minWidth, DEFAULT_EM_WIDTH);
	}
	
	/**
	 * @param minWidth
	 * @param emWidth
	 */
	public WidthCalculator(int minWidth, int emWidth) {
		this.width = minWidth;
		this.emWidth = emWidth;
	}
	
	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @param newWidth
	 */
	public void newWidth(int newWidth) {
		if (width <= newWidth) {
			width = newWidth;
		}
	}
	
	/**
	 * @param extraPixels
	 * @param string
	 * @param extraChars
	 */
	public void newWidth(int extraPixels, String string, int extraChars) {
		if (string!=null) {
			newWidth(extraPixels, string.length() + extraChars);
		}
	}
	
	/**
	 * @param extraPixels
	 * @param string
	 */
	public void newWidth(int extraPixels, String string) {
		if (string!=null) {
			newWidth(extraPixels, string.length());
		}
	}
	
	/**
	 * @param extraPixels
	 * @param chars
	 */
	public void newWidth(int extraPixels, int chars) {
		newWidth(extraPixels + chars * emWidth);
	}
}
