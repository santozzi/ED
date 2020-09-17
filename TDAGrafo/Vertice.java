package TDAGrafo;

import TDALista.DoubleLinkedList;
import TDALista.Position;
import TDALista.PositionList;

public class Vertice<V,E> implements Vertex<V> {
   protected V rotulo;
   protected Position<Vertice<V,E>> posicionEnListaVertices;
   protected PositionList<Arco<V,E>> adyacentes;
	
   public Vertice(V rotulo){
	this.rotulo=rotulo;
	adyacentes = new DoubleLinkedList<Arco<V,E>>();
	
}
	
	@Override
   public V element() {
	return rotulo;
	}

   public void setRotulo(V rot){
	   rotulo=rot;
   }
   
   public PositionList<Arco<V,E>> getAdyacentes(){
	return adyacentes;
   }

   public void setPosicionEnVerticies(Position<Vertice<V,E>> nodo){
	   posicionEnListaVertices=nodo;
   }
   
   public Position<Vertice<V,E>> getPosicionEnVertice(){
	   return posicionEnListaVertices;
   }
   
   
}
