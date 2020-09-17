package TDAGrafo;

import TDALista.DoubleLinkedList;
import TDALista.Position;
import TDALista.PositionList;

public class GrafoConMatriz<V,E> {
  protected PositionList<Vertex<V>> vertices;
  protected PositionList<Edge<E>> arcos;
  protected Edge<E>[][] matriz; 
  protected int cantidadDeVertices;
  
  @SuppressWarnings("unchecked")
public GrafoConMatriz(int n){
	  vertices = new DoubleLinkedList<Vertex<V>>();
	  arcos= new DoubleLinkedList<Edge<E>>();
	  matriz= (Edge<E>[][])new Edge[n][n];
	  cantidadDeVertices=0;
  }
  private class Vertice<V> implements Vertex<V>{
   protected V rotulo;
   protected int indiceVertice;
   protected Position<Vertex<V>> poicion;
   
   public Vertice(V rotulo){
	  indiceVertice=0;
   } 
	@Override
	public V element() {
		return rotulo;
	}
	  
  }
  
  public void insertVertex(){
	  
  }
}
