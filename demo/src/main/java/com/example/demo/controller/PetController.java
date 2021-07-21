package com.example.demo.controller;

import com.example.demo.dto.PetDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Pet;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @GetMapping ("/clients/{id}/pets")
    public List<Pet> getPetsByClientId(@PathVariable Long id){
        Optional<Client> possibbleClient = clientRepository.findById(id);
        if (possibbleClient.isPresent()){
            return possibbleClient.get().getPet();
        }
        else{
            return null;
        }
    }

    //Save
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

    //Search by name and page
    static final int pageSize = 1;
    @GetMapping ("/pets/search")
    public List<Pet> searchPetsByName(@RequestParam String name,@RequestParam int page){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Pet> userPage = petRepository.findByNameContaining(name, pageable);
        return userPage.toList();
    }

}
