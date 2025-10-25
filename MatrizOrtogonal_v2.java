import java.util.Scanner;

public class MatrizOrtogonal_v2 {

    Scanner cin = new Scanner(System.in);//para interactuar con la consola

    static class Nodo {
        int fila, columna;
        boolean status;
        String color; // Blanco = B o negro = N, sin color = S
        String tipo; // dama = D o reina = R, sin tipo = T
        Nodo arriba, abajo, izquierda, derecha;
        String colorTablero;

        public Nodo(int fila, int columna, boolean status, String color, String tipo) {
            this.fila = fila;
            this.columna = columna;
            this.status = status;
            this.color = color;
            this.tipo = tipo;
            this.arriba = this.abajo = this.izquierda = this.derecha = null;
            this.colorTablero = null;
        }
    }

    private Nodo inicio; // Nodo superior izq de la matriz
    private int filas, columnas;
    private int fichasBlancas=12;
    private int fichasNegras=12;

    public MatrizOrtogonal_v2() {
        inicio = null;
        filas = columnas = 0;
    }

    // crea una matriz ortogonal enlazada de tamaño folas x columnas
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
        inicio = nodos[0][0]; // nodo inicial (esquina superior izquierda)
    }// :3

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
            filaActual = filaActual.abajo;
        }
    }

    public void inicializarTablero() {
        Nodo filaActual = inicio;
        Boolean filaPar = false;

        while (filaActual != null) {
            Nodo colActual = filaActual;
            Boolean colorNegro = filaPar;

            while (colActual != null) {
                colActual.colorTablero = colorNegro ? "B" : "N";
                colorNegro = !colorNegro;
                colActual = colActual.derecha;
            }
            filaPar = !filaPar;
            filaActual = filaActual.abajo;

        }
    }

    public void inicializarFichas() {
        Nodo filaActual = inicio;

        // fichas negras (jugador 2)
        for (int i = 0; i < 3 && filaActual != null; i++) {
            Nodo colActual = filaActual;

            for (int j = 0; j < columnas && colActual != null; j++) {

                if ((i + j) % 2 == 0) {
                    colActual.color = "N"; // asignamos
                    colActual.status = true;
                    colActual.tipo = "D";
                }

                colActual = colActual.derecha;
            }
            filaActual = filaActual.abajo;
        }

        // fichas rojas (jugador 1) las 3 ultimas filas
        filaActual = inicio;
        for (int i = 0; i < 5 && filaActual != null; i++) {
            // recorre la matriz - es posible hacer esto con while
            filaActual = filaActual.abajo;
        }
        // vamos a ir a las filas del jugador 1
        for (int i = 5; i < 8 && filaActual != null; i++) {
            Nodo colActual = filaActual;

            for (int j = 0; j < columnas && colActual != null; j++) {
                // intercambiamos igual las fichas
                if ((i + j) % 2 == 0) {
                    colActual.color = "R";
                    colActual.status = true;
                    colActual.tipo = "D";
                }
                colActual = colActual.derecha;
            }
            filaActual = filaActual.abajo;
        }
    }

    // asigna un valor a una celda especifica de la matriz
    public void setValor(int fila, int columna, String color) {
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
            filaUltima.derecha = filaUltima.derecha;
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

    // metodo para agregar una nueva columna
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

            // enlaza con el izquierdo
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
    }// el constructor es clave

    public void mostrarTableroDamas(int filaInicio, int colInicio) {
        if (filaInicio < 0 || colInicio < 0 || filaInicio >= filas || colInicio >= columnas) {
            System.out.println("[ERROR] Coordenadas fuera de rango.");
            return;
        }

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
            System.out.print(" " + letra + "");
        }
        System.out.println();

        boolean colorNegroInicio = ((filaInicio + colInicio) % 2 == 0);

        Nodo filaImprimir = inicioColumna;
        int numFila = filaInicio + 1;

        while (filaImprimir != null) {
            Nodo colActual = filaImprimir;
            // boolean colorNegro = colorNegroInicio;

            System.out.printf("%2d ", numFila);

            while (colActual != null) {
                if (colActual.tipo == "D") {
                    if (colActual.color != "R") {
                        System.out.print("⚫");
                    } else
                        System.out.print("🔴");
                }
                if (colActual.tipo != "D" && colActual.colorTablero == "N") {
                    System.out.print("⬛");
                } else if (colActual.tipo != "D" && colActual.colorTablero == "B") {
                    System.out.print("⬜");

                }
                // colorNegro = !colorNegro;
                colActual = colActual.derecha;
            }

            System.out.printf(" %2d", numFila);
            System.out.println();
            // colorNegroInicio = !colorNegroInicio;
            filaImprimir = filaImprimir.abajo;
            numFila++;
        }

        System.out.print("   ");
        for (int j = colInicio; j < columnas; j++) {
            char letra = (char) ('A' + j);
            System.out.print(" " + letra + "");
        }
        System.out.println();
    }

    public void moverFichas(String color) {
        Nodo actual = inicio;
        //como nos movemos a la columna deseada ? moviendonos
        /*for(int i=1; i<8; i++){
            ficha = ficha.derecha;
        }

        for(int j)*/
        System.out.println("Fila de origen: "); int filaOrigen = cin.nextInt();
        System.out.println("Columna de origen: "); int columnaOrigen = cin.nextInt();
        System.out.println("Fila destino: "); int filaDestino = cin.nextInt();
        System.out.println("Columna destino: "); int columnaDestino = cin.nextInt();

        //Vamos a obtener el valor original
        for(int i=0; i<filaOrigen; i++) actual = actual.abajo;
        for(int j=0; j<columnaOrigen; j++) actual = actual.derecha;

        if(actual.tipo != "D"){
            System.out.println("No hay una ficha en esa posicion, intenta con otra :3");
            return;
        }

        //tablero.setValor(filaOrigen,columnaOrigen, 0);//movemos ficha
        if(actual.color == "B"){//si es ficha Blanca
            //¿¿
        }else if(actual.color == "N"){//si es ficha Negra
            //¿¿
        }else{//sin color
            System.out.println("La ficha no existe.");
        }
        //tablero.setValor(filaDestino,columnaDestino,valor);//situamos ficha

        System.out.println("Movimiento realizado :3");

        //como nos movemos a la fila correcta ? moviendonos

    }

    //creamos una funcion para jugar :D
    public void jugar(){
        int opcion;
        do{
            //imprimirTablero();
            System.out.println("===OPCIONES PARA JUGAR===");
            System.out.println("1. Mover ficha.");
            System.out.println("2. Salir :c");
            System.out.println("Elige una opcion: "); opcion = cin.nextInt();
            /*     
            switch(opcion){
                case 1 -> this.moverFichas();
                case 2 -> System.out.println("Adios :c");
                default -> System.out.println("Esa opcion no existe...");
            }
            */
        }while(opcion!=2);
    }

    //co : columna origina, fo : fila original
    //cd : columna destino, fo : fila destino
    public void moverFicha(int co, int fo, int cd, int fd){
        Nodo actual = inicio;
        Nodo mover = inicio;
        
        //nos movemos a la ficha original
        for(int i=1; i<fo; i++) actual = actual.abajo;
        for(int j=1; j<co; j++) actual = actual.derecha;

        //nos movemos a la casilla destino
        for(int i=1; i<fd; i++) mover = mover.abajo;
        for(int j=1; j<cd; j++) mover = mover.derecha;
        
        //si la dama es del mismo color no pasa nada
        if (actual.color==mover.color){
            System.out.println("damas del mismo color");
            return;
        }
        else{
            if (mover.color=="S"){
                mover.color=actual.color;
                actual.color="S";
                actual.tipo="T";
                actual.status=false;
                mover.tipo="D";
                mover.status=true;
            }
            else{
                //falta descontar fichas
            }
        }


    }

    public static void main(String[] args) {
        // MatrizOrtogonal matriz=new MatrizOrtogonal();
        MatrizOrtogonal_v2 tablero = new MatrizOrtogonal_v2();


        tablero.crearMatriz(8, 8);

        tablero.inicializarFichas();
        tablero.inicializarTablero();
        // tablero.imprimirMatriz();
        tablero.mostrarTableroDamas(0, 0);
        tablero.moverFicha(1, 3, 2, 4);
        tablero.mostrarTableroDamas(0, 0);
        tablero.moverFicha(2, 6, 3, 5);
        tablero.mostrarTableroDamas(0, 0);

        //tablero.jugar();
    }
}
