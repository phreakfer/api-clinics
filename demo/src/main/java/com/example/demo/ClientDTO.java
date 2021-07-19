package com.example.demo;

public class ClientDTO {
    private String name;
    private String address;
    private Long clinicId;

    public String getName() {
        return name;
    }
    public String getAddress(){
        return address;
    }
    public Long getClinicId() {
        return clinicId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public ClientDTO(){}
    public ClientDTO(String name, String address, Long clinicId){
        this.name = name;
        this.address = address;
        this.clinicId = clinicId;
    }
}
