/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Abdoul Nuru
 */
@Entity
public class Booking {
    @Id
    private String id=UUID.randomUUID().toString();
    private  Date bookDate;
    private String location;
    private String workType;
    private Double totalPrice;
    private boolean paymentStatus=false;
    @ManyToOne
    private Employer employer;
    @ManyToOne
    private Freelancer freelancers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

  

    public Freelancer getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(Freelancer freelancers) {
        this.freelancers = freelancers;
    }
    

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", bookDate=" + bookDate + ", location=" + location + ", workType=" + workType + ", totalPrice=" + totalPrice + ", paymentStatus=" + paymentStatus + ", employer=" + employer + ", freelancers=" + freelancers + '}';
    }

 

   
    
    
    
    
}
