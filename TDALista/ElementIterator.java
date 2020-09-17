package TDALista;
import java.util.*;



public class ElementIterator<E> implements Iterator<E> {
    protected PositionList<E> list;//Lista a iterarar.
    protected Position<E> cursor;//Posición del elemento corriente.
   
    public ElementIterator(PositionList<E> lista) throws EmptyListException {
    	list=lista;
    	if(list.isEmpty())
    		cursor=null;
    	else
    	  cursor=list.first();
    	
    }
    
	@Override
	public boolean hasNext() {
	  return cursor!=null;
	}

	
	public E next() throws NoSuchElementException{
		if(cursor==null)
		  throw new NoSuchElementException("No tiene siguiete");
		
		E toReturn =cursor.element();
		try {
			cursor = (cursor==list.last())?null:list.next(cursor);
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return toReturn;
	}

}
