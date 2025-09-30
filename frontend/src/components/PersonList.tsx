import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api';
import type { Persona } from '../PersonaInterface';
import PersonForm from './PersonForm';

export default function PersonList() {
  const [personas, setPersonas] = useState<Persona[]>([]);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const fetchList = async () => {
    setLoading(true);
    try {
      const resp = await api.get('/personas');
      setPersonas(resp.data);
    } catch (e: any) {
      alert('No se pudo obtener lista: ' + (e.message || JSON.stringify(e.response?.data)));
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchList(); }, []);

  const del = async (rut: string) => {
    if (!confirm('Eliminar persona con RUT ' + rut + '?')) return;
    try {
      await api.delete('/personas/' + rut);
      setPersonas(personas.filter(p => p.rut !== rut)); // eliminar del estado
    } catch (e: any) {
      alert('Error al eliminar: ' + (e.response?.data || e.message));
    }
  };

  // Callback para agregar nuevo registro al estado
  const handleAdd = (newPersona: Persona) => {
    setPersonas(prev => [...prev, newPersona]);
  };

  return (
    <div>
      <h2>Agregar Persona</h2>
      <PersonForm onAdd={handleAdd} />

      <hr />

      {loading ? (
        <div>Cargando...</div>
      ) : (
        <table style={{ width: '100%', borderCollapse: 'collapse' }}>
          <thead>
            <tr>
              <th>RUT</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Edad</th>
              <th>Dirección</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {personas.map((p) => (
              <tr key={p.rut} style={{ borderTop: '1px solid #ddd' }}>
                <td>{p.rut}</td>
                <td>{p.nombre}</td>
                <td>{p.apellido}</td>
                <td>{p.edad ?? '-'}</td>
                <td>{p.direccionCalle}, {p.direccionComuna}, {p.direccionRegion}</td>
                <td>
                  <button onClick={() => del(p.rut)}>Eliminar</button>{' '}
                  <button onClick={() => navigate(`/edit/${p.rut}`)}>Editar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
