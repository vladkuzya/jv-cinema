package mate.academy.dao.impl;

import java.util.List;
import mate.academy.dao.MovieDao;
import mate.academy.model.Movie;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return super.add(movie, Movie.class);
    }

    @Override
    public Movie getById(Long id) {
        return super.getById(id, Movie.class);
    }

    @Override
    public List<Movie> getAll() {
        return super.getAll(Movie.class);
    }
}
