/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Employer;
import models.Freelancer;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Seth
 */
public class EmployerDao extends GenericDao<Employer>{
    public List<Employer> findByEmail(String email) {
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Employer where email =:d");
        q.setString("d", email);
        List<Employer> uno = q.list();
        System.out.println(uno);
        return uno;
    }
}
