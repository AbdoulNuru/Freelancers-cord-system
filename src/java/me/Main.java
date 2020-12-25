/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me;


import dao.EmployerDao;
import dao.FreelancerDao;
import dao.NewHibernateUtil;
import dao.UsersDao;
import java.util.List;
import models.Users;
import org.hibernate.Hibernate;

/**
 *
 * @author Seth
 */
public class Main {
    public static void main(String[] args) {
        //System.out.println(new UsersDao().findByEmail("seth@gmail.com"));
//     NewHibernateUtil.getSessionFactory().openSession();
//       NewHibernateUtil.getSessionFactory().close();
      //  System.out.println(new UsersDao().findByEmail("seth@gmail.com"));
//        System.out.println(new FreelancerDao().getData());
        //System.out.println(new UsersDao().allUsers("ju"));
        //System.out.println(new FreelancerDao().findByEmail("nuru@gmail.com"));
        System.out.println(new FreelancerDao().allFreelancer("active"));
    }
}
