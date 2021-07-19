package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PetController {
    @Autowired
    PetRepository petRepository;

    //Get all pets
    @GetMapping("/pets")
    public List<Pet> getPets(){
        return petRepository.findAll();
    }

    //Find pet by id
    @GetMapping ("/pets/{id}")
    @ResponseBody
    public Pet getPetById(@PathVariable Long id){
        Optional<Pet> possibblePet = petRepository.findById(id);
        return possibblePet.orElse(null);
    }

    //Find client by clientid
    @GetMapping ("/petsbyclientid/{id}")
    @ResponseBody
    public Pet getPetsByClientid(@PathVariable Long id){
        Optional<Pet> possibblePet = petRepository.findByClientid(id);
        return possibblePet.orElse(null);
    }

    //Save pet
    @GetMapping ("/addpet")
    public Pet addPet(@RequestParam Long clientid, @RequestParam String name){
        Pet newClinic = new Pet(clientid, name);
        return petRepository.save(newClinic);
    }
}
