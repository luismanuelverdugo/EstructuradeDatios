import java.io.*;
import java.util.Random;

public class listaSimple {
    private static class Nodo{
        int dato;
        Nodo siguiente;
        

        Nodo(int dato){
            this.dato=dato;
        }
    }

    private static class Lista{
        private Nodo cabeza;

        void insertar(int dato){
            Nodo nuevo = new Nodo(dato);           
            if(cabeza==null){
                cabeza=nuevo;
                return;
            }
            Nodo actual = cabeza;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=nuevo;
        }

        void eliminar(int dato){
            if(cabeza==null){
                System.out.println("lista vacia.");
                return;
            }
            // caso 1: el nodo a eliminar es la cabeza
            if(cabeza.dato==dato){
                cabeza=cabeza.siguiente;
                return;
            }

            //caso 2 
            Nodo actual=cabeza;
            while (actual.siguiente!=null && actual.siguiente.dato!=dato) {
                actual=actual.siguiente;                
            }

            if(actual.siguiente==null){
                System.out.println("dato no existe");
            }
            else{
                actual.siguiente=actual.siguiente.siguiente;
                System.out.println("dato eliminado");
            }
        }

        void muestra(){
            Nodo actual = cabeza;

            while (actual!=null) {
                System.out.print(actual.dato+"->");
                actual=actual.siguiente;
            }
            System.out.println("null");
        }
    
        void actualizar(int dato_ant, int dato_new, int pos){
            if(cabeza==null){
                System.out.println("La lista esta vacia");
                return;
            }
            Nodo actual = cabeza;
            int lugar = 1;
            while(actual!=null && lugar!=pos ){
                actual=actual.siguiente;
                lugar++;
            }
            if(actual==null){
                System.out.println("Dato no existe");
            }
            else{
                if(actual.dato==dato_ant)
                    actual.dato=dato_new;
            }
        }

        void Burbuja(){
            int contador=0;
            if(cabeza == null) return; //si no existe, no puede ordenarse

            boolean cambio;
            do{
                Nodo actual = cabeza;
                Nodo siguiente = cabeza.siguiente;
                cambio=false;

                while(siguiente !=null){
                    if(actual.dato > siguiente.dato){
                        int aux = actual.dato;
                        actual.dato = siguiente.dato;
                        siguiente.dato = aux;
                        cambio = true;
                        contador++;
                    }
                    actual = siguiente;
                    siguiente = siguiente.siguiente;
                }
            }while(cambio);
            System.out.println("Cantidad de cambios:");
            System.err.println(contador);
        }

        void insertarordenado(int dato){
            Nodo nuevo = new Nodo(dato);           
            if(cabeza==null || dato<cabeza.dato){
                nuevo.siguiente=cabeza;
                cabeza=nuevo;
                return;
            }

            Nodo actual=cabeza;
            while(actual.siguiente!=null && actual.siguiente.dato<dato){
                actual=actual.siguiente;               
            }
            nuevo.siguiente=actual.siguiente;
            actual.siguiente=nuevo;
        }

        // Método para generar 100 números aleatorios y guardarlos en un archivo
        public static void generarNumeros(String nombreArchivo) {
            Random rand= new Random();
            try(BufferedWriter bw= new BufferedWriter(new FileWriter(nombreArchivo))){
                for(int i=0; i<1000; i++) {
                    int numero= rand.nextInt(1000); //0 y 999
                    bw.write(String.valueOf(numero));
                    bw.newLine();
                }  
                System.out.println("se generaro 1000 numeros en:"+nombreArchivo);             
            }catch(IOException e){
                System.out.println("error al generar:"+nombreArchivo+" "+e.getMessage());
            }
        }

        // Método para leer los números del archivo y mostrarlos
        public static void leerNumeros(String nombreArchivo, Lista lista) {
            try(BufferedReader br= new BufferedReader(new FileReader(nombreArchivo))){
                String linea;
                System.out.println("numeros leidos del archivo:");
                while ((linea=br.readLine())!=null) {
                    //lista.insertarordenado(Integer.parseInt(linea));
                    lista.insertar(Integer.parseInt(linea));
                    //System.out.println(linea);
                }
            }catch(IOException e){
                System.out.println("error en el archivo:"+e.getMessage());
            }
        }

    }
    public static void main(String[] args){
        Lista lista = new Lista();
        String archivo = "numeros.txt";

        lista.generarNumeros(archivo);

        lista.leerNumeros(archivo, lista);

        lista.muestra();
         
        lista.Burbuja();

        lista.muestra();

        /* 
        lista.insertar(40);
        lista.insertar(70);
        lista.insertar(30);
            
        lista.insertar(90);
        lista.insertar(1);
        lista.insertar(4);
        lista.insertar(7);
        lista.insertar(3);
        lista.insertar(9);
        lista.insertar(51);        
        lista.muestra();

        /* 
        lista.insertar(10);
        lista.insertar(-7);
        lista.insertar(13);
        lista.insertar(15);
        lista.insertar(7);
        lista.insertar(6);
        lista.insertar(1000);
        lista.insertar(1);
        */
        /* 
        lista.insertarordenado(2);
        lista.insertarordenado(4);
        lista.insertarordenado(6);
        lista.insertarordenado(5);
        lista.insertarordenado(1);
        System.out.println("Elementos de la lista:");
        lista.muestra();
        */
        //lista.actualizar(8, 21, 6);
        //lista.muestra();

        //ordenar con burbuja la lista ya actualizada
    }
}
