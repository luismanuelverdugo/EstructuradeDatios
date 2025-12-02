public class RadixSort {
    public static void radixSort(int[] arr){
        //obtener el numero maximo pra saber cuantos
        //digitos se deben procesar
        int max  = getMax(arr);

        /*
        recorremos cada digito:
        exp=1 -> unidades
        exp=10 -> decenas
        exp=100 -> centanas
        y asi sucesivamente mientras existan digitos
        en el numero mayor
         */
        for (int exp=1; max/exp > 0; exp *= 10){
            countingSort(arr, exp);
        }
    }
    /*
    metodo que devuelve el valor maximo dentro un arreglo
    se usa para saber cuantos digitos debe procesar radixsort

    arr: arreglo de numeros
    return: el numero mas grande del arreglo    
     */
    private static int getMax(int[] arr){
        int max = arr[0];

        //recorre el arreglo buscando el valor mayor
        for(int num:arr){
            if(num>max){
                max=num;
            }
        }
        return max;
    }

    private static void countingSort(int[] arr, int exp){
        int n = arr.length;

        //arreglo de salida (temporal)
        int[] output = new int[n];

        //arreglo de conteo 
        int[] count = new int[10];

        /*contar cuantas veces aparece cada digito
        digit=(num/exp)%10  obtiene el digito deseado
        */
        for(int num : arr){
            int digit=(num/exp) % 10;
            count[digit]++;
        }

        /*transforma count[] en un arreglo acumulado
        esto indica las posiciones finales de cada
        digito en output[]
         */
        for(int i=1; i<10; i++){
            count[1]+=count[i-1];
        }

        /*construccion del arreglo output[]
        recorremos de derecha a izquierda para mantener

         */
        for(int i=n-1; i>=0; i--){
            int digit =(arr[i]/exp)%10;
            output[count[digit]-1]=arr[i];
            count[digit]--; //reducimos la posicion disponible
        }

        /*copiar los valores ordenados temporalmente 
        al arreglo original
        ahora arr[] esta ordenado segun el digito exp
         */
        for(int i=0; i<n; i++){
            arr[i]=output[i];
        }
    }

    public static void main(String[] agrs){
        //arreglo a ordenar
        int[] datos ={170,45,75,90,802,24,2,66,49,76};

        System.out.println("antes de ordenar:");
        for(int n : datos)
            System.out.println(n+" ");

        //aplicamos radixsort
        radixSort(datos);

        System.out.println("despues de ordenas");
        for(int n : datos)
            System.out.println(n+" ");
    }
}
