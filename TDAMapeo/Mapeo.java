package TDAMapeo;

import TDAColaConPrioridad.Entry;

public class Mapeo<K,V> implements Map<K,V> {
   protected K key;
   protected V value;
   protected int size;
	
   public Mapeo(K key,V value){
	   this.key= key;
	   this.value=value;
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
	if (key==null)
		throw new InvalidKeyException("key invalida");
	
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

}
