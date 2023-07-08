package algorithms;

public class HeapSort {
    public static void heapSort(int[] array) {
        int n = array.length;

        // Construir el heap (reorganizar el arreglo)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i >= 0; i--) {
            // Mover la raíz actual al final del arreglo
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Llamar a heapify en el subárbol reducido
            heapify(array, i, 0);
        }
    }

    public static void heapify(int[] array, int n, int i) {
        int largest = i;  // Inicializar el más grande como la raíz
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Si el hijo izquierdo es más grande que la raíz
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // Si el hijo derecho es más grande que el más grande hasta ahora
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // Si el más grande no es la raíz
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursivamente hacer heapify en el subárbol afectado
            heapify(array, n, largest);
        }
    }
}
