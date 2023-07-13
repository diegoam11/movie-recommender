package com.example.MovieRecommender;

import com.example.MovieRecommender.algorithms.HeapSort;
import com.example.MovieRecommender.algorithms.MergeSort;
import com.example.MovieRecommender.algorithms.Quicksort;
import com.example.MovieRecommender.contollers.DirectorController;
import com.example.MovieRecommender.contollers.GenreController;
import com.example.MovieRecommender.contollers.MovieController;
import com.example.MovieRecommender.models.Director;
import com.example.MovieRecommender.models.Genre;
import com.example.MovieRecommender.models.Movie;
import com.example.MovieRecommender.models.User;
import com.example.MovieRecommender.models.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

@SpringBootApplication
public class MovieRecommenderApplication implements CommandLineRunner {

	@Autowired
	private DirectorController directorController;
	@Autowired
	private GenreController genreController;
	@Autowired
	private MovieController movieController;
	private static Graph graph = new Graph(true);
	public static void main(String[] args) {
		SpringApplication.run(MovieRecommenderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			Scanner scanner = new Scanner(System.in);
			User currentUser = new User();
			var movies = movieController.getAllMovies();
			var directors = directorController.getAllDirectors();
			var genre = genreController.getAllGenres();
			graph.addMoviesToGraph(movies);
			graph.addDirectorsToGraph(directors);
			graph.addGenresToGraph(genre);
			menu(scanner, graph, currentUser);

		for(var director : directors){
			System.out.println(director.getName());
		}
	}

	public static void menu(Scanner scanner, Graph graph, User currentUser) {
		while (true) {
			int option = menuOptions(scanner);
			scanner.nextLine();

			if (option == 1) {
				subMenu(scanner, graph);

			} else if (option == 2) {
				graph.recommender(currentUser.getWatchlist(), graph);
				Queue<Movie> movies = graph.convertHashToQueue(graph.getMovies());
				MergeSort.mergeSortMovieQueue(movies);
				for (Movie m: movies){
					System.out.println(m.getString());
				}

			} else if (option == 3) {
				Queue<Movie> watchlist = currentUser.getWatchlist();
				for(Movie m: watchlist) {
					System.out.println(m.getString());
				}

			} else if (option == 4) {
				int rpta;
				do {
					System.out.print("Titulo: ");
					String title = scanner.nextLine();
					System.out.print("Genero: ");
					String genre = scanner.nextLine();
					System.out.print("Año: ");
					int year = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Director: ");
					String director = scanner.nextLine();

					Movie m = new Movie(title, genre, year, director);
					currentUser.addToWatchlist(m);
					System.out.println("Pelicula agregada!");
					System.out.print("¿Desea agregar otra pelicula?(sí:1 | no:0): " );
					rpta = scanner.nextInt();
					scanner.nextLine();
				}while(rpta != 0);
			}else if (option == 0) {
				System.out.println("Exiting...");
				break;
			} else{
				System.out.println("This option does not exist, try again.");
			}
		}
	}

	public static int menuOptions(Scanner scanner) {
		System.out.println("::::::MoviesApp::::::");
		System.out.println("1. Buscar por");
		System.out.println("2. Recomendador");
		System.out.println("3. Watchlist");
		System.out.println("4. Agregar pelicula a favoritas");
		System.out.println("0. Salir");
		System.out.print("Seleccione una opcion: ");

		return scanner.nextInt();
	}

	public static void subMenu(Scanner scanner, Graph graph) {
		while (true) {
			int opt = subMenuOptions(scanner);
			scanner.nextLine();

			if (opt == 1) {
				System.out.println("Año: ");
				int decade = scanner.nextInt();
				ArrayList<Movie> movies = new ArrayList<>(graph.getMovies().values());
				Quicksort.sortByYear(movies);
				graph.printMoviesByDecade(movies, decade);
				break;

			} else if (opt == 2) {
				System.out.print("Genre: ");
				String genre = scanner.nextLine();
				graph.printMoviesByGenre(genre.toLowerCase());
				break;

			} else if (opt == 3) {
				System.out.print("Director: ");
				String director = scanner.nextLine();
				graph.printMoviesByDirector(director.toLowerCase());
				break;

			} else if (opt == 4) {
				ArrayList<Movie> arrayMovies = new ArrayList<>(graph.getMovies().values());
				HeapSort.heapSortDescending(arrayMovies);
				for (Movie movie : arrayMovies) {
					System.out.println(movie.getString());
				}
				break;

			} else {
				System.out.println("This option does not exist, try again.");

			}
		}
	}

	public static int subMenuOptions(Scanner scanner) {
		System.out.println(":::Buscar Por:::");
		System.out.println("1. Por fecha (1980s, 1990s, 2000s) ");
		System.out.println("2. Por genero");
		System.out.println("3. Por director");
		System.out.println("4. Por popularidad");
		System.out.print("Seleccione una opcion: ");

		return scanner.nextInt();
	}


}




