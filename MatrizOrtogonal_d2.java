//crea una matriz ortogonal de n filas por m columnas
//Elaborado por: Diego Ivan Duarte Alvarez 
public class MatrizOrtogonal_d2 {
    static class Nodo {
        int fila, columna;
        String color /* negro=N o blanco=B o sin color=S*/, tipo /*dama=D o reina=R, sin tipo=T */;
        boolean estado;
        Nodo arriba, abajo, izquierda, derecha;

        public Nodo (int fila, int columna, String color, String tipo, boolean status) {
            this.fila=fila;
            this.columna=columna;
            this.color=color;
            this.tipo=tipo;
            this.estado=estado;
            this.arriba=this.abajo=this.izquierda=this.derecha=null;
        }
    }

    private Nodo inicio; //nodo superior izquierdo de la matriz

    private int filas, columnas;

    public MatrizOrtogonal (){
        inicio=null;
        filas=columnas=0;
    }

    //crea una matriz ortogonal enlazada de tamaño filas x columnas
    //filas: numero de filas
    //coumnas: numero de columnas
    public void crearMatriz (int filas, int columnas){
        Nodo [][] nodos = new Nodo [filas][columnas];
        this.filas=filas;
        this.columnas=columnas;
        
        //crear nodos
        for (int i=0; i<filas; i++){
            for (int j=0; j<columnas; j++){
                nodos [i][j]=new Nodo (i, j, "S", "T", false); 
            }
        }
        //enlazamos nodos
        for (int i=0; i<filas ; i++){
            for (int j=0; j<columnas ; j++){
                if (i>0)
                    nodos[i][j].arriba=nodos[i-1][j]; 
                if (i<filas-1)
                    nodos[i][j].abajo=nodos[i+1][j];
                if (j>0)
                    nodos[i][j].izquierda=nodos[i][j-1];
                if (j<columnas-1)
                    nodos[i][j].derecha=nodos[i][j+1];
            }
        }
        inicio=nodos[0][0]; //nodo inicial (esquina superior izquierda)
    }

    public void imprimirMatriz (){
        Nodo filaActual=inicio;
        int c1=1;

        while(filaActual!=null){
            Nodo colActual=filaActual;
            while(colActual!=null){
                c1++;
                if (colActual.tipo == "D"){
                    if (colActual.color == "N")
                        System.out.print("⚫" + "\t");
                    else 
                        System.out.print("⚪" + "\t");
                }
                else if (c1%2==0)
                    System.out.print("⬛" + "\t");
                else 
                    System.out.print("⬜" + "\t");
                colActual=colActual.derecha;
            }
            System.out.println();
            System.out.println();
            filaActual=filaActual.abajo;
            c1++;
        }
    }

    public void agregarColor (int fila, int columna, String color, boolean status, String type){
        Nodo actual = inicio;

        //moverse hacia la fila deseada
        for (int i=0;i<fila;i++){
            if (actual.abajo!=null)
                actual=actual.abajo;
        }
        //moverse hacia la columna deseada
        for (int i=0;i<columna;i++){
            if (actual.derecha!=null)
                actual=actual.derecha;
        }

        actual.color=color;
        actual.estado=status;
        actual.tipo=type;
    }



    //metodo para agregar una nueva fila al final
    public void agregarFila (){
        Nodo filaUlt = inicio;

        //ir hasta la ultima fila
        while(filaUlt.abajo!=null){
            filaUlt=filaUlt.abajo;
        }

        //crea nodos para la nueva fila
        Nodo [] nuevaFila = new Nodo [columnas];

        for (int i=0; i<columnas; i++){
            nuevaFila[i] = new Nodo(filas, i, "S", "T", false);
        }

        Nodo nodoArriba = filaUlt;
        for (int i=0; i<columnas; i++){
            if (i>0){
                nuevaFila[i].izquierda = nuevaFila[i-1];
                nuevaFila[i-1].derecha = nuevaFila[i];
            }
            nuevaFila[i].arriba = nodoArriba;
            nodoArriba.abajo= nuevaFila[i];
            nodoArriba = nodoArriba.derecha;
        }
        filas++;
    }

    //metodo para agregar una nueva columna al final
    public void agregarColumna (){
        Nodo columnaUlt = inicio;

        //ir hasta la ultima fila
        while(columnaUlt.derecha!=null){
            columnaUlt=columnaUlt.derecha;
        }

        Nodo nodoIzquireda = columnaUlt;

        //crea nodos para la nueva columna
        for (int i=0; i<filas; i++){
            Nodo nuevo = new Nodo(i, columnas, "S","T", false);

            //enlaza con el izquierdo
            nodoIzquireda.derecha=nuevo;
            nuevo.izquierda=nodoIzquireda;

            //enlaza hacia arriba si existe
            if (i>0){
                nuevo.arriba=nodoIzquireda.arriba.derecha;
                nuevo.arriba.abajo=nuevo;
            }
            nodoIzquireda=nodoIzquireda.abajo;
        }
        columnas++;
    }

    public void acomodarFichas (){
        for(int i=0; i<3; i++){
            for (int j=0; j<filas; j=j+2){
                if (i==1 && j==0)
                    j++;
                agregarColor(i, j, "N", true, "D");
            }
        }

        for (int i=5; i<columnas; i++){
            for (int j=1; j<filas; j=j+2){
                if(i==6 && j==1)
                    j--;
                agregarColor(i, j, "B", true, "D");
            }
        }
    }

    public void mostrarTableroDamas(int filaInicio, int colInicio) {
        if (filaInicio < 0 || colInicio < 0 || filaInicio >= filas || colInicio >= columnas) {
            System.out.println("[ERROR] Coordenadas fuera de rango.");
            return;
        }
        int c1=1;
        Nodo filaActual = inicio;
        for (int i = 0; i < filaInicio; i++) {
            if (filaActual.abajo != null)
                filaActual = filaActual.abajo;
        }

        Nodo inicioColumna = filaActual;
        for (int j = 0; j < colInicio; j++) {
            if (inicioColumna.derecha != null)
                inicioColumna = inicioColumna.derecha;
        }

        System.out.print("   ");
        for (int j = colInicio; j < columnas; j++) {
            char letra = (char) ('A' + j);
            System.out.print(" " + letra + " ");
        }
        System.out.println();

        boolean colorNegroInicio = ((filaInicio + colInicio) % 2 == 0);

        Nodo filaImprimir = inicioColumna;
        int numFila = filaInicio + 1;

        while (filaImprimir != null) {
            Nodo colActual = filaImprimir;
            boolean colorNegro = colorNegroInicio;

            System.out.printf("%2d ", numFila);

            while (colActual != null) {
                c1++;
                if (colActual.tipo == "D"){
                    if (colActual.color == "N")
                        System.out.print("⚫ ");
                    else 
                        System.out.print("⚪ ");
                }
                else if (c1%2==0)
                    System.out.print("⬛ ");
                else 
                    System.out.print("⬜ ");
                colActual=colActual.derecha;
            }

            System.out.printf(" %2d", numFila);
            System.out.println();
            colorNegroInicio = !colorNegroInicio;
            filaImprimir = filaImprimir.abajo;
            numFila++;
            c1++;
        }

        System.out.print("   ");
        for (int j = colInicio; j < columnas; j++) {
            char letra = (char) ('A' + j);
            System.out.print(" " + letra + " ");
        }

        System.out.println();
    }

    public static void main (String[] args){
        MatrizOrtogonal_d2 tablero = new MatrizOrtogonal_d2();

        //creamos la matriz de 3 filas x 4 columnas
        tablero.crearMatriz(8,8);
        tablero.mostrarTableroDamas(0, 0);
        //tablero.imprimirMatriz();
        System.out.println("-----------------------------------------------------");
        tablero.acomodarFichas();
        //tablero.imprimirMatriz();
        tablero.mostrarTableroDamas(0, 0);
    }
}
//metodos por agregar: mover fichas