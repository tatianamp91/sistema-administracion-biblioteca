package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.exceptions.ZMessManager;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.unicatolica.modelo.SabVolumen;
import co.edu.utilities.Utilities;

public class SabVolumenLogica {
    public List<SabVolumen> getSabVolumen() throws Exception {
        List<SabVolumen> list = new ArrayList<SabVolumen>();

        try {
            list = XMLHibernateDaoFactory.getInstance().getSabVolumenDAO()
                                         .findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SabVolumen");
        } finally {
        }

        return list;
    }

    public void saveSabVolumen(String descripcion, Long estado)
        throws Exception {
        SabVolumen entity = null;

        try {
            if (descripcion == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((descripcion != null) &&
                    (Utilities.checkWordAndCheckWithlength(descripcion, 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (estado == 0L) {
                throw new ZMessManager().new EmptyFieldException("estado");
            }

            if ((estado != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        estado, 1, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("estado");
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

        if (idVolumen == null) {
            throw new ZMessManager().new EmptyFieldException("idVolumen");
        }

        List<SabLibro> sabLibros = null;
        entity = getSabVolumen(idVolumen);

        if (entity == null) {
            throw new ZMessManager().new EmptyFieldException("SabVolumen");
        }

        try {
            sabLibros = XMLHibernateDaoFactory.getInstance().getSabLibroDAO()
                                              .findByProperty("sabVolumen.idVolumen",
                    idVolumen);

            if (Utilities.validationsList(sabLibros) == true) {
                throw new ZMessManager().new DeletingException("sabLibros");
            }

            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabVolumenDAO()
                                  .delete(entity);
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
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((descripcion != null) &&
                    (Utilities.checkWordAndCheckWithlength(descripcion, 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (estado == 0L) {
                throw new ZMessManager().new EmptyFieldException("estado");
            }

            if ((estado != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        estado, 1, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("estado");
            }

            if (idVolumen == null) {
                throw new ZMessManager().new EmptyFieldException("idVolumen");
            }

            if ((idVolumen != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idVolumen, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idVolumen");
            }

            entity = getSabVolumen(idVolumen);

            if (entity == null) {
                throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
            }

            entity.setDescripcion(descripcion);
            entity.setEstado(estado);
            entity.setIdVolumen(idVolumen);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabVolumenDAO()
                                  .update(entity);
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
            entity = XMLHibernateDaoFactory.getInstance().getSabVolumenDAO()
                                           .findById(idVolumen);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabVolumen");
        } finally {
        }

        return entity;
    }

    public List<SabVolumen> findPageSabVolumen(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SabVolumen> entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabVolumenDAO()
                                           .findPageSabVolumen(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabVolumen");
        } finally {
        }

        return entity;
    }

    public Long findTotalNumberSabVolumen() throws Exception {
        Long entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabVolumenDAO()
                                           .findTotalNumberSabVolumen();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabVolumen Count");
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
    public List<SabVolumen> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SabVolumen> list = new ArrayList<SabVolumen>();
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
            list = XMLHibernateDaoFactory.getInstance().getSabVolumenDAO()
                                         .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
