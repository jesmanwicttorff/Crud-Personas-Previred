package com.example.personas.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "personas", uniqueConstraints = {@UniqueConstraint(columnNames = {"rut"})})
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "direccion_calle", nullable = false)
    private String direccionCalle;

    @Column(name = "direccion_comuna", nullable = false)
    private String direccionComuna;

    @Column(name = "direccion_region", nullable = false)
    private String direccionRegion;

    public Persona() {}

    public Persona(String rut, String nombre, String apellido, LocalDate fechaNacimiento,
                   String direccionCalle, String direccionComuna, String direccionRegion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccionCalle = direccionCalle;
        this.direccionComuna = direccionComuna;
        this.direccionRegion = direccionRegion;
    }

    // getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getDireccionCalle() { return direccionCalle; }
    public void setDireccionCalle(String direccionCalle) { this.direccionCalle = direccionCalle; }

    public String getDireccionComuna() { return direccionComuna; }
    public void setDireccionComuna(String direccionComuna) { this.direccionComuna = direccionComuna; }

    public String getDireccionRegion() { return direccionRegion; }
    public void setDireccionRegion(String direccionRegion) { this.direccionRegion = direccionRegion; }

    @Transient
    public int getEdad() {
        if (this.fechaNacimiento == null) return 0;
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(id, persona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
