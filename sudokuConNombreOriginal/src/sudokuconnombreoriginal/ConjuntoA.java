package sudokuconnombreoriginal;

import java.util.Iterator;

public class ConjuntoA <T> implements ConjuntoADT <T> {
    private T[] conjunto;
    private int cardinalidad;
    private final int MAX=20;
    
    //constructor por omisión
    public ConjuntoA (){
        conjunto = (T[]) new Object[MAX];
        cardinalidad=0;
    }
    
    //constructor en que el usuario da la cardinalidad
    private ConjuntoA(int card) {
        conjunto = (T[]) new Object[card];
        cardinalidad=0;
    }
    
    public int getCardinalidad (){
        return cardinalidad;
    }
    
    public boolean estaVacio(){
        return cardinalidad==0;
    }
    
    public Iterator <T> iterator (){
        return new IteradorArreglo(cardinalidad,conjunto);
    }
    
    /* para ver si contiene al elemento
    puede ser con:
    ciclo while
    iterador
    recursividad
    */
    public boolean contiene (T elem){
        boolean resp=false;
        //para asociar el iterador al conjunto y la cardinalidad
        Iterator <T> it = iterator();
        //para buscarlo - funciona mientras el dato no sea el último de la colección
        while (it.hasNext()&& !resp)
            resp=!elem.equals(it.next());
        
        return resp;
    }//fin del método
    
    //contiene recursivo
    private boolean contieneRecursivo (T elemento,Iterator <T> it){
        if (!it.hasNext())
            return false;
        else
            if (it.next().equals(elemento))
                return true;
            else 
                return contieneRecursivo (elemento,it);
    }//fin del método
    
    //el que ve el usuario
    public boolean contieneRecursivo (T elemento){
        return contieneRecursivo (elemento,iterator());
    }
    
    //para agregar elementos sin repetir
    public boolean agrega (T elem){
        boolean resp=false;
        
        if(!contiene(elem)){
            resp=true;
            if (cardinalidad==conjunto.length)
                expande();
            conjunto[cardinalidad]=elem;
            cardinalidad++;
        }//cierre del if
        return resp;
    }//fin del método
    
    //para expandir el arreglo - auxiliar
    private void expande (){
        T[] nuevo = (T[])new Object [conjunto.length*2];
        for (int i=0;i<cardinalidad;i++)
            nuevo[i]=conjunto[i];
        conjunto = nuevo;
    }//fin del método
    
    /*para quitar un elemento
    no se puede hacer con el iterador porque se requiere 
    accesar a la estructura interna 
    */ 
    public T quita (T elem){
        T resp;
        int pos=buscaPosicion(elem);
        //asegurar que lo encuentra
        if (pos>=0){
            resp=conjunto[pos];
            conjunto[pos]=conjunto[cardinalidad-1];
            conjunto[cardinalidad-1]=null;
            cardinalidad--;
        }//cierre del if
        else
            resp=null;
        
        return resp;
    }//fin del método
    
    //para regresar la posición - auxiliar
    private int buscaPosicion (T elem){
        int i=0;
        while (i<cardinalidad && !conjunto[i].equals(elem))
            i++;
        if (i==cardinalidad)
            i=-1;
        
        return i;
    }//fin del método 
    
    //método para realizar la operación unión 
    public ConjuntoADT <T> unionConjuntos (ConjuntoADT <T> conjunto2){
        ConjuntoA <T> resp = new ConjuntoA(cardinalidad+conjunto2.getCardinalidad());
        
        if (conjunto!=null){
            if (cardinalidad>conjunto2.getCardinalidad()){
                for (int i=0;i<cardinalidad;i++){
                    resp.conjunto[i]=conjunto[i];
                }//cierre del for
                resp.cardinalidad=cardinalidad;
                agregaConjunto(conjunto2,resp);
            }//cierre del if
            else {
                Iterator <T> it = conjunto2.iterator();
                int i=0;
                while (it.hasNext()){
                    resp.conjunto[i]=it.next();
                    i++;
                }//cierre del while
                resp.cardinalidad=conjunto2.getCardinalidad();
                agregaConjunto(this,resp);
            }//cierre del else
        }//cierre del if
        else
            resp=null;
        
        return resp;
    }//fin del método
    
    //auxiliar de union
    private void agregaConjunto (ConjuntoADT <T> fuente, ConjuntoADT <T> destino){
        Iterator <T> it = fuente.iterator();
        while (it.hasNext())
            destino.agrega(it.next());
    }//fin del método
    
    /*otra manera de hacer union
    public ConjuntoADT <T> union (ConjuntoADT <T> conjunto){
        if (conjunto!=null){
            ConjuntoA <T> resp = new ConjuntoA();
            
            Iterator <T> it1=iterator();
            Iterator <T> it2=conjunto.iterator();
    
            while (it1.hasNext())
                resp.agrega(it.next());
            while (it2.hasNext())
                resp.agrega(it2.next());
        }//cierre del if
    
        return resp;
    }//fin del método
    */
 
    //para realizar la operación interseccion
    public ConjuntoADT <T> interseccionConjuntos (ConjuntoADT <T> conjunto2){ 
        int min;
        if (cardinalidad>conjunto2.getCardinalidad())
            min=conjunto2.getCardinalidad();
        else
            min=cardinalidad;
        
        Iterator <T> it=conjunto2.iterator();
        ConjuntoA <T> resp=new ConjuntoA <T>(min);
        
        if (conjunto!=null){
            T aux;
            int i=0;
            
            while(it.hasNext()){
                aux=it.next();
                if(contiene(aux)){
                    resp.conjunto[i]=aux;
                    i++;
                }//cierre del if
            }//cierre del while
            resp.cardinalidad=i;
        }//Cierre del if
        return resp;
    }//fin del método
        
    public ConjuntoADT <T> diferenciaConjuntos (ConjuntoADT <T> conjunto2){
        ConjuntoA <T> diferencia = new ConjuntoA(cardinalidad+conjunto2.getCardinalidad());
        
        if (conjunto!=null){
            Iterator <T> it=iterator();
            T aux;
            int j=0;
            int cont=0;
            int n=conjunto2.getCardinalidad();
            
            while (it.hasNext() && cont<n){
                aux=it.next();
                if (!conjunto2.contiene(aux)){
                    diferencia.conjunto[j]=aux;
                    j++;
                }//cierre del if
                else 
                    cont++;
            }//cierre del while
            diferencia.cardinalidad=j;
        }//cierre del if
        else 
            diferencia=null;
        
        return diferencia;
    }//fin del método
    
    //para imprimir los arreglos
    public String toString () {
        StringBuilder resp = new StringBuilder();
        
        for (int i=0;i<cardinalidad;i++)
            resp.append(conjunto[i]).append(" ");
        
        return resp.toString();
    }//fin del método
    
    
//    public static <T> String impArre (T[]arre, int tot) {
//        StringBuilder cad = new StringBuilder();
//        
//        for(int i=0;i<tot;i++)
//            cad.append(arre[i]).append(" ");
//        
//        return cad.toString();
//    }//fin del método
    

}//fin de la clase
