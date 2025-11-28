
// Archivo: ArbolAVL.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ArbolAVL {

    static class NodoAVL {
        int valor; // Valor almacenado en el nodo
        int altura; // Altura del nodo (para balancear el árbol)
        NodoAVL izquierda; // Hijo izquierdo
        NodoAVL derecha; // Hijo derecho

        // Constructor del nodo
        NodoAVL(int dato) {
            valor = dato;
            altura = 1; // Todo nodo nuevo inicia con altura 1
        }
    }

    // Nodo raíz del árbol
    private NodoAVL raiz;

    // Retorna la altura de un nodo (si es null, es 0)
    int altura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    // Retorna el máximo entre dos números (utilizado para actualizar alturas)
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Calcula el factor de balance de un nodo:
    // balance > 1 → muy cargado a la izquierda
    // balance < -1 → muy cargado a la derecha
    int obtenerBalance(NodoAVL nodo) {
        if (nodo == null)
            return 0;
        return altura(nodo.izquierda) - altura(nodo.derecha);
    }

    // Rotación simple a la derecha (Right Rotation)
    /*
     * y x
     * / \ Rotación Derecha / \
     * x T3 -----------------> T1 y
     * / \ / \
     * T1 T2 T2 T3
     */
    NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierda;
        NodoAVL T2 = x.derecha;

        // Realizar la rotación
        x.derecha = y;
        y.izquierda = T2;

        // Actualizar alturas
        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;

        // Nueva raíz del subárbol
        return x;
    }

    // Rotación simple a la izquierda (Left Rotation)
    /*
     * x y
     * / \ Rotación Izquierda / \
     * T1 y ------------------> x T3
     * / \ / \
     * T2 T3 T1 T2
     */
    NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecha;
        NodoAVL T2 = y.izquierda;

        // Realizar rotación
        y.izquierda = x;
        x.derecha = T2;

        // Actualizar alturas
        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;

        // Nueva raíz del subárbol
        return y;
    }

    /*
     * Inserta un valor en el árbol AVL respetando las reglas del
     * Árbol Binario de Búsqueda (ABB). Luego verifica si el árbol quedó
     * desbalanceado y, de ser así, ejecuta la rotación correspondiente.
     */
    NodoAVL insertar(NodoAVL nodo, int valor) {

        // 1. Inserción normal de ABB
        if (nodo == null)
            return new NodoAVL(valor);

        //si el valor es menor se va por la izquierda, en caso contrario por la derecha
        if (valor < nodo.valor)
            nodo.izquierda = insertar(nodo.izquierda, valor);
        else if (valor > nodo.valor)
            nodo.derecha = insertar(nodo.derecha, valor);
        else
            return nodo; // No se permiten valores duplicados

        // 2. Actualizar la altura del nodo padre
        nodo.altura = 1 + max(altura(nodo.izquierda), altura(nodo.derecha));

        // 3. Calcular el factor de balance
        int balance = obtenerBalance(nodo);

        // Caso Izquierda-Izquierda
        if (balance > 1 && valor < nodo.izquierda.valor)
            return rotacionDerecha(nodo);

        // Caso Derecha-Derecha
        if (balance < -1 && valor > nodo.derecha.valor)
            return rotacionIzquierda(nodo);

        // Caso Izquierda-Derecha
        if (balance > 1 && valor > nodo.izquierda.valor) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }

        // Caso Derecha-Izquierda
        if (balance < -1 && valor < nodo.derecha.valor) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }

        // Si está balanceado, se retorna sin cambios
        return nodo;
    }

    // Método público para insertar desde el main
    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
    }

    /*
     * El recorrido inorder imprime los valores en orden ascendente.
     * 1) Recorre subárbol izquierdo
     * 2) Imprime nodo actual
     * 3) Recorre subárbol derecho
     */
    void inorder(NodoAVL nodo) {
        if (nodo != null) {
            inorder(nodo.izquierda);
            System.out.print(nodo.valor + " ");
            inorder(nodo.derecha);
        }
    }

    public void mostrarInorder() {
        inorder(raiz);
        System.out.println();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Metodo auxiliar recursivo para imprimir con indentacion (Reverse Inorder)
    private void imprimirEstructura(NodoAVL nodo, String prefijo, boolean esIzquierdo) {
        if (nodo != null) {
            // Recorrer Subarbol Derecho (imprime la rama superior)
            imprimirEstructura(nodo.derecha, prefijo + (esIzquierdo ? "│   " : "    "), false);

            // Imprimir el nodo actual (Raiz), mostrando valor, altura y balance
            System.out.println(prefijo + (esIzquierdo ? "└── " : "┌── ") +
                    nodo.valor + " (H: " + nodo.altura + " B: " + obtenerBalance(nodo) + ")");

            // Recorrer Subarbol Izquierdo (imprime la rama inferior)
            imprimirEstructura(nodo.izquierda, prefijo + (esIzquierdo ? "│   " : "    "), true);
        }
    }

    // Metodo publico para iniciar la impresion de la estructura
    public void mostrarEstructura() {
        System.out.println("\n--- Estructura del Árbol AVL ---");
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
        } else {
            // El 'false' inicial indica que la raiz no es un hijo izquierdo/derecho de nadie
            imprimirEstructura(raiz, "", false);
        }
        System.out.println("--------------------------------\n");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Imprime el arbol AVL en una representacion gráfica horizontal,
     * donde los nodos y las ramas se distribuyen de forma similar a un diagrama
     * de árbol tradicional, por ejemplo:
     * Esta función calcula un "canvas" (una matriz de texto) y coloca cada
     * nodo en su posición correspondiente.
     */
    public void imprimirArbolExacto() {

        // Obtiene la altura total del árbol
        int altura = altura_2(raiz);

        /**
         * El ancho total del dibujo se basa en 2^(altura).
         * Esto garantiza suficiente espacio horizontal para los nodos
         * y sus ramas.
         */
        int ancho = (int) Math.pow(2, altura + 2);

        // Canvas (matriz) donde se dibujara el arbol
        String[][] canvas = new String[altura * 2][ancho];

        // Rellenar el canvas con espacios en blanco
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                canvas[i][j] = " ";
            }
        }

        // Dibujar el arbol recursivamente empezando desde la raiz
        dibujar(raiz, canvas, 0, ancho / 2, altura);

        // Imprimir el canvas en consola
        for (String[] fila : canvas) {
            StringBuilder sb = new StringBuilder();
            for (String s : fila)
                sb.append(s);
            System.out.println(sb);
        }
    }

    /**
     * Dibuja un nodo del arbol en el canvas.
     *
     * @param nodo   Nodo actual que se desea dibujar.
     * @param canvas Matriz de texto donde se dibuja el arbol.
     * @param fila   Fila donde se colocara este nodo.
     * @param col    Columna donde se colocara este nodo.
     * @param altura Altura total del arbol (para calcular espacios).
     */
    private void dibujar(NodoAVL nodo, String[][] canvas, int fila, int col, int altura) {
        if (nodo == null)
            return;

        // Colocar el valor del nodo en el canvas
        canvas[fila][col] = String.valueOf(nodo.valor);

        /**
         * gap = separación horizontal entre nodos hijos
         * Mientras mas abajo este el nodo, mas pequeño es el espacio.
         *
         * Está basado en:
         * gap = 2^(altura - nivel)
         */
        int gap = (int) Math.pow(2, altura - (fila / 2 + 1));

        // Dibujar subarbol izquierdo
        if (nodo.izquierda != null) {
            canvas[fila + 1][col - gap] = "/"; // Dibuja la rama "/"
            dibujar(nodo.izquierda, canvas, fila + 2, col - gap * 2, altura);
        }

        // Dibujar subarbol derecho
        if (nodo.derecha != null) {
            canvas[fila + 1][col + gap] = "\\"; // Dibuja la rama "\"
            dibujar(nodo.derecha, canvas, fila + 2, col + gap * 2, altura);
        }
    }

    /**
     * Calcula la altura del arbol.
     *
     * La altura de un árbol vacío es 0.
     * La altura de un nodo es 1 + la altura mayor entre sus subarboles.
     *
     * @param nodo Nodo raiz del árbol o subarbol.
     * @return Altura del arbol.
     */
    private int altura_2(NodoAVL nodo) {
        if (nodo == null)
            return 0;
        return 1 + Math.max(altura(nodo.izquierda), altura(nodo.derecha));
    }

    // Método para leer los números del archivo y mostrarlos
    public static void leerNumeros(String nombreArchivo, ArbolAVL arbol) {
        try(BufferedReader br= new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            System.out.println("numeros leidos del archivo:");
            while ((linea=br.readLine())!=null) {
                arbol.insertar(Integer.parseInt(linea));
            }
        }catch(IOException e){
                System.out.println("error en el archivo:"+e.getMessage());
        }
    }    

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        // Crear árbol
        ArbolAVL arbol = new ArbolAVL();

        // Insertar valores (algunos provocan rotaciones)
        /* 
        arbol.insertar(0); //10 //2
        arbol.insertar(20);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();

        arbol.insertar(5);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();

        arbol.insertar(4); 
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();

        arbol.insertar(15); 
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();
 
        arbol.insertar(3);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();

        arbol.insertar(1);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();

        arbol.insertar(6);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();

        arbol.insertar(11);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();

        arbol.insertar(2);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto();  
        
        arbol.insertar(-1);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto(); 

        arbol.insertar(-2);
        arbol.mostrarEstructura();
        arbol.imprimirArbolExacto(); 
        
        arbol.insertar(18);
        arbol.insertar(12);
        arbol.insertar(49);
        arbol.insertar(19);
        arbol.insertar(24);
        arbol.insertar(23);
        arbol.insertar(67);
        arbol.insertar(23);
        arbol.insertar(32);
        */
        // Mostrar recorrido inorder
        // System.out.println("Recorrido Inorder del Árbol AVL:");
        
        arbol.leerNumeros("numeros.txt", arbol);
        
        arbol.mostrarInorder();

        arbol.mostrarEstructura();
        // javac -encoding UTF-8 ArbolAVL.java
        // java ArbolAVL

        arbol.imprimirArbolExacto();

    }
}
