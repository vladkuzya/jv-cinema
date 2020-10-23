package mate.academy.dao.impl;

import mate.academy.dao.ShoppingCartDao;
import mate.academy.exceptions.DataProcessingException;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl extends AbstractDao<ShoppingCart> implements ShoppingCartDao {
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        return super.add(shoppingCart, ShoppingCart.class);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query = session.createQuery("FROM ShoppingCart sc "
                    + "LEFT JOIN FETCH sc.tickets "
                    + "WHERE sc.user = :user ", ShoppingCart.class);
            query.setParameter("user", user);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Cant get shopping cart by user "
                    + user, e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        super.update(shoppingCart, ShoppingCart.class);
    }
}
