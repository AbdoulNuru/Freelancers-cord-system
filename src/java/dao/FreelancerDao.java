/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Freelancer;
import models.Users;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Seth
 */
public class FreelancerDao extends GenericDao<Freelancer>{
     public List<Freelancer>allFreelancer(String x){
     

     //return NewHibernateUtil.getSessionFactory().openSession().createQuery(x).list();
         
         Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Freelancer where status =:d");
        q.setString("d", x);
        List<Freelancer> uno = q.list();
        return uno;

     }
     
     public List<Freelancer> getData(){
       Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Freelancer");
        List<Freelancer> uno = q.list();
         System.out.println(uno);
         return uno;
     }
     
     public List<Freelancer> findByEmail(String email) {
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Freelancer where email =:d");
        q.setString("d", email);
        List<Freelancer> uno = q.list();
        System.out.println(uno);
        return uno;
    }
}
