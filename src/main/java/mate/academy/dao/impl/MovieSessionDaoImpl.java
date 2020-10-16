package mate.academy.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import mate.academy.dao.MovieSessionDao;
import mate.academy.lib.Dao;
import mate.academy.model.MovieSession;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Dao
public class MovieSessionDaoImpl extends AbstractDao<MovieSession> implements MovieSessionDao {
    private static final Logger logger = LoggerFactory.getLogger(MovieSessionDaoImpl.class);

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery("FROM MovieSession "
                    + "WHERE movie.id = :movieId AND showTime BETWEEN :startDate AND :endDate",
                    MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("startDate", date.atStartOfDay());
            query.setParameter("endDate", date.atTime(LocalTime.MAX));
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Cant fetch MovieSessions for movieId={}, date={} from DB",
                    movieId, date, e);
            return Collections.emptyList();
        }

    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return super.add(movieSession, MovieSession.class);
    }
}
