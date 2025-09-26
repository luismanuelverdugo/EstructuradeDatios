public class ListaDobleCircularEjemplo{
    //clase que representa al nodo
    static class Nodo{
        public int dato;
        public Nodo siguiente; // apunta al sig. nodo
        public Nodo anterior; // apunta al nodo ant.

        public Nodo(int dato){
            this.dato=dato;
            this.siguiente=null;
            this.anterior=null;
        }
    }


    static class ListaDobleCircular{
        private Nodo inicio;
        private int tamano;

        //verifica si la lista esta vacia
        public boolean estaVacia(){
            return inicio==null;
        }

        public void insertaAlInicio(int dato){
            Nodo nuevo = new Nodo(dato);
            
            //la lista esta vacia
            if(estaVacia()){
                inicio = nuevo ;
                inicio.siguiente = inicio;
                inicio.anterior = inicio;
            }
            else{
                //la lista ya tiene elementos
                Nodo ultimo = inicio.anterior;

                nuevo.siguiente = inicio;
                nuevo.anterior = ultimo;

                inicio.anterior = nuevo;
                ultimo.siguiente = nuevo;

                //el nuevo nodo pasa a ser el inicio
                inicio = nuevo; 
            }
            tamano++;
            System.out.println("insertado:"+dato);
        }

        public void recorrerAdelante(){
            if(!estaVacia()){
                Nodo actual = inicio;

                System.out.println("-- inicio --");
                do{
                    System.out.println(" dato->"+actual.dato);
                    actual=actual.siguiente;
                }while(actual!=inicio);
                System.out.println("-- final --");
            }
            else{
                System.out.println("tan vacia como tu corazon");
            }
        }
    }

    public static void main(String[] args){
        ListaDobleCircular lista = new ListaDobleCircular();

        lista.insertaAlInicio(9);
        lista.insertaAlInicio(17);
        lista.insertaAlInicio(31);
        lista.insertaAlInicio(8);
        lista.insertaAlInicio(2);
        lista.insertaAlInicio(16);
        lista.insertaAlInicio(20);
        lista.insertaAlInicio(30);

        lista.recorrerAdelante();

    }
}