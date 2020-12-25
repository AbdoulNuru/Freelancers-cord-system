package controllers;

import dao.BookingDao;
import dao.FreelancerDao;
import dao.UsersDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Booking;
import models.Employer;
import models.Freelancer;
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
@SessionScoped
public class HomeController {
    private UsersDao userDao  = new UsersDao();
    private Freelancer user;
    private Booking booking= new Booking();
    String id = "";
    Employer employerLoggedIn;

    public UsersDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UsersDao userDao) {
        this.userDao = userDao;
    }
    
    public void setUser(String id) {
        this.user = new FreelancerDao().findByOne(Freelancer.class, id);
        System.out.println("the user => "+this.user);
        this.id = this.user.getId();
    }
    
    public Freelancer getUser() {
        return this.user;
    }
    public void book(){
        System.out.println(id);
        Freelancer fre = new FreelancerDao().findByOne(Freelancer.class, id);
        //employerLoggedIn
         this.employerLoggedIn = (Employer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("employerLoggedIn");
         booking.setEmployer(employerLoggedIn);
         booking.setFreelancers(fre);
         System.out.println("daljfa "+ id);
         new BookingDao().create(booking);
        
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employer getEmployerLoggedIn() {
        return employerLoggedIn;
    }

    public void setEmployerLoggedIn(Employer employerLoggedIn) {
        this.employerLoggedIn = employerLoggedIn;
    }
    
}
