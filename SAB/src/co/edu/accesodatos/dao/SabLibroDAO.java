package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabLibro;


/**
 * A data access object (DAO) providing persistence and search support for
 * SabLibro entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SabLibroDAO {
    private static final Log log = LogFactory.getLog(SabLibroDAO.class);

    // property constants
    //public static final Long  CANTIDAD = "cantidad";
    public static final String CANTIDAD = "cantidad";

    //public static final Long  CANTIDADPRESTADOS = "cantidadPrestados";
    public static final String CANTIDADPRESTADOS = "cantidadPrestados";

    //public static final Long  IDLIBRO = "idLibro";
    public static final String IDLIBRO = "idLibro";

    //public static final String  TITULO = "titulo";
    public static final String TITULO = "titulo";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    /**
    *
    * @param Instance
    *            SabLibro Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SabLibro instance) {
        log.debug("saving SabLibro instance");

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
    *            SabLibro Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SabLibro instance) {
        log.debug("deleting SabLibro instance");

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
    *            SabLibro Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(SabLibro instance) {
        log.debug("updating SabLibro instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabLibro findById(Long id) {
        log.debug("finding SabLibro instance with id: " + id);

        try {
            SabLibro instance = (SabLibro) getSession().get(SabLibro.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabLibro failed", re);
            throw re;
        }
    }

    public List<SabLibro> findByExample(SabLibro instance) {
        log.debug("finding SabLibro instance by example");

        try {
            List results = getSession().createCriteria("SabLibro")
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
    * Find all  SabLibro entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< SabLibro> found by query
        */
    public List<SabLibro> findByProperty(String propertyName, Object value) {
        log.debug("finding SabLibro instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from SabLibro as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabLibro> findByCantidad(Object cantidad) {
        return findByProperty(CANTIDAD, cantidad);
    }

    public List<SabLibro> findByCantidadPrestados(Object cantidadPrestados) {
        return findByProperty(CANTIDADPRESTADOS, cantidadPrestados);
    }

    public List<SabLibro> findByIdLibro(Object idLibro) {
        return findByProperty(IDLIBRO, idLibro);
    }

    public List<SabLibro> findByTitulo(Object titulo) {
        return findByProperty(TITULO, titulo);
    }

    /**
    * Find all SabLibro entities.
    *
    * @return List<SabLibro> all SabLibro instances
    */
    public List<SabLibro> findAll() {
        log.debug("finding all SabLibro instances");

        try {
            String queryString = "from SabLibro";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabLibro> findByCriteria(String whereCondition) {
        log.debug("finding SabLibro " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabLibro model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabLibro> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabLibro failed", re);
            throw re;
        }
    }

    public List<SabLibro> findPageSabLibro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabLibro findPageSabLibro");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabLibro model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabLibro model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabLibro() {
        log.debug("finding SabLibro count");

        try {
            String queryString = "select count(*) from SabLibro model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
