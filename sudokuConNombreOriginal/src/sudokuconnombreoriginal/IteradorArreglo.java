package sudokuconnombreoriginal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorArreglo <T> implements Iterator <T> {
    private T[] coleccion;
    private int total;
    private int actual;
    
    //constructor
    public IteradorArreglo (int tot, T[] arre){
        coleccion=arre;
        total=tot;
        actual=0;
    }

    public boolean hasNext() {
        return actual<total;
    }

    /*
    da acceso al que está apuntando y se va al siguiente
    */
    public T next() {
        if (hasNext()){
            T resp;
            resp=coleccion[actual];
            actual++;
            return resp;
        }//cierre del if
        else
            throw new NoSuchElementException();
    }//fin del método

    public void remove() {
        throw new UnsupportedOperationException("Operación no válida");
    }
    
}//fin de clase
