/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conjuntos;

import java.util.Iterator;



/**
 *
 * @author hca
 */
public class ConjuntoA<T> implements ConjuntoADT<T> {
    private T[] conjunto;
    private int cardinalidad;
    private final int MAX=20;
    
    public ConjuntoA(){
        conjunto=(T[]) new Object[MAX];
        cardinalidad=0;
        
    }
    public ConjuntoA(int tam){
        conjunto=(T[]) new Object[tam];
        cardinalidad=0;
        
    }
    
    public int getCardinalidad(){
        return cardinalidad;
        
    }
    
    public boolean estaVacio(){
        return cardinalidad==0;
    }
    
//    public boolean contiene(T elem){
//        boolean resp;
//        resp=false;
//        
//        Iterator<T>it=iterator();
//        
//        while(it.hasNext() && !resp)
//            
//            resp=elem.equals(it.hasNext());
//            
//        return it.hasNext();
//        
//        
//        
//    }
    
    public Iterator<T> iterator(){
        return new IteradorArreglo(cardinalidad, conjunto);
        
    }
    
    public boolean contiene(T elem){
        return contiene(elem, iterator());
        
    }
    
    private boolean contiene(T elem, Iterator<T> it){
        if(!it.hasNext())
            return false;
        else
            if(it.next().equals(elem))
                return true;
            else
                return contiene(elem, it);
    }
    
    public boolean agrega(T elem){
        boolean resp=false;
        
        if(!contiene(elem)){
            resp = true;
            if(cardinalidad==conjunto.length)
                expande();
            conjunto[cardinalidad]= elem;
            cardinalidad++;
            
        }
        
        return resp;
    }
    
    private void expande(){
        T[] nuevo = (T[]) new Object[conjunto.length*2];
        
        for(int i = 0; i< cardinalidad; i++)
            nuevo[i] = conjunto[i];
        
        conjunto = nuevo;
    }
    
    public T quita(T elem){
       int pos;
       T res;
       
       pos=buscaPosic(elem);
       if(pos>=0){
           res = conjunto[pos];
           conjunto[pos] = conjunto[cardinalidad-1];
           conjunto[cardinalidad-1]=null;
           cardinalidad--;
                   
       }else
           res = null;
       
       return res;
    }
    
    private int buscaPosic(T elem){
        int i;
        i=0;
        
        while(i<cardinalidad && !conjunto[i].equals(elem))
            i++;
        if(i==cardinalidad)
            i=-1;
        
        return i;
    }
    
    public ConjuntoADT<T> union(ConjuntoADT<T> conj){
        ConjuntoA<T> res = new ConjuntoA(cardinalidad+conj.getCardinalidad());
        
        if(conj!=null){
            if(cardinalidad>conj.getCardinalidad()){
                for(int i=0; i<cardinalidad; i++){
                    res.conjunto[i]=conjunto[i];
                }
                res.cardinalidad=cardinalidad;
                agregaConj(conj, res);

            }else{
                Iterator<T> it = conj.iterator();
                int i=0;
                while(it.hasNext()){
                    res.conjunto[i]=it.next();
                    i++;
                }
                
                res.cardinalidad=conj.getCardinalidad();
                agregaConj(this,res);

            }
        }
        
        return res;
            
    }
    
    private void agregaConj(ConjuntoADT<T> fuente, ConjuntoADT<T> destino){
        Iterator<T> it = fuente.iterator();
        while(it.hasNext())
            destino.agrega(it.next());
    }
    
   
    public ConjuntoADT <T> interseccion (ConjuntoADT <T> conjunto2){ 
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
        
    public ConjuntoADT <T> diferencia (ConjuntoADT <T> conjunto2){
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
            resp.append(conjunto[i]);
        
        return resp.toString();
    }//fin del método
     
    /*
    public static <T> String impArre (T[]arre, int tot) {
        StringBuilder cad = new StringBuilder();
        
        for(int i=0;i<tot;i++)
            cad.append(arre[i]).append(" ");
        
        return cad.toString();
    }//fin del método
    */

    
    
    
}//fin de la clase
     

