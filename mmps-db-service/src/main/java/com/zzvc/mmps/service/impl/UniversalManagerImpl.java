package com.zzvc.mmps.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zzvc.mmps.dao.UniversalDao;
import com.zzvc.mmps.service.UniversalManager;

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("universalManager")
public class UniversalManagerImpl implements UniversalManager {
    /**
     * Log instance for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * UniversalDao instance, ready to charge forward and persist to the database
     */
    @Resource
    protected UniversalDao universalDao;

    /**
     * {@inheritDoc}
     */
    public Object get(Class clazz, Serializable id) {
        return universalDao.get(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    public List getAll(Class clazz) {
        return universalDao.getAll(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public Object save(Object o) {
        return universalDao.save(o);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Class clazz, Serializable id) {
        universalDao.remove(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
	public void remove(Object o) {
		universalDao.remove(o);
	}
}
