package com.example.personas.repository;

import com.example.personas.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByRut(String rut);
    void deleteByRut(String rut);
    boolean existsByRut(String rut);
}
