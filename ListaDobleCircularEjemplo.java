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

        //metodo para borrar el chocoboyo de lia
        public boolean eliminaChocoBoyo(int dato){
            if(estaVacia()){
                System.out.println("lista vacia como la bolsa del chocoboyo...");
            }

            Nodo actual=inicio;
            Nodo anterior=null;

            do{
                if(actual.dato==dato){
                    if(tamano==1){
                        //solo hay un nodo en la lista
                        inicio=null;
                    }
                    else{
                        //muerte al chocoboyo
                        //la causa de muerte de la langosta fue el puchi chocoboyo, langosta inri
                        Nodo siguiente=actual.siguiente;
                        Nodo previo=actual.anterior;

                        //chocoboyo para jefe de grupo

                        previo.siguiente=siguiente;
                        siguiente.anterior=previo;

                        if(actual==inicio){
                            //si eliminamos el nodo de inicio, el nuevo inicio es el siguiente
                            inicio=siguiente;
                        }
                    }
                    tamano--;
                    System.out.println("eliminado el chocoboyo:"+dato);
                    return true;
                }
                anterior=actual;
                actual=actual.siguiente;
            }while(actual!=inicio);
            System.out.println("chocoboyo no chocoencontrado:"+dato);
            return false;
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

        lista.eliminaChocoBoyo(2);
        lista.eliminaChocoBoyo(16);

        lista.recorrerAdelante();

    }
}