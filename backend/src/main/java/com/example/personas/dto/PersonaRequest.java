package com.example.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class PersonaRequest {
    @NotBlank(message = "rut es obligatorio")
    // simple pattern allow digits, dots, dash, K/k
    @Pattern(regexp = "^[0-9.kK-]+$", message = "RUT inválido (formato)")
    private String rut;

    @NotBlank(message = "nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "apellido es obligatorio")
    private String apellido;

    @NotNull(message = "fechaNacimiento es obligatorio")
    @Past(message = "fechaNacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "direccionCalle es obligatorio")
    private String direccionCalle;

    @NotBlank(message = "direccionComuna es obligatorio")
    private String direccionComuna;

    @NotBlank(message = "direccionRegion es obligatorio")
    private String direccionRegion;

    // getters y setters
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
}
