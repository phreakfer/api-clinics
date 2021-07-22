package com.example.demo.controller;
import com.example.demo.dto.PetDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Pet;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Pet>> getPets(){
        return new ResponseEntity<>(petRepository.findAll(), HttpStatus.OK);
    }

    //Find pet by id
    @GetMapping ("/pets/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Long id){
        Optional<Pet> possibblePet = petRepository.findById(id);
        if (possibblePet.isPresent()){
            return new ResponseEntity<>(possibblePet.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Pet does not exist",HttpStatus.NOT_FOUND);
        }
    }

    //Find pet by clientid
    @GetMapping ("/clients/{id}/pets")
    public ResponseEntity<?> getPetsByClientId(@PathVariable Long id){
        Optional<Client> possibbleClient = clientRepository.findById(id);
        if (possibbleClient.isPresent()){
            return new ResponseEntity<>(possibbleClient.get().getPet(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Client does not exist", HttpStatus.NOT_FOUND);
        }
    }

    //Save
    @RequestMapping(value = "/pets", method = RequestMethod.POST)
    public ResponseEntity<?> getPetsPost(@RequestBody PetDTO petDTO) {
        Optional<Client> possibbleClient = clientRepository.findById(petDTO.getClientId());
        if (possibbleClient.isPresent()){
            Client client = possibbleClient.get();
            Pet newPet = new Pet(client, petDTO.getName());
            return new ResponseEntity<>(petRepository.save(newPet), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Client does not exist", HttpStatus.NOT_FOUND);
        }
    }

    //Search by name and page
    @GetMapping ("/pets/search")
    public Page<Pet> searchPetsByName(@RequestParam String name,Pageable p){
        Page<Pet> userPage = petRepository.findByNameContaining(name, p);
        return userPage;
    }

}
