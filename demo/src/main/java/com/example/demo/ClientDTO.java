package com.example.demo;

public class ClientDTO {
    private String name;
    private Long clinicId;

    public String getName() {
        return name;
    }
    public Long getClinicId() {
        return clinicId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public ClientDTO(){}
    public ClientDTO(String name, String address, Long clinicId){
        this.name = name;
        this.clinicId = clinicId;
    }
}
