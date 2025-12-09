public class Burbuja{
    public static void bubbleSort(int[] arr){
        int n=arr.length;
        boolean huboCambios;

        //recorremos el camino amarrillo del arreglo
        for(int i=0; i<n-1; i++){
            huboCambios=false;

            //comparar elementos
            for(int j=0; j<n-1; j++){
                //intercambia si estan en el orden incorrecto
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    huboCambios=true;
                }
            }
            //si en una pasada no hubo cambios, ya esta ordenado
            if(huboCambios)
                break;
        }
    }
    public static void main(String[] args){
        int[] datos={5,1,4,2,8,9,10,23,76,84,33,3};

        System.out.println("antes de ordenar tacos con todo");
        for(int num:datos){
            System.out.print(num+" ");
        }

        bubbleSort(datos);

        System.out.println("\ndespues de ordenar los tacos");
        for(int num:datos){
            System.out.print(num+" ");
        }   
    }
}