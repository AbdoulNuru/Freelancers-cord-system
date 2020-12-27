/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Booking;
import models.Freelancer;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Seth
 */
public class BookingDao extends GenericDao<Booking>{
    public List<Booking> findAll(String x){
      Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Booking where freelancers_id =:d");
        q.setString("d", x);
        List<Booking> uno = q.list();
        return uno;
    }
    
}
