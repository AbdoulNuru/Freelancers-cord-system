/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UsersDao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Users;

/**
 *
 * @author Abdoul Nuru
 */
@ManagedBean
@SessionScoped
public class UserController {

    private Users user = new Users();
    private List<Users> getAllUsers = new UsersDao().allUsers("from Users");
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
        List<Users> exist = new UsersDao().findByEmail(user.getEmail());

        if (exist.isEmpty()) {
            FacesMessage message = new FacesMessage("You don't have an account, please register");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (!exist.isEmpty()) {
            if (!exist.get(0).getPassword().equalsIgnoreCase(encryptPassword(user.getPassword()))) {
                FacesMessage message = new FacesMessage("Incorrect email or password");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                if(exist.get(0).getRole().equalsIgnoreCase("freelancer")){
                    return "freelancerRegister.xhtml";
                }else{
                    return "employeerRegister.xhtml";
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
    
    public List<Users> getAllFreelancers(){

    return new UsersDao().allUsers("freelancer");
      
    }

    public List<Users> getGetAllUsers() {
        return getAllUsers;
    }

    public void setGetAllUsers(List<Users> getAllUsers) {
        this.getAllUsers = getAllUsers;
    }

    
    
}
