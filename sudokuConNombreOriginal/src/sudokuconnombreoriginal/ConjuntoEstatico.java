package proyecto_2_ed;

import java.util.Iterator;
import proyecto_1_ed.EmptyCollectionException;

public class ConjuntoEstatico {
    
    public static <T> boolean sonIguales (ConjuntoADT <T> c1, ConjuntoADT <T> c2){
        boolean resp=false;
        if (c1!=null && c2!=null){
            //obtener la cardinalidad
            int card1=c1.getCardinalidad();
            int card2=c2.getCardinalidad();
            //si la cardinalidad es la misma,
            //hay posibilidad de que sean iguales
            if (c1==c2){
                //conjunto auxiliar
                ConjuntoADT <T> aux;
                aux=c1.interseccionConjuntos(c2);
                //si la cardinalidad es la misma entonces el conjunto es igual
                if (aux.getCardinalidad()==card1)
                    resp=true;
            }//cierre del if
        }//cierre del if grande
        return resp;
    }//fin del método
    
    //sonIguales pero en recursivo
    private static <T> boolean sonIgualesRecursivo (ConjuntoADT <T> c2, Iterator <T> cont){
        if (cont.hasNext()){
            T aux=cont.next();
            if (!c2.contiene(aux))
                return false;
            return sonIgualesRecursivo(c2,cont);
        }//cierre del if
        else 
            return true;
    }//fin del método
    
    //el que ve el usuario
    public static <T> boolean sonIgualesRecursivo (ConjuntoADT <T> c1, ConjuntoADT <T> c2){
        boolean resp=false;
        if (c1!=null && c2!=null){
            if (c1.getCardinalidad()>0 && c2.getCardinalidad()>0 && c1.getCardinalidad()==c2.getCardinalidad()){
                Iterator <T> cont = c1.iterator();
                resp=sonIgualesRecursivo (c2,cont);
            }//cierre del if
        }
        else
            throw new EmptyCollectionException("\nError en los datos");
        
        return resp;     
    }//fin del método
    
}
