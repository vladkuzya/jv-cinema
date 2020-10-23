package mate.academy.dao.impl;

import java.util.List;
import mate.academy.exceptions.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDao<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);
    protected final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T add(T entity, Class<T> clazz) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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

    public T getById(Long id, Class<T> clazz) {
        logger.info("Try to get " + clazz.getSimpleName()
                + " entity with id " + id);
        try (Session session = sessionFactory.openSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get " + clazz.getSimpleName()
                    + " entity with id " + id, e);
        }
    }

    public List<T> getAll(Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
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
            session = sessionFactory.openSession();
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
