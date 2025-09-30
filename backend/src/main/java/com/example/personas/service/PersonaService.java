package com.example.personas.service;

import com.example.personas.dto.PersonaRequest;
import com.example.personas.dto.PersonaResponse;
import com.example.personas.model.Persona;
import com.example.personas.repository.PersonaRepository;
import com.example.personas.util.RutUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
   // private final LocalQueueService localQueueService;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;

    }

    public List<PersonaResponse> listAll() {
        return personaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
// Se Busca el Rut de la tabla personas
    public PersonaResponse findByRut(String rut) {
        return personaRepository.findByRut(rut).map(this::toResponse).orElseThrow(() -> new IllegalArgumentException("No existe persona con rut " + rut));
    }
// Creación de un nuevo registro en la tabla persona
    @Transactional
    public PersonaResponse create(PersonaRequest req) {
        // validate rut
        if (!RutUtils.isValidRut(req.getRut())) {
            throw new IllegalArgumentException("RUT inválido"); 
        }
        Persona p = new Persona(req.getRut(), req.getNombre(), req.getApellido(), req.getFechaNacimiento(),
                req.getDireccionCalle(), req.getDireccionComuna(), req.getDireccionRegion());
        try {
            Persona saved = personaRepository.save(p);
            return toResponse(saved);
        } catch (RuntimeException ex) {
            // fallback: enqueue
            PersonaResponse resp = new PersonaResponse();
            resp.setRut(req.getRut());
            resp.setNombre(req.getNombre());
            resp.setApellido(req.getApellido());
            resp.setFechaNacimiento(req.getFechaNacimiento());
            resp.setDireccionCalle(req.getDireccionCalle());
            resp.setDireccionComuna(req.getDireccionComuna());
            resp.setDireccionRegion(req.getDireccionRegion());
            resp.setEdad(0);
            return resp;
        }
    }
// Se actualiza  el registro persona con los cambios
    @Transactional
    public PersonaResponse update(String rut, PersonaRequest req) {
        Persona existing = personaRepository.findByRut(rut).orElseThrow(() -> new IllegalArgumentException("No existe persona con rut " + rut));
        existing.setNombre(req.getNombre());
        existing.setApellido(req.getApellido());
        existing.setFechaNacimiento(req.getFechaNacimiento());
        existing.setDireccionCalle(req.getDireccionCalle());
        existing.setDireccionComuna(req.getDireccionComuna());
        existing.setDireccionRegion(req.getDireccionRegion());
        Persona saved = personaRepository.save(existing);
        return toResponse(saved);
    }
 // elimina el registro asociado al rut
    @Transactional
    public void delete(String rut) {
        if (!personaRepository.existsByRut(rut)) throw new IllegalArgumentException("No existe persona con rut " + rut);
        personaRepository.deleteByRut(rut);
    }

    private PersonaResponse toResponse(Persona p) {
        PersonaResponse r = new PersonaResponse();
        r.setId(p.getId());
        r.setRut(p.getRut());
        r.setNombre(p.getNombre());
        r.setApellido(p.getApellido());
        r.setFechaNacimiento(p.getFechaNacimiento());
        r.setDireccionCalle(p.getDireccionCalle());
        r.setDireccionComuna(p.getDireccionComuna());
        r.setDireccionRegion(p.getDireccionRegion());
        r.setEdad(p.getEdad());
        return r;
    }
}
