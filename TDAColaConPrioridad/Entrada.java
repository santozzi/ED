package TDAColaConPrioridad;

public class Entrada<K,V> implements Entry<K,V>{
   protected K key;
   protected V value;
  
   public Entrada(K key,V value){
	  this.key = key;
	  this.value= value;
   }
   
   @Override
	public K getKey() {
	 return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	public void setKey(K k){
		key=k;
	}
	
	public void setValue(V v){
		value=v;
	}
	public String toString(){
		  return "("+key+","+value+")";
	  }
	public boolean equals(Entrada<K,V> ent){
		return key==ent.getKey()&&value==ent.getValue();
	}
	

}
