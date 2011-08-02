package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabEdicion;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;


public class SabEdicionLogica {
	
    public List<SabEdicion> getSabEdicion() throws Exception {
        List<SabEdicion> list = new ArrayList<SabEdicion>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabEdicionDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void saveSabEdicion(String descripcion, Long estado) throws Exception {
        SabEdicion entity = null;
        try {
            if (descripcion == null) {
                throw new Exception(FacesUtils.getMensaje("error.descripcion"));
            }
            if ((descripcion != null) && (Utilities.checkWordAndCheckWithlength(descripcion, 20) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.descripcion.no.valido"));
            }
            if (estado == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.estado"));
            }
            if ((estado != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 1, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.estado.no.valido"));
            }

            entity = new SabEdicion();
            entity.setDescripcion(descripcion);
            entity.setEstado(estado);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEdicionDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabEdicion(Long idEdicion) throws Exception {
        SabEdicion entity = null;

        if (idEdicion == null) {
        	throw new Exception(FacesUtils.getMensaje("error.idEdicion"));
        }

        List<SabLibro> sabLibros = null;
        entity = getSabEdicion(idEdicion);

        if (entity == null) {
        	throw new Exception(FacesUtils.getMensaje("error.edicion.no.encontrado"));
        }

        try {
            sabLibros = XMLHibernateDaoFactory.getInstance().getSabLibroDAO().findByProperty("sabEdicion.idEdicion",idEdicion);

            if (Utilities.validationsList(sabLibros) == true) {
            	throw new Exception(FacesUtils.getMensaje("error.edicion.no.eliminar"));
            }

            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEdicionDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabEdicion(String descripcion, Long estado, Long idEdicion)
        throws Exception {
        SabEdicion entity = null;

        try {
        	if (descripcion == null) {
                throw new Exception(FacesUtils.getMensaje("error.descripcion"));
            }
            if ((descripcion != null) && (Utilities.checkWordAndCheckWithlength(descripcion, 20) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.descripcion.no.valido"));
            }
            if (estado == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.estado"));
            }
            if ((estado != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estado, 1, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.estado.no.valido"));
            }
            if (idEdicion == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idEdicion"));
            }
            if ((idEdicion != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idEdicion, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idEdicion.no.valido"));
            }

            entity = getSabEdicion(idEdicion);

            if (entity == null) {
            	throw new Exception(FacesUtils.getMensaje("error.edicion.no.encontrado"));
            }

            entity.setDescripcion(descripcion);
            entity.setEstado(estado);
            entity.setIdEdicion(idEdicion);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabEdicionDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabEdicion getSabEdicion(Long idEdicion) throws Exception {
        SabEdicion entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabEdicionDAO().findById(idEdicion);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }

}
