package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabEditorial;

public class SabEditorialDAO {
    private static final Log log = LogFactory.getLog(SabEditorialDAO.class);

    // property constants
    //public static final Long  ESTADO = "estado";
    public static final String ESTADO = "estado";

    //public static final Long  IDEDITORIAL = "idEditorial";
    public static final String IDEDITORIAL = "idEditorial";

    //public static final String  NOMBRE = "nombre";
    public static final String NOMBRE = "nombre";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    public void save(SabEditorial instance) {
        log.debug("saving SabEditorial instance");

        try {
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SabEditorial instance) {
        log.debug("deleting SabEditorial instance");

        try {
            getSession().delete(instance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(SabEditorial instance) {
        log.debug("updating SabEditorial instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabEditorial findById(Long id) {
        log.debug("finding SabEditorial instance with id: " + id);

        try {
            SabEditorial instance = (SabEditorial) getSession()
                                                       .get(SabEditorial.class,
                    id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabEditorial failed", re);
            throw re;
        }
    }

    public List<SabEditorial> findByExample(SabEditorial instance) {
        log.debug("finding SabEditorial instance by example");

        try {
            List results = getSession().createCriteria("SabEditorial")
                               .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " +
                results.size());

            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List<SabEditorial> findByProperty(String propertyName, Object value) {
        log.debug("finding SabEditorial instance with property: " +
            propertyName + ", value: " + value);

        try {
            String queryString = "from SabEditorial as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabEditorial> findByEstado(Object estado) {
        return findByProperty(ESTADO, estado);
    }

    public List<SabEditorial> findByIdEditorial(Object idEditorial) {
        return findByProperty(IDEDITORIAL, idEditorial);
    }

    public List<SabEditorial> findByNombre(Object nombre) {
        return findByProperty(NOMBRE, nombre);
    }

    public List<SabEditorial> findAll() {
        log.debug("finding all SabEditorial instances");

        try {
            String queryString = "from SabEditorial order by idEditorial";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabEditorial> findByCriteria(String whereCondition) {
        log.debug("finding SabEditorial " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabEditorial model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabEditorial> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabEditorial failed", re);
            throw re;
        }
    }

    public List<SabEditorial> findPageSabEditorial(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabEditorial findPageSabEditorial");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabEditorial model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabEditorial model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabEditorial() {
        log.debug("finding SabEditorial count");

        try {
            String queryString = "select count(*) from SabEditorial model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
