package mate.academy.dao.impl;

import java.util.List;
import mate.academy.exceptions.DataProcessingException;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

    public T add(T entity, Class<T> clazz) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            logger.info(clazz.getSimpleName() + " was created " + entity);
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert " + clazz.getSimpleName() + " to DB "
                    + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<T> getAll(Class<T> clazz) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all "
                    + clazz.getSimpleName(), e);
        }
    }

    public void update(T entity, Class<T> clazz) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            logger.info(clazz.getSimpleName() + " was updated " + entity);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update " + clazz.getSimpleName() + " "
                    + entity.toString(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
