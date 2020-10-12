package mate.academy.dao.impl;

import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.exceptions.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Order;
import mate.academy.model.User;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;

@Dao
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    @Override
    public Order add(Order order) {
        return super.add(order, Order.class);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "JOIN FETCH o.tickets "
                    + "WHERE o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving order history for user "
                    + user.toString(), e);
        }
    }
}
