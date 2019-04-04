package Metodos;

import Conjuntos.ConjuntoA;
import Conjuntos.ConjuntoADT;
import Conjuntos.EmptyCollectionException;

public class Backend {

    private static int[][] sudoku;
    private static final int VACIO=0;
    private static final int MAX=9;
    //conjuntos de los renglones
    private static ConjuntoADT <Integer> renglones[]= new ConjuntoADT[MAX];
    //conjuntos de las columnas
    private static ConjuntoADT <Integer> columnas[]= new ConjuntoADT[MAX];
    //conjuntos de las áreas
    private static ConjuntoADT <Integer> areas[]= new ConjuntoADT[MAX];
    
    /**
     *<pre>
     * Método determinaArea
     * 
     * Método auxiliar que recibe el número de un renglón y de una columna, es decir
     * la coordenada de una celda, y que utiliza los números recibidos para
     * determinar en qué área se encuentra la celda en la que se está operando.
     * @param ren Número del renglón del que se desea determinar
     *              el área. 
     * @param col Número de la columna de la que se desea determinar
     *              el área. 
     * @return int: el rengón y la columna ingresada fueron analizados
     *                  de manera exitosa 
     *         
     * </pre>
     */
    public static int determinaArea (int ren, int col){
        int area=-1;
        /*para verificar que los valores del renglón y la
        columna recibidos sean válidos
        */
        if (ren>=0 && ren<=9 && col>=0 && col<=9){
            //para verificar si se encuentra en la primeras 3 áreas
            if (0<=ren && ren<=2){
                if (col<3){
                    //área 1
                    area=0;
                }//cierre del primer if pequeño
                else if (col<6){
                    //área 2
                    area=1;
                }else
                    //área 3
                    area=2;
            }//cirre de if
            //para verificar si se encuentra en las áreas 4 a 6
            else if (3<=ren && ren<=5){
                if (col<3){
                    //área 4
                    area=3;
                }//cierre del primer if pequeño
                else if (col<6){
                    //área 5
                    area=4;
                }else
                    //área 6
                    area=5;
            }//cierre del segundo if
            //para verificar si se encuentra en las áreas 5 a 9
            else { //significa que se encuentra en las últimas regiones
                if (col<3){
                    //área 7
                    area=6;
                }//cierre del primer if pequeño
                else if (col<6){
                    //área 8
                    area=7;
                }else
                    //área 9
                    area=8;
            }//cierre de else (de áreas 5 a 9) 
        }//cierre del if grande
        else
            throw new EmptyCollectionException("\nError: Fuera de área");
        
        return area;
    }//fin del método
    
    /**
     * <pre>
     * Método posibleValor 
     * 
     * Método auxiliar que determina si cierto valor puede ser ingresado a 
     * una celda específica de la matriz del sudoku. 
     * 
     * </pre>
     * @param ren - Número del renglón del que se desea determinar
     *              el área. 
     * @param col - Número de la columana de la que se desea determinar
     *              el área. 
     * @param valor- Número que se intentará ingresar a la celda del sudoku.
     * 
     * @return <ul>
     *         <li>true: el número que se pretende ingresar a la 
     *                   celda de la matriz del sudoku es válido y 
     *                   pudo ser agregado exitosamente.</li>
     *         <li>false: el número que se pretende ingresar a la 
     *                   celda de la matriz del sudoku no es válido 
     *                   y por lo tanto no pudo ser agregado.</li>
     *         </ul>
     * 
     */
    
    public static boolean determinaValor(int ren, int col, int valor){
        boolean resp=false;
        /*
        para verificar si el valor se puede agregar a 
        los conjuntos del renglón, columna y área. También 
        verifica que el valor que se quiere agregar sea un
        número del 0 al 9
        */
        if(!(renglones[ren].contiene(valor)) 
          && !(columnas[col].contiene(valor)) 
          && !(areas[determinaArea(ren,col)].contiene(valor))
          && valor>0 && valor<=9){ 
            //para agregar al conjunto del renglón
            renglones[ren].agrega(valor);
            //para agregar al conjunto del columna
            columnas[col].agrega(valor);
            //para agregar al conjunto del área
            areas[determinaArea(ren,col)].agrega(valor);
            //para ingresar el valor a la celda deseada
            sudoku[ren][col]=valor;
            
            resp=true;
        }//cierre del if grande
        
        return resp;
    }//fin del método
    
    public static void anterior(int i, int j){
        int x = sudoku[i][j];
        renglones[i].quita(x);
        columnas[j].quita(x);
        areas[determinaArea(i, j)].quita(x);
        sudoku[i][j] = VACIO;
    }
    
    public static boolean resuelveSudoku(int i, int j){
        
        int valor=1;
        if(j>=MAX){ 
            j=0; 
            i=i+1; 
        }
        if(i>=MAX) 
           return true;
        else if(sudoku[i][j] != 0) 
            return resuelveSudoku(i,j+1);
        else{
            return resuelveSudoku (i,j,valor);
        }    
    }
        
        
    
    
    private static boolean resuelveSudoku(int i, int j, int valor){
        
        if (valor>MAX){
            return false;
        }else{
            if(determinaValor(i, j, valor)){
                if(resuelveSudoku(i, j+1)){
                    return true;
                }else{
                    anterior(i,j); 
                    return resuelveSudoku(i, j, valor+1);
                }
             
            } else{
                return resuelveSudoku(i, j, valor+1);
            }
           
        }
    }
    
    public static int[][] resolver(int matriz[][]){
        sudoku = new int[MAX][MAX];
        
        for(int i=0;i<MAX;i++) {
            renglones[i] = new ConjuntoA();
            columnas[i] = new ConjuntoA();
            areas[i] = new ConjuntoA();
        }
        
        for(int i=0; i<MAX ; i++){
            for(int j=0; j<MAX ; j++) {              
                if(matriz[i][j] != 0) 
                    determinaValor(i, j, matriz[i][j]); 
                else
                    sudoku[i][j]=0;
            }
        }
        
        if(resuelveSudoku(0, 0))
            return sudoku;
        else{ 
            System.out.println("error");
            return matriz;
        }
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        int[][] matrix = {  {0, 0, 0,  3, 9, 0,  0, 1, 0},
                            {5, 0, 1,  0, 0, 0,  0, 4, 0},
                            {9, 0, 0,  7, 0, 0,  5, 0, 0},
                            
                            {6, 0, 2,  5, 3, 0,  0, 7, 0},
                            {0, 0, 0,  0, 7, 0,  0, 0, 8},
                            {7, 0, 0,  8, 0, 0,  9, 0, 3},
                            
                            {8, 0, 3,  0, 1, 0,  0, 9, 0},
                            {0, 9, 0,  2, 0, 6,  0, 0, 7},
                            {4, 0, 0,  0, 0, 3,  0, 6, 1} };
        
//                int[][] matrix = {  {0, 0, 0,  0, 0, 0,  0, 0, 0},
//                                    {0, 0, 0,  0, 0, 0,  0, 0, 0},
//                                    {0, 0, 0,  0, 0, 0,  0, 0, 0},
//
//                                    {0, 0, 0,  0, 0, 0,  0, 0, 0},
//                                    {0, 0, 0,  0, 0, 0,  0, 0, 0},
//                                    {0, 0, 0,  0, 0, 0,  0, 0, 0},
//
//                                    {0, 0, 0,  0, 0, 0,  0, 0, 0},
//                                    {0, 0, 0,  0, 0, 0,  0, 0, 0},
//                                    {0, 0, 0,  0, 0, 0,  0, 0, 0} };
        
        int [][] pls;
        pls = resolver(matrix);
        
        
        for (int x=0; x < pls.length; x++)  {
            
            System.out.print("\n");
            
            for (int y=0; y < pls[x].length; y++) {

            System.out.print(" | ");System.out.print (pls[x][y]); System.out.print(" | ");

            }
        }
    
    }
}
