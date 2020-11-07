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
    private String firstName;
    private String lastName;
    private String email;
    private String category;
    private Double pricePerHour;
    private String status;
    private String role;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Booking> booking;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Image> images;

    public Users() {
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
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
   
    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", category=" + category + ", pricePerHour=" + pricePerHour + ", status=" + status + ", role=" + role + ", password=" + password + ", booking=" + booking + '}';
    }

    
   
    
    
    
}
