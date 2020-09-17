package TDAMapeo;

import TDAColaConPrioridad.Entrada;
import TDAColaConPrioridad.Entry;

class MapConArreglo<K,V> implements Map<K,V> {
    protected int size;
    protected K key;
    protected V value;
    protected Entry<K,V>[] arreglo;
	@SuppressWarnings("unchecked")
	public MapConArreglo(int max){
		
		arreglo = (Entrada<K,V>[])new Entrada[max];
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
		if(key==null){
			throw new InvalidKeyException("key invalido");
		}
		boolean esta= false;
		V res=null;
		for(int i=0;i<size&&!esta;i++){
			if(key.equals(arreglo[i].getKey())){
				res=arreglo[i].getValue();
				esta=true;
			}
				
		}
		return res;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Key invalido");
		V res=null;
		boolean esta=false;
		for(int i=0;i<size&&!esta;i++){
			
		}
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
