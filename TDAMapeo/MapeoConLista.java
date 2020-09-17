package TDAMapeo;

import java.util.Iterator;


import TDAColaConPrioridad.Entrada;
import TDAColaConPrioridad.Entry;
import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;

public class MapeoConLista<K,V> implements Map<K,V> {
    protected int size;
    //deberia ser PositionList<K,V> S;
    protected DoubleLinkedList<Entrada<K,V>> S;
    
    public MapeoConLista(){
      S= new DoubleLinkedList<Entrada<K,V>>();	
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
	public V get(K key) throws InvalidKeyException {
	 if(key==null)
		 throw new InvalidKeyException("Key nula");
		for(TDALista.Position<Entrada<K,V>> pos :S.positions()){
		 if(pos.element().getKey().equals(key))
			 return pos.element().getValue();
	 }
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("key nulla");
		boolean esta=false;
		Iterable<TDALista.Position<Entrada<K, V>>> pos=S.positions();
		Iterator<TDALista.Position<Entrada<K, V>>> it = pos.iterator();
        TDALista.Position<Entrada<K,V>> p;
		V valor=null;
        while(!esta&&it.hasNext()){
			p=it.next();
			if(p.element().getKey().equals(key)){
				esta=true;
				valor= p.element().getValue();
				p.element().setValue(value);
			}
		}
        if(!esta){
        	S.addLast(new Entrada<K,V>(key,value));
        size++;
        }
		return valor;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
	 if(key==null)
		 throw new InvalidKeyException("key nula");
	Iterable<TDALista.Position<Entrada<K,V>>> pos = S.positions();
	 Iterator<TDALista.Position<Entrada<K,V>>> it = pos.iterator();
	boolean esta=false;
	V valor=null;
	TDALista.Position<Entrada<K,V>> ent;
	while(it.hasNext()&&!esta){
		ent=it.next();
		if(ent.element().getKey().equals(key)){
			esta=true;
			valor= ent.element().getValue();
			try {
				S.remove(ent);
				size--;
			} catch (InvalidPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	 return valor;
	}

	@Override
	public Iterable<K> keys() {
		TDALista.PositionList<K> lista = new DoubleLinkedList<K>();
		for(TDALista.Position<Entrada<K, V>> pos: S.positions())
			lista.addLast(pos.element().getKey());
		return lista;
	}

	@Override
	public Iterable<V> values() {
		TDALista.PositionList<V> lista = new DoubleLinkedList<V>();
		for(TDALista.Position<Entrada<K, V>> pos: S.positions())
			lista.addLast(pos.element().getValue());
		return lista;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		TDALista.PositionList<Entry<K,V>> lista = new DoubleLinkedList<Entry<K,V>>();
		for(TDALista.Position<Entrada<K, V>> pos: S.positions())
			lista.addLast(pos.element());
		return lista;	
	}

}
