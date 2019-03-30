package sudokuconnombreoriginal;

import java.util.Iterator;

public interface ConjuntoADT <T> extends Iterable <T> {
    
    public Iterator <T> iterator();
    
    public boolean agrega (T elem); 
    
    public boolean contiene (T elem);
    
    public int getCardinalidad();
    
    public boolean estaVacio();
    
    public ConjuntoADT <T> unionConjuntos(ConjuntoADT <T> conjunto);
    
    public ConjuntoADT <T> interseccionConjuntos (ConjuntoADT <T> conjunto);
    
    public ConjuntoADT <T> diferenciaConjuntos (ConjuntoADT <T> conjunto);
    
    public String toString ();

}//fin de la clase
