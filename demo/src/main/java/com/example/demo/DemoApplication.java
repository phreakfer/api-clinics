package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication{
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//@Autowired
	//ClinicRepository clinicRepository;
	//Clinic newclinic = new Clinic("Nombre", "Direccion");
	//clinicRepository.save(newclinic);
}
