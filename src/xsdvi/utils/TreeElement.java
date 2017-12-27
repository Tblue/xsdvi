package xsdvi.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Václav Slavìtínskı
 *
 */
public class TreeElement {
	private TreeElement parent;
	private List<TreeElement> children;
	
	/**
	 * 
	 */
	public TreeElement() {
		children = new ArrayList<TreeElement>();
	}
	
	/**
	 * @return
	 */
	public int getIndex() {
		if (!hasParent()) return 1;
		return parent.children.indexOf(this)+1;
	}
	
	/**
	 * @return
	 */
	public TreeElement getParent() {
		return parent;
	}
	
	/**
	 * @param parent
	 */
	public void setParent(TreeElement parent) {
		this.parent = parent;
	}
	
	/**
	 * @return
	 */
	public boolean hasParent() {
		return parent != null;
	}
	
	/**
	 * @return
	 */
	public TreeElement getLastChild() {
		return children.get(children.size()-1);
	}
	
	/**
	 * @return
	 */
	public boolean isLastChild() {
		if (!hasParent()) return true;
		return parent.children.indexOf(this) == parent.children.size()-1;
	}
	
	/**
	 * @return
	 */
	public boolean isFirstChild() {
		if (!hasParent()) return true;
		return parent.children.indexOf(this) == 0;
	}
	
	/**
	 * @param child
	 */
	public void addChild(TreeElement child) {
		children.add(child);
	}
	
	/**
	 * @return
	 */
	public List<TreeElement> getChildren() {
		return children;
	}
	
	/**
	 * @return
	 */
	public boolean hasChildren() {
		return !children.isEmpty();
	}
	
	/**
	 * @return
	 */
	public String code() {
		StringBuffer buffer = new StringBuffer();
		TreeElement element = this;
		while (element.hasParent()) {
			buffer.insert(0, element.getIndex());
			buffer.insert(0, '_');
			element = element.getParent();
		}
		buffer.insert(0, "_1");
		return buffer.toString();
	}
}