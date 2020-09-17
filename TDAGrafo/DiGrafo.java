package TDAGrafo;

import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

public class DiGrafo<V,E> implements GraphD<V,E> {
    protected PositionList<Vertice<V,E>> nodos;
    
    public DiGrafo(){
       nodos = new DoubleLinkedList<Vertice<V,E>>(); 	
    }
	
	@Override
	public Iterable<Vertex<V>> vertices() {
	PositionList<Vertex<V>> lista = new DoubleLinkedList<Vertex<V>>();
	for(Vertex<V> vert:nodos){
		lista.addLast(vert);
	}
		return lista;
	}
	
	@Override
	public Iterable<Edge<E>> edges() {
	 PositionList<Edge<E>> lista = new DoubleLinkedList<Edge<E>>();
	    for(Vertex<V> ver : nodos){
	    	try {
				for(Edge<E> ed : succesorEdges(ver))
				  lista.addLast(ed);
			} catch (InvalidVertexException e) {
			      e.printStackTrace();
			}
	    }
		return lista;
	}
   
	//para no dirigido
	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
	return null;
	}
    
	@SuppressWarnings("unchecked")
	private Vertice<V,E> checkVertex(Vertex<V> v)throws InvalidVertexException{
		Vertice<V,E> nuevo = null;
		if(v==null){
			throw new InvalidVertexException("Vertex invalido");
		}
		try{
			nuevo= (Vertice<V,E>)v;
			
		}catch(ClassCastException e){
			throw new InvalidVertexException("Mal casteo");
		}
		return nuevo;
	}
	
	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> n = checkVertex(v);
		PositionList<Edge<E>> lista = new DoubleLinkedList<Edge<E>>();
		for(Edge<E> ed: n.getAdyacentes())
			lista.addLast(ed);
	
		return lista;

	}
   
	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		checkVertex(v);
		Arco<V,E> ar = checkEdge(e);
		Vertex<V> res=null;
		if(ar.pred()==v){
			res= ar.suc();
		}else
			res=ar.pred();
		
		return res;
	}
    
	@SuppressWarnings("unchecked")
	private Arco<V,E> checkEdge(Edge<E> e)throws InvalidEdgeException{
     if(e==null){
    	 throw new InvalidEdgeException("Edge invalido");
     }
     Arco<V,E> nuevo=null;
     try{
    	 nuevo=(Arco<V,E>)e;
    	 
     }catch(ClassCastException e1){
    	 throw new InvalidEdgeException("Cast invalido");
     }
     return nuevo;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		
		Vertex<V>[] arrayVertex = (Vertex<V>[])new Vertex[2];
		Arco<V,E> arco = checkEdge(e);
		arrayVertex[0]= arco.pred();
		arrayVertex[1]=arco.suc();
		return arrayVertex;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E> n1 = checkVertex(v);
		Vertice<V,E> n2 = checkVertex(w);
		Vertice<V,E> menor;
		Vertice<V,E> op;
		if(n1.getAdyacentes().size()<=n2.getAdyacentes().size()){
			menor=n1;
			op=n2;
		}else{
			menor=n2;
	        op=n1;	
		}
		boolean res=false;
		for(Arco<V,E> arco: menor.getAdyacentes()){
			if(arco.suc()==op||arco.pred()==op){
				res=true;
				break;
			}
		}
		
		return res;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> n = checkVertex(v);
		V res = n.element();
		n.setRotulo(x);
		return res;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> ver=new Vertice<V,E>(x);
		nodos.addLast(ver);
		return ver;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E> prev = checkVertex(v);
		Vertice<V,E> suc = checkVertex(w);
		Arco<V,E> arco = new Arco<V,E>(e,prev,suc);
		prev.getAdyacentes().addLast(arco);
		suc.getAdyacentes().addLast(arco);
		return arco;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
	    Vertice<V,E> n = checkVertex(v);
	    try {
			nodos.remove(n.getPosicionEnVertice());
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return n.element();
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		E res= arco.element();
		
		try {
			arco.pred().getAdyacentes().remove(arco.getPosArco());
		    arco.suc().getAdyacentes().remove(arco.getPosArco());
		
		} catch (InvalidPositionException e1) {
			
			e1.printStackTrace();
		}
		
		return res;
	}

}
