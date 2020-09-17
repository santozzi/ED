package TDADiccionario;

import java.util.Iterator;

import TDAColaConPrioridad.Entrada;
import TDAColaConPrioridad.Entry;
import TDAColaConPrioridad.InvalidKeyException;
import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

public class DiccionarioConLista<K,V> implements Dictionary<K,V> {
    protected int size;
//deberia decir PositionList<E> D;
    protected DoubleLinkedList<Entry<K,V>> D;
    
    public DiccionarioConLista(){
    	D = new DoubleLinkedList<Entry<K,V>>();
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
	 if(key==null)
		 throw new InvalidKeyException("key no valida");
	    Iterator<Entry<K,V>> it= D.iterator();
		  boolean esta=false;
	     Entry<K,V> ent=null;
	     while(!esta&&it.hasNext()){
	    	ent=it.next();
	    	esta=ent.getKey().equals(key);
	     }
	    
		return ent; 
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("key invalida");
		TDALista.PositionList<Entry<K,V>> lista = new DoubleLinkedList<Entry<K,V>>();
		for(TDALista.Position<Entry<K,V>> pos : D.positions()){
			if(pos.element().getKey().equals(key)){
				lista.addLast(pos.element());
			}
		}
			
		return lista;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("key invalida");
		Entry<K,V> entrada = new Entrada<K,V>(key, value);
		D.addLast(entrada); 
		size++;
		return entrada;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
	  Entry<K,V> entrada=null;
		for(TDALista.Position<Entry<K,V>> pos: D.positions()){
		  entrada= pos.element();
			if(entrada==e){
			  try {
				entrada=D.remove(pos);
				size--;
			} catch (InvalidPositionException e1) {
				
				e1.printStackTrace();
			}
		  }}
		if(entrada==null)
			throw new InvalidEntryException("No existe la entrada");
		
		return entrada;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		return D;
	}

}
