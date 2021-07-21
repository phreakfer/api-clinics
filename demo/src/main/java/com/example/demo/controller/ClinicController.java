package com.example.demo.controller;
import com.example.demo.entity.Clinic;
import com.example.demo.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    //Save
    @RequestMapping(value = "/clinics", method = RequestMethod.POST)
    public Clinic getClinicPost(@RequestBody Map<String, String> json) {
        String name = json.get("name");
        String address = json.get("address");
        Clinic newClinic = new Clinic(name, address);
        return clinicRepository.save(newClinic);
    }

    //Search by name and page
    static final int pageSize = 1;
    @GetMapping ("/clinics/search")
    public List<Clinic> searchClinicsByName(@RequestParam String name,@RequestParam int page){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Clinic> userPage = clinicRepository.findByNameContaining(name, pageable);
        return userPage.toList();
    }

}
