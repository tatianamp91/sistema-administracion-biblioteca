package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabRol;


/**
 * A data access object (DAO) providing persistence and search support for
 * SabRol entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SabRolDAO {
    private static final Log log = LogFactory.getLog(SabRolDAO.class);

    // property constants
    //public static final String  DESCRIPCION = "descripcion";
    public static final String DESCRIPCION = "descripcion";

    //public static final Long  IDROL = "idRol";
    public static final String IDROL = "idRol";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    /**
    *
    * @param Instance
    *            SabRol Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SabRol instance) {
        log.debug("saving SabRol instance");

        try {
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /**
    * @param Instance
    *            SabRol Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SabRol instance) {
        log.debug("deleting SabRol instance");

        try {
            getSession().delete(instance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /**
    *
    * @param Instance
    *            SabRol Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(SabRol instance) {
        log.debug("updating SabRol instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabRol findById(Long id) {
        log.debug("finding SabRol instance with id: " + id);

        try {
            SabRol instance = (SabRol) getSession().get(SabRol.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabRol failed", re);
            throw re;
        }
    }

    public List<SabRol> findByExample(SabRol instance) {
        log.debug("finding SabRol instance by example");

        try {
            List results = getSession().createCriteria("SabRol")
                               .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " +
                results.size());

            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /**
    * Find all  SabRol entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< SabRol> found by query
        */
    public List<SabRol> findByProperty(String propertyName, Object value) {
        log.debug("finding SabRol instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from SabRol as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabRol> findByDescripcion(Object descripcion) {
        return findByProperty(DESCRIPCION, descripcion);
    }

    public List<SabRol> findByIdRol(Object idRol) {
        return findByProperty(IDROL, idRol);
    }

    /**
    * Find all SabRol entities.
    *
    * @return List<SabRol> all SabRol instances
    */
    public List<SabRol> findAll() {
        log.debug("finding all SabRol instances");

        try {
            String queryString = "from SabRol";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabRol> findByCriteria(String whereCondition) {
        log.debug("finding SabRol " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabRol model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabRol> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabRol failed", re);
            throw re;
        }
    }

    public List<SabRol> findPageSabRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabRol findPageSabRol");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabRol model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabRol model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabRol() {
        log.debug("finding SabRol count");

        try {
            String queryString = "select count(*) from SabRol model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
