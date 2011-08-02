package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabEditorial;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;


public class SabEditorialLogica {
    
	public List<SabEditorial> getSabEditorial() throws Exception {
        List<SabEditorial> list = new ArrayList<SabEditorial>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabEditorialDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void saveSabEditorial(Long estado, String nombre) throws Exception {
        SabEditorial entity = null;

        try {
            if (nombre == null) {
            	throw new Exception(FacesUtils.getMensaje("error.nombre"));
            }
            if ((nombre != null) && (Utilities.checkWordAndCheckWithlength(nombre, 50) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.nombre.no.valido"));
            }
            if (estado == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.estado"));
            }
            if ((estado != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 1, 0) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.estado.no.valido"));
            }

            entity = new SabEditorial();
            entity.setEstado(estado);
            entity.setNombre(nombre);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEditorialDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabEditorial(Long idEditorial) throws Exception {
        SabEditorial entity = null;

        if (idEditorial == null) {
        	throw new Exception(FacesUtils.getMensaje("error.idEditorial"));
        }

        List<SabLibro> sabLibros = null;
        entity = getSabEditorial(idEditorial);

        if (entity == null) {
        	throw new Exception(FacesUtils.getMensaje("error.editorial.no.encontrado"));
        }

        try {
            sabLibros = XMLHibernateDaoFactory.getInstance().getSabLibroDAO().findByProperty("sabEditorial.idEditorial",idEditorial);

            if (Utilities.validationsList(sabLibros) == true) {
            	throw new Exception(FacesUtils.getMensaje("error.editorial.no.eliminar"));
            }

            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEditorialDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabEditorial(Long estado, Long idEditorial, String nombre)
        throws Exception {
        SabEditorial entity = null;

        try {
            if (idEditorial == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idEditorial"));
            }

            if ((idEditorial != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +idEditorial, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idEditorial.no.valido"));
            }

            if (nombre == null) {
            	throw new Exception(FacesUtils.getMensaje("error.nombre"));
            }
            if ((nombre != null) && (Utilities.checkWordAndCheckWithlength(nombre, 50) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.nombre.no.valido"));
            }
            if (estado == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.estado"));
            }
            if ((estado != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 1, 0) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.estado.no.valido"));
            }

            entity = getSabEditorial(idEditorial);

            if (entity == null) {
            	throw new Exception(FacesUtils.getMensaje("error.editorial.no.encontrado"));
            }

            entity.setEstado(estado);
            entity.setIdEditorial(idEditorial);
            entity.setNombre(nombre);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEditorialDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabEditorial getSabEditorial(Long idEditorial)
        throws Exception {
        SabEditorial entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabEditorialDAO().findById(idEditorial);
        } catch (Exception e) {
            throw e;
        }

        return entity;
    }

}
