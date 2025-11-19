/*
 * este programa implementa un arbol binario de busqueda
 * un abb es una estructura de datos jerarquica donde cada nodo tiene:
 * un valor (dato almacenado)
 * un hijo izquiero (valores menores) 
 * un hijo derecho (valores mayores)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.Random;

public class ArbolesBinarios {
    /*
     * la clase Nodo representa la unidad basica del arbol binario
     * cada nodo almacena:
     * un valor entero
     * una referencia al hijo izquierdo
     * una referecnua al hijo derecho
     */
    class Nodo{
        int valor; //dato que contiene el nodo
        Nodo izquierda; //puntero al subarbol izquierdo
        Nodo derecha; //puntero al subarbol derecho
    
        /*
         * constructor del Nodo
         * inicializa el valor y establece los hijos como nulos
         */
        public Nodo(int valor){
            this.valor=valor;
            izquierda=derecha=null;
        }
    }
    /*
     * esta clase representa el arbol binario
     * contiene la raiz del arbol y los metodos para manipulario
     */
    static class ArbolBinario {
        Nodo raiz; //nodo raiz : punto de entrada del arbol
        int saltos;
        
        /*
         * inserta un nuevo valor en el arbol
         * si el arbol esta vacio, el nuevo nodo se convierte en la raiz
         * si no, se busca la posicion adecuada segun la regla:
         * menores a la izquierda
         * mayores a la derecha
         * param valor: valor entero a insertar en el arbol
         */
        public void insertar(int valor){
            raiz=insertaRecursivo(raiz, valor);
        }

        /*
         * inserta recursivamente un nuevo nodo en la posicion correcta
         * logica:
         *  si el nodo actual es null -> se crea un nuevo nodo
         *  si el valor es menos -> se recorre el subarbol izquierdo
         *  si el valor es mayor -> se recorre el subarbol derecho
         *  si el valor es igual -> no se inserta (evita duplicados)
         * param actual: Nodo actual que se esta evaluando
         * param valor: valor entero a insertar
         * param Nodo: actualizado despues de la insercion
         */
        private Nodo insertaRecursivo(Nodo actual, int valor){
            if(actual==null){
                return new ArbolesBinarios().new Nodo(valor);
            }
            if(valor<actual.valor){
                actual.izquierda=insertaRecursivo(actual.izquierda, valor);
            }
            else if(valor>actual.valor){
                actual.derecha=insertaRecursivo(actual.derecha, valor);
            }
            //si el valor ya existe, no hace nada
            return actual;
        }

        /*
         * busca si un valor existe dentro del arbol
         * param valor: valor entero a buscar
         * return: true si el valor existe en el arbol y false si no
         */
        public boolean buscar(int valor){
            return buscarRecursivo(raiz, valor);
        }

        /*
         * busca un valor recorriendo el arbol de forma recursiva
         * logica:
         * si el nodo actual es null -> arbol este vacio
         * si el valor es igual al actual.valor -> true
         * ojito aqui ;)
         * SI ES MENOR -> busca en el subarbol izquierda
         * SI ES MAYOR -> busca en el subarbol derecha
         * 
         * param actual: Nodo actual del recorrido
         * param valor: valor a buscar
         * return: true-> si se encuentra el valor, false-> si no lo encuentra
         */
        private boolean buscarRecursivo(Nodo actual, int valor){
            saltos++;
            if(actual==null)
                return false;
            if(actual.valor==valor)
                return true;
            return valor<actual.valor
                ? buscarRecursivo(actual.izquierda, valor)
                : buscarRecursivo(actual.derecha, valor);
        }

        /*
         * recorre el arbol en "orden: In-Orden" izquierda -> raiz -> derecha
         * este recorrido muestra los valores del arbol en orden ascendente
         * ejemplo:
         * si el arbol contiene: 50,30,70,20,40,60,80
         * el recorrido InOrden mostra: 20,30,40,50,60,70,80
         */
        public void recorridoInOrden(Nodo nodo){
            if(nodo!=null){
                recorridoInOrden(nodo.izquierda);
                System.out.println(nodo.valor+" ");
                recorridoInOrden(nodo.derecha);
            }
        }

        public void imprimirArbolInOrder(Nodo nodo, int nivel){
            if(nodo!=null){
                imprimirArbolInOrder(nodo.derecha, nivel+1);
                System.out.println("   ".repeat(nivel)+nodo.valor);
                imprimirArbolInOrder(nodo.izquierda, nivel+1);
            }

        }
        /*
         * recorre el arbol en "Orden: Pre-Orden" raiz -> izquierda -> derecha
         * ejemplo:
         * si el arbol contiene: 50,30,70,20,40,60,80
         * resultado: 50,30,20,40,70,60,80
         * 
         */
        public void recorridoPreOrden(Nodo nodo){
            if(nodo!=null){
                System.out.println(nodo.valor+" ");
                recorridoPreOrden(nodo.izquierda);
                recorridoPreOrden(nodo.derecha);
            }
        }

        /*
         * recorre el arbol en "Orden Post-Orden" Izquierda -> Derecha -> Raiz
         * ejemplo:
         * si el arbol contiene: 50,30,70,20,40,60,80
         * resultado: 20, 40, 30, 60, 80, 70, 50
         */
        public void recorridoPostOrden(Nodo nodo){
            if(nodo!=null){
                recorridoPostOrden(nodo.izquierda);
                recorridoPostOrden(nodo.derecha);
                System.out.println(nodo.valor+" ");
            }
        }

        /* 
         * Elimina in nodo con un valor especifico dentro de un arbol
         * -nodo: referencia al nodo actual (puede ser la raiz o un subarbol)
         * -valor: valor del nodo a eliminar
        */
        public Nodo eliminar(Nodo nodo, int valor){
            //si el arbol esta vacio, no se hace nada
            if(nodo==null)
                return null;

            //busca el nodo a eliminar
            if(valor<nodo.valor)
                nodo.izquierda=eliminar(nodo.izquierda, valor); //busca en subarbol izquierdo
            else if(valor>nodo.valor)
                nodo.derecha=eliminar(nodo.derecha, valor); //busca en subarbol derecho
            else{
                //nodo encontrado
                //caso 1: nodo sin hijos
                //se elimina simplemente 
                if(nodo.izquierda==null && nodo.derecha==null)
                    return null;

                //caso 2: nodo con un solo hijo
                else if(nodo.izquierda==null)
                    return nodo.derecha; //solo tiene hijo derecho
                else if(nodo.derecha==null)
                    return nodo.izquierda; //solo tiene hijo izquierdo
                
                //caso 3: nodo con dos hijos papá luchon
                //se debe encontrar un valor que lo reemplace (sucersor inorden)
                //el sucesor inorden es el nodo mas pequeño del subarbol derecho
                nodo.valor=valorMinimo(nodo.derecha);

                //luego se elimina
                nodo.derecha=eliminar(nodo.derecha, nodo.valor);
            }
            //regresa la raiz actulizada del subarbol
            return nodo;
        }

        /*
        * busca y devuelve el valor minimo dentro de un subarbol
        * en un arbol de busqueda, el valor minimo se
        * encuentra recorriendo siempre hacia la izquierda
        * -nodo: nodo raiz del arbol subarbol donde se buscara el minimo
        * -return: el valor mas pequeño encontrado en ese subarbol
        */
        private int valorMinimo(Nodo nodo){
            int min=nodo.valor;

            //mientras existan nodos a la izquierda, sigue bajando
            while(nodo.izquierda!=null){
                min=nodo.izquierda.valor;
                nodo=nodo.izquierda;
            }
                //ultimo nodo a la izquierda es el valor minimo
            return min;
        }

        // Método para leer los números del archivo y mostrarlos
        public static void leerNumeros(String nombreArchivo, ArbolBinario arboll) {
            try(BufferedReader br= new BufferedReader(new FileReader(nombreArchivo))){
                String linea;
                System.out.println("numeros leidos del archivo:");
                while ((linea=br.readLine())!=null) {
                    arboll.insertar(Integer.parseInt(linea));
                }
            }catch(IOException e){
                System.out.println("error en el archivo:"+e.getMessage());
            }
        }

        
    }

    public static void main(String[] args){
        ArbolBinario arbol = new ArbolBinario();
        //inserta nodos en el arbol
        /* 
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        */
        arbol.leerNumeros("numeros.txt", arbol);
        arbol.saltos=0;
        System.out.println("chocobollo valor -1:"+arbol.buscar(-1));
        System.out.println("numero de saltos:"+arbol.saltos);
        arbol.saltos=0;
        System.out.println("chocobollo valor 40:"+arbol.buscar(40));
        System.out.println("numero de saltos:"+arbol.saltos);
        arbol.saltos=0;
        System.out.println("chocobollo valor 1:"+arbol.buscar(1));
        System.out.println("numero de saltos:"+arbol.saltos);
        arbol.saltos=0;
        System.out.println("chocobollo valor 840:"+arbol.buscar(840));
        System.out.println("numero de saltos:"+arbol.saltos);

        //arbol.recorridoInOrden(arbol.raiz);
        arbol.imprimirArbolInOrder(arbol.raiz, 0);
        //busqueda del valor en el arbol
        /*
        System.out.println("----- busqueda -----");
        System.out.println("chocobollo valor 40:"+arbol.buscar(40));
        System.out.println("chocobollo not found 90:"+arbol.buscar(90));

        System.out.println("=== recorrido en In-Orden ===");
        arbol.recorridoInOrden(arbol.raiz);

        System.out.println("=== recorrido en Pre-Orden ===");
        arbol.recorridoPreOrden(arbol.raiz);

        System.out.println("=== recorrido en Post-Orden ===");
        arbol.recorridoPostOrden(arbol.raiz);

        System.out.println("-------- elimina valor del arbol 70 -------");
        arbol.imprimirArbolInOrder(arbol.raiz, 0);

        arbol.eliminar(arbol.raiz, 70);

        System.out.println("=== recorrido en In-Orden ===");
        //arbol.recorridoInOrden(arbol.raiz);
        arbol.imprimirArbolInOrder(arbol.raiz, 0);
        */
    }
}
