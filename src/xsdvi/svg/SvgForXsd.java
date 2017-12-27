package xsdvi.svg;

import xsdvi.utils.TreeElement;
import xsdvi.utils.WriterHelper;

/**
 * @author Václav Slavìtínský
 *
 */
public class SvgForXsd {
	protected WriterHelper writer;
	private String styleUri = null;
	private boolean embodyStyle = true;

	/**
	 * 
	 */
	protected static final String XML_DECLARATION =
		"<?xml version='1.0' encoding='UTF-8'?>";
	
	/**
	 * 
	 */
	protected static final String SVG_DOCTYPE =
		"<!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'>";
	
	/**
	 * 
	 */
	protected static final String SVG_START =
		"<svg id='svg' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' onload='loadSVG();'>";
	
	/**
	 * 
	 */
	protected static final String TITLE =
		"<title>XsdVi</title>\n";
	
	/**
	 * 
	 */
	protected static final String SCRIPT =
		"<script type='text/ecmascript'><![CDATA[\n"+
		"\n"+
		"  var efBoxes = [];\n"+
		"  var eSvg = null;\n"+
		"\n"+
		"////////// loadSVG()\n"+
		"  function loadSVG() {\n"+
		"    efBoxes = getElementsByClassName('box', document.getElementsByTagName('g'));\n"+
		"    eSvg = document.getElementById('svg');\n"+
		"    expandAll();\n"+
		"  }\n"+
		"\n"+
		"////////// getElementsByClassName(string, nodeList)\n"+
		"  function getElementsByClassName(sClass, nlNodes) {\n"+
		"    var elements = [];\n"+
		"    for (var i=0; i<nlNodes.length; i++) {\n"+
		"      if(nlNodes.item(i).nodeType==1 && sClass==nlNodes.item(i).getAttribute('class')) {\n"+
		"        elements.push(nlNodes.item(i));\n"+
		"      }\n"+
		"    }\n"+
		"    return elements;\n"+
		"  }\n"+
		"\n"+
		"////////// show(string)\n"+
		"  function show(sId) {\n"+
		"    var useElement = document.getElementById('s'+sId);\n"+
		"    var moveNext = false;\n"+
		"    var eBoxLast;\n"+
		"    var maxX = 500;\n"+
		"    \n"+
		"    if (notPlus(useElement)) {\n"+
		"      eBoxLast = document.getElementById(sId);\n"+
		"      setPlus(useElement);\n"+
		"      for (var i=0; i<efBoxes.length; i++) {\n"+
		"        var eBox = efBoxes[i];\n"+
		"        if (moveNext) {\n"+
		"          move(eBoxLast, eBox);\n"+
		"        }\n"+
		"        else if (isDescendant(sId, eBox.id)) {\n"+
		"          eBox.setAttribute('visibility', 'hidden');\n"+
		"        }\n"+
		"        else if (isHigherBranch(sId, eBox.id)) {\n"+
		"          move(eBoxLast, eBox);\n"+
		"          moveNext = true;\n"+
		"        }\n"+
		"        if (eBox.getAttribute('visibility') != 'hidden') {\n"+
		"          eBoxLast = eBox;\n"+
		"          x = xTrans(eBox);\n"+
		"          if (x > maxX) maxX = x;\n"+
		"        }\n"+
		"      }\n"+
		"    }\n"+
		"\n"+
		"    else {\n"+
		"      setMinus(useElement);\n"+
		"      var skipDescendantsOf;\n"+
		"      for (var i=0; i<efBoxes.length; i++) {\n"+
		"        var eBox = efBoxes[i];\n"+
		"        if (moveNext) {\n"+
		"          move(eBoxLast, eBox);\n"+
		"        }\n"+
		"        else if (isDescendant(sId, eBox.id) && (!skipDescendantsOf || !isDescendant(skipDescendantsOf.id, eBox.id))) {\n"+
		"          eBox.setAttribute('visibility', 'visible');\n"+
		"          move(eBoxLast, eBox);\n"+
		"          if (nextClosed(eBox)) skipDescendantsOf = eBox;\n"+
		"        }\n"+
		"        else if (isHigherBranch(sId, eBox.id)) {\n"+
		"          move(eBoxLast, eBox);\n"+
		"          moveNext = true;\n"+
		"        }\n"+
		"        if (eBox.getAttribute('visibility') != 'hidden') {\n"+
		"          eBoxLast = eBox;\n"+
		"          x = xTrans(eBox);\n"+
		"          if (x > maxX) maxX = x;\n"+
		"        }\n"+
		"      }\n"+
		"    }\n"+
		"    setHeight(yTrans(eBoxLast)+"+(AbstractSymbol.MAX_HEIGHT+AbstractSymbol.Y_INDENT)+");\n"+
		"    setWidth(maxX+300);\n"+
		"  }\n"+
		"\n"+
		"////////// collapseAll()\n"+
		"  function collapseAll() {\n"+
		"    for (var i=0; i<efBoxes.length; i++) {\n"+
		"      var eBox = efBoxes[i];\n"+
		"      var useElement = document.getElementById('s'+eBox.id);\n"+
		"      if (useElement) setPlus(useElement);\n"+
		"      if (eBox.id != '_1') eBox.setAttribute('visibility', 'hidden');\n"+
		"    }\n"+
		"    setHeight(400);\n"+
		"    setWidth(500);\n"+
		"  }\n"+
		"\n"+
		"////////// expandAll()\n"+
		"  function expandAll() {\n"+
		"    var eBoxLast;\n"+
		"    var maxX = 0;\n"+
		"    for (var i=0; i<efBoxes.length; i++) {\n"+
		"      var eBox = efBoxes[i];\n"+
		"      var useElement = document.getElementById('s'+eBox.id);\n"+
		"      if (useElement) setMinus(useElement);\n"+
		"      move(eBoxLast, eBox);\n"+
		"      eBox.setAttribute('visibility', 'visible');\n"+
		"      eBoxLast = eBox;\n"+
		"      var x = xTrans(eBox);\n"+
		"      if (x > maxX) maxX = x;\n"+
		"    }\n"+
		"    setHeight(yTrans(eBoxLast)+"+(AbstractSymbol.MAX_HEIGHT+AbstractSymbol.Y_INDENT)+");\n"+
		"    setWidth(maxX+300);\n"+
		"  }\n"+
		"\n"+
		"////////// makeVisible(string)\n"+
		"  function makeVisible(sId) {\n"+
		"    var childNodes = document.getElementById(sId).childNodes;\n"+
		"    var hidden = getElementsByClassName('hidden', childNodes);\n"+
		"    var visible = getElementsByClassName('visible', childNodes);\n"+
		"    inheritVisibility(hidden);\n"+
		"    hiddenVisibility(visible);\n"+
		"  }\n"+
		"\n"+
		"////////// makeHidden(string)\n"+
		"  function makeHidden(sId) {\n"+
		"    var childNodes = document.getElementById(sId).childNodes;\n"+
		"    var hidden = getElementsByClassName('hidden', childNodes);\n"+
		"    var visible = getElementsByClassName('visible', childNodes);\n"+
		"    inheritVisibility(visible);\n"+
		"    hiddenVisibility(hidden);\n"+
		"  }\n"+
		"\n"+
		"////////// inheritVisibility(element[])\n"+
		"  function inheritVisibility(efElements) {\n"+
		"    for (var i=0; i<efElements.length; i++) {\n"+
		"      efElements[i].setAttribute('visibility', 'inherit');\n"+
		"    }\n"+
		"  }\n"+
		"\n"+
		"////////// hiddenVisibility(element[])\n"+
		"  function hiddenVisibility(efElements) {\n"+
		"    for (var i=0; i<efElements.length; i++) {\n"+
		"      efElements[i].setAttribute('visibility', 'hidden');\n"+
		"    }\n"+
		"  }\n"+
		"\n"+
		"////////// nextClosed(element)\n"+
		"  function nextClosed(eBox) {\n"+
		"    var useElement = document.getElementById('s'+eBox.id);\n"+
		"    return (useElement && !notPlus(useElement));\n"+
		"  }\n"+
		"\n"+
		"////////// isHigherBranch(string, string)\n"+
		"  function isHigherBranch(sSerialLower, sSerialHigher) {\n"+
		"    var sLower = sSerialLower.split('_');\n"+
		"    var sHigher = sSerialHigher.split('_');\n"+
		"    for (var i=0; i<sLower.length; i++) {\n"+
		"      if (Number(sHigher[i]) > Number(sLower[i])) return true;\n"+
		"      else if (Number(sHigher[i]) < Number(sLower[i])) return false;\n"+
		"    }\n"+
		"    return false;\n"+
		"  }\n"+
		"\n"+
		"////////// isOnHigherLevel(element, element)\n"+
		"  function isOnHigherLevel(eBoxLower, eBoxHigher) {\n"+
		"    var sLower = eBoxLower.id.split('_');\n"+
		"    var sHigher = eBoxHigher.id.split('_');\n"+
		"    for (var i=0; i<sLower.length; i++) {\n"+
		"      if (Number(sHigher[i]) > Number(sLower[i])) return true;\n"+
		"    }\n"+
		"    return false;\n"+
		"  }\n"+
		"\n"+
		"////////// isDescendant(string, string)\n"+
		"  function isDescendant(sSerialAsc, sSerialDesc) {\n"+
		"    return (sSerialDesc.length > sSerialAsc.length && sSerialDesc.indexOf(sSerialAsc) === 0);\n"+
		"  }\n"+
		"\n"+
		"////////// getParent(element)\n"+
		"  function getParent(eBox) {\n"+
		"    var serial = eBox.id.substring(0, eBox.id.lastIndexOf('_'));\n"+
		"    return document.getElementById(serial);\n"+
		"  }\n"+
		"\n"+
		"////////// move(element, element)\n"+
		"  function move(eBoxLast, eBox) {\n"+
		"    if (!eBoxLast) return;\n"+
		"    if (isOnHigherLevel(eBoxLast, eBox)) {\n"+
		"      setYTrans(eBox, yTrans(eBoxLast)+"+(AbstractSymbol.MAX_HEIGHT+AbstractSymbol.Y_INDENT)+");\n"+
		"      var parent = getParent(eBox);\n"+
		"      var line = document.getElementById('p'+eBox.id);\n"+
		"      if (!parent || !line) return;\n"+
		//"      line.y1.baseVal.valueInSpecifiedUnits = yTrans(parent)-yTrans(eBox)+"+AbstractSymbol.MAX_HEIGHT/2+";\n"+
		"      line.setAttribute('y1', String(yTrans(parent)-yTrans(eBox)+"+AbstractSymbol.MAX_HEIGHT/2+"));\n"+
		"    }\n"+
		"    else {\n"+
		"      setYTrans(eBox, yTrans(eBoxLast));\n"+
		"    }\n"+
		"  }\n"+
		"\n"+
		"////////// notPlus(element)\n"+
		"  function notPlus(eUseElement) {\n"+
		"    return (eUseElement.getAttributeNS('http://www.w3.org/1999/xlink', 'href') != '#plus');\n"+
		"  }\n"+
		"\n"+
		"////////// setPlus(element)\n"+
		"  function setPlus(eUseElement) {\n"+
		"    eUseElement.setAttributeNS('http://www.w3.org/1999/xlink', 'href', '#plus');\n"+
		"  }\n"+
		"\n"+
		"////////// setMinus(element)\n"+
		"  function setMinus(eUseElement) {\n"+
		"    eUseElement.setAttributeNS('http://www.w3.org/1999/xlink', 'href', '#minus');\n"+
		"  }\n"+
		"\n"+
		"////////// setHeight(number)\n"+
		"  function setHeight(nHeight) {\n"+
		"    eSvg.setAttribute('height', nHeight);\n"+
		"  }\n"+
		"\n"+
		"////////// setWidth(number)\n"+
		"  function setWidth(nWidth) {\n"+
		"    eSvg.setAttribute('width', nWidth);\n"+
		"  }\n"+
		"\n"+
		"////////// xyTrans(element)\n"+
		"  function xTrans(eBox) {\n"+
		//"    return eBox.transform.baseVal.getItem(0).matrix.e;\n"+
		"    var transform = eBox.getAttribute('transform');\n"+
	    "    var x = Number(transform.substring(10, Number(transform.length)-1).split(',')[0]);\n"+
	    "    if(!x) x = 0;\n"+
	    "    return x;\n"+
		"  }\n"+
		"\n"+
		"////////// yTrans(element)\n"+
		"  function yTrans(eBox) {\n"+
		//"    return eBox.transform.baseVal.getItem(0).matrix.f;\n"+
		"    var transform = eBox.getAttribute('transform');\n"+
	    "    var y = Number(transform.substring(10, Number(transform.length)-1).split(',')[1]);\n"+
	    "    if(!y) y = 0;\n"+
	    "    return y;\n"+
		"  }\n"+
		"\n"+
		"////////// setYTrans(element, number)\n"+
		"  function setYTrans(eBox, nValue) {\n"+
		//"    return eBox.transform.baseVal.getItem(0).matrix.f = nValue;\n"+
		"    eBox.setAttribute('transform', 'translate('+xTrans(eBox)+','+nValue+')');\n"+
		"  }\n"+
		"\n"+
		"]]></script>\n";
	
	/**
	 * 
	 */
	protected static final String STYLE =
		"svg {pointer-events: none;}\n"+
		"text {font-family: arial; font-size: 11px;}\n"+
		"line, polyline, polygon {fill: none; stroke: black;}\n"+
		"\n"+
		".strong {font-size: 12px; font-weight: bold;}\n"+
		".small {font-size: 10px;}\n"+
		".big {font-size: 15px; fill: #882222;}\n"+
		"\n"+
		".button {fill: white; stroke: black; pointer-events: all;}\n"+
		".shadow {fill: #ccccd8; stroke: none;}\n"+
		".connection {fill: none; stroke: #666666;}\n"+
		".empty {fill: none; stroke: black;}\n"+
		".filled {fill: black; stroke: none;}\n"+
		"\n"+
		".boxelement, .boxany, .boxattribute1, .boxanyattribute {fill: #FFFFBB; stroke: #776633; pointer-events: all;}\n"+
		".boxattribute2 {fill: #FFFFBB; stroke: #776633; pointer-events: all; stroke-dasharray: 2;}\n"+
		".boxschema, .boxloop, .boxcompositor {fill: #E7EBF3; stroke: #666677;}\n"+
		".boxselector, .boxfield, .boxidc {fill: #E0F7B7; stroke: #667733;}\n"+
		"\n"+
		".lax {fill: white; stroke: black;}\n"+
		".skip {fill: #cc6666; stroke: black;}\n"+
		".strict {fill: black; stroke: none;}\n"+
		"\n"+
		".border {fill: #f9f9f9; stroke: #dddddd;}";

	/**
	 * 
	 */
	protected static final String DEFINED_SYMBOLS =
		"  <symbol class='button' id='plus'>\n"+
		"    <rect x='1' y='1' width='10' height='10'/>\n"+
		"    <line x1='3' y1='6' x2='9' y2='6'/>\n"+
		"    <line x1='6' y1='3' x2='6' y2='9'/>\n"+
		"  </symbol>\n"+
		"  <symbol class='button' id='minus'>\n"+
		"    <rect x='1' y='1' width='10' height='10'/>\n"+
		"    <line x1='3' y1='6' x2='9' y2='6'/>\n"+
		"  </symbol>\n";
	
	/**
	 * 
	 */
	protected static final String MENU_BUTTONS =
		"<rect class='button' x='300' y='10' width='20' height='20' onclick='collapseAll()'/>\n"+
		"<line x1='303' y1='20' x2='317' y2='20'/>\n"+
		"<text x='330' y='20'>collapse all</text>\n"+
		"<rect class='button' x='400' y='10' width='20' height='20' onclick='expandAll()'/>\n"+
		"<line x1='403' y1='20' x2='417' y2='20'/>\n"+
		"<line x1='410' y1='13' x2='410' y2='27'/>\n"+
		"<text x='430' y='20'>expand all</text>\n";
	
	/**
	 * 
	 */
	protected static final String SVG_END =
		"</svg>";
	
	/**
	 * @param w
	 */
	public SvgForXsd(WriterHelper w) {
		this.writer = w;
	}
	
	/**
	 * 
	 */
	protected void printStyleRef() {
		print("<?xml-stylesheet href='"+styleUri+"' type='text/css'?>");
	}

	/**
	 * 
	 */
	protected void printEmbodiedStyle() {
		print("<style type='text/css'><![CDATA[");
		print(STYLE);
		print("]]></style>");
	}
	
	/**
	 * @param style
	 * @param symbols
	 */
	protected void printDefs(boolean style, boolean symbols) {
		print("<defs>");
		if (style) {
			printEmbodiedStyle();
		}
		if (symbols) {
			print(DEFINED_SYMBOLS);
		}
        print("</defs>");
	}
	
	/**
	 * 
	 */
	public void printExternStyle() {
		writer.newWriter(styleUri);
		print(STYLE);
		writer.close();
	}
	
	/**
	 * 
	 */
	public void begin() {
		print(XML_DECLARATION);
		if (!embodyStyle) {
			printStyleRef();
		}
		print(SVG_DOCTYPE);
		print(SVG_START);
		print(TITLE);
		
        print(SCRIPT);
        
        printDefs(embodyStyle, true);

    	print(MENU_BUTTONS);
	}

	/**
	 * 
	 */
	public void end() {
		print(SVG_END);
	    writer.close();
	}

	/**
	 * @param string
	 */
	protected void print(String string) {
		writer.append(string+"\n");
	}

	/**
	 * @return
	 */
	public WriterHelper getWriter() {
		return writer;
	}

	/**
	 * @param w
	 */
	public void setWriter(WriterHelper w) {
		this.writer = w;
	}
	
	/**
	 * @param embody
	 */
	public void setEmbodyStyle(boolean embody) {
		embodyStyle = embody;
	}

	/**
	 * @param styleUri
	 */
	public void setStyleUri(String styleUri) {
		this.styleUri = styleUri;
	}

	/**
	 * @return
	 */
	public String getStyleUri() {
		return styleUri;
	}

	/**
	 * @return
	 */
	public boolean embodyStyle() {
		return embodyStyle;
	}

	/**
	 * @param rootSymbol
	 */
	public void draw(AbstractSymbol rootSymbol) {
		begin();
		drawSymbol(rootSymbol);
		end();
	}

	/**
	 * @param symbol
	 */
	private void drawSymbol(AbstractSymbol symbol) {
		symbol.setSvg(this);
		symbol.prepareBox();
		symbol.draw();
		for (TreeElement s : symbol.getChildren()) {
			drawSymbol((AbstractSymbol) s);
		}
	}
}