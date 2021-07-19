package com.example.demo;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
    private String name;

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

    //constructors
    public Client() {
    }
    public Client(Clinic clinic, String name) {
        this.clinic = clinic;
        this.name = name;
    }
    //public Client(Long clinic_id, String name){
    //    this.clinic_id = clinic_id;
    //    this.name = name;
    //}

}
