package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "clinic_id")
    @JsonIgnore
    private Clinic clinic;
    private String name;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pet;

    //getters
    public Long getId() {
        return id;
    }
    public Clinic getClinic() {
        return clinic;
    }
    public String getName() {
        return name;
    }
    public List<Pet> getPet() {return pet;}

    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPet(List<Pet> pet) {this.pet = pet;}

    //constructors
    public Client() {
    }
    public Client(Clinic clinic, String name) {
        this.clinic = clinic;
        this.name = name;
    }
  }
