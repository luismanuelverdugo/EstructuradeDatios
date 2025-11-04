/*
 * este programa implementa un arbol binario de busqueda
 * un abb es una estructura de datos jerarquica donde cada nodo tiene:
 * un valor (dato almacenado)
 * un hijo izquiero (valores menores) 
 * un hijo derecho (valores mayores)
 */
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
    }

    public static void main(String[] args){
        ArbolBinario arbol = new ArbolBinario();
        //inserta nodos en el arbol
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
    }
}
