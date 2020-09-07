/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import org.hibernate.Session;

/**
 *
 * @author Abdoul Nuru
 */
public class GenericDao<X> {
    public void create(X obj){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.save(obj);
        ss.getTransaction().commit();
        ss.close();
    }
    
    public void update(X obj){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.update(obj);
        ss.getTransaction().commit();
        ss.close();
    }
    
    public void delete(X obj){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.delete(obj);
        ss.getTransaction().commit();
        ss.close();
    }
    
      public X findByOne(Class sr, Serializable id) {
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        X src = (X) ss.get(sr, id);
        ss.close();
        return src;

    }
    
}
