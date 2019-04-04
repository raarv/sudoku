package Conjuntos;

public class MAG {

    //búsqueda en arreglo desordenado 
    public static <T> int buscaSec (T[]arre, int tot, T dato){
        int i=0;
		
        while (i<tot && !arre[i].equals(dato)) //para T siempre se compara con equals
            i++; 
        if(i==tot)
            i=-1; //significa que no está en el arreglo 
        
        return i;
    }//fin del método
    
    //búsqueda de objeto en arreglo desordenado
    public static <T> int buscaSecObj (T[]arre, int tot, T object){
        int i=0;
		
        while (i<tot && !arre[i].equals(object)) //para T siempre se compara con equals
            i++; 
        if(i==tot)
            i=-1; //significa que no está en el arreglo 
        
        return i;
    }//fin del método
    
    
    

    //alta de datos en arreglo desordenado 
    public static <T> int altaDes (T[]arre, int tot, T dato) {
        
        if(tot<arre.length) {
            arre[tot]=dato;
            tot++;
	}
        
        return tot;
    }//fin del método 

    //imprimir arreglo (ordenado o desordenado)
    public static <T> String impArre (T[]arre, int tot) {
        StringBuilder cad = new StringBuilder();
        
        for(int i=0;i<tot;i++)
            cad.append(arre[i]).append(" ");
        
        return cad.toString();
    }//fin del método
	
    //eliminación de datos desordenados
    public static <T> int eliminaDes (T[] arre, int tot, T dato) {
        int pos;
		
	pos=buscaSec(arre,tot,dato);
        if(pos>=0) { //como está desordenado se pasa dato del último a la posición donde se elimina el dato 
            tot--;
            arre[pos]=arre[tot-1];
            arre[tot-1]=null;
	}//cierre de if
        
        return tot;
    }
    
    //para arreglos en los que se conoce el orden 
    
    //búsqueda binaria ordenada
    public static <T extends Comparable <T>> int buscaBin (T[] arre, int tot, T dato) {
        int izq, der, cen;
	izq=0;
        der=tot-1;
	cen=der/2;
		
        while(izq<=der && !dato.equals(arre[cen])) {
            if(arre[cen].compareTo(dato)>0)
                der=cen-1;
            else
                izq=cen+1;
            
            cen=(izq+der)/2;
	}//cierre del while
        if(izq>der)
            cen=(izq+1)*-1;
		
        return cen;
    }//fin del método
    
    //búsqueda binaria ordenada
    public static <T extends Comparable <T>> int buscaBinObjeto (T[] arre, int tot, T object) {
        int izq, der, cen;
	izq=0;
        der=tot-1;
	cen=der/2;
		
        while(izq<=der && !object.equals(arre[cen])) {
            if(arre[cen].compareTo(object)>0)
                der=cen-1;
            else
                izq=cen+1;
            
            cen=(izq+der)/2;
	}//cierre del while
        if(izq>der)
            cen=(izq+1)*-1;
		
        return cen;
    }//fin del método
	
    //insertar datos ordenados
    public static <T extends Comparable <T>> int insertaOrden (T[] arre, int tot, T dato) {
        //comprobar que hay espacio
        if(tot<arre.length) {
            //buscar dato para ver si lo tiene el arreglo o el lugar donde debe estar
            int pos;
            pos=buscaBin(arre,tot,dato);
            if (pos<0) { //significa que no está en el arreglo 
                pos=pos*-1 -1;
                recorreDer(arre,tot,pos);
		arre[pos]=dato;
                tot++;
            }//cierre mini if
        }//cierre del if
        
        return tot;
    }//fin del método

    //método auxiliar para insertaOrden 
    public static <T> void recorreIzq (T[] arre, int tot, int pos) {
        for(int i=pos; i>tot;i++)
            arre[i]=arre[i+1];
    }//fin del método 
    
    //elimina datos ordenados 
    public static <T extends Comparable <T>> int eliminaOrden (T[] arre, int tot, T dato) {
        //busca posición del dato en el arreglo 
        int pos;
        pos=buscaBin(arre,tot,dato);
        //significa que encontró el dato en el arreglo
        if(pos>=0) {
            recorreIzq(arre,tot,pos);
            tot--;
            //poner un null para prevenir que el dato se repita 
            arre[tot]=null;
        }//cierre del if

        return tot;
    }//fin del método 

    //método auxiliar para eliminar datos de forma ordenada 
    public static <T> void recorreDer (T[] arre, int tot, int pos) {
        for(int i=tot;i>pos;i--)
            arre[i]=arre[i-1];
    }//fin del método 

    //para odenar el arreglo de menor a mayor 
    public static <T extends Comparable <T>> void ordenaSelDir (T[] arre, int tot){
        int i,j,pos;
        T menor;
		
        for(i=0;i<tot-1;i++) {
            //para establecer que este es el menor y poder comparar dentro del otro for 
            menor=arre[i];
            pos=i;
            //para recorrer arreglo y encontrar el dato más pequeño 
            for(j=i+1;j<tot;j++) {
                if(menor.compareTo(arre[j])>0) {//quiere decir que lo que tenía como menor es más grande que el nuevo dato 
                    menor=arre[j];
                    pos=j;
                }//cierre de mini if 
                arre[pos]=arre[i];
                arre[i]=menor;
            }//cierre for chico
        }//cierre for grande 
    }//fin del método
    
}
