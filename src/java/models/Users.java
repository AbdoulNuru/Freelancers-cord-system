/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Abdoul Nuru
 */
@Entity
public class Users {

    @Id
    private String id = UUID.randomUUID().toString();
    private String status;
    private String role;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Booking> booking;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Image> images;

    @OneToMany(mappedBy = "users")
    private List<Freelancer> freeLancer;

    @OneToMany(mappedBy = "users")
    private List<Employer> employer;

    public Users() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Freelancer> getFreeLancer() {
        return freeLancer;
    }

    public void setFreeLancer(List<Freelancer> freeLancer) {
        this.freeLancer = freeLancer;
    }

    public List<Employer> getEmployer() {
        return employer;
    }

    public void setEmployer(List<Employer> employer) {
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", status=" + status + ", role=" + role + ", email=" + email + ", password=" + password + ", booking=" + booking + ", images=" + images + ", freeLancer=" + freeLancer + ", employer=" + employer + '}';
    }

  

}
