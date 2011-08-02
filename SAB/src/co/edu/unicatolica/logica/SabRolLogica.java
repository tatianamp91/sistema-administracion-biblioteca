package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabRol;
import co.edu.unicatolica.modelo.SabUsuario;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;

public class SabRolLogica {
	
    public List<SabRol> getSabRol() throws Exception {
        List<SabRol> list = new ArrayList<SabRol>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabRolDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void saveSabRol(String descripcion) throws Exception {
        SabRol entity = null;
        try {
        	if (descripcion == null) {
                throw new Exception(FacesUtils.getMensaje("error.descripcion"));
            }
            if ((descripcion != null) && (Utilities.checkWordAndCheckWithlength(descripcion, 20) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.descripcion.no.valido"));
            }

            entity = new SabRol();
            entity.setDescripcion(descripcion);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabRolDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabRol(Long idRol) throws Exception {
        SabRol entity = null;

        try {
	        if (idRol == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.idRol"));
	        }
	        List<SabUsuario> sabUsuarios = null;
	        entity = getSabRol(idRol);
	
	        if (entity == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.rol.no.encontrado"));
	        }
            sabUsuarios = XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().findByProperty("sabRol.idRol",idRol);

            if (Utilities.validationsList(sabUsuarios) == true) {
                throw new Exception("error.rol.no.eliminar");
            }

            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabRolDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabRol(String descripcion, Long idRol)
        throws Exception {
        SabRol entity = null;

        try {
        	if (descripcion == null) {
                throw new Exception(FacesUtils.getMensaje("error.descripcion"));
            }
            if ((descripcion != null) && (Utilities.checkWordAndCheckWithlength(descripcion, 20) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.descripcion.no.valido"));
            }

            if (idRol == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.idRol"));
	        }

            if ((idRol != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idRol, 8, 0) == false)) {
	        	throw new Exception(FacesUtils.getMensaje("error.idRol.no.valido"));
            }

            entity = getSabRol(idRol);

            if (entity == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.rol.no.encontrado"));
            }

            entity.setDescripcion(descripcion);
            entity.setIdRol(idRol);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabRolDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabRol getSabRol(Long idRol) throws Exception {
        SabRol entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabRolDAO().findById(idRol);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }

}
