package TDALista;

public class NodoD<E> implements Position<E>{
	private E element;
	private NodoD<E> anterior,siguiente;
	public NodoD(NodoD<E> ant,E e,NodoD<E> sig) {
		element= e;
		anterior=ant;
		siguiente=sig;
	}
	public NodoD() {
		this(null,null,null);
	}
	@Override
	public E element() {
		
		return element;
	}
	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public NodoD<E> getAnterior() {
		return anterior;
	}
	public void setAnterior(NodoD<E> anterior) {
		this.anterior = anterior;
	}
	public NodoD<E> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoD<E> siguiente) {
		this.siguiente = siguiente;
	}
	

}
