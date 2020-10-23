package mate.academy.service;

import java.util.List;
import mate.academy.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    Movie getById(Long id);

    List<Movie> getAll();
}
