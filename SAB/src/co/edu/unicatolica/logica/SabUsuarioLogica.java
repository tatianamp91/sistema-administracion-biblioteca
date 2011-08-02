package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabPrestamo;
import co.edu.unicatolica.modelo.SabRol;
import co.edu.unicatolica.modelo.SabUsuario;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;

public class SabUsuarioLogica {
    
	public List<SabUsuario> getSabUsuario() throws Exception {
        List<SabUsuario> list = new ArrayList<SabUsuario>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void saveSabUsuario(String codigo, String email,
        String nombreCompleto, Long numIdentificacion, Long idRol_SabRol)
        throws Exception {
        SabUsuario entity = null;

        try {
            if (codigo == null) {
                throw new Exception(FacesUtils.getMensaje("error.codigo"));
            }
            if ((codigo != null) && (Utilities.checkWordAndCheckWithlength(codigo, 10) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.codigo.no.valido"));
            }
            if (numIdentificacion == null) {
                throw new Exception(FacesUtils.getMensaje("error.numId"));
            }
            if ((numIdentificacion != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + numIdentificacion, 20, 0) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.numId.no.valido"));
            }
            if (nombreCompleto == null) {
                throw new Exception(FacesUtils.getMensaje("error.nombre"));
            }
            if ((nombreCompleto != null) && (Utilities.checkWordAndCheckWithlength(nombreCompleto, 50) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.nombre.no.valido"));
            }
            if (idRol_SabRol == null) {
                throw new Exception(FacesUtils.getMensaje("error.idRol"));
            }

            if ((idRol_SabRol != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idRol_SabRol, 8, 0) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.idRol.no.valido"));
            }
            if (email == null) {
                throw new Exception(FacesUtils.getMensaje("error.correo"));
            }
            if ((email != null) && (Utilities.checkWordAndCheckWithlength(email, 50) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.correo.no.valido"));
            }

            SabRolLogica logicSabRol1 = new SabRolLogica();
            SabRol sabRolClass = logicSabRol1.getSabRol(idRol_SabRol);

            if (sabRolClass == null) {
                throw new Exception(FacesUtils.getMensaje("error.rol.no.encontrado"));
            }

            entity = new SabUsuario();
            entity.setCodigo(codigo);
            entity.setEmail(email);
            entity.setNombreCompleto(nombreCompleto);
            entity.setNumIdentificacion(numIdentificacion);
            entity.setSabRol(sabRolClass);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabUsuario(Long idUsuario) throws Exception {
        SabUsuario entity = null;

        if (idUsuario == null) {
            throw new Exception(FacesUtils.getMensaje("error.idUsuario"));
        }

        List<SabPrestamo> sabPrestamos = null;
        entity = getSabUsuario(idUsuario);

        if (entity == null) {
            throw new Exception(FacesUtils.getMensaje("error.usuario.no.encontrado"));
        }

        try {
            sabPrestamos = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().findByProperty("sabUsuario.idUsuario",idUsuario);

            if (Utilities.validationsList(sabPrestamos) == true) {
                throw new Exception(FacesUtils.getMensaje("error.usuario.no.eliminar"));
            }

            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabUsuario(String codigo, String email, Long idUsuario,
        String nombreCompleto, Long numIdentificacion, Long idRol_SabRol)
        throws Exception {
        SabUsuario entity = null;

        try {
        	if (codigo == null) {
                throw new Exception(FacesUtils.getMensaje("error.codigo"));
            }

            if ((codigo != null) && (Utilities.checkWordAndCheckWithlength(codigo, 10) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.codigo.no.valido"));
            }

            if (email == null) {
                throw new Exception(FacesUtils.getMensaje("error.correo"));
            }

            if ((email != null) && (Utilities.checkWordAndCheckWithlength(email, 50) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.correo.no.valido"));
            }

            if (idUsuario == null) {
                throw new Exception(FacesUtils.getMensaje("error.idUsuario"));
            }

            if ((idUsuario != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idUsuario, 8, 0) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.idUsuario.no.valido"));
            }

            if (nombreCompleto == null) {
                throw new Exception(FacesUtils.getMensaje("error.nombre"));
            }

            if ((nombreCompleto != null) && (Utilities.checkWordAndCheckWithlength(nombreCompleto, 50) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.nombre.no.valido"));
            }

            if (numIdentificacion == null) {
                throw new Exception(FacesUtils.getMensaje("error.numId"));
            }

            if ((numIdentificacion != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + numIdentificacion, 20, 0) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.numId.no.valido"));
            }

            if (idRol_SabRol == null) {
                throw new Exception(FacesUtils.getMensaje("error.idRol"));
            }

            if ((idRol_SabRol != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idRol_SabRol, 8, 0) == false)) {
                throw new Exception(FacesUtils.getMensaje("error.idRol.no.valido"));
            }

            SabRolLogica logicSabRol1 = new SabRolLogica();
            SabRol sabRolClass = logicSabRol1.getSabRol(idRol_SabRol);

            if (sabRolClass == null) {
                throw new Exception(FacesUtils.getMensaje("error.rol.no.encontrado"));
            }

            entity = getSabUsuario(idUsuario);

            if (entity == null) {
                throw new Exception(FacesUtils.getMensaje("error.usuario.no.encontrado"));
            }

            entity.setCodigo(codigo);
            entity.setEmail(email);
            entity.setIdUsuario(idUsuario);
            entity.setNombreCompleto(nombreCompleto);
            entity.setNumIdentificacion(numIdentificacion);
            entity.setSabRol(sabRolClass);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabUsuario getSabUsuario(Long idUsuario) throws Exception {
        SabUsuario entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().findById(idUsuario);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }
    
    public SabUsuario consultarPorCorreoCodigo(String correo, String codigo)throws Exception{
		SabUsuario entity =null;
		try {
			entity = XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().consultarPorCorreoCodigo(correo, codigo);
		} catch (Exception e) {
			throw e;
		}
		return entity;
	}

    public SabUsuario consultarPorCodigo(String codigo)throws Exception{
		SabUsuario entity = null;
		try {
			entity = XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().consultarPorCodigo(codigo);
		} catch (Exception e) {
			throw e;
		}
		return entity;
	}

}
