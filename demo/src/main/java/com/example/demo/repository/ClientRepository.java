package com.example.demo.repository;
import com.example.demo.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Page<Client> findByNameContaining(String name, Pageable pageable);
}
