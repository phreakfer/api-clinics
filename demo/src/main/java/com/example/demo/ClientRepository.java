package com.example.demo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
    //Optional<Client> findByClinicid(Long id);
}
