package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.unicatolica.modelo.SabArea;
import co.edu.unicatolica.modelo.SabAutor;
import co.edu.unicatolica.modelo.SabEdicion;
import co.edu.unicatolica.modelo.SabEditorial;
import co.edu.unicatolica.modelo.SabEstadoLibro;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.unicatolica.modelo.SabLibroAutor;
import co.edu.unicatolica.modelo.SabLibroAutorId;
import co.edu.unicatolica.modelo.SabPrestamo;
import co.edu.unicatolica.modelo.SabVolumen;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;

public class SabLibroLogica {
   
	public List<SabLibro> getSabLibro() throws Exception {
        List<SabLibro> list = new ArrayList<SabLibro>();
        try {
            list = XMLHibernateDaoFactory.getInstance().getSabLibroDAO().findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

	public void saveSabLibro(Long cantidad, Long cantidadPrestados, String titulo,
        Long idArea_SabArea, Long idEdicion_SabEdicion, Long idEditorial_SabEditorial,
        Long idEstado_SabEstadoLibro, Long idVolumen_SabVolumen, List<SabAutor> listAutores)
        throws Exception {
        SabLibro entity = null;

        try {
        	if (titulo == null) {
        		throw new Exception(FacesUtils.getMensaje("error.titulo"));
        	}
        	
        	if ((titulo != null) && (Utilities.checkWordAndCheckWithlength(titulo, 50) == false)) {
        		throw new Exception(FacesUtils.getMensaje("error.titulo.no.valido"));
        	}
        	if (idEdicion_SabEdicion == 0L) {
        		throw new Exception(FacesUtils.getMensaje("error.idEdicion"));
        	}
        	
        	if ((idEdicion_SabEdicion != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idEdicion_SabEdicion, 8, 0) == false)) {
        		throw new Exception(FacesUtils.getMensaje("error.idEdicion.no.valido"));
        	}
        	if (idVolumen_SabVolumen == 0L) {
        		throw new Exception(FacesUtils.getMensaje("error.idVolumen"));
        	}
        	
        	if ((idVolumen_SabVolumen != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idVolumen_SabVolumen, 8, 0) == false)) {
        		throw new Exception(FacesUtils.getMensaje("error.idVolumen.no.valido"));
        	}

        	if (idEditorial_SabEditorial == 0L) {
        		throw new Exception(FacesUtils.getMensaje("error.idEditorial"));
        	}
        	
        	if ((idEditorial_SabEditorial != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idEditorial_SabEditorial, 8, 0) == false)) {
        		throw new Exception(FacesUtils.getMensaje("error.idEditorial.no.valido"));
        	}

            if (idArea_SabArea == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.idArea"));
            }

            if ((idArea_SabArea != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +idArea_SabArea, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idArea.no.valido"));
            }

            if (cantidad == null) {
            	throw new Exception(FacesUtils.getMensaje("error.cantidad"));
            }
            
            if ((cantidad != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +cantidad, 3, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.cantidad.no.valido"));
            }
            
            if (cantidadPrestados == null) {
            	throw new Exception(FacesUtils.getMensaje("error.cantidadPrestados"));
            }
            
            if ((cantidadPrestados != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + cantidadPrestados, 3, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.cantidadPrestados.no.valido"));
            }
            if (idEstado_SabEstadoLibro == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idEstadoLibro"));
            }

            if ((idEstado_SabEstadoLibro != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idEstado_SabEstadoLibro, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idEstadoLibro.no.valido"));
            }

            
            if(listAutores == null || listAutores.isEmpty()){
        		throw new Exception(FacesUtils.getMensaje("libro.no.autores"));
            }
            
            SabAreaLogica logicSabArea = new SabAreaLogica();
            SabEdicionLogica logicSabEdicion = new SabEdicionLogica();
            SabEditorialLogica logicSabEditorial = new SabEditorialLogica();
            SabEstadoLibroLogica logicSabEstadoLibro = new SabEstadoLibroLogica();
            SabVolumenLogica logicSabVolumen = new SabVolumenLogica();
            
            SabArea sabAreaClass = logicSabArea.getSabArea(idArea_SabArea);
            SabEdicion sabEdicionClass = logicSabEdicion.getSabEdicion(idEdicion_SabEdicion);
            SabEditorial sabEditorialClass = logicSabEditorial.getSabEditorial(idEditorial_SabEditorial);
            SabEstadoLibro sabEstadoLibroClass = logicSabEstadoLibro.getSabEstadoLibro(idEstado_SabEstadoLibro);
            SabVolumen sabVolumenClass = logicSabVolumen.getSabVolumen(idVolumen_SabVolumen);

            if (sabAreaClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.area.no.encontrada"));
            }

            if (sabEdicionClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.edicion.no.encontrado"));
            }

            if (sabEditorialClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.editorial.no.encontrado"));
            }

            if (sabEstadoLibroClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.estadoLibro.no.encontrado"));
            }

            if (sabVolumenClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.volumen.no.encontrado"));
            }

            entity = new SabLibro();
            entity.setCantidad(cantidad);
            entity.setCantidadPrestados(cantidadPrestados);
            entity.setTitulo(titulo);
            entity.setSabArea(sabAreaClass);
            entity.setSabEdicion(sabEdicionClass);
            entity.setSabEditorial(sabEditorialClass);
            entity.setSabEstadoLibro(sabEstadoLibroClass);
            entity.setSabVolumen(sabVolumenClass);
           
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabLibroDAO().save(entity);
            for (SabAutor sabAutor : listAutores) {
            	SabLibroAutor libroAutor = new SabLibroAutor();
            	SabLibroAutorId id = new SabLibroAutorId(entity.getIdLibro(), sabAutor.getIdAutor());
            	libroAutor.setId(id);
				XMLHibernateDaoFactory.getInstance().getSabLibroAutorDAO().save(libroAutor);
			}
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabLibro(Long idLibro) throws Exception {
        SabLibro entity = null;

        try {
	        if (idLibro == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.idLibro"));
	        }
	        List<SabPrestamo> sabPrestamos = null;
	        entity = getSabLibro(idLibro);
	
	        if (entity == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.libro.no.encontrado"));
	        }
            sabPrestamos = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().findByProperty("sabLibro.idLibro",idLibro);

            if (Utilities.validationsList(sabPrestamos) == true) {
            	throw new Exception(FacesUtils.getMensaje("error.libro.no.eliminar"));
            }

            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabLibroDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabLibro(Long cantidad,
        Long idLibro, String titulo, Long idArea_SabArea,
        Long idEdicion_SabEdicion, Long idEditorial_SabEditorial,
        Long idVolumen_SabVolumen, List<SabAutor> listAutor)
        throws Exception {
        SabLibro entity = null;

        try {

            if (idLibro == null) {
            	throw new Exception(FacesUtils.getMensaje("error.idLibro"));
            }

            if ((idLibro != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +idLibro, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idLibro.no.valido"));
            }

            if (titulo == null) {
            	throw new Exception(FacesUtils.getMensaje("error.titulo"));
            }

            if ((titulo != null) && (Utilities.checkWordAndCheckWithlength(titulo, 50) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.titulo.no.valido"));
            }


            if (idEdicion_SabEdicion == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.idEdicion"));
            }

            if ((idEdicion_SabEdicion != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idEdicion_SabEdicion, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idEdicion.no.valido"));
            }

            if (idVolumen_SabVolumen == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.idVolumen"));
            }
            
            if ((idVolumen_SabVolumen != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idVolumen_SabVolumen, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idVolumen.no.valido"));
            }
            if (idEditorial_SabEditorial == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.idEditorial"));
            }

            if ((idEditorial_SabEditorial != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + idEditorial_SabEditorial, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idEditorial.no.valido"));
            }

            if (idArea_SabArea == 0L) {
            	throw new Exception(FacesUtils.getMensaje("error.idArea"));
            }
            
            if ((idArea_SabArea != 0L) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +idArea_SabArea, 8, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.idArea.no.valido"));
            }
            if (cantidad == null) {
            	throw new Exception(FacesUtils.getMensaje("error.cantidad"));
            }
            
            if ((cantidad != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +cantidad, 3, 0) == false)) {
            	throw new Exception(FacesUtils.getMensaje("error.cantidad.no.valido"));
            }
            
            if(listAutor == null || listAutor.isEmpty()){
        		throw new Exception(FacesUtils.getMensaje("libro.no.autores"));
            }

            SabAreaLogica logicSabArea1 = new SabAreaLogica();

            SabEdicionLogica logicSabEdicion2 = new SabEdicionLogica();
            SabEditorialLogica logicSabEditorial3 = new SabEditorialLogica();
            SabVolumenLogica logicSabVolumen5 = new SabVolumenLogica();
            SabArea sabAreaClass = logicSabArea1.getSabArea(idArea_SabArea);
            SabEdicion sabEdicionClass = logicSabEdicion2.getSabEdicion(idEdicion_SabEdicion);
            SabEditorial sabEditorialClass = logicSabEditorial3.getSabEditorial(idEditorial_SabEditorial);
            SabVolumen sabVolumenClass = logicSabVolumen5.getSabVolumen(idVolumen_SabVolumen);

            if (sabAreaClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.area.no.encontrada"));
            }
            if (sabEdicionClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.edicion.no.encontrado"));
            }
            if (sabEditorialClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.editorial.no.encontrado"));
            }
            if (sabVolumenClass == null) {
            	throw new Exception(FacesUtils.getMensaje("error.volumen.no.encontrado"));
            }

            entity = getSabLibro(idLibro);

            if (entity == null) {
	        	throw new Exception(FacesUtils.getMensaje("error.libro.no.encontrado"));
            }

            entity.setCantidad(cantidad);
            entity.setIdLibro(idLibro);
            entity.setTitulo(titulo);
            entity.setSabArea(sabAreaClass);
            entity.setSabEdicion(sabEdicionClass);
            entity.setSabEditorial(sabEditorialClass);
            entity.setSabVolumen(sabVolumenClass);
           
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabLibroDAO().update(entity);
            
            for (SabLibroAutor sabLibroAutor : entity.getSabLibroAutors()) {
            	XMLHibernateDaoFactory.getInstance().getSabLibroAutorDAO().delete(sabLibroAutor);
			}
            
            for (SabAutor sabAutor : listAutor) {
            	SabLibroAutor libroAutor = new SabLibroAutor();
            	SabLibroAutorId id = new SabLibroAutorId(entity.getIdLibro(), sabAutor.getIdAutor());
            	libroAutor.setId(id);
				XMLHibernateDaoFactory.getInstance().getSabLibroAutorDAO().save(libroAutor);
			}
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabLibro getSabLibro(Long idLibro) throws Exception {
        SabLibro entity = null;
        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabLibroDAO().findById(idLibro);
        } catch (Exception e) {
            throw e;
        }
        return entity;
    }
    
    public List<SabLibro> consultarLibrosFiltro(Long idLibro, String titulo, Long idArea, String autor) throws Exception{
    	try{
    		return XMLHibernateDaoFactory.getInstance().getSabLibroDAO().consultarLibrosFiltro(idLibro, titulo, idArea, autor);
    	 } catch (Exception e) {
             throw e;
         }
    }

}
