package com.example.demo.controller;
import com.example.demo.dto.ClinicDTO;
import com.example.demo.entity.Clinic;
import com.example.demo.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ClinicController {
    @Autowired
    ClinicRepository clinicRepository;

    //Get all clinics
   @GetMapping ("/clinics")
    public ResponseEntity<List<Clinic>> getClinics(){
        return new ResponseEntity<>(clinicRepository.findAll(), HttpStatus.OK);
    }

    //Find clinic by id
    @GetMapping ("/clinics/{id}")
    public ResponseEntity<?> getClinicById(@PathVariable Long id){
        Optional<Clinic> possibbleClinic = clinicRepository.findById(id);
        if (possibbleClinic.isPresent()){
            return new ResponseEntity<>(possibbleClinic.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Clinic does not exist", HttpStatus.NOT_FOUND);
        }
    }

    //Save
    @RequestMapping(value = "/clinics", method = RequestMethod.POST)
    public ResponseEntity<Clinic> getClinicPost(@RequestBody ClinicDTO clinicDTO) {
        Clinic newClinic = new Clinic(clinicDTO.getName(), clinicDTO.getAddress());
        return new ResponseEntity<>(clinicRepository.save(newClinic),HttpStatus.OK);
    }
    /*
    public Clinic getClinicPost(@RequestBody Map<String, String> json) {
        String name = json.get("name");
        String address = json.get("address");
        Clinic newClinic = new Clinic(name, address);
        return clinicRepository.save(newClinic);
    }
    */

    //Search by name and page
    @GetMapping ("/clinics/search")
    public Page<Clinic> searchClinicsByName(@RequestParam String name, Pageable p){
        //Pageable pageable = PageRequest.of(page, pageSize);
        Page<Clinic> userPage = clinicRepository.findByNameContaining(name, p);
        return userPage;
    }
}
