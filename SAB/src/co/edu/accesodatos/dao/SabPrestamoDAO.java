package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabPrestamo;

public class SabPrestamoDAO {
    private static final Log log = LogFactory.getLog(SabPrestamoDAO.class);

    public static final String ESTADOPRESTAMO = "estadoPrestamo";

    public static final String FECHADEVOLUCION = "fechaDevolucion";

    public static final String FECHAPRESTAMO = "fechaPrestamo";

    public static final String FECHAREALDEVOLUCION = "fechaRealDevolucion";

    public static final String ID = "id";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    public void save(SabPrestamo instance) {
        log.debug("saving SabPrestamo instance");

        try {
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SabPrestamo instance) {
        log.debug("deleting SabPrestamo instance");

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
    *            SabPrestamo Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(SabPrestamo instance) {
        log.debug("updating SabPrestamo instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabPrestamo findById(Long id) {
        log.debug("finding SabPrestamo instance with id: " + id);

        try {
            SabPrestamo instance = (SabPrestamo) getSession()
                                                     .get(SabPrestamo.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabPrestamo failed", re);
            throw re;
        }
    }

    public List<SabPrestamo> findByExample(SabPrestamo instance) {
        log.debug("finding SabPrestamo instance by example");

        try {
            List results = getSession().createCriteria(SabPrestamo.class)
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
    * Find all  SabPrestamo entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< SabPrestamo> found by query
        */
    public List<SabPrestamo> findByProperty(String propertyName, Object value) {
        log.debug("finding SabPrestamo instance with property: " +
            propertyName + ", value: " + value);

        try {
            String queryString = "from SabPrestamo as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabPrestamo> findByEstadoPrestamo(Object estadoPrestamo) {
        return findByProperty(ESTADOPRESTAMO, estadoPrestamo);
    }

    public List<SabPrestamo> findByFechaDevolucion(Object fechaDevolucion) {
        return findByProperty(FECHADEVOLUCION, fechaDevolucion);
    }

    public List<SabPrestamo> findByFechaPrestamo(Object fechaPrestamo) {
        return findByProperty(FECHAPRESTAMO, fechaPrestamo);
    }

    public List<SabPrestamo> findByFechaRealDevolucion(
        Object fechaRealDevolucion) {
        return findByProperty(FECHAREALDEVOLUCION, fechaRealDevolucion);
    }

    public List<SabPrestamo> findById(Object id) {
        return findByProperty(ID, id);
    }

    /**
    * Find all SabPrestamo entities.
    *
    * @return List<SabPrestamo> all SabPrestamo instances
    */
    public List<SabPrestamo> findAll() {
        log.debug("finding all SabPrestamo instances");

        try {
            String queryString = "from SabPrestamo order by id";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabPrestamo> findByCriteria(String whereCondition) {
        log.debug("finding SabPrestamo " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabPrestamo model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabPrestamo> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabPrestamo failed", re);
            throw re;
        }
    }

    public List<SabPrestamo> findPageSabPrestamo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabPrestamo findPageSabPrestamo");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabPrestamo model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabPrestamo model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabPrestamo() {
        log.debug("finding SabPrestamo count");

        try {
            String queryString = "select count(*) from SabPrestamo model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public List<SabPrestamo> findByCriteria(DetachedCriteria criteria) {
		if (criteria == null) {
			throw new IllegalArgumentException("DetachedCriteria must not be null");
		}
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		return executableCriteria.list();
	}
    
    public List<SabPrestamo> buscarPorUsuarioLibro(Long idLibro, Long idUsuario) {
    	try{
    		DetachedCriteria criteria = DetachedCriteria.forClass(SabPrestamo.class);
    		
    		if(idLibro != null){
    			criteria.add(Restrictions.eq("sabLibro.idLibro", idLibro));
    		}
    		
    		if(idUsuario != null){
    			criteria.add(Restrictions.eq("sabUsuario.idUsuario", idUsuario));
    		}
    		
    		return findByCriteria(criteria);
    	} catch (RuntimeException re) {
            log.error("buscar por usuario falló", re);
            throw re;
        }
    }
}
