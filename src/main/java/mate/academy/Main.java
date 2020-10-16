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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy");
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private static final MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Untouchable");
        movieService.add(movie);
        movieService.getAll().forEach(movies -> logger.info(movies.toString()));

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("For adults");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(hall -> logger.info(hall.toString()));

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()).forEach(moviesession -> logger.info(moviesession.toString()));

        authenticationService.register("bob@gmail.com", "1111");
        authenticationService.register("alisa@gmail.com", "1111");
        User bob = new User();
        try {
            bob = authenticationService.login("bob@gmail.com", "1111");
        } catch (AuthenticationException e) {
            logger.warn("Login or password incorrect", e);
        }
        shoppingCartService.addSession(movieSession, bob);
        ShoppingCart shoppingCartBob = shoppingCartService.getByUser(bob);
        logger.info("Bob's shopping cart: " + shoppingCartBob);
        shoppingCartService.clear(shoppingCartBob);
        logger.info("Bob's shopping cart: " + shoppingCartBob);

        User alisa = new User();
        try {
            alisa = authenticationService.login("alisa@gmail.com", "1111");

        } catch (AuthenticationException e) {
            logger.warn("Login or password incorrect", e);
        }
        logger.info("Find by email: " + userService.findByEmail("alisa@gmail.com"));
        shoppingCartService.addSession(movieSession, alisa);
        ShoppingCart shoppingCartAlisa = shoppingCartService.getByUser(alisa);
        orderService.completeOrder(shoppingCartAlisa.getTickets(),
                shoppingCartAlisa.getUser());
        logger.info("Order history: " + orderService.getOrderHistory(shoppingCartAlisa.getUser()));
    }
}
