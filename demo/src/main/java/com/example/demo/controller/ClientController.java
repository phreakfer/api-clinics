package com.example.demo.controller;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Clinic;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClinicRepository clinicRepository;

    //Get all client
    @GetMapping ("/clients")
    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    //Find client by id
    @GetMapping ("/clients/{id}")
    public Client getClientsById(@PathVariable Long id){
        Optional<Client> possibbleClient = clientRepository.findById(id);
        return possibbleClient.orElse(null);
    }

    //Find client by clinicid
    @GetMapping ("/clinics/{id}/clients")
    public List<Client> getClientsByClinicId(@PathVariable Long id){
        Optional<Clinic> possibbleClinic = clinicRepository.findById(id);
        if (possibbleClinic.isPresent()){
            return possibbleClinic.get().getClient();
        }
        else{
            return null;
        }
    }

    //Save
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Client getClientsPost(@RequestBody ClientDTO clientDTO) {
        Optional<Clinic> possibbleClinic = clinicRepository.findById(clientDTO.getClinicId());
        if (possibbleClinic.isPresent()){
            Clinic clinic = possibbleClinic.get();
            Client newClient = new Client(clinic, clientDTO.getName());
            return clientRepository.save(newClient);
        }
        else{
            return null;
        }
    }

    //Search by name and page
    static final int pageSize = 1;
    @GetMapping ("/clients/search")
    public List<Client> searchClientsByName(@RequestParam String name,@RequestParam int page){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Client> userPage = clientRepository.findByNameContaining(name, pageable);
        return userPage.toList();
    }
}
