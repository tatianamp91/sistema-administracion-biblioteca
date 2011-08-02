package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.unicatolica.modelo.SabVolumen;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;

public class SabVolumenLogica {
	
    public List<SabVolumen> getSabVolumen() throws Exception {
        List<SabVolumen> list = new ArrayList<SabVolumen>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabVolumenDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void saveSabVolumen(String descripcion, Long estado)
        throws Exception {
        SabVolumen entity = null;

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

            entity = new SabVolumen();
            entity.setDescripcion(descripcion);
            entity.setEstado(estado);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabVolumenDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabVolumen(Long idVolumen) throws Exception {
        SabVolumen entity = null;

        try {
	        if (idVolumen == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.idVolumen"));
	        }
	
	        List<SabLibro> sabLibros = null;
	        entity = getSabVolumen(idVolumen);
	
	        if (entity == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.volumen.no.encontrado"));
	        }

            sabLibros = XMLHibernateDaoFactory.getInstance().getSabLibroDAO().findByProperty("sabVolumen.idVolumen", idVolumen);

            if (Utilities.validationsList(sabLibros) == true) {
	        	throw new Exception(FacesUtils.getMensaje("error.volumen.no.eliminar"));
            }

            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabVolumenDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabVolumen(String descripcion, Long estado, Long idVolumen)
        throws Exception {
        SabVolumen entity = null;

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
            if (idVolumen == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.idVolumen"));
            }
            if ((idVolumen != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idVolumen, 8, 0) == false)) {
	        	throw new Exception(FacesUtils.getMensaje("error.idVolumen.no.valido"));
            }

            entity = getSabVolumen(idVolumen);

            if (entity == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.volumen.no.encontrado"));
            }

            entity.setDescripcion(descripcion);
            entity.setEstado(estado);
            entity.setIdVolumen(idVolumen);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabVolumenDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabVolumen getSabVolumen(Long idVolumen) throws Exception {
        SabVolumen entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabVolumenDAO().findById(idVolumen);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }

}
