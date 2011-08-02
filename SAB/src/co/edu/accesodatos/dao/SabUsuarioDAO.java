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
import co.edu.unicatolica.modelo.SabUsuario;


/**
 * A data access object (DAO) providing persistence and search support for
 * SabUsuario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class SabUsuarioDAO {
    private static final Log log = LogFactory.getLog(SabUsuarioDAO.class);

    // property constants
    //public static final Long  CODIGO = "codigo";
    public static final String CODIGO = "codigo";

    //public static final String  EMAIL = "email";
    public static final String EMAIL = "email";

    //public static final Long  IDUSUARIO = "idUsuario";
    public static final String IDUSUARIO = "idUsuario";

    //public static final String  NOMBRECOMPLETO = "nombreCompleto";
    public static final String NOMBRECOMPLETO = "nombreCompleto";

    //public static final Long  NUMIDENTIFICACION = "numIdentificacion";
    public static final String NUMIDENTIFICACION = "numIdentificacion";

    private Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    /**
    *
    * @param Instance
    *            SabUsuario Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(SabUsuario instance) {
        log.debug("saving SabUsuario instance");

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
    *            SabUsuario Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(SabUsuario instance) {
        log.debug("deleting SabUsuario instance");

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
    *            SabUsuario Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(SabUsuario instance) {
        log.debug("updating SabUsuario instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public SabUsuario findById(Long id) {
        log.debug("finding SabUsuario instance with id: " + id);

        try {
            SabUsuario instance = (SabUsuario) getSession()
                                                   .get(SabUsuario.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding SabUsuario failed", re);
            throw re;
        }
    }

    public List<SabUsuario> findByExample(SabUsuario instance) {
        log.debug("finding SabUsuario instance by example");

        try {
            List results = getSession().createCriteria("SabUsuario")
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
    * Find all  SabUsuario entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< SabUsuario> found by query
        */
    public List<SabUsuario> findByProperty(String propertyName, Object value) {
        log.debug("finding SabUsuario instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from SabUsuario as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<SabUsuario> findByCodigo(Object codigo) {
        return findByProperty(CODIGO, codigo);
    }

    public List<SabUsuario> findByEmail(Object email) {
        return findByProperty(EMAIL, email);
    }

    public List<SabUsuario> findByIdUsuario(Object idUsuario) {
        return findByProperty(IDUSUARIO, idUsuario);
    }

    public List<SabUsuario> findByNombreCompleto(Object nombreCompleto) {
        return findByProperty(NOMBRECOMPLETO, nombreCompleto);
    }

    public List<SabUsuario> findByNumIdentificacion(Object numIdentificacion) {
        return findByProperty(NUMIDENTIFICACION, numIdentificacion);
    }

    /**
    * Find all SabUsuario entities.
    *
    * @return List<SabUsuario> all SabUsuario instances
    */
    public List<SabUsuario> findAll() {
        log.debug("finding all SabUsuario instances");

        try {
            String queryString = "from SabUsuario";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<SabUsuario> findByCriteria(String whereCondition) {
        log.debug("finding SabUsuario " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from SabUsuario model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<SabUsuario> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in SabUsuario failed", re);
            throw re;
        }
    }

    public List<SabUsuario> findPageSabUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding SabUsuario findPageSabUsuario");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from SabUsuario model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from SabUsuario model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberSabUsuario() {
        log.debug("finding SabUsuario count");

        try {
            String queryString = "select count(*) from SabUsuario model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public SabUsuario consultarPorCorreoCodigo(String correo, Long codigo) {
        log.debug("finding SapUsuario findPageSapUsuario");
        try {
        	DetachedCriteria criteria = DetachedCriteria.forClass(SabUsuario.class);
        	criteria.add(Restrictions.eq("email", correo));
        	criteria.add(Restrictions.eq("codigo", codigo));
        	
        	List<SabUsuario> usuario = findByCriteria(criteria);
        	if(usuario != null && !usuario.isEmpty()){
        		return usuario.get(0);
        	}else{
        		return null;
        	}
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public SabUsuario consultarPorCodigo(Long codigo) {
        log.debug("finding SapUsuario findPageSapUsuario");
        try {
        	DetachedCriteria criteria = DetachedCriteria.forClass(SabUsuario.class);
        	criteria.add(Restrictions.eq("codigo", codigo));
        	
        	List<SabUsuario> usuario = findByCriteria(criteria);
        	if(usuario != null && !usuario.isEmpty()){
        		return usuario.get(0);
        	}else{
        		return null;
        	}
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List<SabUsuario> findByCriteria(DetachedCriteria criteria) {
		if (criteria == null) {
			throw new IllegalArgumentException("DetachedCriteria must not be null");
		}
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		return executableCriteria.list();
	}


}
