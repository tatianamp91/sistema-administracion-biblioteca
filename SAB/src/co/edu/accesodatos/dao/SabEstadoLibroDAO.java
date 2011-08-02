package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabEstadoLibro;

public class SabEstadoLibroDAO {
    private static final Log log = LogFactory.getLog(SabEstadoLibroDAO.class);

    // property constants
    //public static final String  DESCRIPCION = "descripcion";
    public static final String DESCRIPCION = "descripcion";

    //public static final Long  IDESTADO = "idEstado";
    public static final String IDESTADO = "idEstado";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    public void save(SabEstadoLibro instance) {
        log.debug("saving SabEstadoLibro instance");

        try {
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SabEstadoLibro instance) {
        log.debug("deleting SabEstadoLibro instance");

        try {
            getSession().delete(instance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(SabEstadoLibro instance) {
        log.debug("updating SabEstadoLibro instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabEstadoLibro findById(Long id) {
        log.debug("finding SabEstadoLibro instance with id: " + id);

        try {
            SabEstadoLibro instance = (SabEstadoLibro) getSession()
                                                           .get(SabEstadoLibro.class,
                    id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabEstadoLibro failed", re);
            throw re;
        }
    }

    public List<SabEstadoLibro> findByExample(SabEstadoLibro instance) {
        log.debug("finding SabEstadoLibro instance by example");

        try {
            List results = getSession().createCriteria("SabEstadoLibro")
                               .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " +
                results.size());

            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List<SabEstadoLibro> findByProperty(String propertyName, Object value) {
        log.debug("finding SabEstadoLibro instance with property: " +
            propertyName + ", value: " + value);

        try {
            String queryString = "from SabEstadoLibro as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabEstadoLibro> findByDescripcion(Object descripcion) {
        return findByProperty(DESCRIPCION, descripcion);
    }

    public List<SabEstadoLibro> findByIdEstado(Object idEstado) {
        return findByProperty(IDESTADO, idEstado);
    }

    public List<SabEstadoLibro> findAll() {
        log.debug("finding all SabEstadoLibro instances");

        try {
            String queryString = "from SabEstadoLibro order by idEstado";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabEstadoLibro> findByCriteria(String whereCondition) {
        log.debug("finding SabEstadoLibro " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabEstadoLibro model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabEstadoLibro> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabEstadoLibro failed", re);
            throw re;
        }
    }

    public List<SabEstadoLibro> findPageSabEstadoLibro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabEstadoLibro findPageSabEstadoLibro");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabEstadoLibro model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabEstadoLibro model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabEstadoLibro() {
        log.debug("finding SabEstadoLibro count");

        try {
            String queryString = "select count(*) from SabEstadoLibro model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
