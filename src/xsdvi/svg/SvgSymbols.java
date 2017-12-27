package xsdvi.svg;

import xsdvi.utils.WriterHelper;

/**
 * @author Václav Slavìtínský
 *
 */
public class SvgSymbols extends SvgForXsd{
	
	/**
	 * @param w
	 */
	public SvgSymbols(WriterHelper w) {
		super(w);
	}
	
	/**
	 * @param name
	 * @param w
	 * @param h
	 */
	public void begin(String name, int w, int h) {
		writer.newWriter("../psani/symbols/"+name+".svg");
		
		print(XML_DECLARATION);
		print("<svg id='svg' width='"+w+"px' height='"+h+"px' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink'>");
		print(TITLE);
		
		printDefs(true, false);
    	
    	print("<rect class='border' x='1' y='1' width='"+(w-2)+"' height='"+(h-2)+"'/>");
	}

	/**
	 * 
	 */
	public void drawSymbols() {
		AbstractSymbol symbol;

		begin("element", 220, 80);
		symbol = new SymbolElement("{name}", "{namespace}", "{type}", "{cardinality}", true, false, "{substitution}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(140);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();
		
		begin("element2", 220, 80);
		symbol = new SymbolElement("{name}", "{namespace}", "{type}", "{cardinality}", true, false, "{substitution}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(140);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		((SymbolElement) symbol).drawAlternate();
		end();
		
		begin("any_strict", 190, 80);
		symbol = new SymbolAny("{namespace}", AbstractSymbol.PC_STRICT, "{cardinality}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(110);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("any_lax", 190, 80);
		symbol = new SymbolAny("{namespace}", AbstractSymbol.PC_LAX, "{cardinality}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(110);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("any_skip", 190, 80);
		symbol = new SymbolAny("{namespace}", AbstractSymbol.PC_SKIP, "{cardinality}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(110);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("any_attribute_strict", 190, 70);
		symbol = new SymbolAnyAttribute("{namespace}", AbstractSymbol.PC_STRICT);
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(110);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("any_attribute_lax", 190, 70);
		symbol = new SymbolAnyAttribute("{namespace}", AbstractSymbol.PC_LAX);
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(110);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("any_attribute_skip", 190, 70);
		symbol = new SymbolAnyAttribute("{namespace}", AbstractSymbol.PC_SKIP);
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(110);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("attribute_optional", 180, 70);
		symbol = new SymbolAttribute("{name}", "{namespace}", "{type}", false, "{constraint}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(100);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();
		
		begin("attribute_optional2", 180, 70);
		symbol = new SymbolAttribute("{name}", "{namespace}", "{type}", false, "{constraint}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(100);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		((SymbolAttribute) symbol).drawAlternate();
		end();

		begin("attribute_required", 180, 70);
		symbol = new SymbolAttribute("{name}", "{namespace}", "{type}", true, "{constraint}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(100);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("unique", 180, 70);
		symbol = new SymbolUnique("{name}", "{namespace}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(100);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("key", 180, 70);
		symbol = new SymbolKey("{name}", "{namespace}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(100);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("keyref", 180, 70);
		symbol = new SymbolKeyref("{name}", "{namespace}", "{refer}");
		symbol.setXPosition(40);
		symbol.setYPosition(10);
		symbol.setWidth(100);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("selector", 150, 60);
		symbol = new SymbolSelector("{xpath}");
		symbol.setXPosition(40);
		symbol.setYPosition(5);
		symbol.setWidth(70);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("field", 150, 60);
		symbol = new SymbolField("{xpath}");
		symbol.setXPosition(40);
		symbol.setYPosition(5);
		symbol.setWidth(70);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("sequence", 150, 65);
		symbol = new SymbolSequence("{cardinality}");
		symbol.setXPosition(40);
		symbol.setYPosition(5);
		symbol.setWidth(70);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("choice", 150, 65);
		symbol = new SymbolChoice("{cardinality}");
		symbol.setXPosition(40);
		symbol.setYPosition(5);
		symbol.setWidth(70);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("all", 150, 70);
		symbol = new SymbolAll("{cardinality}");
		symbol.setXPosition(40);
		symbol.setYPosition(5);
		symbol.setWidth(70);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("schema", 160, 45);
		symbol = new SymbolSchema();
		symbol.setXPosition(40);
		symbol.setYPosition(0);
		symbol.setWidth(70);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();

		begin("_loop", 135, 45);
		symbol = new SymbolLoop();
		symbol.setXPosition(40);
		symbol.setYPosition(0);
		symbol.setWidth(55);
		symbol.setHeight(symbol.getHeight());
		symbol.setSvg(this);
		symbol.draw();
		end();
	}
	
}