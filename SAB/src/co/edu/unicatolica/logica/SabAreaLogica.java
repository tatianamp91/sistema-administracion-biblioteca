package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabArea;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;

public class SabAreaLogica {
   
	public List<SabArea> getSabArea() throws Exception {
        List<SabArea> list = new ArrayList<SabArea>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabAreaDAO().findAll();
        } catch (Exception e) {
            throw e;
        } 
        return list;
    }

    public void saveSabArea(Long estado, String nombre)
        throws Exception {
        SabArea entity = null;
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

            entity = new SabArea();
            entity.setEstado(estado);
            entity.setNombre(nombre);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAreaDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabArea(Long idArea) throws Exception {
        SabArea entity = null;

        if (idArea == null) {
            throw new Exception(FacesUtils.getMensaje("error.idArea"));
        }

        List<SabLibro> sabLibros = null;
        entity = getSabArea(idArea);

        if (entity == null) {
            throw new Exception(FacesUtils.getMensaje("error.area.no.encontrada"));
        }

        try {
            sabLibros = XMLHibernateDaoFactory.getInstance().getSabLibroDAO().findByProperty("sabArea.idArea", idArea);

            if (Utilities.validationsList(sabLibros) == true) {
                throw new Exception(FacesUtils.getMensaje("error.area.no.eliminar"));
            }

            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAreaDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabArea(Long estado, Long idArea, String nombre)
        throws Exception {
        SabArea entity = null;

        try {
           
            if (idArea == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idArea"));
            }

            if ((idArea != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idArea, 8, 0) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.idArea.no.valido"));
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
            
            entity = getSabArea(idArea);

            if (entity == null) {
            	throw new Exception(FacesUtils.getMensaje("error.area.no.encontrada"));
            }

            entity.setEstado(estado);
            entity.setIdArea(idArea);
            entity.setNombre(nombre);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAreaDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabArea getSabArea(Long idArea) throws Exception {
        SabArea entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabAreaDAO().findById(idArea);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }

}
