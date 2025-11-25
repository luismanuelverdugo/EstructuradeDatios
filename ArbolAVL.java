public class ArbolAVL {
    //arbol que nace torcido se le caen los pajaritos

    static class NodoAVL {
        int valor; //valor en el nodo
        int altura; //altura del nodo (para balancear el arbol)
        NodoAVL izquierda; //hijo zurdo
        NodoAVL derecha; //hijo diestro

        //constructor del nodo
        NodoAVL(int dato){
            valor=dato;
            altura=1; //todo nodo nuevo inicia con altura 1
        }        
    }

    //nodo raiz del arbol
    private NodoAVL raiz;

    //regresa la altura de un nodo (si es null regresa 0)
    int altura(NodoAVL nodo){
        return (nodo==null) ? 0:nodo.altura;
    }

    //regresa el max entre dos numeros (utilizado para actualizar alturas)
    int max(int a, int b){
        return (a > b) ? a : b;
    }

    //calcula el factor de balance de un nodo
    //balance > 1 -> muy cargado a la izquierda
    //balance < 1 -> muy cargado a la dereccha
    int obtenerBalance(NodoAVL nodo){
        if(nodo==null)
            return 0;
        return altura(nodo.izquierda)-altura(nodo.derecha);
    }

    //rotacion simple a la derecha
    /*
          y   ------>   x   
         | |           |  |              
         x T3         T1  y   
        | |             |   |
       T1 T2            T2  T3   
    */
    NodoAVL rotacionDerecha(NodoAVL y){
        NodoAVL x = y.izquierda;
        NodoAVL T2 = x.derecha;

        //relizar la rotacion
        x.derecha = y;
        y.izquierda = T2;

        //actualizar alturas
        y.altura=max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura=max(altura(x.izquierda), altura(x.derecha)) + 1;

        //nueva raiz del subarbol
        return x;
    }
    //rotacion simple a la izquierda
    /*
          x   ------->    y  
        |   |           |   | 
        T1  y           x   T3    
          |   |       |   |
          T2  T3     T1   T2   
    */
    NodoAVL rotacionIzquierda(NodoAVL x){
        NodoAVL y = x.derecha;
        NodoAVL T2 = y.izquierda;

        //realiza rotacion
        y.izquierda = x;
        x.derecha = T2;

        //actualizar alturas
        x.altura=max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura=max(altura(y.izquierda), altura(y.derecha)) + 1;

        //nueva raiz del subarbol
        return y;
    }

    /*
    inserta un valor en el arbol avl respetando las reglas del
    arbol binario, luego verifica si el arbol quedo desbalanceado,
    de ser asi, ejecuta la rotacion correspondiente
     */
    NodoAVL insertar(NodoAVL nodo, int valor){
        //1. insercion normal de AB = Arbol binario
        if(nodo==null)
            return new NodoAVL(valor);
        if(valor<nodo.valor)
            nodo.izquierda=insertar(nodo.izquierda, valor);
        else if(valor>nodo.valor)
            nodo.derecha=insertar(nodo.derecha, valor);
        else
            return nodo; //no se permiten valores duplicados

        //2. actializar la altura del nodo padre
        nodo.altura=1+max(altura(nodo.izquierda), altura(nodo.derecha));
    
        //3. calcular el factor de balance
        int balance = obtenerBalance(nodo);

        //caso izquierda-izquierda
        if(balance > 1 && valor < nodo.izquierda.valor)
            return rotacionDerecha(nodo);
        //caso derecha-derecha
        if(balance < 1 && valor > nodo.derecha.valor)
            return rotacionIzquierda(nodo);

        //caso izquierda-derecha
        if(balance > 1 && valor > nodo.izquierda.valor){
            nodo.izquierda=rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }

        //caso derecha-izquierda
        if(balance < -1 && valor < nodo.derecha.valor){
            nodo.derecha=rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }

        //si es balanceado, no se le caen los pajaritos "se retorna sin cambios"
        return nodo;
    }

    //metodo publico para insertar desde el main
    public void insertar(int valor){
        raiz = insertar(raiz, valor);
    }

    /* 
    el recorrido inorden imprime los valores en orden ascendente
    1. recorre subarbol izquierdo
    2. imprime nodo actual
    3. recorre subarbol derecho
    */
    void inorden(NodoAVL nodo){
        //entre solo si el arbol tiene nodos
        if(nodo!=null){
            inorden(nodo.izquierda);
            System.out.print(nodo.valor+" ");
            inorden(nodo.derecha);
        }
    }

    public void mostrarInorden(){
        inorden(raiz);
        System.out.print(" ");
    }

    public static void main(String[] args){
        //crear arbol
        ArbolAVL arbol = new ArbolAVL();

        //inserta valores
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(5);
        arbol.insertar(4);  //provoca una rotacion
        arbol.insertar(15); //provoca otra rotacion

        //mostrar recorrido inorden
        arbol.mostrarInorden();
    }
}
