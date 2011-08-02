package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabArea;

public class SabAreaDAO {
    private static final Log log = LogFactory.getLog(SabAreaDAO.class);

    // property constants
    //public static final Long  ESTADO = "estado";
    public static final String ESTADO = "estado";

    //public static final Long  IDAREA = "idArea";
    public static final String IDAREA = "idArea";

    //public static final String  NOMBRE = "nombre";
    public static final String NOMBRE = "nombre";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    public void save(SabArea instance) {
        log.debug("saving SabArea instance");

        try {
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SabArea instance) {
        log.debug("deleting SabArea instance");

        try {
            getSession().delete(instance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(SabArea instance) {
        log.debug("updating SabArea instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabArea findById(Long id) {
        log.debug("finding SabArea instance with id: " + id);

        try {
            SabArea instance = (SabArea) getSession().get(SabArea.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabArea failed", re);
            throw re;
        }
    }

    public List<SabArea> findByExample(SabArea instance) {
        log.debug("finding SabArea instance by example");

        try {
            List results = getSession().createCriteria("SabArea")
                               .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " +
                results.size());

            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List<SabArea> findByProperty(String propertyName, Object value) {
        log.debug("finding SabArea instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from SabArea as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabArea> findByEstado(Object estado) {
        return findByProperty(ESTADO, estado);
    }

    public List<SabArea> findByIdArea(Object idArea) {
        return findByProperty(IDAREA, idArea);
    }

    public List<SabArea> findByNombre(Object nombre) {
        return findByProperty(NOMBRE, nombre);
    }

    public List<SabArea> findAll() {
        log.debug("finding all SabArea instances");

        try {
            String queryString = "from SabArea order by idArea";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabArea> findByCriteria(String whereCondition) {
        log.debug("finding SabArea " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabArea model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabArea> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabArea failed", re);
            throw re;
        }
    }

    public List<SabArea> findPageSabArea(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabArea findPageSabArea");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabArea model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabArea model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabArea() {
        log.debug("finding SabArea count");

        try {
            String queryString = "select count(*) from SabArea model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
