package xsdvi.svg;

import xsdvi.utils.WidthCalculator;

/**
 * @author Václav Slavìtínský
 *
 */
public class SymbolElement extends AbstractSymbol{	
	private String name = null;
	private String namespace = null;
	private String type = null;
	private String cardinality = null;
	private boolean nillable = false;
	private boolean abstr = false;
	private String substitution = null;
	
	/**
	 * @param name
	 * @param namespace
	 * @param type
	 * @param cardinality
	 * @param nillable
	 * @param abstr
	 * @param substitution
	 */
	public SymbolElement(String name, String namespace, String type, String cardinality, boolean nillable, boolean abstr, String substitution) {
		this();
		this.name = name;
		this.namespace = namespace;
		this.type = type;
		this.cardinality = cardinality;
		this.nillable = nillable;
		this.abstr = abstr;
		this.substitution = substitution;
	}

	/**
	 * 
	 */
	public SymbolElement() {
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
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * @return
	 */
	public boolean isNillable() {
		return nillable;
	}

	/**
	 * @param nillable
	 */
	public void setNillable(boolean nillable) {
		this.nillable = nillable;
	}

	/**
	 * @return
	 */
	public boolean isAbstr() {
		return abstr;
	}

	/**
	 * @param abstr
	 */
	public void setAbstr(boolean abstr) {
		this.abstr = abstr;
	}

	/**
	 * @return
	 */
	public String getSubstitution() {
		return substitution;
	}

	/**
	 * @param substitution
	 */
	public void setSubstitution(String substitution) {
		this.substitution = substitution;
	}
	
	/* (non-Javadoc)
	 * @see xsdvi.svg.AbstractSymbol#draw()
	 */
	@Override
	public void draw() {
		drawGStart();
		print("<rect class='shadow' x='3' y='3' width='"+width+"' height='"+height+"'/>");
		print("<rect class='boxelement' x='0' y='0' width='"+width+"' height='"+height+"'");
		drawMouseover();
		if (namespace!=null) {
			print("<text class='visible' x='5' y='13'>"+namespace+"</text>");
		}
		if (substitution!=null) {
			print("<text class='hidden' visibility='hidden' x='5' y='13'>subst.: "+substitution+"</text>");
			print("<text class='hidden' visibility='hidden' x='5' y='41'>nillable: "+(nillable ? "1" : "0")+", abstract: "+(abstr ? "1" : "0")+"</text>");
		}
		else {
			print("<text class='hidden' visibility='hidden' x='5' y='13'>nillable: "+(nillable ? "1" : "0")+"</text>");
			print("<text class='hidden' visibility='hidden' x='5' y='41'>abstract: "+(abstr ? "1" : "0")+"</text>");
		}
		if (name!=null) {
			print("<text class='strong' x='5' y='27'>"+name+"</text>");
		}
		if (type!=null) {
			print("<text class='visible' x='5' y='41'>"+type+"</text>");
		}
		if (cardinality!=null) {
			print("<text x='5' y='59'>"+cardinality+"</text>");
		}
		drawConnection();
		drawUse();
		drawGEnd();
	}
	
	/**
	 * 
	 */
	void drawAlternate() {
		drawGStart();
		print("<rect class='shadow' x='3' y='3' width='"+width+"' height='"+height+"'/>");
		print("<rect class='boxelement' x='0' y='0' width='"+width+"' height='"+height+"'");
		drawMouseover();
		if (namespace!=null) {
			print("<text class='hidden' visibility='hidden' x='5' y='13'>"+namespace+"</text>");
		}
		if (substitution!=null) {
			print("<text class='visible' x='5' y='13'>subst.: "+substitution+"</text>");
			print("<text class='visible' x='5' y='41'>nillable: "+(nillable ? "1" : "0")+", abstract: "+(abstr ? "1" : "0")+"</text>");
		}
		else {
			print("<text class='visible' x='5' y='13'>nillable: "+(nillable ? "1" : "0")+"</text>");
			print("<text class='visible' x='5' y='41'>abstract: "+(abstr ? "1" : "0")+"</text>");
		}
		if (name!=null) {
			print("<text class='strong' x='5' y='27'>"+name+"</text>");
		}
		if (type!=null) {
			print("<text class='hidden' visibility='hidden' x='5' y='41'>"+type+"</text>");
		}
		if (cardinality!=null) {
			print("<text x='5' y='59'>"+cardinality+"</text>");
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
		calc.newWidth(15, name, 3);
		calc.newWidth(15, namespace);
		calc.newWidth(15, type);
		calc.newWidth(15, cardinality);
		calc.newWidth(15, (substitution==null ? 11 : 22));
		calc.newWidth(15, substitution, 8);
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