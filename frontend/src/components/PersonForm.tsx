import React, { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import api from '../api';
import type { Persona } from '../PersonaInterface'

type PersonFormProps = {
  onAdd?: (persona: Persona) => void;
  editingPersona?: Persona | null;
};

export default function PersonForm({ onAdd, editingPersona }: PersonFormProps) {
  const {
    register,
    handleSubmit,
    watch,
    reset,
    setValue,
    setError,
    formState: { errors },
  } = useForm<Persona>({
    defaultValues: {
      rut: '',
      nombre: '',
      apellido: '',
      fechaNacimiento: '',
      direccionCalle: '',
      direccionComuna: '',
      direccionRegion: '',
    },
  });

  const fecha = watch('fechaNacimiento');

  useEffect(() => {
    if (editingPersona) {
      for (const key in editingPersona) {
        setValue(key as keyof Persona, editingPersona[key as keyof Persona]);
      }
    } else {
      reset();
    }
  }, [editingPersona]);

  // Calculamos la edad
  const calcEdad = (fechaStr?: string) => {
    if (!fechaStr) return 0;
    const dob = new Date(fechaStr);
    const diff = Date.now() - dob.getTime();
    return Math.abs(new Date(diff).getUTCFullYear() - 1970);
  };
// Se carga en el payload los datos cargado + y la edad calculada
  const onSubmit = async (data: Persona) => {
    try {
      const payload = { ...data, edad: calcEdad(data.fechaNacimiento) };

      let resp;
      if (editingPersona) {
        resp = await api.put(`/personas/${editingPersona.rut}`, payload);
        alert('Persona actualizada correctamente!');
      } else {
        resp = await api.post('/personas', payload);
        alert('Persona creada correctamente!');
      }

      if (onAdd) onAdd(resp.data as Persona);
      reset();
    } catch (err: any) {
      // si el backend devuelve mensaje, lo asignamos al campo RUT
      if (err.response?.data && err.response.status === 400) {
        setError('rut', { type: 'server', message: err.response.data });
      } else {
        alert('Error al guardar: Rut Invalido');
      }
    }
  };

  const maxDate = new Date().toISOString().split('T')[0];

  return (
    <form onSubmit={handleSubmit(onSubmit)} style={{ display: 'grid', gap: 8 }}>
      <div style={{ display: 'flex', gap: 8 }}>
        <div style={{ flex: 1 }}>
          <label>RUT</label>
          <input
            {...register('rut', {
              required: 'RUT requerido',
              pattern: { value: /^[0-9.\-kK]+$/, message: 'Formato RUT inválido' },
            })}
            readOnly={!!editingPersona}
          />
          {errors.rut && <small style={{ color: 'red' }}>{errors.rut.message}</small>}
        </div>
        <div style={{ flex: 1 }}>
          <label>Nombre</label>
          <input {...register('nombre', { required: 'Nombre requerido' })} />
          {errors.nombre && <small style={{ color: 'red' }}>{errors.nombre.message}</small>}
        </div>
        <div style={{ flex: 1 }}>
          <label>Apellido</label>
          <input {...register('apellido', { required: 'Apellido requerido' })} />
          {errors.apellido && <small style={{ color: 'red' }}>{errors.apellido.message}</small>}
        </div>
      </div>

      <div style={{ display: 'flex', gap: 8 }}>
        <div style={{ flex: 1 }}>
          <label>Fecha Nacimiento</label>
          <input
            type="date"
            max={maxDate}
            {...register('fechaNacimiento', { required: 'Fecha requerida' })}
          />
          {errors.fechaNacimiento && <small style={{ color: 'red' }}>{errors.fechaNacimiento.message}</small>}
        </div>
        <div style={{ flex: 1 }}>
          <label>Edad</label>
          <input value={calcEdad(fecha)} readOnly />
        </div>
      </div>

      <div>
        <label>Calle</label>
        <input {...register('direccionCalle', { required: 'Calle requerida' })} />
        {errors.direccionCalle && <small style={{ color: 'red' }}>{errors.direccionCalle.message}</small>}
      </div>

      <div style={{ display: 'flex', gap: 8 }}>
        <div style={{ flex: 1 }}>
          <label>Comuna</label>
          <input {...register('direccionComuna', { required: 'Comuna requerida' })} />
          {errors.direccionComuna && <small style={{ color: 'red' }}>{errors.direccionComuna.message}</small>}
        </div>
        <div style={{ flex: 1 }}>
          <label>Región</label>
          <input {...register('direccionRegion', { required: 'Región requerida' })} />
          {errors.direccionRegion && <small style={{ color: 'red' }}>{errors.direccionRegion.message}</small>}
        </div>
      </div>

      <div>
        <button type="submit">{editingPersona ? 'Actualizar' : 'Guardar'}</button>
        <button type="button" onClick={() => reset()} style={{ marginLeft: 8 }}>Limpiar</button>
      </div>
    </form>
  );
}
