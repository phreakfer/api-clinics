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


}
