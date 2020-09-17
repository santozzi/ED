package TDAMapeo;

@SuppressWarnings("rawtypes")
public class Pixel implements Comparable{
  protected int rojo;
  protected int verde;
  protected int azul;
  public Pixel(int r, int v,int a){
	  rojo=r;
	  verde=v;
	  azul=a;
  }
  

public int getRojo() {
	return rojo;
}
public void setRojo(int rojo) {
	this.rojo = rojo;
}
public int getVerde() {
	return verde;
}
public void setVerde(int verde) {
	this.verde = verde;
}
public int getAzul() {
	return azul;
}
public void setAzul(int azul) {
	this.azul = azul;
}

@Override
public int compareTo(Object arg) {
	int res;
	Pixel p= (Pixel)arg;
	float prom1= (rojo+verde+azul)/3;
	float prom2= (p.getRojo()+((Pixel) p).getAzul()+p.getVerde())/3;
	if(prom1<prom2)
		res=-1;
	else if(prom1==prom2)
		res=0;
	else
		res=1;
	return res;
}
  
  
}
