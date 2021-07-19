package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ClinicController {
    @Autowired
    ClinicRepository clinicRepository;

    //Get all clinics
    @GetMapping ("/clinics")
    public List<Clinic> getClinics(){
        return clinicRepository.findAll();
    }

    //Find clinic by id
    @GetMapping ("/clinics/{id}")
    public Clinic getClinicById(@PathVariable Long id){
        Optional<Clinic> possibbleClinic = clinicRepository.findById(id);
        return possibbleClinic.orElse(null);
    }

    //Save clinic
    //@GetMapping ("/addclinic")
    //public Clinic addClinic(@RequestParam String name,@RequestParam String address){
    //    Clinic newClinic = new Clinic(name, address);
    //    return clinicRepository.save(newClinic);
    //}
    @RequestMapping(value = "/clinics", method = RequestMethod.POST)
    public Clinic getClinicPost(@RequestBody Map<String, String> json) {
        String name = json.get("name");
        String address = json.get("address");
        Clinic newClinic = new Clinic(name, address);
        return clinicRepository.save(newClinic);
    }

    //Get all clinics
    @GetMapping ("/autoload")
    public String autoload(){
        Clinic clinic1 = new Clinic("Clinic Number One", "One Street Name 1234");
        Clinic clinic2 = new Clinic("Clinic Number Two", "Other Street Name 5678");
        clinicRepository.save(clinic1);
        clinicRepository.save(clinic2);
        return "Clinics auto load";
    }


}
