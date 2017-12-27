package xsdvi.utils;

/**
 * @author Václav Slavìtínský
 *
 */
public class TreeBuilder {
	private TreeElement parent = null;
	private TreeElement root = null;

	/**
	 * @param child
	 */
	public void appendChild(TreeElement child) {
		parent.addChild(child);
		child.setParent(parent);
		parent = child;
	}

	/**
	 * 
	 */
	public void levelUp() {
		parent = parent.getParent();
	}
	
	/**
	 * @param newRoot
	 */
	public void setRoot(TreeElement newRoot) {
		parent = newRoot;
		root = newRoot;
	}
	
	/**
	 * @return
	 */
	public TreeElement getRoot() {
		return root;
	}
}