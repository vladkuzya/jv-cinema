package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy");
    private static MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    public static void main(String[] args) {
//        Movie movie = new Movie();
//        movie.setTitle("Untouchable");
//        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
