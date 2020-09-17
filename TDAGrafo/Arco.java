package TDAGrafo;

import TDALista.Position;

public class Arco<V, E> implements Edge<E> {
protected E rotulo;
protected Vertice<V,E> predecesor, sucesor;
protected Position<Arco<V,E>> posicionEnAdyacentes;

public Arco(E rotulo, Vertice<V,E> pred,Vertice<V,E> suc){
	this.rotulo= rotulo;
	predecesor = pred;
	sucesor=suc;
}

@Override
public E element() {
	return rotulo;
}

public void setPred(Vertice<V,E> p){
	predecesor= p;
}

public void setSuc(Vertice<V,E> s){
	sucesor= s;
}

public void setPosAdy(Position<Arco<V,E>> ad){
	posicionEnAdyacentes= ad;
}

public Vertice<V,E> pred(){
	return predecesor;
}

public Vertice<V,E> suc(){
	return sucesor;
}

public Position<Arco<V,E>> getPosArco(){
	return posicionEnAdyacentes;
}
}


