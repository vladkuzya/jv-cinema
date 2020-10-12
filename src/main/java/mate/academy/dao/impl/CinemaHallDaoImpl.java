package mate.academy.dao.impl;

import java.util.List;
import mate.academy.dao.CinemaHallDao;
import mate.academy.lib.Dao;
import mate.academy.model.CinemaHall;

@Dao
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return super.add(cinemaHall, CinemaHall.class);
    }

    @Override
    public List<CinemaHall> getAll() {
        return getAll(CinemaHall.class);
    }
}
