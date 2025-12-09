public class QuickSort {
    public static void quickSort(int[] arr, int inicio, int fin){
        //solo se ordena si el segmento tiene al
        //menos 2 elementos
        if(inicio<fin){
            //la funsicion particion() coloca el pivote en su
            //posicion correcta y regresa el indice donde
            //ese pivote
            int indicePivote=particion(arr, inicio, fin);

            //ordenar la sublista izquierda (elementos menores al pivote)
            quickSort(arr, inicio, indicePivote-1);

            //ordenar la sublista derecha (elementos mayores al pivote)
            quickSort(arr, indicePivote+1, fin);
        }
    }

    private static int particion(int arr[], int inicio, int fin){
        //elegimos el ultimo elemento como pivote
        int pivote = arr[fin];

        //i lleva el indice donde se colocaran los
        //elementos menores al pivote
        int i=inicio-1;

        //recorremos desde inicio hasta antes del pivote
        for(int j=inicio; j<fin; j++){
            //si encontramos un elemento menor
            //o igual al pivote
            if(arr[j]<=pivote){
                i++; //avanzamos el indice de los menores

                //intercambiamos arr[j] con arr[i]
                int temp =arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        //finalmente colocamos el pivote es su
        //posicion correcta: despues del ultimo
        //elemento menor
        int temp=arr[i+1];
        arr[i+1]=arr[fin];
        arr[fin]=temp;

        //ese es el indice donde quedo el pivote
        return i+1;
    }

    public static void main(String[] args){
        int[] datos ={5,1,4,2,8,9,10,23,76,84,3,33};

        System.out.println("antes de ordenar tacos con todo");
        for(int num:datos){
            System.out.print(num+" ");
        }

        quickSort(datos, 0, datos.length-1);

        System.out.println("\ndespues de ordenar los tacos");
        for(int num:datos){
            System.out.print(num+" ");
        }         
    }
}
