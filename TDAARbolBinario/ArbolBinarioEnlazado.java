package TDAARbolBinario;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.PositionList;


public class ArbolBinarioEnlazado<E> implements BinaryTree<E> {
   
	protected BTPosition<E> raiz;
    protected int size;
    
    public ArbolBinarioEnlazado(){
	 raiz= null;
	 size=0;
   }
   
	private BTPosition<E> checkPosition(Position<E> p)throws InvalidPositionException{
		if(p==null)
			throw new InvalidPositionException("Posición invalida");
		BTPosition<E> nuevo = null;
		try{
		  	nuevo= (BTPosition<E>) p;
		}catch(ClassCastException e){
			throw new InvalidPositionException(e.getMessage());
		}
		return nuevo;
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
	public Iterator<E> iterator() {
	Iterable<Position<E>> posicion = positions();
	PositionList<E> elem = new DoubleLinkedList<E>();
	for(Position<E> pos : posicion){
		elem.addLast(pos.element());
	}
		return elem.iterator();
	}
	
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> posicion = new DoubleLinkedList<Position<E>>();
		if(size!=0)
			try {
				preorderPositions(root(),posicion);
			} catch (EmptyTreeException | InvalidPositionException | BoundaryViolationException e) {
				
				e.printStackTrace();
			}
		return posicion;
	}
	
	private void preorderPositions(Position<E> v,PositionList<Position<E>> pos) throws InvalidPositionException, BoundaryViolationException{
	  	pos.addLast(v);
	  	if(hasLeft(v))
	  		preorderPositions(left(v),pos);
	  	if(hasRight(v))
	  		preorderPositions(right(v),pos);
	  	
	}
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTPosition<E> n = checkPosition(v);
		E aux = n.element();
		n.setElement(e);
		return aux;
	}
	
	@Override
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException("El Arbol esta vacio");
		return raiz;
	}
	
	@Override
	public Position<E> parent(Position<E> v)
			throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> n = checkPosition(v);
		if(n==raiz)
			throw new BoundaryViolationException("La raiz no tiene padre");
		
		return n.getParent();
	}
	
	@Override
	public Iterable<Position<E>> children(Position<E> v)
			throws InvalidPositionException {
		BTPosition<E> n=checkPosition(v);
		PositionList<Position<E>> hijos = new DoubleLinkedList<Position<E>>();
		if(hasLeft(v))
			hijos.addLast(n.getLeft());
		if(hasRight(v))
			hijos.addLast(n.getRight());
		return hijos;
	}
	
	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
	 BTPosition<E> n = checkPosition(v);
	 
		return !(n.getLeft()==null&&n.getRight()==null);
	}
	
	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		
		return !isInternal(v);
	}
	
	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTPosition<E> n = checkPosition(v);
		return n==raiz;
	}
	
	@Override
	public Position<E> left(Position<E> v)
			throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> n = checkPosition(v);
		if(n.getLeft()==null)
			throw new BoundaryViolationException("No hay left");
		return n.getLeft();
	}
	
	@Override
	
    public Position<E> right(Position<E> v)
			throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> n= checkPosition(v);
		if(n.getRight()==null)
			throw new BoundaryViolationException("No hay Right");
		return n.getRight();
	}
	
	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTPosition<E> n = checkPosition(v);
		
		return n.getLeft()!=null;
	}
	
	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPosition<E> n = checkPosition(v);
		return n.getRight()!=null;
	}
	
	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(!isEmpty())
			throw new InvalidOperationException("Ya hay una raiz");
		raiz = new BTNode<E>(r);
		size++;
		return raiz;
	}
	
	@Override
	public Position<E> addLeft(Position<E> v, E r)
			throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> n= checkPosition(v);
		if(n.getLeft()!=null)
			throw new InvalidOperationException("Ya existe un Left");
		BTPosition<E> izq = new BTNode<E>(r);
		n.setLeft(izq);
		izq.setParent(n);
		size++;
		return izq; 
	}
	
	@Override
	
	public Position<E> addRight(Position<E> v, E r)
			throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> n= checkPosition(v);
		if(n.getRight()!=null)
			throw new InvalidOperationException("Ya existe un Left");
		BTPosition<E> der =new BTNode<E>(r);
		der.setParent(n);
		n.setRight(der);
		
		
		size++;
		return der; 
	}
	
	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> n = checkPosition(v);
		if(n.getLeft()!=null&&n.getRight()!=null)
			throw new InvalidOperationException("La posicion tiene dos hijos");
		E res = n.element();
		BTPosition<E> hijo = null;
		BTPosition<E> izq = n.getLeft();
		BTPosition<E> der = n.getRight();
		BTPosition<E> abuelo = n.getParent();
		if(izq!=null)
			hijo=izq;
		else
			hijo=der;
		
		if(n==raiz){
			raiz=hijo;
			raiz.setParent(null);
		}else{
		   if(hijo==null){
		    	if(abuelo.getLeft()==n)
			    	abuelo.setLeft(null);
		    	 else
				abuelo.setRight(null);
		   }else{
			   hijo.setParent(abuelo);
			   if(n.getLeft()==hijo)
				   n.setLeft(null);
			   else
				   n.setRight(null);
		      if(abuelo.getLeft()==n)
		    	  abuelo.setLeft(hijo);
		      else
		    	  abuelo.setRight(hijo);
		   }
		   
		   
			
		   
		}
     size--;
				
		return res;
	}
	
	@Override
	public void Attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2)
			throws InvalidPositionException {
	BTPosition<E> n = checkPosition(r);
	if(isInternal(r))
		throw new InvalidPositionException("No puede ser un nodo interno");
	try {
	if(!T1.isEmpty()){
		BTPosition<E> v;
		
			v = checkPosition(T1.root());
		
		n.setLeft(v);
		v.setParent(n);
	}
	if(!T2.isEmpty()){
		BTPosition<E> d= checkPosition(T2.root());
		n.setRight(d);
		d.setParent(n);
		
	}
	} catch (EmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}

}
