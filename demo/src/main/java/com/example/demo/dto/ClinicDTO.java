package com.example.demo.dto;

public class ClinicDTO {
    private String name;
    private String address;

    public String getName() {return name;}
    public String getAddress() {return address;}

    public void setName(String name) {this.name = name;}
    public void setAddress(String address) {this.address = address;}

    public ClinicDTO(){}
    public ClinicDTO(String name, String address){
        this.name = name;
        this.address = address;
    }
}
