package com.example.personas.controller;

import com.example.personas.dto.PersonaRequest;
import com.example.personas.dto.PersonaResponse;

import com.example.personas.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;

    }

    @GetMapping
    public ResponseEntity<List<PersonaResponse>> listAll() {
        return ResponseEntity.ok(personaService.listAll());
    }

    @GetMapping("/{rut}")
    public ResponseEntity<PersonaResponse> getByRut(@PathVariable("rut") String rut) {
        return ResponseEntity.ok(personaService.findByRut(rut));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PersonaRequest req) {
        PersonaResponse r = personaService.create(req);
        // if edad==0 and no id -> probably enqueued
        if (r.getId() == null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(r);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping("/{rut}")
    public ResponseEntity<PersonaResponse> update(@PathVariable("rut") String rut,
                                                  @Valid @RequestBody PersonaRequest req) {
        return ResponseEntity.ok(personaService.update(rut, req));
    }


    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> delete(@PathVariable("rut") String rut) {
        personaService.delete(rut);
        return ResponseEntity.noContent().build();
    }


}
