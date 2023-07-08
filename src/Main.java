import algorithms.HeapSort;
import algorithms.Quicksort;
import entities.vertices.Director;
import entities.vertices.Genre;
import entities.vertices.Movie;
import user.User;
import entities.Graph;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Graph graph = new Graph(true);
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;


    public static void main(String[] args) {
        registerAndLoginMenu();
        User currentUser = getCurrentUser();
        loadMovies();
        loadDirectors();
        loadGenres();

        if (currentUser != null) {
            menu(scanner, graph, currentUser);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void registerAndLoginMenu() {
        while (true) {
            int option = registerAndLoginMenuOptions();
            scanner.nextLine();

            if (option == 1) {
                User newUser = register();
                if (newUser != null) {
                    users.add(newUser);
                    setCurrentUser(newUser);
                    break;
                }
            } else if (option == 2) {
                User existingUser = login();
                if (existingUser != null) {
                    setCurrentUser(existingUser);
                    break;
                }
            } else if (option == 3) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opcion no existe, intente de nuevo.");
            }
        }
    }

    public static int registerAndLoginMenuOptions() {
        System.out.println("::::::MoviesApp::::::");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesion");
        System.out.println("3. Salir");
        System.out.print("Elija una opcion: ");

        return scanner.nextInt();
    }

    public static User register() {
        System.out.print("Ingrese su username: ");
        String username = scanner.next();
        System.out.print("Ingrese su contrasenia: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Usuario ya existente. Elija otro username.");
                return null;
            }
        }

        User newUser = new User(username, password);
        System.out.println("Registration successful!");
        return newUser;
    }

    public static User login() {
        System.out.print("Ingrese su username: ");
        String username = scanner.next();
        System.out.print("Ingrese su contrasenia: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Inicio exitoso!");
                return user;
            }
        }

        System.out.println("Usuario o contrasenia incorrectos. Por favor, intente de nuevo.");
        return null;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void menu(Scanner scanner, Graph graph, User currentUser) {
        while (true) {
            clearConsole();
            int option = menuOptions(scanner);
            scanner.nextLine();

            if (option == 1) {
                subMenu(scanner, graph);
            } else if (option == 2) {
                Movie m1 = new Movie("prueba1", "Drama", 1968, "David Fincher");
                Movie m2 = new Movie("prueba2", "Horror", 1960, "Christopher Nolan");
                Movie m3 = new Movie("prueba3", "Horror", 1978, "Martin Scorsese");
                Movie m4 = new Movie("prueba4", "Crime", 1978, "David Fincher");

                currentUser.addToWatchlist(m1);
                currentUser.addToWatchlist(m2);
                currentUser.addToWatchlist(m3);
                currentUser.addToWatchlist(m4);

                graph.recommender(currentUser.getWatchlist(), graph);
            } else if (option == 3) {
                System.out.println("No yet..");
                break;
            } else if (option == 4) {
                System.out.println("No yet...");
                break;
            } else if (option == 5) {
                System.out.println("No yet...");
                break;
            } else if (option == 6) {
                System.out.println("Exiting the system...");
                break;
            }else {
                System.out.println("This option does not exist, try again.");
            }
        }
    }

    public static int menuOptions(Scanner scanner) {
        System.out.println("::::::MoviesApp::::::");
        System.out.println("1. Buscar por");
        System.out.println("2. Recomendador");
        System.out.println("3. Watchlist");
        System.out.println("4. Views");
        System.out.println("5. Favoritos");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion: ");

        return scanner.nextInt();
    }

    public static void subMenu(Scanner scanner, Graph graph) {
        while (true) {
            int opt = subMenuOptions(scanner);
            scanner.nextLine();

            if (opt == 1) {
                graph.printAllMovies();
                waitForEnter(scanner);
                break;
            } else if (opt == 2) {
                System.out.print("Genre: ");
                String genre = scanner.nextLine();
                graph.printMoviesByGenre(genre);
                System.out.println("********************");
                System.out.println(graph.getGenres().get("horror").getGenre() + " " + graph.getGenres().get("horror").getPopularity());

                waitForEnter(scanner);
                break;
            } else if (opt == 3) {
                System.out.print("Director: ");
                String director = scanner.nextLine();
                graph.printMoviesByDirector(director);
                waitForEnter(scanner);
                break;
            } else if (opt == 4) {
                System.out.print("No yet...");
                waitForEnter(scanner);
                break;
            } else {
                System.out.println("This option does not exist, try again.");
                waitForEnter(scanner);
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

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            // Manejo de excepciones
        }
    }

    public static void waitForEnter(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
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