package com.example.demo.repository;
import com.example.demo.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PetRepository extends CrudRepository<Pet,Long> {
    List<Pet> findAll();
    Page<Pet> findByNameContaining(String name, Pageable pageable);
}
