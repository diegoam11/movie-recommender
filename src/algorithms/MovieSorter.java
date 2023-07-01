package algorithms;
import entities.vertices.Movie;

import java.util.ArrayList;

public class MovieSorter {
    public static void sortByYear(ArrayList<Movie> movies) {
        quicksort(movies, 0, movies.size() - 1);
    }

    private static void quicksort(ArrayList<Movie> movies, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(movies, low, high);

            quicksort(movies, low, pivotIndex - 1);
            quicksort(movies, pivotIndex + 1, high);
        }
    }

    private static int partition(ArrayList<Movie> movies, int low, int high) {
        int pivot = movies.get(high).getYear();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (movies.get(j).getYear() <= pivot) {
                i++;

                Movie temp = movies.get(i);
                movies.set(i, movies.get(j));
                movies.set(j, temp);
            }
        }

        Movie temp = movies.get(i + 1);
        movies.set(i + 1, movies.get(high));
        movies.set(high, temp);

        return i + 1;
    }
}

