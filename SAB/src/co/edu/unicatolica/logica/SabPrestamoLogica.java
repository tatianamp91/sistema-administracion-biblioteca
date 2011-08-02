package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabEstadoLibro;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.unicatolica.modelo.SabPrestamo;
import co.edu.unicatolica.modelo.SabUsuario;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;

public class SabPrestamoLogica {
	
    public List<SabPrestamo> getSabPrestamo() throws Exception {
        List<SabPrestamo> list = new ArrayList<SabPrestamo>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void saveSabPrestamo(Long idLibro, Long idUsuario,
        Long estadoPrestamo, Date fechaDevolucion, Date fechaPrestamo,
        Date fechaRealDevolucion) throws Exception {
        SabPrestamo entity = null;

        try {
        	if (idLibro == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idLibro"));
            }
            if ((idLibro != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +idLibro, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idLibro.no.valido"));
            }

            if (idUsuario == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idUsuario"));
            }

            if ((idUsuario != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idUsuario, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idUsuario.no.valido"));
            }

            if (estadoPrestamo == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idEstadoPrestamo"));
            }

            if ((estadoPrestamo != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + estadoPrestamo, 1, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idEstadoPrestamo.no.valido"));
            }

            if (fechaDevolucion == null) {
            	throw new Exception(FacesUtils.getMensaje("error.fechaDevolucion"));
            }

            if (fechaPrestamo == null) {
            	throw new Exception(FacesUtils.getMensaje("error.fechaPrestamo"));
            }
            
            SabLibro libro = XMLHibernateDaoFactory.getInstance().getSabLibroDAO().findById(idLibro);
            SabUsuario usuario = XMLHibernateDaoFactory.getInstance().getSabUsuarioDAO().findById(idUsuario);
            
            entity = new SabPrestamo();
            entity.setEstadoPrestamo(estadoPrestamo);
            entity.setFechaDevolucion(fechaDevolucion);
            entity.setFechaPrestamo(fechaPrestamo);
            entity.setFechaRealDevolucion(fechaRealDevolucion);
            entity.setSabLibro(libro);
            entity.setSabUsuario(usuario);
            
            if(libro != null){
            	libro.setCantidadPrestados(libro.getCantidadPrestados() + 1);
            	Long idEstadoLibro;
            	if(libro.getCantidad().equals(libro.getCantidadPrestados())){
		    		idEstadoLibro = Long.parseLong(FacesUtils.getEtiqueta("estadoInactivo"));
            	}else{
            		idEstadoLibro = Long.parseLong(FacesUtils.getEtiqueta("estadoActivo"));
            	}
            	
            	SabEstadoLibro sabEstadoLibro = XMLHibernateDaoFactory.getInstance().getSabEstadoLibroDAO().findById(idEstadoLibro);
            	
            	libro.setSabEstadoLibro(sabEstadoLibro);
            }else{
            	throw new Exception(FacesUtils.getMensaje("error.libro.no.encontrado"));
            }
            
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().save(entity);
            XMLHibernateDaoFactory.getInstance().getSabLibroDAO().update(libro);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    
    public void devolverSabPrestamo(Long idPrestamo, Long estadoPrestamo) throws Exception{
    	try{
    		SabPrestamo prestamo = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().findById(idPrestamo);
    		
    		if(prestamo == null){
            	throw new Exception(FacesUtils.getMensaje("error.prestamo"));
    		}
    		
    		prestamo.setFechaRealDevolucion(new Date());
    		prestamo.setEstadoPrestamo(estadoPrestamo);
    		
    		SabLibro libro = prestamo.getSabLibro();
    		libro.setCantidadPrestados(libro.getCantidadPrestados() -1);
    		
    		Long idEstadoLibro;
    		if(libro.getCantidad() > libro.getCantidadPrestados()){
    			idEstadoLibro = Long.parseLong(FacesUtils.getEtiqueta("estadoActivo"));
        	}else{
        		idEstadoLibro = Long.parseLong(FacesUtils.getEtiqueta("estadoInactivo"));
        	}
			SabEstadoLibro sabEstadoLibro = XMLHibernateDaoFactory.getInstance().getSabEstadoLibroDAO().findById(idEstadoLibro);
			libro.setSabEstadoLibro(sabEstadoLibro);
    			
    		HibernateSessionFactory.beginTransaction();
    		XMLHibernateDaoFactory.getInstance().getSabLibroDAO().update(libro);
    		XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().update(prestamo);
    		HibernateSessionFactory.commit();
    	}catch (Exception e) {
			HibernateSessionFactory.rollback();
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
    }
    
    public SabPrestamo getSabPrestamo(Long idPrestamo) throws Exception {
        SabPrestamo entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().findById(idPrestamo);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }
    
    public List<SabPrestamo> buscarPorUsuarioLibro(Long idLibro, String idUsuario) throws Exception{
    	try {
			return XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().buscarPorUsuarioLibro(idLibro, idUsuario);
		} catch (Exception e) {
			throw e;
		}
    }
    
}
