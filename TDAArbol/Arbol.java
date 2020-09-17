package TDAArbol;

import java.util.Iterator;

import TDALista.*;

public class Arbol<E> implements Tree<E>{
    protected TNodo<E> root;
	protected int size;
	public Arbol() {
		root=null;
		size=0;	
	}
	
	@Override
	public Iterator<E> iterator() {
	  PositionList<E> list = new DoubleLinkedList<E>();
	  if(size>0)
		  recPreorden(root,list);
		return list.iterator();
	}
	
	private void recPreorden(TNodo<E> r, PositionList<E> l) {
		l.addLast(r.element());
		for(TNodo<E> p:r.getHijos())
			recPreorden(p,l);
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
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> list = new DoubleLinkedList<Position<E>>();
		if(size>0)
			recPosorden(root,list);
		return list;
	}
	
	private void recPosorden(TNodo<E> r,PositionList<Position<E>> l) {
		l.addLast(r);
		for(TNodo<E> p:r.getHijos()) 
			recPosorden(p,l);
		
	}
	
 	protected TNodo<E> checkPosition(Position<E> p)throws InvalidPositionException{
	 TNodo<E> nodo = null;
		if(p==null)
		 throw new InvalidPositionException("Posicion invalida");
	   try {
		   nodo = (TNodo<E>)p;
	   }catch(ClassCastException e) {
		   throw new InvalidPositionException("Error de casteo");
	   } 
		return nodo;
	}
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> nuevo = checkPosition(v);
		E aux = v.element();
		nuevo.setElement(e);
		
		return aux;
	}
	

	@Override	
	/**
	 * Devuelve la raiz
	 */
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException("El arbol esta vacio");
		return root;
	}
	
	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
	   TNodo<E> nuevo = checkPosition(v);
		if(nuevo==root)
			throw new BoundaryViolationException("La Raiz no tiene padre");
		
		
		return nuevo.getPadre();
	}
	
	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> nuevo = checkPosition(v);
		PositionList<Position<E>> lista = new DoubleLinkedList<Position<E>>();
		for(TNodo<E> p:nuevo.getHijos())
			lista.addLast(p);
	    	
    return lista;
	}
	
	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		boolean res=false;
		if(!nodo.getHijos().isEmpty())
			res=true;
		return res;
	}
	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo= checkPosition(v);
		boolean res=false;
		if(nodo.getHijos().isEmpty()) {
			res=true;
		}
		
		return res;
	}
	
	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> nuevo = checkPosition(v);
		boolean res=false;
		if(nuevo==root)
			res=true;
		return res;
	}
	
	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(root!=null)
			throw new InvalidOperationException("Ya hay una raiz");
		root = new TNodo<E>(null,e);
		size++;
	}
	
	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if(isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> hijo = new TNodo<E>(nodo,e);
		nodo.getHijos().addFirst(hijo);
		size++;
		return hijo;
	}
	
	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if(isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> hijo= new TNodo<E>(nodo,e);
		nodo.getHijos().addLast(hijo);
		size++;
		return hijo;
	}
	
	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> nuevo=null;
		try {
					TNodo<E> padre = checkPosition(p);
		TNodo<E> hermanoD = checkPosition(rb);
		 nuevo =new TNodo<E>(padre,e);
		
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
              if(hijosPadre.isEmpty())
	              hijosPadre.addFirst(nuevo);
		boolean encontre=false;
		Position<TNodo<E>> posHijoPadre;
			posHijoPadre = hijosPadre.first();
			while(posHijoPadre!=null&&!encontre) {
				if(hermanoD!=posHijoPadre.element())
					posHijoPadre=(posHijoPadre!=hijosPadre.last())?hijosPadre.next(posHijoPadre):null;
				else {
					encontre=true;
				}	
			}
			if(!encontre)
				throw new InvalidPositionException("rb no es hijo de p");
			hijosPadre.addBefore(posHijoPadre, nuevo);
		} catch (EmptyListException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BoundaryViolationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		size++;	
		
	    	
    return nuevo;
	}
	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> nuevo=null;
		try {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermanoD = checkPosition(lb);
		nuevo =new TNodo<E>(padre,e);
		//Lista de hijos
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
		
			
		if(hijosPadre.isEmpty())
			hijosPadre.addFirst(nuevo);
		boolean encontre=false;
		Position<TNodo<E>> posHijoPadre;
			posHijoPadre = hijosPadre.first();
			while(posHijoPadre!=null&&!encontre) {
				if(hermanoD!=posHijoPadre.element())
					posHijoPadre=(posHijoPadre!=hijosPadre.last())?hijosPadre.next(posHijoPadre):null;
				else {
					encontre=true;
				}	
			}
			if(!encontre)
				throw new InvalidPositionException("rb no es hijo de p");
			
			hijosPadre.addAfter(posHijoPadre, nuevo);
			
		} catch (EmptyListException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BoundaryViolationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		size++;	
		
	    	
    return nuevo;
	}
	
	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
	if(isEmpty())
	  throw new InvalidPositionException("El arbol está vacio");
	
			
	TNodo<E> n = checkPosition(p);
	if(!n.getHijos().isEmpty())
		throw new InvalidPositionException("p no es hoja");	
	if(n==root)
		root=null;
	else {
	TNodo<E> padre = n.getPadre();
	
	PositionList<TNodo<E>> hijosPadre = padre.getHijos();
    
	boolean  encontre = false;
    Position<TNodo<E>> pos =null;
    Iterable<Position<TNodo<E>>> posiciones = hijosPadre.positions();
    Iterator<Position<TNodo<E>>> it = posiciones.iterator();
    while(it.hasNext()&&!encontre) {
    	pos = it.next();
    	if(pos.element()==n)
    		encontre=true;
    }
    if(!encontre)
    	throw new InvalidPositionException("p no esta en la lista");
    hijosPadre.remove(pos);
	}
    size--;
    	
    
	}
	
	
	
	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
     TNodo<E> padre = checkPosition(p);
     if(isEmpty())
    	  throw new InvalidPositionException("El arbol esta vacio");
	 if(padre.getHijos().isEmpty())
		   throw new InvalidPositionException("El arbol esta vacio");
      try {

    	 PositionList<TNodo<E>> hijos =padre.getHijos();
    	  TNodo<E> raiz = hijos.first().element();   
       
	if(padre==root) {
 	   if(padre.getHijos().size()==1) {
 		   raiz.setPadre(null);
 	       root=raiz;
 	   }else 
		      throw new InvalidPositionException("la raiz tiene hijos"); 
	}else {
       
    		   
       TNodo<E> abuelo = padre.getPadre();
      
       PositionList<TNodo<E>> nietos = abuelo.getHijos(); 
	   Position<TNodo<E>> pos = nietos.first();
	   
	  
	   
    	  
    
	   for(TNodo<E> lista:hijos) {
    	   lista.setPadre(abuelo);
    	   
       }
	   boolean encontre=false;
	   while(pos!=null&&!encontre) {
		   if(padre==pos.element()) {
			   encontre=true;
			   
			   
		   }
		   else {
			 pos =nietos.next(pos); 
			
		   }
	   }
	   if(!encontre)
		   throw new InvalidPositionException("Es de otro arbol");
	  
	   for(TNodo<E> l:hijos) {
		   nietos.addBefore(pos, l);
		   System.out.println(nietos.size());
		   
	   }
	   nietos.remove(pos);
	}
	} catch (EmptyListException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (BoundaryViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}       
      
  	size--;
	}
	
	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		
		if(nodo.getHijos().size()==0)
			removeExternalNode(p);
		else
			removeInternalNode(p);
		
	}

}
