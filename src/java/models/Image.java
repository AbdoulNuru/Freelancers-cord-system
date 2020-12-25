/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Seth
 */
@Entity
public class Image {
    @Id
    private String id=UUID.randomUUID().toString();
    private String name;
    @ManyToOne
    private Freelancer freelancer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }



    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", name=" + name + ", user=" + freelancer + '}';
    }
    
    
    
    
}
