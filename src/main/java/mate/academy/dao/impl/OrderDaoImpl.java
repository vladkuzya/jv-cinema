package mate.academy.dao.impl;

import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.model.Order;
import mate.academy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Order add(Order order) {
        return super.add(order, Order.class);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "JOIN FETCH o.tickets "
                    + "WHERE o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        }
    }
}
