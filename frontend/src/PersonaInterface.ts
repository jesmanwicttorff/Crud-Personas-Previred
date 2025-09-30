export type Persona = {
  id?: number;
  rut: string;
  nombre: string;
  apellido: string;
  fechaNacimiento: string; // ISO yyyy-MM-dd
  direccionCalle: string;
  direccionComuna: string;
  direccionRegion: string;
  edad?: number;
};
