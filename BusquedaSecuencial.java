public class BusquedaSecuencial {
    //todo
    public static int buscar(int[] arreglo, int valor){
        for(int i=0; i<arreglo.length; i++){
            if(arreglo[i]==valor){
                return i; //regresa el indice donde se encontro
            }
        }
        return -1; //si no lo encuentra regresa -1
    }

    public static void main(String[] args){
        int[] datos={5,3,9,1,7,12,16,18,78};

        int buscado=100;

        int resultado=buscar(datos, buscado);

        if(resultado!=-1){
           System.out.println("\nValor "+buscado+" encontrado en:"+resultado);
        }else{
            System.out.println("valor no encontrado");
        }
    }
}
