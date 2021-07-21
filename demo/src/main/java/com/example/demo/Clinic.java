package com.example.demo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Client> client;

    //getters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress(){
        return address;
    }

    public List<Client> getClient() {
        return client;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setClient(List<Client> client) {
        this.client = client;
    }

    //constructors
     public Clinic(){}
     public Clinic(String name, String address){
        this.name = name;
        this.address = address;
     }
}
