/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import Conjuntos.ConjuntoA;
import Conjuntos.ConjuntoADT;

/**
 *
 * @author hca
 */
public class experimento {
    
   
    private static final int MAX=9;
    private static ConjuntoADT<Integer> arreglo[] = new ConjuntoADT[MAX];
    
    private static void resolver(){
        
        for(int i=0;i<MAX;i++)
            arreglo[i] = new ConjuntoA();
        
        System.out.println(arreglo[0].agrega(1));
        System.out.println(arreglo[0].agrega(2));
        System.out.println(arreglo[0].agrega(3));
        System.out.println(arreglo[0].agrega(4));
    }
        
    
    public static void main(String[] args) {
        resolver();
        System.out.println(arreglo[0].contiene(1));
    }
}
