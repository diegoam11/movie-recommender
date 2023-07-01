import entities.vertices.Director;
import entities.vertices.Genre;
import entities.vertices.Movie;
import entities.Graph;
import algorithms.MovieSorter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph(true);

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

        // Directors

        Director davidFincher = graph.addDirector("David Fincher");
        Director christopherNolan = graph.addDirector("Christopher Nolan");


        //genres
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
        graph.createEdgesByGenre(biography);
        graph.createEdgesByGenre(drama);
        graph.createEdgesByGenre(crime);

        graph.createEdgesByDirector(davidFincher);
        graph.createEdgesByDirector(christopherNolan);

        menu(scanner, graph);
    }

    public static void menu(Scanner scanner, Graph graph) {
        while (true) {
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
        System.out.println("1. Search by");
        System.out.println("2. Recommender");
        System.out.println("3. Watchlist");
        System.out.println("4. Views");
        System.out.println("5. Favorites");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

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
                break;
            } else if (opt == 2) {
                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();
                ArrayList<Genre> genres = graph.getGenres();
                for (Genre g: genres){
                    if (Objects.equals(g.getGenre().toLowerCase(), genre.toLowerCase())){
                        g.printMovies();
                    }
                }
                break;
            } else if (opt == 3) {
                System.out.print("Enter director: ");
                String director = scanner.nextLine();
                ArrayList<Director> directors = graph.getDirectors();
                for (Director d: directors){
                    if (Objects.equals(d.getName().toLowerCase(), director.toLowerCase())){
                        d.printMovies();
                    }
                }
                break;
            } else if (opt == 4) {
                System.out.print("No yet...");
                break;
            } else {
                System.out.println("This option does not exist, try again.");
            }
        }
    }

    public static int subMenuOptions(Scanner scanner) {
        System.out.println(":::Sub Menu:::");
        System.out.println("1. By date (1980s, 1990s, 2000s) ");
        System.out.println("2. By genre");
        System.out.println("3. By director");
        System.out.println("4. By popularity");
        System.out.print("Enter your choice: ");

        return scanner.nextInt();
    }

}