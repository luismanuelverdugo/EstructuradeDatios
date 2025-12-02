public class RadixSort {

    /**
     * Método principal de Radix Sort.
     * Este algoritmo ordena números dígito por dígito, comenzando desde
     * el dígito menos significativo (LSD: Least Significant Digit).
     *
     * @param arr Arreglo de enteros a ordenar.
     */
    public static void radixSort(int[] arr) {
        // Obtener el número máximo para saber cuántos dígitos se deben procesar
        int max = getMax(arr);

        /*
         * Recorremos cada dígito:
         * exp = 1 → unidades
         * exp = 10 → decenas
         * exp = 100 → centenas
         * y así sucesivamente mientras existan dígitos en el número mayor.
         */
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    /**
     * Método que devuelve el valor máximo dentro del arreglo.
     * Se usa para saber cuántos dígitos debe procesar Radix Sort.
     *
     * @param arr Arreglo de números.
     * @return El número más grande del arreglo.
     */
    private static int getMax(int[] arr) {
        int max = arr[0];

        // Recorremos el arreglo buscando el valor mayor
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Counting Sort modificado para Radix Sort.
     * Este método ordena los elementos de acuerdo a un dígito específico,
     * determinado por exp (1 = unidades, 10 = decenas, etc.)
     *
     * @param arr Arreglo a ordenar.
     * @param exp Representa el dígito a evaluar (1, 10, 100...)
     */
    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;

        // Arreglo de salida (temporal)
        int[] output = new int[n];

        // Arreglo de conteo (0 a 9 porque los dígitos decimales van de 0 a 9)
        int[] count = new int[10];

        /*
         * 1) Contar cuántas veces aparece cada dígito.
         * digit = (num / exp) % 10 obtiene el dígito deseado.
         */
        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        /*
         * 2) Transformar count[] en un arreglo acumulado.
         * Esto indica las posiciones finales de cada dígito en output[].
         */
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        /*
         * 3) Construcción del arreglo output[].
         * Recorremos de derecha a izquierda para mantener estabilidad.
         * (La estabilidad es clave para Radix Sort).
         */
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--; // Reducimos la posición disponible
        }

        /*
         * 4) Copiar los valores ordenados temporalmente al arreglo original.
         * Ahora arr[] está ordenado según el dígito 'exp'.
         */
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // ----------------- MÉTODO PRINCIPAL (EJEMPLO) -----------------

    public static void main(String[] args) {

        // Arreglo de ejemplo
        int[] datos = { 170, 45, 75, 90, 802, 24, 2, 66 };

        System.out.println("Antes de ordenar:");
        for (int n : datos)
            System.out.print(n + " ");

        // Aplicamos Radix Sort
        radixSort(datos);

        System.out.println("\nDespués de ordenar:");
        for (int n : datos)
            System.out.print(n + " ");
    }
}
