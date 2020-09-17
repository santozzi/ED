package TDAARbolBinario;

public class BTNode<E> implements BTPosition<E> {
    private BTPosition<E> left,right,parent;
    private E element;
	public BTNode(E element, BTPosition<E> left,BTPosition<E> right,BTPosition<E> parent){
    	this.parent=parent;
    	this.right=right;
    	this.left=left;
    	this.element=element;
    }
	public BTNode(E element){
		this(element,null,null,null);
	}
	@Override
	public E element() {
	 return element;
	}
	@Override
	public void setLeft(BTPosition<E> v) {
	 left =v;
		
	}
	@Override
	public void setRight(BTPosition<E> v) {
	 right=v;	
		
	}
	@Override
	public BTPosition<E> getLeft() {
	
		return left;
	}
	@Override
	public BTPosition<E> getRight() {
	
		return right;
	}
	public void setElement(E e){
		element =e;
	}
	@Override
	public void setParent(BTPosition<E> p) {
	 parent=p;
		
	}
	@Override
	public BTPosition<E> getParent() {
	 	
		return parent;
	}
	}

	