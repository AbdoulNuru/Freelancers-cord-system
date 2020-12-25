/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Image;
import models.Users;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Abdoul Nuru
 */
public class UsersDao extends GenericDao<Users> {

    public List<Users> findById(String id) {
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Users where id =:d");
        q.setString("d", id);
        List<Users> uno = q.list();
        return uno;
    }

    public List<Users> findByEmail(String email) {
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Users where email =:d");
        q.setString("d", email);
        List<Users> uno = q.list();
        System.out.println(uno);
        return uno;
    }
    
     public List<Users>allUsers(String x){
     

     //return NewHibernateUtil.getSessionFactory().openSession().createQuery(x).list();
         
         Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Users");
       // q.setString("d", x);
        List<Users> uno = q.list();
        return uno;

     }
    
  
}
