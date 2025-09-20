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

        public static void main(String[] args){
            ListaDoble listad = new ListaDoble();
            listad.agregarNodo(40);
            listad.agregarNodo(70);
            listad.agregarNodo(30);
            listad.agregarNodo(90);
            listad.agregarNodo(10);

            listad.mostrarAdelante();
            listad.mostrarAtras();
        }
    }
}
