package sudokuconnombreoriginal;

public class EmptyCollectionException extends RuntimeException {
    
    
    //constructor
    public EmptyCollectionException (){
        super ("Colección vacía");
    }
    
    //constructor
    public EmptyCollectionException (String mensaje) {
        super(mensaje);
    }
    
}
