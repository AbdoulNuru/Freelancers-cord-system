package controllers;

import dao.UsersDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import models.Users;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seth
 */
@ManagedBean
@ViewScoped
public class HomeController {
    private UsersDao userDao  = new UsersDao();
    private Users user;

    public UsersDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UsersDao userDao) {
        this.userDao = userDao;
    }
    
    public void setUser(String id) {
        this.user = userDao.findByOne(Users.class, id);
    }
    
    public Users getUser() {
        return this.user;
    }
}
