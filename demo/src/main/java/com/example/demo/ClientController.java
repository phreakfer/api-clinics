package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    @GetMapping ("/clientsbyclinicid/{id}")
    public List<Client> getClientsByClinicId(@PathVariable Long id){
        Optional<Clinic> possibbleClinic = clinicRepository.findById(id);
        if (possibbleClinic.isPresent()){
            return possibbleClinic.get().getClient();
        }
        else{
            return null;
        }
    }

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
}
