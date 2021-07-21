package com.example.demo.dto;

public class PetDTO {
    private String name;
    private Long clientId;

    public String getName() {
        return name;
    }
    public Long getClientId() {
        return clientId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public PetDTO(){}
    public PetDTO(String name, Long clientId){
        this.name = name;
        this.clientId = clientId;
    }

}
