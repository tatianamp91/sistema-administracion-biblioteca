package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabAutor;

public class SabAutorDAO {
    private static final Log log = LogFactory.getLog(SabAutorDAO.class);

    // property constants
    //public static final Long  IDAUTOR = "idAutor";
    public static final String IDAUTOR = "idAutor";

    //public static final String  NOMBRE = "nombre";
    public static final String NOMBRE = "nombre";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    public void save(SabAutor instance) {
        log.debug("saving SabAutor instance");

        try {
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SabAutor instance) {
        log.debug("deleting SabAutor instance");

        try {
            getSession().delete(instance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(SabAutor instance) {
        log.debug("updating SabAutor instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabAutor findById(Long id) {
        log.debug("finding SabAutor instance with id: " + id);

        try {
            SabAutor instance = (SabAutor) getSession().get(SabAutor.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabAutor failed", re);
            throw re;
        }
    }

    public List<SabAutor> findByExample(SabAutor instance) {
        log.debug("finding SabAutor instance by example");

        try {
            List results = getSession().createCriteria("SabAutor")
                               .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " +
                results.size());

            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List<SabAutor> findByProperty(String propertyName, Object value) {
        log.debug("finding SabAutor instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from SabAutor as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabAutor> findByIdAutor(Object idAutor) {
        return findByProperty(IDAUTOR, idAutor);
    }

    public List<SabAutor> findByNombre(Object nombre) {
        return findByProperty(NOMBRE, nombre);
    }

    public List<SabAutor> findAll() {
        log.debug("finding all SabAutor instances");

        try {
            String queryString = "from SabAutor order by idAutor";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabAutor> findByCriteria(String whereCondition) {
        log.debug("finding SabAutor " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabAutor model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabAutor> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabAutor failed", re);
            throw re;
        }
    }

    public List<SabAutor> findPageSabAutor(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabAutor findPageSabAutor");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabAutor model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabAutor model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabAutor() {
        log.debug("finding SabAutor count");

        try {
            String queryString = "select count(*) from SabAutor model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
