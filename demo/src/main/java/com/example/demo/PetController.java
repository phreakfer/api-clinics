package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PetController {
    @Autowired
    PetRepository petRepository;

    @Autowired
    ClientRepository clientRepository;

    //Get all pets
    @GetMapping("/pets")
    public List<Pet> getPets(){
        return petRepository.findAll();
    }

    //Find pet by id
    @GetMapping ("/pets/{id}")
    public Pet getPetById(@PathVariable Long id){
        Optional<Pet> possibblePet = petRepository.findById(id);
        return possibblePet.orElse(null);
    }

    //Find pet by clientid
    @GetMapping ("/petsbyclient/{id}")
    public List<Pet> getPetsByClientId(@PathVariable Long id){
        Optional<Client> possibbleClient = clientRepository.findById(id);
        if (possibbleClient.isPresent()){
            return possibbleClient.get().getPet();
        }
        else{
            return null;
        }
    }

    //Save pet
    @RequestMapping(value = "/pets", method = RequestMethod.POST)
    public Pet getPetsPost(@RequestBody PetDTO petDTO) {
        Optional<Client> possibbleClient = clientRepository.findById(petDTO.getClientId());
        if (possibbleClient.isPresent()){
            Client client = possibbleClient.get();
            Pet newPet = new Pet(client, petDTO.getName());
            return petRepository.save(newPet);
        }
        else{
            return null;
        }
    }

}
