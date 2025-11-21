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
}
