package com.example.demo.repository;
import com.example.demo.entity.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClinicRepository extends CrudRepository<Clinic,Long> {
    List<Clinic> findAll();
    Page<Clinic> findByNameContaining(String name, Pageable pageable);
    //Page<Clinic> findAll(Pageable pageable);
}
