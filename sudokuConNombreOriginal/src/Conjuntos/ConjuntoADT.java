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
public interface ConjuntoADT<T> extends Iterable<T> {
    public Iterator<T> iterator();
    public boolean agrega(T elem);
    public T quita(T elem);
    public boolean contiene(T elem);
    public int getCardinalidad();
    public boolean estaVacio();
        
}


