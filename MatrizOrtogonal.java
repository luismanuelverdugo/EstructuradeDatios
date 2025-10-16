// crea una matriz ortogonal de n filas por m columnas
public class MatrizOrtogonal {
    static class Nodo {
        int fila, columna;
        boolean status;
        String color; //blanco = B, negro = N, sin color = S
        String tipo; //dama = D, reina = R, sin tipo = T
        Nodo arriba, abajo, izquierda, derecha;

        public Nodo(int fila, int columna, boolean status, String color, String tipo) {
            this.fila = fila;
            this.columna = columna;
            this.status= status;
            this.color = color;
            this.tipo = tipo;
            this.arriba = this.abajo = this.izquierda = this.derecha = null;
        }
    }

    private Nodo inicio; // Nodo superior izq de la matriz
    private int filas, columnas;

    public MatrizOrtogonal() {
        inicio = null;
        filas = columnas = 0;
    }

    // crea una matriz ortogonal enlazada de tamaño filas x columnas
    // filas: numero de filas
    // columnas: numero de columnas
    public void crearMatriz(int filas, int columnas) {
        // ahora vamos a morir todos...
        Nodo[][] nodos = new Nodo[filas][columnas];
        this.filas = filas;
        this.columnas = columnas;

        // crear nodos
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                nodos[i][j] = new Nodo(i, j, false, "S", "T");
            }
        }

        // enlazamos nodos
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i > 0)
                    nodos[i][j].arriba = nodos[i - 1][j];
                if (i < filas - 1)
                    nodos[i][j].abajo = nodos[i + 1][j];
                if (j > 0)
                    nodos[i][j].izquierda = nodos[i][j - 1];
                if (j < columnas - 1)
                    nodos[i][j].derecha = nodos[i][j + 1];
            }
        }
        inicio = nodos[0][0]; // nodo inicial (esquina superior izquida)
    }

    // imprime la matriz ortogonal
    public void imprimirMatriz() {
        Nodo filaActual = inicio;
        while (filaActual != null) {
            Nodo colActual = filaActual;
            while (colActual != null) {
                System.out.print(colActual.color + "\t");
                colActual = colActual.derecha;
            }
            System.out.println();
            System.out.println();
            filaActual = filaActual.abajo;
        }
    }

    public void inicializarFichas(){
        //empieza desde el nodo inicial (arriba izq)
        Nodo filaActual = inicio;

        //fichas negras (jugador2) las 3 filas de fichas
        for(int i=0;i<3 && filaActual != null; i++){
            Nodo colActual = filaActual;

            for(int j=0; j< columnas && colActual != null; j++){
                //intercalamos las fichas:
                if((i+j)%2==0){
                    colActual.color="N"; //asignamos intercaladamente color
                    colActual.status=true; //ahora existe una ficha intercalada
                    colActual.tipo="D"; //empiezan todas las fichas como damas
                }
                colActual=colActual.derecha;//mueve der
            }
            filaActual = filaActual.abajo;//mueve hacia abajo
        }

        //fichas rojas (jugador1) las 3 ultimas filas
        filaActual=inicio;
        for(int i=0;i<5 && filaActual !=null; i++){
            //recorre la matriz - es posible hacer esto con un while(?)
            filaActual = filaActual.abajo;
        }

        //vamos a ir a las filas del jugador1 (ultimas 3 filas)
        for(int i=5; i<8 && filaActual !=null; i++){
            Nodo colActual = filaActual;

            for(int j=0;j<columnas && colActual !=null; j++){
                //intercalamos igual las fichas, pero esta vez:
                if((i+j)%2==0){
                    colActual.color="R";
                    colActual.status=true;
                    colActual.tipo = "D";
                }
                colActual = colActual.derecha; //volvemos a mover pointer
            }
            filaActual =filaActual.abajo; //y aqui tmb :3
        }
    }

    public void acomodarFichas (){
        for (int i=0; i<3; i++){ //inicializa las fichas negras
            for (int j=0; j<filas; j=j+2){
                if (i==1 && j==0)
                    j++;
                setValor(i, j, "N", true, "D");
            }
        }

        for (int i=5; i<columnas; i++) {
            for (int j=1; j<filas; j=j+2){
                if (i==6 && j==1)
                    j--;
                setValor(i, j, "B", true , "D");
            }
        }
    }

    // asigna un valor a una celca especifica de la matriz
    public void setValor(int fila, int columna, String color, boolean estado, String type) { // se le agregaron los aparametros booleano estado y string type
        Nodo actual = inicio;

        // moverse hacia la fila deseada
        for (int i = 0; i < fila; i++) {
            if (actual.abajo != null)
                actual = actual.abajo;
        }

        // moverse hacia la columna deseada
        for (int j = 0; j < columna; j++) {
            if (actual.derecha != null)
                actual = actual.derecha;
        }

        actual.color = color;
        actual.status = estado; //le agregue esta asignacion
        actual.tipo = type; //le agregue esta asignacion
    }

    // metodo para agregar una nueva fila al final
    public void agregarFila() {
        Nodo filaUltima = inicio;

        // ir hasta la ultima fila
        while (filaUltima.abajo != null) {
            filaUltima = filaUltima.abajo;
        }

        Nodo[] nuevaFila = new Nodo[columnas];

        // crea nodos para la nueva fila
        for (int j = 0; j < columnas; j++) {
            nuevaFila[j] = new Nodo(filas, j, false, "S", "T");

            // enlaza hacia la derecha
            //filaUltima.derecha = filaUltima.derecha;
        }
        // enlaza con la fila anterior
        Nodo nodoArriba = filaUltima;
        for (int j = 0; j < columnas; j++) {
            if (j > 0) {
                nuevaFila[j].izquierda = nuevaFila[j - 1];
                nuevaFila[j - 1].derecha = nuevaFila[j];
            }
            nuevaFila[j].arriba = nodoArriba;
            nodoArriba.abajo = nuevaFila[j];
            nodoArriba = nodoArriba.derecha;
        }
        filas++;
    }

    // metodo para agregar una nueva columna al final
    public void agregarColumna() {
        Nodo colUltima = inicio;
        // ir hasta la ultima columna
        while (colUltima.derecha != null) {
            colUltima = colUltima.derecha;
        }
        Nodo nodoIzquierda = colUltima;
        // crear nodos de la nueva columna
        for (int i = 0; i < filas; i++) {
            Nodo nuevo = new Nodo(i, columnas, false, "S", "T");
            // enlazar con el izquierdo
            nodoIzquierda.derecha = nuevo;
            nuevo.izquierda = nodoIzquierda;
            // enlazar hacia arriba si existe
            if (i > 0) {
                nuevo.arriba = nodoIzquierda.arriba.derecha;
                nuevo.arriba.abajo = nuevo;
            }
            nodoIzquierda = nodoIzquierda.abajo;
        }
        columnas++;
    }

    public static void main(String[] args) {
        //MatrizOrtogonal matriz = new MatrizOrtogonal();

        MatrizOrtogonal tablero = new MatrizOrtogonal();

        // creamos la matriz de 4 filas x 4 columnas
        /* 
        matriz.crearMatriz(4, 4);
        matriz.imprimirMatriz();
        System.out.println("--------------------------");
        matriz.setValor(0, 0, 5);
        matriz.setValor(1, 2, 10);
        matriz.setValor(2, 3, 15);

        matriz.imprimirMatriz();

        System.out.println("--------------------------");
        matriz.agregarFila();
        matriz.imprimirMatriz();

        System.out.println("--------------------------");
        matriz.agregarColumna();
        matriz.imprimirMatriz();

        System.out.println("--------------------------");
        */
        tablero.crearMatriz(8, 8);
        //tablero.acomodarFichas();
        tablero.inicializarFichas();
        tablero.imprimirMatriz();
    }
}
