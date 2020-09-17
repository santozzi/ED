package TDAMapeo;



import TDAColaConPrioridad.Entrada;
import TDAColaConPrioridad.Entry;
import TDALista.DoubleLinkedList;


public class MapeoConHashCerrado<K,V> implements Map<K,V> {
    protected Entry<K,V> DISPONIBLE= new Entrada<K,V>(null,null);
	protected int size;
    protected Entry<K,V>[] contenedor;
    protected int capacidad;

	
    @SuppressWarnings("unchecked")
	public MapeoConHashCerrado(int cap){
    	contenedor = (Entry<K,V>[]) new Entry[cap];
    	capacidad= cap;
    	size=0;
  
    }
    public MapeoConHashCerrado(){
    this(50001);	
    }
    
    @Override
	public int size() {
	 return size;
	}
    
    private int sigPrimo(int num){
 	  System.out.println("sigPrimo");
    	boolean esPrim=false;
 	  if(num!=2){
 		  
 	   while(!esPrim){
 		  esPrim=esPrimo(num);
 		  num++;
 	   }}else
 		   num++;
 	   return num;
 }

    private boolean esPrimo(int num){
    	 System.out.println("esPrimo");
    	boolean primo=false;
 	  
 	  for(int i=2;i<num&&!primo;i++){
 		  primo=!(num%i!=0);
 	  }
 	  return primo; 
 }
   
	@Override
	public boolean isEmpty() {
	return size==0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
	 if(key==null)
		 throw new InvalidKeyException("Key nula");
	 
	 int fun = hasValue(key);

     boolean esta=false;
     boolean noEsta=false;
     V res=null;
     
     for(int i=0; i<capacidad&&!esta&&!noEsta;i++){
    	 System.out.println(key+" "+i);
    	if(contenedor[fun]==null){
    		noEsta=true;
    	}else if(contenedor[fun]==DISPONIBLE){
    		fun=(fun+1)%capacidad;
    	}
    		else{
    		if(contenedor[fun].getKey().equals(key)){
    			esta=true;
    		    res=contenedor[fun].getValue();
    	    }else{
    	    	fun=(fun+1)%capacidad;
    	    }
    	}
    	 
     }
	 return res;
	 }
	
	public boolean buscarKey(K key) throws InvalidKeyException {
		 if(key==null)
			 throw new InvalidKeyException("Key nula");
		 
		 int fun = hasValue(key);
	     boolean esta=false;
	     boolean noEsta=false;
	   
	     for(int i=0; i<capacidad&&!esta&&!noEsta;i++){
	    	
	    	 if(contenedor[fun]==null)
	    	    noEsta=true;
	    	else if(contenedor[fun]==DISPONIBLE)
	    		fun=(fun+1)%capacidad;
	    	else{
	    	  esta=contenedor[fun].getKey().equals(key);
	    	  fun=(fun+1)%capacidad;
	    	  
	    	}
	      	
	    	
	    		
	    	}
	     //System.out.println("Esta "+esta+" No esta "+noEsta+" "+fun);
	   
		 return esta;
		 }
	
    public int hasValue(K key){
    	return Math.abs(key.hashCode())%capacidad;
    }
    
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("key nulla");
		
		V valor=null;
		int fun = hasValue(key);
		if(size==capacidad-1){
			contenedor= agrandar();
			
		}
			
		if(buscarKey(key)){
			
		  boolean acaEsta=false;
		 
		  while(!acaEsta){
			  
				if(contenedor[fun].getKey().equals(key)){
					acaEsta=true;
					valor=contenedor[fun].getValue();
					((Entrada<K,V>)contenedor[fun]).setValue(value);
					
				}else{
					fun=(fun+1)%capacidad;
				 }
			}
		}else{
			
		  boolean disponible=false;
		  while(!disponible){
			//  System.out.println("put");
			if(contenedor[fun]==null||contenedor[fun]==DISPONIBLE){
			  contenedor[fun]=new Entrada<K,V>(key,value);
			  disponible=true;
			 
			 
			}else
				 fun=(fun+1)%capacidad;
		  }
			size++;
		}
	
		
		
		return valor;
	}
	
@SuppressWarnings("unchecked")
	private Entry<K,V>[] agrandar(){
		int tam= sigPrimo(capacidad*2);
	
	
		MapeoConHashCerrado<K,V> map = new MapeoConHashCerrado<K,V>(tam);
		
		Entry<K,V>[] aux = (Entrada<K,V>[])new Entry[tam];
        for(Entry<K,V> ent : entries()){
        	try {
				map.put(ent.getKey(), ent.getValue());
			} catch (InvalidKeyException e) {
		
				e.printStackTrace();
			}
        }int i=0;
        for(Entry<K,V> ent2:map.entries()){
        	aux[i++]=ent2; 
        }
        	
		//	for(int i=0;i<capacidad;i++){
		//	fun=Math.abs((contenedor[i].getKey()).hashCode())%tam;
		//	aux[fun]=new Entrada<K,V>(contenedor[i].getKey(),contenedor[i].getValue());
			
	//	}
		capacidad=tam;
		System.out.println(tam);
		return aux;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
	 if(key==null)
		 throw new InvalidKeyException("key nula");
	 
	 V valor=null;
	 
	 boolean encontrada=false;
	 
	 if(buscarKey(key)){
		
		 int fun= hasValue(key);
		 while(!encontrada){
			System.out.println("remove");
			 if(contenedor[fun].getKey().equals(key)){
				 encontrada=true;
				 valor= contenedor[fun].getValue();
				 contenedor[fun]=DISPONIBLE;
				 size--;
			
			 }else{
				 fun=(fun+1)%capacidad;
				 
			
			 }
		 }
	 }
	 return valor;
	}

	@Override
	public Iterable<K> keys() {
		System.out.println("key");
		DoubleLinkedList<K> lista= new DoubleLinkedList<K>();
		for(int i=0;i<capacidad;i++){
		 if(contenedor[i]!=null&&contenedor[i]!=DISPONIBLE)
			 lista.addLast(contenedor[i].getKey());
			
		}
		return lista;
	}

	@Override
	public Iterable<V> values() {
		System.out.println("values");
		DoubleLinkedList<V> lista= new DoubleLinkedList<V>();
		for(int i=0;i<capacidad;i++){
		 if(contenedor[i]!=null&&contenedor[i]!=DISPONIBLE)
			 lista.addLast(contenedor[i].getValue());
			
		}
		return lista;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		System.out.println("entries");
		TDALista.PositionList<Entry<K,V>> lista= new DoubleLinkedList<Entry<K,V>>();
		for(int i=0;i<capacidad;i++){
		 if(contenedor[i]!=null&&contenedor[i]!=DISPONIBLE)
			 lista.addLast(contenedor[i]);
			
		}
		return lista;	
	}

}
