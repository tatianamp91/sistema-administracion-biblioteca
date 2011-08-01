package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.exceptions.ZMessManager;
import co.edu.unicatolica.modelo.SabArea;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.utilities.Utilities;

public class SabAreaLogica {
    public List<SabArea> getSabArea() throws Exception {
        List<SabArea> list = new ArrayList<SabArea>();

        try {
            list = XMLHibernateDaoFactory.getInstance().getSabAreaDAO().findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL + "SabArea");
        } finally {
        }

        return list;
    }

    public void saveSabArea(Long estado, String nombre)
        throws Exception {
        SabArea entity = null;

        try {

            if (nombre == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((nombre != null) &&
                    (Utilities.checkWordAndCheckWithlength(nombre, 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }
            if (estado == 0L) {
                throw new ZMessManager().new EmptyFieldException("estado");
            }

            if ((estado != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        estado, 1, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("estado");
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
            throw new ZMessManager().new EmptyFieldException("idArea");
        }

        List<SabLibro> sabLibros = null;
        entity = getSabArea(idArea);

        if (entity == null) {
            throw new ZMessManager().new EmptyFieldException("SabArea");
        }

        try {
            sabLibros = XMLHibernateDaoFactory.getInstance().getSabLibroDAO()
                                              .findByProperty("sabArea.idArea", idArea);

            if (Utilities.validationsList(sabLibros) == true) {
                throw new ZMessManager().new DeletingException("sabLibros");
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
                throw new ZMessManager().new EmptyFieldException("idArea");
            }

            if ((idArea != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idArea, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("idArea");
            }

            if (nombre == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((nombre != null) &&
                    (Utilities.checkWordAndCheckWithlength(nombre, 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }
            
            if (estado == 0L) {
                throw new ZMessManager().new EmptyFieldException("estado");
            }

            if ((estado != 0L) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        estado, 1, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("estado");
            }
            
            entity = getSabArea(idArea);

            if (entity == null) {
                throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
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
            entity = XMLHibernateDaoFactory.getInstance().getSabAreaDAO()
                                           .findById(idArea);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabArea");
        } finally {
        }

        return entity;
    }

    public List<SabArea> findPageSabArea(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SabArea> entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabAreaDAO()
                                           .findPageSabArea(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabArea");
        } finally {
        }

        return entity;
    }

    public Long findTotalNumberSabArea() throws Exception {
        Long entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabAreaDAO()
                                           .findTotalNumberSabArea();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabArea Count");
        } finally {
        }

        return entity;
    }

  
    public List<SabArea> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SabArea> list = new ArrayList<SabArea>();
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
            list = XMLHibernateDaoFactory.getInstance().getSabAreaDAO()
                                         .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
