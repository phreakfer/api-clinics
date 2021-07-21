package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
    private String name;

    public Long getId() {return id;}
    public Client getClient() {return client;}
    public String getName() {return name;}

    public void setId(Long id) {this.id = id;}
    public void setClient(Client client) {this.client = client;}
    public void setName(String name) {this.name = name;}

    public Pet(){}
    public Pet(Client client, String name){
        this.client = client;
        this.name = name;
    }
}
