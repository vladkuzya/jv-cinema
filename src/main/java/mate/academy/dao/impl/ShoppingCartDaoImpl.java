package mate.academy.dao.impl;

import mate.academy.dao.ShoppingCartDao;
import mate.academy.exceptions.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class ShoppingCartDaoImpl extends AbstractDao<ShoppingCart> implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        return super.add(shoppingCart, ShoppingCart.class);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
