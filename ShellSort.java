/* gap -> intervalos en que los que se divide el arreglo
 */
public class ShellSort {
    public static void ShellSort(int[] arr) {
        int n = arr.length; //numero de elementos en el arreglo

        /*1. eleccion del gap (intervalo entre elementos a comparar)
        usamos la secuencia : n/2, n/4, n/8 ... 1

         */
        for (int gap = n/2; gap>0; gap/=2 ){
            /*en lugar de comparar posiciones consecutivas (j y j-1)
            comparamos j con j-gap
             */
            for(int i=gap; i<n; i++){
                //elemento actual que queremos insertar en la 
                //sublista correspondientes
                int valorActual = arr[i];

                //j se movera hacia atras en saltos de gap
                int j = i;

                /*recorremos hacias atras dentro del subarreglo,
                moviendo elementos que sean mayores que valoractual
                para dejar espacion "es algo mas grande que el espacio"

                while:
                - no salgamos del 
                - el elemento j-gap sea mayor que el actual
                - desplazamos arr[j-gap] hacia la derecha (arr[j])
                 */
                while (j>=gap && arr[j-gap]>valorActual) {
                    arr[j] = arr[j-gap];
                    j -= gap;            
                }

                /*insertamos el valoractual en su posicion correcta
                dentro del subarbol ordenado por salto de gap
                 */
                arr[j]=valorActual;
            } 
        }    
    }

    public static void main(String[] args)
    {
        int[] datos = {23,12,1,8,34,54,2,3};

        System.out.println("antes de ordenar");
        for(int n: datos ) System.out.print(n+" ");

        ShellSort(datos);

        System.out.println("\n Despues de ordenar");
        for(int n: datos ) System.out.print(n+" ");

    }
}
