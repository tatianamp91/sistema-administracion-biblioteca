package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabLibroAutor;
import co.edu.unicatolica.modelo.SabLibroAutorId;

public class SabLibroAutorDAO {
    private static final Log log = LogFactory.getLog(SabLibroAutorDAO.class);

    // property constants
    //public static final SabLibroAutorId  ID = "id";
    public static final String ID = "id";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    public void save(SabLibroAutor instance) {
        log.debug("saving SabLibroAutor instance");

        try {
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SabLibroAutor instance) {
        log.debug("deleting SabLibroAutor instance");

        try {
            getSession().delete(instance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(SabLibroAutor instance) {
        log.debug("updating SabLibroAutor instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabLibroAutor findById(SabLibroAutorId id) {
        log.debug("finding SabLibroAutor instance with id: " + id);

        try {
            SabLibroAutor instance = (SabLibroAutor) getSession()
                                                         .get(SabLibroAutor.class,
                    id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabLibroAutor failed", re);
            throw re;
        }
    }

    public List<SabLibroAutor> findByExample(SabLibroAutor instance) {
        log.debug("finding SabLibroAutor instance by example");

        try {
            List results = getSession().createCriteria("SabLibroAutor")
                               .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " +
                results.size());

            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List<SabLibroAutor> findByProperty(String propertyName, Object value) {
        log.debug("finding SabLibroAutor instance with property: " +
            propertyName + ", value: " + value);

        try {
            String queryString = "from SabLibroAutor as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabLibroAutor> findById(Object id) {
        return findByProperty(ID, id);
    }

    public List<SabLibroAutor> findAll() {
        log.debug("finding all SabLibroAutor instances");

        try {
            String queryString = "from SabLibroAutor";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabLibroAutor> findByCriteria(String whereCondition) {
        log.debug("finding SabLibroAutor " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabLibroAutor model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabLibroAutor> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabLibroAutor failed", re);
            throw re;
        }
    }

    public List<SabLibroAutor> findPageSabLibroAutor(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabLibroAutor findPageSabLibroAutor");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabLibroAutor model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabLibroAutor model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabLibroAutor() {
        log.debug("finding SabLibroAutor count");

        try {
            String queryString = "select count(*) from SabLibroAutor model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
