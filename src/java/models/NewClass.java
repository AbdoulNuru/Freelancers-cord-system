/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dao.GenericDao;
import dao.NewHibernateUtil;

/**
 *
 * @author Abdoul Nuru
 */
public class NewClass {
    public static void main(String[] args) {
       Users user = new Users();
       user.setFirstName("hello");
       new GenericDao<Users>().create(user);
        
    }
    
}
