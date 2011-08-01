package co.edu.unicatolica.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.accesodatos.fabricaDao.XMLHibernateDaoFactory;
import co.edu.accesodatos.session.HibernateSessionFactory;
import co.edu.exceptions.ZMessManager;
import co.edu.unicatolica.modelo.SabAutor;
import co.edu.utilities.Utilities;

public class SabAutorLogica {
    public List<SabAutor> getSabAutor() throws Exception {
        List<SabAutor> list = new ArrayList<SabAutor>();

        try {
            list = XMLHibernateDaoFactory.getInstance().getSabAutorDAO()
                                         .findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SabAutor");
        } finally {
        	
        }

        return list;
    }

    public void saveSabAutor(String nombre)
        throws Exception {
        SabAutor entity = null;

        try {
           
            if (nombre == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((nombre != null) &&
                    (Utilities.checkWordAndCheckWithlength(nombre, 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }


            entity = new SabAutor();
            entity.setNombre(nombre);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAutorDAO().save(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void deleteSabAutor(Long idAutor) throws Exception {
        SabAutor entity = null;

        if (idAutor == null) {
            throw new ZMessManager().new EmptyFieldException("idAutor");
        }

        entity = getSabAutor(idAutor);

        if (entity == null) {
            throw new ZMessManager().new EmptyFieldException("SabAutor");
        }

        try {
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAutorDAO().delete(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void updateSabAutor(Long idAutor, String nombre)
        throws Exception {
        SabAutor entity = null;

        try {
            if (idAutor == null) {
                throw new ZMessManager().new EmptyFieldException("idAutor");
            }

            if ((idAutor != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        idAutor, 8, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("idAutor");
            }

            if (nombre == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((nombre != null) &&
                    (Utilities.checkWordAndCheckWithlength(nombre, 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            entity = getSabAutor(idAutor);

            if (entity == null) {
                throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
            }

            entity.setIdAutor(idAutor);
            entity.setNombre(nombre);
            HibernateSessionFactory.beginTransaction();
            XMLHibernateDaoFactory.getInstance().getSabAutorDAO().update(entity);
            HibernateSessionFactory.commit();
        } catch (Exception e) {
            HibernateSessionFactory.rollback();
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public SabAutor getSabAutor(Long idAutor) throws Exception {
        SabAutor entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabAutorDAO()
                                           .findById(idAutor);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabAutor");
        } finally {
        }

        return entity;
    }

    public List<SabAutor> findPageSabAutor(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SabAutor> entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabAutorDAO()
                                           .findPageSabAutor(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabAutor");
        } finally {
        }

        return entity;
    }

    public Long findTotalNumberSabAutor() throws Exception {
        Long entity = null;

        try {
            entity = XMLHibernateDaoFactory.getInstance().getSabAutorDAO()
                                           .findTotalNumberSabAutor();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SabAutor Count");
        } finally {
        }

        return entity;
    }

    public List<SabAutor> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SabAutor> list = new ArrayList<SabAutor>();
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
            list = XMLHibernateDaoFactory.getInstance().getSabAutorDAO()
                                         .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
