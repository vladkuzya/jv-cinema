package mate.academy.service.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import mate.academy.model.MovieSession;
import mate.academy.model.dto.request.MovieSessionRequestDto;
import mate.academy.model.dto.response.MovieSessionResponseDto;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto mapToDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setId(movieSession.getId());
        dto.setMovieId(movieSession.getMovie().getId());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setMovieTitle(movieSession.getMovie().getTitle());
        dto.setShowTime(movieSession.getShowTime()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return dto;
    }

    public MovieSession mapToEntity(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(dto.getMovieId()));
        movieSession.setShowTime(LocalDateTime.parse(dto.getShowTime(),
                DateTimeFormatter.ISO_DATE_TIME));
        movieSession.setCinemaHall(cinemaHallService.getById(dto.getCinemaHallId()));
        return movieSession;
    }
}
