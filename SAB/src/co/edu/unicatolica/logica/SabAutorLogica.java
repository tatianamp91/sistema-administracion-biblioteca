package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabAutor;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;

public class SabAutorLogica {
    
	public List<SabAutor> getSabAutor() throws Exception {
        List<SabAutor> list = new ArrayList<SabAutor>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabAutorDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void saveSabAutor(String nombre)throws Exception {
        SabAutor entity = null;
        try {
            if (nombre == null) {
            	throw new Exception(FacesUtils.getMensaje("error.nombre"));
            }

            if ((nombre != null) && (Utilities.checkWordAndCheckWithlength(nombre, 50) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.nombre.no.valido"));
            }

            entity = new SabAutor();
            entity.setNombre(nombre);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAutorDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabAutor(Long idAutor) throws Exception {
        SabAutor entity = null;
        if (idAutor == null) {
            throw new Exception(FacesUtils.getMensaje("error.idAutor"));
        }
        entity = getSabAutor(idAutor);
        if (entity == null) {
            throw new Exception(FacesUtils.getMensaje("error.autor.no.encontrado"));
        }
        try {
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAutorDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabAutor(Long idAutor, String nombre) throws Exception {
        SabAutor entity = null;

        try {
            if (idAutor == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idAutor"));
            }
            if ((idAutor != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idAutor, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idAutor.no.valido"));
            }
            if (nombre == null) {
            	throw new Exception(FacesUtils.getMensaje("error.nombre"));
            }
            if ((nombre != null) && (Utilities.checkWordAndCheckWithlength(nombre, 50) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.nombre.no.valido"));
            }

            entity = getSabAutor(idAutor);

            if (entity == null) {
            	throw new Exception(FacesUtils.getMensaje("error.autor.no.encontrado"));
            }

            entity.setIdAutor(idAutor);
            entity.setNombre(nombre);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAutorDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabAutor getSabAutor(Long idAutor) throws Exception {
        SabAutor entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabAutorDAO().findById(idAutor);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }

}
