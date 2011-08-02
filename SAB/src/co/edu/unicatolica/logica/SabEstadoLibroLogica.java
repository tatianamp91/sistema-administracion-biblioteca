package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabEstadoLibro;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;


public class SabEstadoLibroLogica {
	
    public List<SabEstadoLibro> getSabEstadoLibro() throws Exception {
        List<SabEstadoLibro> list = new ArrayList<SabEstadoLibro>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabEstadoLibroDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void saveSabEstadoLibro(String descripcion) throws Exception {
        SabEstadoLibro entity = null;

        try {
        	if (descripcion == null) {
                throw new Exception(FacesUtils.getMensaje("error.descripcion"));
            }
            if ((descripcion != null) && (Utilities.checkWordAndCheckWithlength(descripcion, 20) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.descripcion.no.valido"));
            }

            entity = new SabEstadoLibro();
            entity.setDescripcion(descripcion);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEstadoLibroDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabEstadoLibro(Long idEstado) throws Exception {
        SabEstadoLibro entity = null;

        try {
	        if (idEstado == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.idEstadoLibro"));
	        }
	        List<SabLibro> sabLibros = null;
	        entity = getSabEstadoLibro(idEstado);
	
	        if (entity == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.estadoLibro.no.encontrado"));
	        }

            sabLibros = XMLHibernateDaoFactory.getInstance().getSabLibroDAO().findByProperty("sabEstadoLibro.idEstado",idEstado);
            if (Utilities.validationsList(sabLibros) == true) {
            	throw new Exception(FacesUtils.getMensaje("error.estadoLibro.no.eliminar"));
            }
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEstadoLibroDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabEstadoLibro(String descripcion, Long idEstado)
        throws Exception {
        SabEstadoLibro entity = null;

        try {
        	if (descripcion == null) {
                throw new Exception(FacesUtils.getMensaje("error.descripcion"));
            }
            if ((descripcion != null) && (Utilities.checkWordAndCheckWithlength(descripcion, 20) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.descripcion.no.valido"));
            }

            if (idEstado == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.idEstadoLibro"));
	        }

            if ((idEstado != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idEstado, 8, 0) == false)) {
	        	throw new Exception(FacesUtils.getMensaje("error.idEstadoLibro.no.valido"));
            }

            entity = getSabEstadoLibro(idEstado);

            if (entity == null) {
            	throw new Exception(FacesUtils.getMensaje("error.estadoLibro.no.encontrado"));
            }

            entity.setDescripcion(descripcion);
            entity.setIdEstado(idEstado);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEstadoLibroDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabEstadoLibro getSabEstadoLibro(Long idEstado) throws Exception {
        SabEstadoLibro entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabEstadoLibroDAO().findById(idEstado);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }

}
