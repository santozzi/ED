package TDALista;

import java.util.Iterator;

public class DoubleLinkedList<E> implements PositionList<E>{
    protected NodoD<E> header,trailer;
    protected int size;
    
    public DoubleLinkedList() {
    	header= new NodoD<E>();
    	trailer= new NodoD<E>(header,null,null);
    	header.setSiguiente(trailer);
    	size=0;
    }

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean isEmpty() {
		
		return size==0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException("Lista vacia");
		
		return header.getSiguiente();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException("Lista vacia");
		return trailer.getAnterior();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoD<E> nuevo = checkPosition(p);
		if(isEmpty())
			throw new InvalidPositionException("Lista vacia");
		if(nuevo.getSiguiente()==trailer)
			throw new BoundaryViolationException("No hay siguiente");
		
		return nuevo.getSiguiente();
	}
    
    private NodoD<E> checkPosition(Position<E> p)throws InvalidPositionException{
    	if(p==null)
    	  throw new InvalidPositionException("Posición invalida-Nodo nulo");
    	if(p==header)
      	  throw new InvalidPositionException("Posición invalida-Header no es utilizable");
    	if(p==trailer)
      	  throw new InvalidPositionException("Posición invalida-Trailer no es untilizable");
    	try {
    		NodoD<E> temp = (NodoD<E>)p;
    		if((temp.getAnterior()==null)||(temp.getSiguiente()==null))
    			throw new InvalidPositionException("Error");
    		return temp;
    	}catch(ClassCastException e) {
    		throw new InvalidPositionException("error");
    	}
    }
    
	@Override
	
    public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoD<E> nuevo= checkPosition(p);
		if(isEmpty())
			throw new InvalidPositionException("Lista vacia");
		if(nuevo.getAnterior()==header)
			throw new BoundaryViolationException("No hay anterior");
		
		return nuevo.getAnterior();
	}

	@Override
	public void addFirst(E element) {
		NodoD<E> siguiente = header.getSiguiente();
		NodoD<E> nuevo= new NodoD<E>(header,element,siguiente);
		header.setSiguiente(nuevo);
		siguiente.setAnterior(nuevo);
		siguiente=null;
		nuevo=null;
		size++;
	}

	@Override
	public void addLast(E element) {
	NodoD<E> anterior = trailer.getAnterior();
	NodoD<E> nuevo = new NodoD<E>(anterior,element,trailer);
	trailer.setAnterior(nuevo);
	anterior.setSiguiente(nuevo);
	anterior=null;
	nuevo=null;
	size++;
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		NodoD<E> anterior= checkPosition(p);
		if(isEmpty())
			throw new InvalidPositionException("La lista esta vacia");
		NodoD<E> siguiente = anterior.getSiguiente();
		NodoD<E> nuevo = new NodoD<E>(anterior,element,siguiente);
		anterior.setSiguiente(nuevo);
		siguiente.setAnterior(nuevo);
		
		nuevo=null;
		siguiente=null;
		anterior=null;
		size++;
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		NodoD<E> siguiente = checkPosition(p);
		NodoD<E> anterior = siguiente.getAnterior();
		NodoD<E> nuevo = new NodoD<E>(anterior,element,siguiente);
		anterior.setSiguiente(nuevo);
		siguiente.setAnterior(nuevo);
		nuevo=null;
		anterior=null;
		siguiente=null;
		size++;
		
		
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		NodoD<E> borrar = checkPosition(p);
		E aux = borrar.element();
		NodoD<E> siguiente = borrar.getSiguiente();
		NodoD<E> anterior = borrar.getAnterior();
		borrar.setSiguiente(null);
		borrar.setAnterior(null);
		anterior.setSiguiente(siguiente);
		siguiente.setAnterior(anterior);
		anterior=null;
		siguiente=null;
		borrar=null;
	
		size--;
		return aux;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		NodoD<E>nuevo = checkPosition(p);
		E aux = p.element();
		nuevo.setElement(element);
		nuevo=null;
		return aux;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> it=null;
		try {
			it = new ElementIterator<E>(this);
		}catch(EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return it;
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista= new DoubleLinkedList<Position<E>>();
		if(!isEmpty()) {
		NodoD<E> nuevo= header.getSiguiente();
		while(nuevo!=trailer) {
			lista.addLast(nuevo);
			nuevo=nuevo.getSiguiente();
		}
			}
			
		return lista;
	}
}
