import entities.vertices.Director;
import entities.vertices.Genre;
import entities.vertices.Movie;
import user.User;
import entities.Graph;
import algorithms.MovieSorter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Graph graph = new Graph(true);
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {

        //Users

        User user1 = new User("RodrigoDV", "rodrigodv");
        User user2 = new User("DiegoAM", "diegoam");
        User user3 = new User("DiegoFG", "diegofg");
        User user4 = new User("KevinTA", "kevinta");
        User user5 = new User("MarianoVC", "marianovc");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        
        //Movies

        Movie inception = graph.addMovie("Inception", "Science Fiction", 2010, "Christopher Nolan");
        Movie darkKnight = graph.addMovie("The Dark Knight", "Action", 2008, "Christopher Nolan");
        Movie interstellar = graph.addMovie("Interstellar", "Science Fiction", 2014, "Christopher Nolan");
        Movie memento = graph.addMovie("Memento", "Thriller", 2000, "Christopher Nolan");
        Movie dunkirk = graph.addMovie("Dunkirk", "War", 2017, "Christopher Nolan");
        Movie prestige = graph.addMovie("The Prestige", "Mystery", 2006, "Christopher Nolan");
        Movie batmanBegins = graph.addMovie("Batman Begins", "Action", 2005, "Christopher Nolan");


        Movie seven = graph.addMovie("Seven", "Suspense", 1995, "David Fincher");
        Movie fightClub = graph.addMovie("Fight Club", "Drama", 1999, "David Fincher");
        Movie zodiac = graph.addMovie("Zodiac", "Crime", 2007, "David Fincher");
        Movie goneGirl = graph.addMovie("Gone Girl", "Thriller", 2014, "David Fincher");
        Movie theSocialNetwork = graph.addMovie("The Social Network", "Biography", 2010, "David Fincher");
        Movie theGirlWithTheDragonTattoo = graph.addMovie("The Girl with the Dragon Tattoo", "Crime", 2011, "David Fincher");
        Movie panicRoom = graph.addMovie("Panic Room", "Thriller", 2002, "David Fincher");

        // Directores

        Director davidFincher = graph.addDirector("David Fincher");
        Director christopherNolan = graph.addDirector("Christopher Nolan");


        //Generos
        Genre scienceFiction = graph.addGenre("Science Fiction");
        Genre action = graph.addGenre("Action");
        Genre thriller = graph.addGenre("Thriller");
        Genre war = graph.addGenre("War");
        Genre mystery = graph.addGenre("Mystery");
        Genre suspense = graph.addGenre("Suspense");
        Genre biography = graph.addGenre("Biography");
        Genre drama = graph.addGenre("Drama");
        Genre crime = graph.addGenre("Crime");

        //edges
        graph.createEdgesByGenre(scienceFiction);
        graph.createEdgesByGenre(action);
        graph.createEdgesByGenre(thriller);
        graph.createEdgesByGenre(war);
        graph.createEdgesByGenre(mystery);
        graph.createEdgesByGenre(suspense);
        graph.createEdgesByGenre(biography);
        graph.createEdgesByGenre(drama);
        graph.createEdgesByGenre(crime);

        graph.createEdgesByDirector(davidFincher);
        graph.createEdgesByDirector(christopherNolan);
        
        registerAndLoginMenu();
        User currentUser = getCurrentUser();
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
                System.out.println("No yet...");
                break;
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
                ArrayList<Movie> movies = graph.getMovies();
                MovieSorter.sortByYear(movies);
                for (Movie m: movies){
                    System.out.println(m.getString());
                }
                waitForEnter(scanner);
                break;
            } else if (opt == 2) {
                System.out.print("Ingrese un genero: ");
                String genre = scanner.nextLine();
                ArrayList<Genre> genres = graph.getGenres();
                for (Genre g: genres){
                    if (Objects.equals(g.getGenre().toLowerCase(), genre.toLowerCase())){
                        g.printMovies();
                    }
                }
                waitForEnter(scanner);
                break;
            } else if (opt == 3) {
                System.out.print("Ingrese el nombre de un director: ");
                String director = scanner.nextLine();
                ArrayList<Director> directors = graph.getDirectors();
                for (Director d: directors){
                    if (Objects.equals(d.getName().toLowerCase(), director.toLowerCase())){
                        d.printMovies();
                    }
                }
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

}