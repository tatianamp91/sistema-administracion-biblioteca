package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.exceptions.ZMessManager;
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
import co.edu.utilities.Utilities;

public class SabLibroLogica {
    public List<SabLibro> getSabLibro() throws Exception {
        List<SabLibro> list = new ArrayList<SabLibro>();

        try {
            list = XMLHibernateDaoFactory.getInstance().getSabLibroDAO()
                                         .findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SabLibro");
        } finally {
        }

        return list;
    }

	public void saveSabLibro(Long cantidad, Long cantidadPrestados, String titulo,
        Long idArea_SabArea, Long idEdicion_SabEdicion, Long idEditorial_SabEditorial,
        Long idEstado_SabEstadoLibro, Long idVolumen_SabVolumen, List<SabAutor> listAutores)
        throws Exception {
        SabLibro entity = null;

        try {
            if (cantidad == null) {
                throw new ZMessManager().new EmptyFieldException("cantidad");
            }

            if ((cantidad != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        cantidad, 3, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("cantidad");
            }

            if (cantidadPrestados == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "cantidadPrestados");
            }

            if ((cantidadPrestados != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        cantidadPrestados, 3, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "cantidadPrestados");
            }

            if (titulo == null) {
                throw new ZMessManager().new EmptyFieldException("titulo");
            }

            if ((titulo != null) &&
                    (Utilities.checkWordAndCheckWithlength(titulo, 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("titulo");
            }

            if (idArea_SabArea == 0L) {
                throw new ZMessManager().new EmptyFieldException(
                    "idArea_SabArea");
            }

            if ((idArea_SabArea != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idArea_SabArea, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idArea_SabArea");
            }

            if (idEdicion_SabEdicion == 0L) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEdicion_SabEdicion");
            }

            if ((idEdicion_SabEdicion != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idEdicion_SabEdicion, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idEdicion_SabEdicion");
            }

            if (idEditorial_SabEditorial == 0L) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEditorial_SabEditorial");
            }

            if ((idEditorial_SabEditorial != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idEditorial_SabEditorial, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idEditorial_SabEditorial");
            }

            if (idEstado_SabEstadoLibro == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEstado_SabEstadoLibro");
            }

            if ((idEstado_SabEstadoLibro != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idEstado_SabEstadoLibro, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idEstado_SabEstadoLibro");
            }

            if (idVolumen_SabVolumen == 0L) {
                throw new ZMessManager().new EmptyFieldException(
                    "idVolumen_SabVolumen");
            }

            if ((idVolumen_SabVolumen != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idVolumen_SabVolumen, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idVolumen_SabVolumen");
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
                throw new ZMessManager().new ForeignException("sabArea");
            }

            if (sabEdicionClass == null) {
                throw new ZMessManager().new ForeignException("sabEdicion");
            }

            if (sabEditorialClass == null) {
                throw new ZMessManager().new ForeignException("sabEditorial");
            }

            if (sabEstadoLibroClass == null) {
                throw new ZMessManager().new ForeignException("sabEstadoLibro");
            }

            if (sabVolumenClass == null) {
                throw new ZMessManager().new ForeignException("sabVolumen");
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

        if (idLibro == null) {
            throw new ZMessManager().new EmptyFieldException("idLibro");
        }

        List<SabPrestamo> sabPrestamos = null;
        entity = getSabLibro(idLibro);

        if (entity == null) {
            throw new ZMessManager().new EmptyFieldException("SabLibro");
        }

        try {
            sabPrestamos = XMLHibernateDaoFactory.getInstance()
                                                 .getSabPrestamoDAO()
                                                 .findByProperty("sabLibro.idLibro",
                    idLibro);

            if (Utilities.validationsList(sabPrestamos) == true) {
                throw new ZMessManager().new DeletingException("sabPrestamos");
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
            if (cantidad == null) {
                throw new ZMessManager().new EmptyFieldException("cantidad");
            }

            if ((cantidad != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        cantidad, 3, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("cantidad");
            }

            if (idLibro == null) {
                throw new ZMessManager().new EmptyFieldException("idLibro");
            }

            if ((idLibro != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idLibro, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("idLibro");
            }

            if (titulo == null) {
                throw new ZMessManager().new EmptyFieldException("titulo");
            }

            if ((titulo != null) &&
                    (Utilities.checkWordAndCheckWithlength(titulo, 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("titulo");
            }

            if (idArea_SabArea == 0L) {
                throw new ZMessManager().new EmptyFieldException(
                    "idArea_SabArea");
            }

            if ((idArea_SabArea != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idArea_SabArea, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idArea_SabArea");
            }

            if (idEdicion_SabEdicion == 0L) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEdicion_SabEdicion");
            }

            if ((idEdicion_SabEdicion != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idEdicion_SabEdicion, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idEdicion_SabEdicion");
            }

            if (idEditorial_SabEditorial == 0L) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEditorial_SabEditorial");
            }

            if ((idEditorial_SabEditorial != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idEditorial_SabEditorial, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idEditorial_SabEditorial");
            }

            if (idVolumen_SabVolumen == 0L) {
                throw new ZMessManager().new EmptyFieldException(
                    "idVolumen_SabVolumen");
            }

            if ((idVolumen_SabVolumen != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idVolumen_SabVolumen, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idVolumen_SabVolumen");
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
                throw new ZMessManager().new ForeignException("sabArea");
            }

            if (sabEdicionClass == null) {
                throw new ZMessManager().new ForeignException("sabEdicion");
            }

            if (sabEditorialClass == null) {
                throw new ZMessManager().new ForeignException("sabEditorial");
            }

            if (sabVolumenClass == null) {
                throw new ZMessManager().new ForeignException("sabVolumen");
            }

            entity = getSabLibro(idLibro);

            if (entity == null) {
                throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
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
            entity = XMLHibernateDaoFactory.getInstance().getSabLibroDAO()
                                           .findById(idLibro);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabLibro");
        } finally {
        }

        return entity;
    }

    public List<SabLibro> findPageSabLibro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SabLibro> entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabLibroDAO()
                                           .findPageSabLibro(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabLibro");
        } finally {
        }

        return entity;
    }

    public Long findTotalNumberSabLibro() throws Exception {
        Long entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabLibroDAO()
                                           .findTotalNumberSabLibro();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabLibro Count");
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
    public List<SabLibro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SabLibro> list = new ArrayList<SabLibro>();
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
            list = XMLHibernateDaoFactory.getInstance().getSabLibroDAO()
                                         .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
