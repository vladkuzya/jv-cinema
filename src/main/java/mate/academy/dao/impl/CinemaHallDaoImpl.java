package mate.academy.dao.impl;

import java.util.List;
import mate.academy.dao.CinemaHallDao;
import mate.academy.model.CinemaHall;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {
    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return super.add(cinemaHall, CinemaHall.class);
    }

    @Override
    public CinemaHall getById(Long id) {
        return super.getById(id, CinemaHall.class);
    }

    @Override
    public List<CinemaHall> getAll() {
        return getAll(CinemaHall.class);
    }
}
