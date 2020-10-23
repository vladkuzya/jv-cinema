package mate.academy.service.impl;

import java.util.List;
import mate.academy.dao.MovieDao;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie getById(Long id) {
        return movieDao.getById(id);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
