package com.example.personas.dto;

import java.time.LocalDate;

public class PersonaResponse {
    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String direccionCalle;
    private String direccionComuna;
    private String direccionRegion;
    private int edad;

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

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}
