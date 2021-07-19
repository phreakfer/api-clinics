package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long clientid;
    private String name;

    public Long getId() {return id;}
    public Long getClientid() {return clientid;}
    public String getName() {return name;}

    public void setId(Long id) {this.id = id;}
    public void setClientid(Long clientid) {this.clientid = clientid;}
    public void setName(String name) {this.name = name;}

    public Pet(){}
    public Pet(Long clientid, String name){
        this.clientid = clientid;
        this.name = name;
    }
}
