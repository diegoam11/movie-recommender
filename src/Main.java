import algorithms.HeapSort;
import algorithms.MergeSort;
import algorithms.Quicksort;
import entities.vertices.Director;
import entities.vertices.Genre;
import entities.vertices.Movie;
import user.User;
import entities.Graph;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Graph graph = new Graph(true);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User currentUser = new User();
        loadMovies();
        loadDirectors();
        loadGenres();
        menu(scanner, graph, currentUser);
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

            } else if (option == 5) {
                Stack<Movie> favorites = currentUser.getFavoriteMovies();
                for(Movie m: favorites){
                    System.out.println(m.getString());
                }

            } else if (option == 6) {
                System.out.println("Titulo: ");
                String title = scanner.nextLine();
                System.out.println("Genero: ");
                String genre = scanner.nextLine();
                System.out.println("Año: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Director: ");
                String director = scanner.nextLine();

                Movie m = new Movie(title, genre, year, director);
                currentUser.addFavoriteMovie(m);
            }else if (option == 7) {
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
        System.out.println("4. Agregar pelicula a watchlist");
        System.out.println("5. Favoritas");
        System.out.println("6. Agregar pelicula a favoritas");
        System.out.println("7. Salir");
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

    private static void loadMovies(){
        graph.addMovie("Inception", "Science Fiction", 2010, "Christopher Nolan");
        graph.addMovie("The Dark Knight", "Action", 2008, "Christopher Nolan");
        graph.addMovie("Interstellar", "Science Fiction", 2014, "Christopher Nolan");
        graph.addMovie("Memento", "Thriller", 2000, "Christopher Nolan");
        graph.addMovie("Dunkirk", "War", 2017, "Christopher Nolan");
        graph.addMovie("The Prestige", "Mystery", 2006, "Christopher Nolan");
        graph.addMovie("Batman Begins", "Action", 2005, "Christopher Nolan");

        graph.addMovie("Seven", "Suspense", 1995, "David Fincher");
        graph.addMovie("Fight Club", "Drama", 1999, "David Fincher");
        graph.addMovie("Zodiac", "Crime", 2007, "David Fincher");
        graph.addMovie("Gone Girl", "Thriller", 2014, "David Fincher");
        graph.addMovie("The Social Network", "Biography", 2010, "David Fincher");
        graph.addMovie("The Girl with the Dragon Tattoo", "Crime", 2011, "David Fincher");
        graph.addMovie("Panic Room", "Thriller", 2002, "David Fincher");

        graph.addMovie("2001: A Space Odyssey", "Science Fiction", 1968, "Stanley Kubrick");
        graph.addMovie("A Clockwork Orange", "Drama", 1971, "Stanley Kubrick");
        graph.addMovie("The Shining", "Horror", 1980, "Stanley Kubrick");
        graph.addMovie("Full Metal Jacket", "War", 1987, "Stanley Kubrick");
        graph.addMovie("Eyes Wide Shut", "Drama", 1999, "Stanley Kubrick");
        graph.addMovie("Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb", "Comedy", 1964, "Stanley Kubrick");
        graph.addMovie("Barry Lyndon", "Drama", 1975, "Stanley Kubrick");
        graph.addMovie("Paths of Glory", "War", 1957, "Stanley Kubrick");

        graph.addMovie("Taxi Driver", "Drama", 1976, "Martin Scorsese");
        graph.addMovie("Goodfellas", "Crime", 1990, "Martin Scorsese");
        graph.addMovie("Raging Bull", "Biography", 1980, "Martin Scorsese");
        graph.addMovie("Casino", "Crime", 1995, "Martin Scorsese");
        graph.addMovie("The Departed", "Crime", 2006, "Martin Scorsese");
        graph.addMovie("Shutter Island", "Mystery", 2010, "Martin Scorsese");
        graph.addMovie("The Wolf of Wall Street", "Biography", 2013, "Martin Scorsese");
        graph.addMovie("Gangs of New York", "Drama", 2002, "Martin Scorsese");
    }

    public static void loadDirectors(){
        Director davidFincher = graph.addDirector("David Fincher");
        Director christopherNolan = graph.addDirector("Christopher Nolan");
        Director stanleyKubrick = graph.addDirector("Stanley Kubrick");
        Director martinScorsese = graph.addDirector("Martin Scorsese");
        graph.createEdgesDirectorToMovie(davidFincher);
        graph.createEdgesDirectorToMovie(christopherNolan);
        graph.createEdgesDirectorToMovie(martinScorsese);
        graph.createEdgesDirectorToMovie(stanleyKubrick);
    }

    public static void loadGenres(){
        Genre scienceFiction = graph.addGenre("Science Fiction");
        Genre action = graph.addGenre("Action");
        Genre thriller = graph.addGenre("Thriller");
        Genre war = graph.addGenre("War");
        Genre mystery = graph.addGenre("Mystery");
        Genre suspense = graph.addGenre("Suspense");
        Genre biography = graph.addGenre("Biography");
        Genre drama = graph.addGenre("Drama");
        Genre crime = graph.addGenre("Crime");
        Genre comedy = graph.addGenre("Comedy");
        Genre horror = graph.addGenre("Horror");

        graph.createEdgesGenreToMovie(scienceFiction);
        graph.createEdgesGenreToMovie(action);
        graph.createEdgesGenreToMovie(thriller);
        graph.createEdgesGenreToMovie(war);
        graph.createEdgesGenreToMovie(mystery);
        graph.createEdgesGenreToMovie(suspense);
        graph.createEdgesGenreToMovie(biography);
        graph.createEdgesGenreToMovie(drama);
        graph.createEdgesGenreToMovie(crime);
        graph.createEdgesGenreToMovie(comedy);
        graph.createEdgesGenreToMovie(horror);
    }
}