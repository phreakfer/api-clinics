package com.example.demo.controller;
import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Clinic;
import com.example.demo.repository.ClientRepository;
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
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClinicRepository clinicRepository;

    //Get all client
    @GetMapping ("/clients")
    public ResponseEntity<List<Client>> getClients(){
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    //Find client by id
    @GetMapping ("/clients/{id}")
    public ResponseEntity<?> getClientsById(@PathVariable Long id){
        Optional<Client> possibbleClient = clientRepository.findById(id);
        if (possibbleClient.isPresent()){
            return new ResponseEntity<>(possibbleClient.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Client does not exist", HttpStatus.NOT_FOUND);
        }
    }

    //Find client by clinicid
    @GetMapping ("/clinics/{id}/clients")
    public ResponseEntity<?> getClientsByClinicId(@PathVariable Long id){
        Optional<Clinic> possibbleClinic = clinicRepository.findById(id);
        if (possibbleClinic.isPresent()){
            return new ResponseEntity<>(possibbleClinic.get().getClient(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Clinic does not exist", HttpStatus.NOT_FOUND);
        }
    }

    //Save
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<?> getClientsPost(@RequestBody ClientDTO clientDTO) {
        Optional<Clinic> possibbleClinic = clinicRepository.findById(clientDTO.getClinicId());
        if (possibbleClinic.isPresent()){
            Clinic clinic = possibbleClinic.get();
            Client newClient = new Client(clinic, clientDTO.getName());
            return new ResponseEntity<>(clientRepository.save(newClient),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Clinic dows not exist",HttpStatus.NOT_FOUND);
        }
    }

    //Search by name and page
    static final int pageSize = 1;
    @GetMapping ("/clients/search")
    public Page<Client> searchClientsByName(@RequestParam String name,Pageable p){
        Page<Client> userPage = clientRepository.findByNameContaining(name, p);
        return userPage;
    }
}
