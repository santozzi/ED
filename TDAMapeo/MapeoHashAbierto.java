package TDAMapeo;

import java.util.Iterator;

import TDAColaConPrioridad.Entrada;
import TDAColaConPrioridad.Entry;
import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

    public class MapeoHashAbierto<K,V> implements Map<K,V> {
    protected static final int INICIO = 5;
    protected int size;
    protected PositionList<Entry<K,V>>[] contenedor;
    protected int capacidad;

@SuppressWarnings("unchecked")
    public MapeoHashAbierto(int cap){
	  contenedor = (DoubleLinkedList<Entry<K,V>>[])new DoubleLinkedList[cap];
	  size=0;
	  capacidad=cap;
}

    public MapeoHashAbierto(){
	this(INICIO);
}

	@Override
	public int size() {
	return size;
	}

	@Override
	public boolean isEmpty() {
	return size==0;
	}

	public int hashValue(K key){
		return Math.abs(key.hashCode())%capacidad;
	}
	
	@Override
	public V get(K key) throws InvalidKeyException {
	if(key==null)
		throw new InvalidKeyException("key invalido");
	int fun = hashValue(key);
	V valor= null;
	boolean esta=false;
	Entry<K,V> ent;
	PositionList<Entry<K,V>> lista;
	if(contenedor[fun]!=null){
		lista=contenedor[fun];
		Iterator<Entry<K,V>> it = lista.iterator();
		while(it.hasNext()&&!esta){
		   ent=it.next();
		   if(ent.getKey().equals(key)){
			   esta=true;
			   valor=ent.getValue();
		   }
		}
	}
	
	
	return valor;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("key invalida");
		PositionList<Entry<K,V>> l1;
		int fun = hashValue(key);
		Entry<K,V> ent;
		V valor=null;
		if(contenedor[fun]==null){
			l1= new DoubleLinkedList<Entry<K,V>>();
			l1.addLast(new Entrada<K,V>(key,value));
			contenedor[fun]=l1;
			size++;
		}else{
			l1=contenedor[fun];
			Iterator<Entry<K,V>> it = l1.iterator();
			boolean esta=false;
			while(it.hasNext()&&!esta){
			 ent=it.next();
			 if(ent.getKey().equals(key)){
				 esta=true;
				 valor=ent.getValue();
				 ((Entrada<K,V>)ent).setValue(value);
			 }
			}
			if(!esta){
				contenedor[fun].addLast(new Entrada<K,V>(key,value));
				size++;
			}
		}
		
		return valor;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
	if(key==null)
		throw new InvalidKeyException("key invalida");
	int fun=hashValue(key);
	V valor=null;
	
	Iterable<TDALista.Position<Entry<K,V>>> iterador; 
	Iterator<TDALista.Position<Entry<K,V>>> l1;

	if(contenedor[fun]!=null){
	  iterador=contenedor[fun].positions();
	  boolean esta= false;
	  l1=iterador.iterator();
	  while(l1.hasNext()&&!esta){
		  TDALista.Position<Entry<K,V>>ent= l1.next();
		  if(ent.element().getKey().equals(key)){
			  try {
				valor= contenedor[fun].remove(ent).getValue();
				size--;
			} catch (InvalidPositionException e) {
				
				e.printStackTrace();
			}
			  esta=true;
			
			 
			  
		  }
	  }
	}
		return valor;
	}

	@Override
	public Iterable<K> keys() {
	TDALista.PositionList<K> lista = new DoubleLinkedList<K>();
	for(int i=0;i<capacidad;i++){
		if(contenedor[i]!=null){
			
			for(TDALista.Position<Entry<K,V>> clave: contenedor[i].positions())
				lista.addLast(clave.element().getKey());
				   }}	
	return lista;
	}

	@Override
	public Iterable<V> values() {
		TDALista.PositionList<V> lista = new DoubleLinkedList<V>();
		for(int i=0;i<capacidad;i++){
			if(contenedor[i]!=null){
				
				for(TDALista.Position<Entry<K,V>> clave: contenedor[i].positions())
					lista.addLast(clave.element().getValue());
					   }}	
		return lista;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		TDALista.PositionList<Entry<K,V>> lista =new DoubleLinkedList<Entry<K,V>>();
		Iterator<Entry<K,V>> it;
		for(int i=0;i<capacidad;i++){
			if(contenedor[i]!=null){
				it=contenedor[i].iterator();
				while(it.hasNext()){
			      lista.addLast(it.next());
				}
					
			}
		}
		return lista;
	}

}
