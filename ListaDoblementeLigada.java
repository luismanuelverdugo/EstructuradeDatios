public class ListaDoblementeLigada {
    private static class Nodo {
        int dato;
        Nodo anterior;
        Nodo siguiente;

        public Nodo(int dato){
            this.dato=dato;
            this.anterior=null;
            this.siguiente=null;
        }        
    }
    
    private static class ListaDoble {
        Nodo cabeza;
        Nodo cola;

        public ListaDoble(){
            this.cabeza=null;
            this.cola=null;
        }
    
        //agrega nuevo nodo al final de la lista
        public void agregarNodo(int dato){
            Nodo nuevoNodo = new Nodo(dato);
            if(cabeza==null){
                cabeza=nuevoNodo;
                cola=nuevoNodo;
            }
            else{
                cola.siguiente=nuevoNodo;
                nuevoNodo.anterior=cola;
                cola=nuevoNodo;
            }
        }    

        public void mostrarAdelante(){
            Nodo actual = cabeza;
            if(cabeza==null){
                System.out.println("lista vacia como tu corazon ;)");
                return;
            }
            System.out.println("-- inicio a fin --");
            while (actual!=null) {
                System.out.println("dato->"+actual.dato);
                actual=actual.siguiente;
            }
        }

        public void mostrarAtras(){
            Nodo actual = cola;
            if(cola==null){
                System.out.println("lista vacia como tus ojos sin alma");
                return;
            }
            System.out.println("== final a inicio ==");
            while (actual!=null) {
                System.out.println("dato->"+actual.dato);
                actual=actual.anterior;                
            }
        }

        //metodo eliminar
        public void eliminarNodo(int dato){
            Nodo actual=cabeza;
            if(cabeza==null){
                System.out.println("lista vacia como tus sentimientos por mi.");
                return;
            }
            //busca el nodo a eliminar
            while (actual!=null && actual.dato!=dato) {
                actual=actual.siguiente;
            }
            //si el nodo a eliminar es la cabeza
            if(actual.anterior==null){
                cabeza=actual.siguiente;
                if(cabeza!=null){
                    cabeza.anterior=null;
                }
                else{
                    cola=null; //la lista quedo vacia
                }
            }
            //si el nodo a eliminar es la cola
            else if (actual.siguiente==null){
                cola=actual.anterior;
                cola.siguiente=null;
            }
            //si el nodo a eliminar esta en medio
            else{
                actual.anterior.siguiente=actual.siguiente;
                actual.siguiente.anterior=actual.anterior;
            }
        }

        // ordena la lista utilizando el algoritmo Quicksort
        public void quicksort(){
            //si la lista esta vacio o solo tiene un nodo
            if(cabeza==null || cabeza.siguiente==null){
                System.out.println("lista vacia como el espacio entre nosotros");
            }
            quicksortAux(cabeza, cola);
        }

        //metodo auxiliar y recursivo que implementa la logica del QS
        // param inicio: el nodo inicial del segmento de la lista a ordenar
        // param fin: el nodo final del segmento de la lista a ordenar
        private void quicksortAux(Nodo inicio, Nodo fin){
            if(fin!=null && inicio!=fin && inicio!=fin.siguiente){
                //particiona la lista y obtiene el "pivote" 
                Nodo pivote=particion(inicio, fin);

                //llama a quicksort para la sublista izquierda
                quicksortAux(inicio, pivote.anterior);

                //llama a quicjsort para la sublista derecha
                quicksortAux(pivote.siguiente, fin);
            }
        }

        /*
         * metodo para la particion de la lista, selecciona el ultimo nodo como pivote
         * y reorganiza los nodos de tal forma que los menores o igules al pivote
         * se muevan a su izquierda y los mayores a su derecha
         * param inicio: el nodo inicial del segmento a particionar
         * param fin: el nodo final del segmento a particionar
         * return el nodo pivote despues de la particion
         */
        private Nodo particion(Nodo inicio, Nodo fin){
            int pivote=fin.dato;
            Nodo i=inicio.anterior;

            //recorremos la lista desde el inicio hasta el final-1
            for(Nodo j = inicio; j != fin; j = j.siguiente){
                if(j.dato<=pivote){
                    //movemos el puntero "i" si "i" no es nulo, intercambiamos los datos
                    i = (i==null) ? inicio : i.siguiente;
                    int temp = i.dato;
                    i.dato=j.dato;
                    j.dato=temp; 
                }
            }
            // movemos el puntero i para posicionar el pivote
            i = (i == null) ? inicio:i.siguiente;

            //intercambiamos el pivote con el elemento en la posiciones correcta
            int temp = i.dato;
            i.dato = fin.dato;
            fin.dato = temp;

            //devolvemos el nodo pivote
            return i;
        }

        public static void main(String[] args){
            ListaDoble listad = new ListaDoble();
            listad.agregarNodo(40);
            listad.agregarNodo(70);
            listad.agregarNodo(30);
            listad.agregarNodo(90);
            listad.agregarNodo(1);
            listad.agregarNodo(4);
            listad.agregarNodo(7);
            listad.agregarNodo(3);
            listad.agregarNodo(9);
            listad.agregarNodo(51);

            listad.mostrarAdelante();

            listad.quicksort();

            listad.mostrarAdelante();
            //listad.mostrarAtras();
            /* 
            System.out.println("vamos a borrar el 30, pelas...");
            listad.eliminarNodo(30);

            listad.mostrarAdelante();

            System.out.println("vamos a borrar el 40 de cabeza, bye...");
            listad.eliminarNodo(40);
            listad.mostrarAdelante();

            System.out.println("vamos a borrar el 10 de cola, pow...");
            listad.eliminarNodo(10);
            listad.mostrarAdelante();
            */
        }
    }
}
