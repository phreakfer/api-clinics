package com.example.demo;
import com.example.demo.entity.Client;
import com.example.demo.entity.Clinic;
import com.example.demo.entity.Pet;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ClinicRepository;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ClinicRepository clinicRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	PetRepository petRepository;

	@Override
	public void run(String... args) throws Exception {
		Clinic clinic1 = new Clinic("Clinic Number One", "One Street Name 1234");
		Clinic clinic2 = new Clinic("Clinic Number Two", "Other Street Name 5678");
		clinicRepository.save(clinic1);
		clinicRepository.save(clinic2);
		/*
		List<Clinic> clinics = new ArrayList();
		clinics.add(clinic1);
		clinics.add(clinic2);
		clinicRepository.saveAll(clinics);
		*/

		Client client1 = new Client(clinic1,"Client 1");
		Client client2 = new Client(clinic1,"Client 2");
		clientRepository.save(client1);
		clientRepository.save(client2);

		Pet pet1 = new Pet(client2, "Loro");
		Pet pet2 = new Pet(client2, "Perro");
		Pet pet3 = new Pet(client2, "Gato");
		petRepository.save(pet1);
		petRepository.save(pet2);
		petRepository.save(pet3);
	}

}
