package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.exceptions.ZMessManager;
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
            list = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO()
                                         .findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SabPrestamo");
        } finally {
        }

        return list;
    }

    public void saveSabPrestamo(Long idLibro, Long idUsuario,
        Long estadoPrestamo, Date fechaDevolucion, Date fechaPrestamo,
        Date fechaRealDevolucion) throws Exception {
        SabPrestamo entity = null;

        try {
            if (idLibro == null) {
                throw new ZMessManager().new EmptyFieldException("idLibro");
            }

            if ((idLibro != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idLibro, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("idLibro");
            }

            if (idUsuario == null) {
                throw new ZMessManager().new EmptyFieldException("idUsuario");
            }

            if ((idUsuario != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idUsuario, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idUsuario");
            }

            if (estadoPrestamo == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoPrestamo");
            }

            if ((estadoPrestamo != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        estadoPrestamo, 1, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoPrestamo");
            }

            if (fechaDevolucion == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaDevolucion");
            }

            if (fechaPrestamo == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaPrestamo");
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
            	throw new Exception("No se encontro el libro.");
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
    			throw new ZMessManager().new EmptyFieldException("Prestamo");
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
    
    public SabPrestamo getSabPrestamo(Long idPrestamo)
        throws Exception {
        SabPrestamo entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO()
                                           .findById(idPrestamo);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabPrestamo");
        } finally {
        }

        return entity;
    }
    
    public List<SabPrestamo> buscarPorUsuarioLibro(Long idLibro, Long idUsuario) throws Exception{
    	try {
			return XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO().buscarPorUsuarioLibro(idLibro, idUsuario);
		} catch (Exception e) {
			throw e;
		}
    }
    
    // BORRAR O NO BORRAR ESA ES LA CUESTION

    public List<SabPrestamo> findPageSabPrestamo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SabPrestamo> entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO()
                                           .findPageSabPrestamo(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabPrestamo");
        } finally {
        }

        return entity;
    }

    public Long findTotalNumberSabPrestamo() throws Exception {
        Long entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO()
                                           .findTotalNumberSabPrestamo();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabPrestamo Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    public List<SabPrestamo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SabPrestamo> list = new ArrayList<SabPrestamo>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = XMLHibernateDaoFactory.getInstance().getSabPrestamoDAO()
                                         .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
