package co.edu.accesodatos.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabLibro;

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

    public List<SabLibro> findAll() {
        log.debug("finding all SabLibro instances");

        try {
            String queryString = "from SabLibro order by idLibro";
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
    
    public List<SabLibro> findByCriteria(DetachedCriteria criteria) {
		if (criteria == null) {
			throw new IllegalArgumentException("DetachedCriteria must not be null");
		}
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		return executableCriteria.list();
	}
    
    public List<SabLibro> consultarLibrosFiltro(Long idLibro, String titulo, Long idArea, String autor){
    	try {
	    	DetachedCriteria criteria = DetachedCriteria.forClass(SabLibro.class);
	    	
	    	if(idLibro != null){
	    		criteria.add(Restrictions.eq("idLibro", idLibro));
	    	}
	    	if(titulo != null && !titulo.isEmpty()){
	    		criteria.add(Restrictions.like("titulo", titulo, MatchMode.ANYWHERE));
	    	}
	    	if(idArea != null && !idArea.equals(0L)){
	    		criteria.add(Restrictions.eq("sabArea.idArea", idArea));
	    	}
	    	if(autor != null && !autor.isEmpty()){
	    		criteria.createCriteria("sabLibroAutors").createCriteria("sabAutor").add(Restrictions.like("nombre", autor, MatchMode.ANYWHERE));
	    	}
	    	
	    	criteria.addOrder(Order.asc("titulo"));
	    	return findByCriteria(criteria);
    	} catch (RuntimeException re) {
            throw re;
        }
    }
    
}
