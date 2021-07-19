package com.example.demo;
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

	@Override
	public void run(String... args) throws Exception {
		Clinic clinic1 = new Clinic("Clinic Number One", "One Street Name 1234");
		Clinic clinic2 = new Clinic("Clinic Number Two", "Other Street Name 5678");
		clinicRepository.save(clinic1);
		clinicRepository.save(clinic2);
	}


	//@Autowired
	//ClinicRepository clinicRepository;
	//Clinic newclinic = new Clinic("Nombre", "Direccion");
	//clinicRepository.save(newclinic);
}
