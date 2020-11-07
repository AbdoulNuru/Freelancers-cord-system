/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import common.FileUpload;
import dao.ImageDao;
import dao.UsersDao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import models.Image;
import models.Users;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Abdoul Nuru
 */
@ManagedBean
@SessionScoped
public class UserController {

    private Users user = new Users();
    private List<Users> getAllUsers = new UsersDao().allUsers("from Users");
    private Users loggedInUser;
    private Users freelancer = new Users();
    private Users targetUser = new Users();
    private List<String> images = new ArrayList<>();
    private String path = "E:\\AUCA\\SEM7\\Memoire\\Sandrine\\Freelancers-cord-system\\web\\resources\\images\\Freelancer-profile\\";

    public void save() throws NoSuchAlgorithmException {
        List<Users> userExist = new UsersDao().findByEmail(user.getEmail());

        if (!userExist.isEmpty()) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You already have an account, please login", null));
        } else {
            user.setPassword(encryptPassword(user.getPassword()));
            user.setRole("freelancer");
            user.setStatus("active");
            new UsersDao().create(user);
            user = new Users();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Your accout is successfully created, you can now login", null));
        }
    }

    public void saveEmployeer() throws NoSuchAlgorithmException {
        user.setPassword(encryptPassword(user.getPassword()));
        user.setRole("employeer");
        new UsersDao().create(user);
        user = new Users();
        FacesMessage message = new FacesMessage("Your accout is successfully created, you can now login");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String login() throws NoSuchAlgorithmException {
        Users userLoggedIn = new Users();
        List<Users> exist = new UsersDao().findByEmail(user.getEmail());

        if (exist.isEmpty()) {
            FacesMessage message = new FacesMessage("You don't have an account, please register");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (!exist.isEmpty()) {
            //this.loggedInUser = userLoggedIn;
            if (!exist.get(0).getPassword().equalsIgnoreCase(encryptPassword(user.getPassword()))) {
                FacesMessage message = new FacesMessage("Incorrect email or password");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                System.out.println(exist.get(0));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLoggedIn", exist.get(0));
               System.out.println( FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn"));
                if (exist.get(0).getRole().equalsIgnoreCase("freelancer")) {
                    return "joblisting.xhtml";
                } else {
                    return "home.xhtml";
                }
            }
        }
        return null;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException {

        String pas = password;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pas.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return (sb.toString());
    }

    public List<Users> getAllFreelancers() {
        System.out.println(new UsersDao().allUsers("freelancer").get(0).getImages().get(0).getName());
        return new UsersDao().allUsers("freelancer");
    }

    public void upload(FileUploadEvent event) {
        
        String newName = new FileUpload().Upload(event, path);
        images.add(newName);
       
    }
    public List<String> insertInto(String me){
        images.add(me);
         System.out.println("listing the name =>"+images);
        return images;
    }
//first get loggedInUser

    public void updateFreelancerProfile() {
        System.out.println("listing the name =>"+images);
        this.loggedInUser = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
        System.out.println("Helloooooooooooooooo" + loggedInUser.getEmail());
        this.targetUser = new UsersDao().findByOne(Users.class, loggedInUser.getId());
        freelancer.setFirstName(loggedInUser.getFirstName());
        //new UsersDao().update(freelancer);
        //Here will put an object to be updated before uploading images
        System.out.println("Helloooooooooooooooo");
        System.out.println("listing the name =>"+images);
        for (String x : images) {
            Image imgs = new Image();
            imgs.setName(x);
            imgs.setUser(targetUser);
            new ImageDao().create(imgs);
            System.out.println("after saving ========");
        }
        this.images = new ArrayList<>();

    }

    public List<Users> getGetAllUsers() {
        return getAllUsers;
    }

    public void setGetAllUsers(List<Users> getAllUsers) {
        this.getAllUsers = getAllUsers;
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Users loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Users getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Users freelancer) {
        this.freelancer = freelancer;
    }

    public Users getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Users targetUser) {
        this.targetUser = targetUser;
    }

    
}
