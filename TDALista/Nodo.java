package TDALista;

public class Nodo<E> implements Position<E>{
	private E element;
    private Nodo<E> siguiente;
    private int size;
    
    public Nodo(E element,Nodo<E> sig) {
    	this.element=element;
    	siguiente=sig;
    }
    
    public Nodo(E element) {
    	this(element,null);
    }
    
    public Nodo() {
    	this(null,null);
    }
    
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return this.element;
	}
	

	public void setElement(E element) {
		this.element = element;
	}
	
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

}
