package TDAArbol;

import TDALista.*;
import TDALista.Position;
import TDALista.PositionList;

public class TNodo<E> implements Position<E> {
      private TNodo<E> padre;
      private E element;
      private PositionList<TNodo<E>> hijos;
	public TNodo(TNodo<E> padre,E element) {
		this.padre=padre;
		this.element=element;
		hijos = new DoubleLinkedList<TNodo<E>>();
	   
	}
	public TNodo() {
	  this(null,null);	
	}
	public void setElement(E e) {
	 element=e;
	}
    
    public void setPadre(TNodo<E> padre) {
		this.padre=padre;
	}
	public void setHijos(PositionList<TNodo<E>> hijos) {
		this.hijos=hijos;
		
	}
	public TNodo<E> getPadre(){
	 return padre;
		
	}
	public E element() {
		return element;
	}
	
    public  PositionList<TNodo<E>> getHijos(){
    	return hijos;
    } 
}
