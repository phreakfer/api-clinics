package com.example.demo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ClinicRepository extends CrudRepository<Clinic,Long> {
    List<Clinic> findAll();

}
