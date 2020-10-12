package mate.academy.dao.impl;

import mate.academy.dao.TicketDao;
import mate.academy.lib.Dao;
import mate.academy.model.Ticket;

@Dao
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        return super.add(ticket, Ticket.class);
    }
}
