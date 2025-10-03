// crea una matriz ortogonal de n filas por m columnas
public class MatrizOrtogonal {
    static class Nodo{
        int fila, columna, valor;
        Nodo arriba, abajo, izquierda, derecha;

        public Nodo(int fila, int columna, int valor){
            this.fila=fila;
            this.columna=columna;
            this.valor=valor;
            this.arriba=this.abajo=this.izquierda=this.derecha=null;
        }
    }

    private Nodo inicio; //Nodo superior izq de la matriz

    public MatrizOrtogonal(){
        inicio=null;
    }

    //crea una matriz ortogonal enlazada de tamaño filas x columnas
    //filas: numero de filas
    //columnas: numero de columnas
    public void crearMatriz(int filas, int columnas){
        //ahora vamos a morir todos...
        Nodo[][] nodos=new Nodo[filas][columnas];

        //crear nodos
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                nodos[i][j]=new Nodo(i, j, 0);
            }
        }

        //enlazamos nodos
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                if(i>0)
                    nodos[i][j].arriba=nodos[i-1][j];
                if(i<filas-1)
                    nodos[i][j].abajo=nodos[i+1][j];
                if(j>0)
                    nodos[i][j].izquierda=nodos[i][j-1];
                if(j<columnas-1)
                    nodos[i][j].derecha=nodos[i][j+1];
            }
        }
        inicio=nodos[0][0]; //nodo inicial (esquina superior izquida)
    }

    //imprime la matriz ortogonal
    public void imprimirMatriz(){
        Nodo filaActual=inicio;
        while (filaActual!=null) {
            Nodo colActual=filaActual;
            while(colActual!=null){
                System.out.print(colActual.valor+"\t");
                colActual=colActual.derecha;
            }
            System.out.println();
            filaActual=filaActual.abajo;
        }
    }

    public static void main(String[] args){
        MatrizOrtogonal matriz=new MatrizOrtogonal();

        //creamos la matriz de 4 filas x 4 columnas
        matriz.crearMatriz(4, 4);
        matriz.imprimirMatriz();
    }
}
