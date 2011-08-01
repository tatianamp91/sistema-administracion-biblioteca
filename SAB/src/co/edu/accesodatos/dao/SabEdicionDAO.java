package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabEdicion;

public class SabEdicionDAO {
    private static final Log log = LogFactory.getLog(SabEdicionDAO.class);

    // property constants
    //public static final String  DESCRIPCION = "descripcion";
    public static final String DESCRIPCION = "descripcion";

    //public static final Long  ESTADO = "estado";
    public static final String ESTADO = "estado";

    //public static final Long  IDEDICION = "idEdicion";
    public static final String IDEDICION = "idEdicion";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    /**
    *
    * @param Instance
    *            SabEdicion Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SabEdicion instance) {
        log.debug("saving SabEdicion instance");

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
    *            SabEdicion Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SabEdicion instance) {
        log.debug("deleting SabEdicion instance");

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
    *            SabEdicion Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(SabEdicion instance) {
        log.debug("updating SabEdicion instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabEdicion findById(Long id) {
        log.debug("finding SabEdicion instance with id: " + id);

        try {
            SabEdicion instance = (SabEdicion) getSession()
                                                   .get(SabEdicion.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabEdicion failed", re);
            throw re;
        }
    }

    public List<SabEdicion> findByExample(SabEdicion instance) {
        log.debug("finding SabEdicion instance by example");

        try {
            List results = getSession().createCriteria("SabEdicion")
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
    * Find all  SabEdicion entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< SabEdicion> found by query
        */
    public List<SabEdicion> findByProperty(String propertyName, Object value) {
        log.debug("finding SabEdicion instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from SabEdicion as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabEdicion> findByDescripcion(Object descripcion) {
        return findByProperty(DESCRIPCION, descripcion);
    }

    public List<SabEdicion> findByEstado(Object estado) {
        return findByProperty(ESTADO, estado);
    }

    public List<SabEdicion> findByIdEdicion(Object idEdicion) {
        return findByProperty(IDEDICION, idEdicion);
    }

    /**
    * Find all SabEdicion entities.
    *
    * @return List<SabEdicion> all SabEdicion instances
    */
    public List<SabEdicion> findAll() {
        log.debug("finding all SabEdicion instances");

        try {
            String queryString = "from SabEdicion";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabEdicion> findByCriteria(String whereCondition) {
        log.debug("finding SabEdicion " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabEdicion model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabEdicion> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabEdicion failed", re);
            throw re;
        }
    }

    public List<SabEdicion> findPageSabEdicion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabEdicion findPageSabEdicion");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabEdicion model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabEdicion model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabEdicion() {
        log.debug("finding SabEdicion count");

        try {
            String queryString = "select count(*) from SabEdicion model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
