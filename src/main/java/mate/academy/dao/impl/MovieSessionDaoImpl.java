package mate.academy.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import mate.academy.dao.MovieSessionDao;
import mate.academy.exceptions.DataProcessingException;
import mate.academy.model.MovieSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl extends AbstractDao<MovieSession> implements MovieSessionDao {
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery("FROM MovieSession "
                            + "WHERE movie.id = :movieId AND "
                            + "showTime BETWEEN :startDate AND :endDate",
                    MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("startDate", date.atStartOfDay());
            query.setParameter("endDate", date.atTime(LocalTime.MAX));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available sessions at this date "
                    + "for movie with id " + movieId, e);
        }

    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return super.add(movieSession, MovieSession.class);
    }
}
