package Metodos;

import Conjuntos.ConjuntoA;
import Conjuntos.ConjuntoADT;


public class prueba {
    
    
    private final static int num = 9;
  
    private static int[][] sudoku;
    private static ConjuntoADT < Integer > renglon[]= new ConjuntoADT[num];
    private static ConjuntoADT < Integer > columna[]= new ConjuntoADT[num];;
    private static ConjuntoADT < Integer > submatriz[]= new ConjuntoADT[num];
   
   public static int encuentraSubmatricula (int ren,int col) throws Exception{
        int resp = -1;
        
        if (col>=0 && col <9 && ren>=0 && ren <9){
        if (col>=0 && col <=2){
            if ( ren>=0 && ren <=2){
                resp = 0;
                
            }
            if (ren > 2 && ren <=5){
                resp = 3;
            }
            
            if (ren >5 && ren <=8){
                resp =6;
            }
        }
        
        if (col > 2 && col <=5){
            if (  ren>=0 && ren <=2){
                resp = 1;
                
            }
            if (ren > 2 && ren <=5){
                resp = 4;
            }
            
            if (ren >5 && ren <=8){
                resp =7;
            }
        }
        
        if (col > 5 && col <=8){
            if (ren>=0 && ren <=2){
                resp = 2;
                
            }
            if (ren > 2 && ren <=5){
                resp = 5;
            }
            
            if (ren >5 && ren <=8){
                resp =8;
            }
        }
        }
        
        if (resp < 0){
          throw new ExcepcionCasiInvalida("ERROR404: CASILLA INVALIDA.");
        }
        
        return resp;
        
        
        
        
    
   }
    
    
   
   public static boolean agregaDato(int ren, int col, int x) throws Exception {
    if (x<1 || x>num || renglon[ren].contiene(x) || columna[col].contiene(x) || submatriz[encuentraSubmatricula(ren, col)].contiene(x)){
        return false;
   
    }
        renglon[ren].agrega(x);
        columna[col].agrega(x);
        submatriz[encuentraSubmatricula(ren, col)].agrega(x);
        sudoku[ren][col] = x;
        return true;
    
       }
   
   private static void regresa(int i, int j) throws Exception  {
        int x = sudoku[i][j];
        renglon[i].quita(x);
        columna[j].quita(x);
        submatriz[encuentraSubmatricula(i, j)].quita(x);
        sudoku[i][j] = 0;
    }
   
   public static boolean resuelveSudoku (int ren, int column) throws Exception {
      int dato = 1;
         if(column >= num) { 
            column = 0; 
            ren = ren +1; 
        }
        if(ren >= num) 
           return true;
        else
            if(sudoku[ren][column] != 0) 
            return resuelveSudoku(ren, column + 1);
        else {
            
            return resuelveSudoku (ren, column, dato);
         
            }
   } 
   private static boolean resuelveSudoku (int ren, int col, int dato) throws Exception{
      
     if (dato>9){
           return false;
       } else{
           if(agregaDato(ren, col, dato)){
              if(resuelveSudoku(ren, col + 1)){
                  return true;
              }else{
                regresa(ren, col); 
               return resuelveSudoku(ren, col, dato+1);
              }
             
           } else{
                return resuelveSudoku(ren, col, dato+1);
                
           }
           
       }
       
   
   
   }
   
   public static int[][] validacion(int matriz[][]) throws Exception {
        sudoku = new int[num][num];        
        
        for(int y = 0; y < num; y++) {
            renglon[y] = new ConjuntoA();
            columna[y] = new ConjuntoA();
            submatriz[y] = new ConjuntoA();
        } 
        
          
       
        
        for(int i = 0; i < num; i++)
            for(int j = 0; j < num; j++) {
                int x = matriz[i][j];
                if(x != 0) {
                    if(!agregaDato(i, j, x)) 
                        throw new ExcepcionCasiInvalida("ERROR404: CASILLA INVALIDA.");
                }
                sudoku[i][j] = x;
            }
        
        if(resuelveSudoku(0, 0))
            return sudoku;
        else 
            throw new ExcepcionCasiInvalida("ERROR404: CASILLA INVALIDA.");
    }
    
    
 

    
  
    public static void main (String[] args) throws Exception{
      int[][] grid = {      {1, 0, 0,  0, 0, 0,  0, 4, 0},
                            {2, 0, 0,  0, 3, 0,  0, 0, 0},
                            {0, 0, 0,  0, 0, 0,  0, 0, 0},
                            
                            {7, 0, 0,  0, 0, 0,  0, 0, 0},
                            {0, 0, 0,  0, 5, 0,  0, 0, 0},
                            {0, 0, 0,  0, 0, 4,  0, 8, 0},
                            
                            {0, 0, 0,  0, 0, 0,  0, 0, 0},
                            {0, 0, 0,  0, 0, 0,  0, 0, 0},
                            {0, 0, 6,  0, 0, 0,  0, 9, 0} };
      
      int [][] porfavor;
      porfavor = validacion(grid);
        
      for (int x=0; x < porfavor.length; x++)  {
      System.out.print("\n");    
    for (int y=0; y < porfavor[x].length; y++) {

     System.out.print(" | ");System.out.print (porfavor[x][y]); System.out.print(" | ");

    }
 
    }
      
        
      

     
    }

   
   
   
        
        
}