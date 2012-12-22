package com.zzvc.mmps.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.zzvc.mmps.dao.UniversalDao;

/**
 * This class serves as the a class that can CRUD any object witout any
 * Spring configuration. The only downside is it does require casting
 * from Object to the object class.
 *
 * @author Bryan Noll
 */
@Repository("universalDao")
public class UniversalDaoHibernate implements UniversalDao {
    @Resource
    private SessionFactory sessionFactory;

	/**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    public Object save(Object o) {
        return getSession().merge(o);
    }

    /**
     * {@inheritDoc}
     */
    public Object get(Class clazz, Serializable id) {
        Object o = getSession().get(clazz, id);

        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }

        return o;
    }

    /**
     * {@inheritDoc}
     */
    public List getAll(Class clazz) {
        return getSession().createCriteria(clazz).list();
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Class clazz, Serializable id) {
    	getSession().delete(get(clazz, id));
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Object o) {
    	getSession().delete(o);
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }
}
