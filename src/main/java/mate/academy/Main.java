package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.exceptions.AuthenticationException;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.security.AuthenticationService;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;
import mate.academy.service.OrderService;
import mate.academy.service.ShoppingCartService;
import mate.academy.service.UserService;
import org.apache.log4j.Logger;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy");
    private static final Logger logger = Logger.getLogger(Main.class);
    private static MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private static ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Untouchable");
        movieService.add(movie);
        movieService.getAll().forEach(logger::info);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("For adults");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(logger::info);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()).forEach(logger::info);

        authenticationService.register("bob@gmail.com", "1111");
        authenticationService.register("alisa@gmail.com", "1111");
        User bob = authenticationService.login("bob@gmail.com", "1111");

        shoppingCartService.addSession(movieSession, bob);
        ShoppingCart shoppingCartBob = shoppingCartService.getByUser(bob);
        logger.info(shoppingCartBob);
        shoppingCartService.clear(shoppingCartBob);
        logger.info(shoppingCartBob);

        User alisa = authenticationService.login("alisa@gmail.com", "1111");
        logger.info(userService.findByEmail("alica@gmail.com"));

        shoppingCartService.addSession(movieSession, alisa);
        ShoppingCart shoppingCartAlisa = shoppingCartService.getByUser(alisa);
        orderService.completeOrder(shoppingCartAlisa.getTickets(),
                shoppingCartAlisa.getUser());
        logger.info(orderService.getOrderHistory(shoppingCartAlisa.getUser()));
    }
}
