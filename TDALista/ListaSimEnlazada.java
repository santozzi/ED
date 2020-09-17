package TDALista;

import java.util.*;

public class ListaSimEnlazada<E> implements PositionList<E>{
    protected Nodo<E> head;
    protected int size;
    public ListaSimEnlazada() {
    	head = null;
    	size = 0;
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
		
		if (isEmpty())
			throw new EmptyListException("La lista esta vacia");
		
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
      if(isEmpty())
    	  throw new EmptyListException("La lista esta vacia");
      Nodo<E> aux = head;
      while(aux.getSiguiente()!=null)
    	  aux=aux.getSiguiente();
		return aux;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		 
		Nodo<E> n =checkPosition(p);
		if(isEmpty())
			throw new InvalidPositionException("No tiene siguiente");
		if(n.getSiguiente()==null)
		  throw new BoundaryViolationException("error");
			
			
		return n.getSiguiente();
	}
	
	private Nodo<E> checkPosition(Position<E> p)throws InvalidPositionException{
	try {	
		if(p==null)
			throw new InvalidPositionException("Posición inválida");
		return (Nodo<E>)p;
	}catch(ClassCastException e) {
		throw new InvalidPositionException("Error de casteo");
	}	
		
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
	  if(isEmpty())
		  throw new InvalidPositionException("Lista vacia");
	  if(p==head)
		 throw new BoundaryViolationException("No tiene previo");
	  
	  Nodo<E> n =checkPosition(p);
	  Nodo<E> b = head;
	  while(b.getSiguiente()!=n&&b.getSiguiente()!=null)
     //preguntar la segunda condicion deberia ir el if de abajo dentro del while.
		  b=b.getSiguiente();
	  
	  if(b.getSiguiente()==null)
	     throw new InvalidPositionException("Lista::prev()"+" Posicion no pertenece a la lista");	  
	return b;
	}

	@Override
	public void addFirst(E element) {
	   head=new Nodo<E>(element,head);	
	size++;
	}

	@Override
	public void addLast(E element) {
	  if(isEmpty())
		  addFirst(element);
	  else {
		 Nodo<E> ultimo =head;
		  while(ultimo.getSiguiente()!=null)
			  ultimo=ultimo.getSiguiente();
		  ultimo.setSiguiente(new Nodo<E>(element));
		  ultimo=null;
		  
		size++;
	  }
	  
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> aux = checkPosition(p);
		aux.setSiguiente(new Nodo<E>(element,aux.getSiguiente()));
	    size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> nuevo = checkPosition(p);
		if(nuevo==head) {
		  addFirst(element);
		}else {
		  Nodo<E> aux= head;
		  while(aux.getSiguiente()!=nuevo) {
			if(aux.getSiguiente()==null)
			   throw new InvalidPositionException("No Pertenece a la lista");
			aux=aux.getSiguiente();
		}
         aux.setSiguiente(new Nodo<E>(element,nuevo));
       
         
		
			
			
		}
		size++;	
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
           if(isEmpty())
        	   throw new InvalidPositionException("Posicion invalida");
		Nodo<E> n= checkPosition(p);		
	       E aux= p.element();
	       if(n==head)
	    	   head=head.getSiguiente();
	       
	       else {
	    	   Nodo<E> anterior =head;
	    	  while(anterior.getSiguiente()!=n) {
	    		 anterior=anterior.getSiguiente(); 
	    		  
	    	  }
			
	          anterior.setSiguiente(n.getSiguiente());
	       }

			size--;
			return aux;
			
			
		
		
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		Nodo<E> n = checkPosition(p);
	    E aux = n.element();
	    n.setElement(element);
		return aux;
	}

	@Override
	public Iterator<E> iterator()  {
    	Iterator<E> it= null;
		try {
		    it =new ElementIterator<E>(this);
    		
		
    	} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());;
		}
	return it;
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>>lista =new ListaSimEnlazada<Position<E>>();
		
		if(!isEmpty()) {
		Nodo<E> nuevo= head;	
		  while(nuevo!=null) {
			 lista.addLast(nuevo);
			 nuevo=nuevo.getSiguiente();
		  }
			  
		}
		return lista;
	}
	
	public String toString() {
	 String res="";
	 Nodo<E> aux=head;
	 
	 while(aux!=null) {
		res +=aux.element().toString()+" "; 
		aux=aux.getSiguiente();
	 }
      return res;		
	}

}
