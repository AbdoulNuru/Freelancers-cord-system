/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Image;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Seth
 */
public class ImageDao extends GenericDao<Image> {

    protected Class<Image> entityClass;
        private static final transient Logger LOGGER = LoggerFactory
            .getLogger(ImageDao.class);
    private static final String PERSISTANT_CLASS_IS_NULL = "Persistent Class is null";
    private static final String WHERE_CLAUSE = " where ";
    private static final String FROM_CLAUSE = " from ";
    private static final String WHERE_PROPERTY_HOLDER = "=?";
    private static final int TRIM_CYCLE = 4;

    public List getGenericListWithHQLParameter(final String[] propertyName,
            final Object[] value, final String hqlStatement)
            throws Exception {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(FROM_CLAUSE);
            strQuery.append(hqlStatement);
            strQuery.append(WHERE_CLAUSE);

            for (int i = 0; i < propertyName.length; i++) {
                strQuery.append(propertyName[i]);
                strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
            }

            strQuery.delete(strQuery.length() - TRIM_CYCLE,
                    strQuery.length() - 1);
            strQuery.append(") ");

            Query query = session.createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }

//            factory.close();
            List lis = query.list();
            session.close();
            return lis;
        } catch (HibernateException hibex) {

            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);

        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        } finally {

        }
    }

}
