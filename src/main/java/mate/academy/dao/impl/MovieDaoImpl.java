package mate.academy.dao.impl;

import java.util.List;
import mate.academy.dao.MovieDao;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;

@Dao
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        return super.add(movie, Movie.class);
    }

    @Override
    public List<Movie> getAll() {
        return super.getAll(Movie.class);
    }
}
