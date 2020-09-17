package TDAColaConPrioridad;
import java.util.Comparator;

public class HeapPQueue<K,V> implements PriorityQueue<K,V> {
	protected Entrada<K,V>[] elems;
	protected Comparator<K> comp;
	protected int size;
	
	
	


	@SuppressWarnings({ "unchecked" })
	public HeapPQueue(int maxElems,Comparator<K> comp){
		elems = (Entrada<K,V>[]) new Entrada[maxElems];
		this.comp=comp;
		size=0;
	}
	public HeapPQueue(Comparator<K> comp){
		this(2000,comp);
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
	public Entry<K, V> min() throws EmptyPriorityQueueException {
      if(isEmpty())
    	  throw new EmptyPriorityQueueException("Cola vacia");
		
      return elems[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("key invalida");
		
		Entrada<K,V> entrada = new Entrada<K,V>(key,value);
		elems[size+1]=entrada;
		size++;
		//Burbojeo para arriba
		int i= size;
		boolean seguir =true;
		
		while(i>1&&seguir){
			Entrada<K,V>elemActual = elems[i];
			Entrada<K,V>elemPadre=elems[i/2];
		
			if(comp.compare(elemActual.getKey(),elemPadre.getKey())<0){
				Entrada<K,V> aux=elems[i];
				elems[i]=elems[i/2];
				elems[i/2]=aux;
				i/=2;
			}else{
			// Si no puede seguir es que esta ordenada
			seguir=false;	
				
			}
			
		}
		return entrada;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> entrada = min();
		
		if(size==1){
			elems[1]=null;
			size=0;
			return entrada;
		}else{
			elems[1]=elems[size];
			elems[size]=null;
			size--;
			int i =1;
			boolean seguir=true;
			while(seguir){
				int hi = i*2;
				int hd=i*2+1;
				
				boolean tieneHijoIzquierdo= hi <=size();
				boolean tieneHijoDerecho=hd<=size();
				
				if(!tieneHijoIzquierdo)
					seguir=false;
				else{
					int m;
					if(tieneHijoDerecho){


						if(comp.compare(elems[hi].getKey(), elems[hd].getKey())<0)
							m=hi;
						else
							m=hd;
					}else 
						m=hi;
				
				if(comp.compare(elems[i].getKey(), elems[m].getKey())>0){
					Entrada<K,V> aux = elems[i];
					
					elems[i]=elems[m];
					elems[m]= aux;
					i=m;
				}else
					seguir=false;
			}}
		}

		return entrada;
	}

}
