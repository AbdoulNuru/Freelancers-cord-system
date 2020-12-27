/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import common.FileUpload;
import dao.BookingDao;
import dao.EmployerDao;
import dao.FreelancerDao;
import dao.ImageDao;
import dao.UsersDao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import models.Booking;
import models.Employer;
import models.Freelancer;
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
    private Freelancer freelancerLoggedIn;
    private List<Freelancer> activeFreelancers  = new VirtualFlow.ArrayLinkedList<>();
    Employer employerLoggedIn;
    private Freelancer freelancer = new Freelancer();
    private Freelancer freelancer2 = new Freelancer();
    private Employer employer = new Employer();
    private Freelancer targetUser = new Freelancer();
    private List<String> images = new ArrayList<>();
    private List<Image> selectedImage = new ArrayList<>();
    private Users selectTargetUser = new Users();
    private String path = "E:\\AUCA\\SEM7\\Memoire\\Sandrine\\Freelancers-cord-system\\web\\resources\\images\\Freelancer-profile\\";

    
    public void save() throws NoSuchAlgorithmException {
        List<Freelancer> freelancerExist = new FreelancerDao().findByEmail(freelancer.getEmail());

        if (!freelancerExist.isEmpty()) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You already have an account, please login", null));
        } else {
            freelancer.setPassword(encryptPassword(freelancer.getPassword()));
            freelancer.setStatus("active");

            new FreelancerDao().create(freelancer);
            freelancer = new Freelancer();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Your accout is successfully created, you can now login", null));
        }
    }

    public void saveEmployeer() throws NoSuchAlgorithmException {
        employer.setPassword(encryptPassword(employer.getPassword()));
        
        new EmployerDao().create(employer);
        employer = new Employer();
        FacesMessage message = new FacesMessage("Your accout is successfully created, you can now login");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String login() throws NoSuchAlgorithmException {
        freelancerLoggedIn = new Freelancer();
        employerLoggedIn = new Employer();
        List<Users> exist = new UsersDao().findByEmail(user.getEmail());
        List<Freelancer> freelancerExist = new FreelancerDao().findByEmail(freelancer.getEmail());
        List<Employer> employerExist = new EmployerDao().findByEmail(employer.getEmail());
        System.out.println(freelancerExist);
        System.out.println(employerExist);

        if (freelancerExist.isEmpty()) {
            FacesMessage message = new FacesMessage("You don't have an account, please register");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else if(employerExist.isEmpty()){
            FacesMessage message = new FacesMessage("You don't have an account, please register");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (!freelancerExist.isEmpty()) {
            if (!freelancerExist.get(0).getPassword().equalsIgnoreCase(encryptPassword(freelancer.getPassword()))) {
                FacesMessage message = new FacesMessage("Incorrect email or password");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLoggedIn", freelancerExist.get(0));
//                System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn"));
//                System.out.println(exist.get(0).getRole().equalsIgnoreCase("freelancer"));
                return "joblisting.xhtml";
            }
        } else if (!employerExist.isEmpty()) {
            if (!employerExist.get(0).getPassword().equalsIgnoreCase(encryptPassword(employer.getPassword()))) {
                FacesMessage message = new FacesMessage("Incorrect email or password");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("employerLoggedIn", employerExist.get(0));
//                System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn"));
//                System.out.println(exist.get(0).getRole().equalsIgnoreCase("freelancer"));
                return "home.xhtml";
            }
        } else {
            return "login.xhtml";
        }

        return null;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        this.user = null;
        context.getExternalContext().invalidateSession();
        freelancerLoggedIn = null;
        return "login.xhtml?faces-redirect=true";
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

    public List<Freelancer> getAllFreelancers() {
        //System.out.println(new UsersDao().allUsers("freelancer").get(0).getImages().get(0).getName());
        return new FreelancerDao().allFreelancer("active");
    }

    public void upload(FileUploadEvent event) {

        String newName = new FileUpload().Upload(event, path);
        images.add(newName);

    }

    public List<String> insertInto(String me) {
        images.add(me);
        System.out.println("listing the name =>" + images);
        return images;
    }
//first get loggedInUser

    public void updateFreelancerProfile() {
        System.out.println("listing the name =>" + images);
        this.freelancerLoggedIn = (Freelancer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
        System.out.println("Helloooooooooooooooo" + this.freelancerLoggedIn.getEmail());
        this.targetUser = new FreelancerDao().findByOne(Freelancer.class, freelancerLoggedIn.getId());
        freelancer.setFirstName(freelancerLoggedIn.getFirstName());
        //new UsersDao().update(freelancer);
        //Here will put an object to be updated before uploading images
        System.out.println("Helloooooooooooooooo");
        System.out.println("listing the name =>" + images);
        for (String x : images) {
            Image imgs = new Image();
            imgs.setName(x);
            imgs.setFreelancer(targetUser);
            new ImageDao().create(imgs);
            System.out.println("after saving ========");
        }
        this.images = new ArrayList<>();

    }

//    public void booking() {
//        this.loggedInUser = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
//
//        System.out.println("Begining of insert by " + this.loggedInUser);
//
//        Booking booking = new Booking();
//        booking.setEmployer(this.loggedInUser.getEmployer().get(0));
//        booking.setFreelancers(this.freelancer);
//        booking.setBookDate(new Date());
//        new BookingDao().create(booking);
//        System.out.println("Inserted into bookings");
//
//    }

    public String assignFreelancers(Users users) throws Exception {
        this.selectedImage = new ImageDao().getGenericListWithHQLParameter(new String[]{"user_id"}, new Object[]{users.getId()}, "Image");
        this.selectTargetUser = users;
        return "usersignle.xhtml?faces-redirect=true";
    }

    public List<Users> getGetAllUsers() {
        return getAllUsers;
    }

    public void setGetAllUsers(List<Users> getAllUsers) {
        this.getAllUsers = getAllUsers;
    }

    public Freelancer getFreelancerLoggedIn() {
        return freelancerLoggedIn;
    }

    public List<Freelancer> getActiveFreelancers() {
        return new FreelancerDao().allFreelancer("active");
    }

    public void setActiveFreelancers(List<Freelancer> activeFreelancers) {
        this.activeFreelancers = activeFreelancers;
    }
    
    public void setFreelancerLoggedIn(Freelancer freelancerLoggedIn) {
        this.freelancerLoggedIn = freelancerLoggedIn;
    }

    public Freelancer getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Freelancer targetUser) {
        this.targetUser = targetUser;
    }

   

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

   

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Image> getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(List<Image> selectedImage) {
        this.selectedImage = selectedImage;
    }

    public Users getSelectTargetUser() {
        return selectTargetUser;
    }

    public void setSelectTargetUser(Users selectTargetUser) {
        this.selectTargetUser = selectTargetUser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Freelancer getFreelancer2() {
        return freelancer2;
    }

    public void setFreelancer2(Freelancer freelancer2) {
        this.freelancer2 = freelancer2;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

}
