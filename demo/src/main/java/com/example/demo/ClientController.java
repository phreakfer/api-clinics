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
    //@GetMapping ("/clientsbyclinicid/{id}")
    //public Client getClientsByClinicid(@PathVariable Long id){
    //    Optional<Client> possibbleClient = clientRepository.findByClinicid(id);
    //    return possibbleClient.orElse(null);
    //}

    //Save client
    //(@GetMapping ("/addclient")
    //public Client addClient(@RequestParam Long clinicid, @RequestParam String name){
    //    Client newClinic = new Client(clinicid,name);
    //    return clientRepository.save(newClinic);
    //}
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Client getClientsPost(@RequestBody ClientDTO clientDTO) {
        //String clinicId = json.get("clinic_id");
        //String name = json.get("name");
        //long id = Long.parseLong(clinicId);
        Optional<Clinic> possibbleClinic = clinicRepository.findById(clientDTO.getClinicId());
        if (possibbleClinic.isPresent()){
            Clinic clinic = possibbleClinic.get();
            clinic.getClient().add(new Client(clinic, clientDTO.getName()));
            //clinic.save();
            return clinicRepository.save(clinic);
            //Client newClient = new Client(clinic, clientDTO.getName());
            //return clientRepository.save(newClient);
        }
        else{
            return null;
        }
    }
}
