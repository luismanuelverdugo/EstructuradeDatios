public class BusquedaBinaria {
    //todo
    public static int buscar(int[] arreglo, int valor){
        int inicio=0;
        int fin=arreglo.length-1;
        while (inicio<=fin) {
            int medio=(inicio+fin)/2; //im batman
            if(arreglo[medio]==valor){
                return medio;
            }
            if(valor<arreglo[medio]){
                fin=medio-1; //buscar en la mitad izquierda
            }else{
                inicio=medio+1; //buscar en la mitas derecha
            }
        }
        return -1; //no encontrado
    }

    public static void main(String[] args){
        int[] datos={1,3,5,7,9,11,13}; //arreglo ordenado
        int buscado=15;

        int resultado=buscar(datos, buscado);

        if(resultado!=-1){
            System.out.println("valor "+buscado+" encontrado en el indice:"+resultado);
        }else{
            System.out.println("valor no encontrado");
        }
    }
}
