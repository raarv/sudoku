package sudokuconnombreoriginal;

public class MetodosResolver {
    private int[][] matriz;
    private static final int VACIO=0;
    private final int MAX=8;
    //conjuntos de los renglones
    private ConjuntoADT ren0;
    private ConjuntoADT ren1;
    private ConjuntoADT ren2;
    private ConjuntoADT ren3;
    private ConjuntoADT ren4;
    private ConjuntoADT ren5;
    private ConjuntoADT ren6;
    private ConjuntoADT ren7;
    private ConjuntoADT ren8;
    //conjuntos de las columnas
    private ConjuntoADT col0;
    private ConjuntoADT col1;
    private ConjuntoADT col2;
    private ConjuntoADT col3;
    private ConjuntoADT col4;
    private ConjuntoADT col5;
    private ConjuntoADT col6;
    private ConjuntoADT col7;
    private ConjuntoADT col8;
    //conjuntos para las regiones
    private ConjuntoADT area1;
    private ConjuntoADT area2;
    private ConjuntoADT area3;
    private ConjuntoADT area4;
    private ConjuntoADT area5;
    private ConjuntoADT area6;
    private ConjuntoADT area7;
    private ConjuntoADT area8;
    private ConjuntoADT area9;
    
    //constructor
    public MetodosResolver (){
        matriz=new int[MAX][MAX];
        //para llenar la matriz de 0
        for (int i=0;i<MAX;i++)
            for (int j=0;j<MAX;j++)
                matriz[i][j]=0;
    }//fin del constructor
    
    /*
    posibleValor
    contieneEnFila
    contieneEnColumna
    contieneEnArea
    valorPermitido
    */

    //método auxiliar que determina en qué área se encuentra una celda
    
    
    //método auxiliar de resuelveSudoku que verifica si el valor dado puede ser insertado
    private static boolean posibleValor(int ren,int col,int[][] matriz,int valor){
        
    }//fin del método
    
    //método público para empezar la recursión
    public static boolean resuelveSudoku (int [][]matriz){
        return resuelveSudoku(0,0,matriz);
    }//fin del método
    
    //método recursivo
    private static boolean resuelveSudoku(int ren, int col, int[][] matriz) {
        boolean resp=false;
        /*
        para verificar si ya se terminaron de agregar 
        valores a todas las columnas de un renglón
        */
        if (col==matriz[ren].length){
            col=0;
            ren++;
            /*
            para verificar si ya se terminaron de agregar
            valores a todos los renglones de la matriz
            */
            if (ren==matriz.length){
               resp=true;
               return resp;
            }//cierre del if pequeño    
        }//cierre del if
        else {
            /*
            para saltarse las celdas que tengan 
            valores diferentes a 0 en ellas
            */
            if (matriz[ren][col]!=VACIO){
                return resuelveSudoku(ren,col+1,matriz);
            }//cierre del if
            else{
                //para intentar agregar valores a las celdas vacías
                for (int valor=1;valor<=matriz.length;valor++){
                    if (posibleValor(ren,col,matriz,valor)){
                        matriz[ren][col]=valor;
                        //para recursar con el valor 
                        //puede cambiarse a solo resuelveSudoku 
                        if (resuelveSudoku(ren,col+1,matriz)){
                            resp=true;
                            return resp;
                        }//cierre de if pequeño   
                    }//cierre del if
                }//cierre del for
            }//cierre del else pequeño
        }//cierre del else 
        /*
        para realizar el backtracking ya que los valores asignados
        a las celdas no formaron una solución posible
        */
        matriz[ren][col]=VACIO;
        return resp;
    }//fin del método
    
}//fin de la clase
