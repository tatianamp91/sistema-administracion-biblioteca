package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabVolumen;


/**
 * A data access object (DAO) providing persistence and search support for
 * SabVolumen entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SabVolumenDAO {
    private static final Log log = LogFactory.getLog(SabVolumenDAO.class);

    // property constants
    //public static final String  DESCRIPCION = "descripcion";
    public static final String DESCRIPCION = "descripcion";

    //public static final Long  ESTADO = "estado";
    public static final String ESTADO = "estado";

    //public static final Long  IDVOLUMEN = "idVolumen";
    public static final String IDVOLUMEN = "idVolumen";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    /**
    *
    * @param Instance
    *            SabVolumen Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SabVolumen instance) {
        log.debug("saving SabVolumen instance");

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
    *            SabVolumen Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SabVolumen instance) {
        log.debug("deleting SabVolumen instance");

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
    *            SabVolumen Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(SabVolumen instance) {
        log.debug("updating SabVolumen instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabVolumen findById(Long id) {
        log.debug("finding SabVolumen instance with id: " + id);

        try {
            SabVolumen instance = (SabVolumen) getSession()
                                                   .get(SabVolumen.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabVolumen failed", re);
            throw re;
        }
    }

    public List<SabVolumen> findByExample(SabVolumen instance) {
        log.debug("finding SabVolumen instance by example");

        try {
            List results = getSession().createCriteria("SabVolumen")
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
    * Find all  SabVolumen entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< SabVolumen> found by query
        */
    public List<SabVolumen> findByProperty(String propertyName, Object value) {
        log.debug("finding SabVolumen instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from SabVolumen as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabVolumen> findByDescripcion(Object descripcion) {
        return findByProperty(DESCRIPCION, descripcion);
    }

    public List<SabVolumen> findByEstado(Object estado) {
        return findByProperty(ESTADO, estado);
    }

    public List<SabVolumen> findByIdVolumen(Object idVolumen) {
        return findByProperty(IDVOLUMEN, idVolumen);
    }

    /**
    * Find all SabVolumen entities.
    *
    * @return List<SabVolumen> all SabVolumen instances
    */
    public List<SabVolumen> findAll() {
        log.debug("finding all SabVolumen instances");

        try {
            String queryString = "from SabVolumen order by idVolumen";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabVolumen> findByCriteria(String whereCondition) {
        log.debug("finding SabVolumen " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabVolumen model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabVolumen> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabVolumen failed", re);
            throw re;
        }
    }

    public List<SabVolumen> findPageSabVolumen(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabVolumen findPageSabVolumen");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabVolumen model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabVolumen model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabVolumen() {
        log.debug("finding SabVolumen count");

        try {
            String queryString = "select count(*) from SabVolumen model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
