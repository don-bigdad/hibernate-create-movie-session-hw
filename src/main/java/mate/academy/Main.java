package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.dao.MovieDao;
import mate.academy.dao.impl.MovieDaoImpl;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall(200,"Cinema Hall for 200 peoples");

        Movie fastAndFurious = new Movie("Fast and Furious",
                "An action film about street racing, heists, and spies.");

        MovieDao movieDao = new MovieDaoImpl();
        movieDao.add(fastAndFurious);
        System.out.println(movieDao.getAll());
        cinemaHallService.add(cinemaHall);

        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(new MovieSession(fastAndFurious,
                cinemaHall, LocalDateTime.now()));

        System.out.println(movieSessionService.get(1L));
        System.out.println(movieSessionService.findAvailableSessions(2L, LocalDate.now()));
        System.out.println(movieDao.getAll());
    }
}
