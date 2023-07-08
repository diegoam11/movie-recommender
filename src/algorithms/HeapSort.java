package algorithms;
import entities.vertices.Movie;

import java.util.ArrayList;

public class HeapSort {
    public static void heapSortDescending(ArrayList<Movie> movies) {
        int n = movies.size();

        // Construir el heap (reorganizar el arreglo)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDescending(movies, n, i);
        }

        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i >= 0; i--) {
            // Mover la raíz actual al final del arreglo
            Movie temp = movies.get(0);
            movies.set(0, movies.get(i));
            movies.set(i, temp);

            // Llamar a heapify en el subárbol reducido
            heapifyDescending(movies, i, 0);
        }
    }

    public static void heapifyDescending(ArrayList<Movie> movies, int n, int i) {
        int largest = i;  // Inicializar el más grande como la raíz
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Si el hijo izquierdo es más pequeño que la raíz
        if (left < n && movies.get(left).getPopularity() < movies.get(largest).getPopularity()) {
            largest = left;
        }

        // Si el hijo derecho es más pequeño que el más grande hasta ahora
        if (right < n && movies.get(right).getPopularity() < movies.get(largest).getPopularity()) {
            largest = right;
        }

        // Si el más grande no es la raíz
        if (largest != i) {
            Movie swap = movies.get(i);
            movies.set(i, movies.get(largest));
            movies.set(largest, swap);

            // Recursivamente hacer heapify en el subárbol afectado
            heapifyDescending(movies, n, largest);
        }
    }
}
