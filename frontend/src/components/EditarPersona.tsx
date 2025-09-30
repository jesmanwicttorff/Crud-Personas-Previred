import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import api from '../api'
import type { Persona } from '../PersonaInterface'

export default function EditPerson() {
  const { rut } = useParams<{ rut: string }>()
  const navigate = useNavigate()
  const [form, setForm] = useState<Persona | null>(null)

  useEffect(() => {
    api.get(`/personas/${rut}`)
      .then(res => setForm(res.data))
      .catch(() => alert('Error al cargar persona'))
  }, [rut])

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!form) return
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    try {
      await api.put(`/personas/${rut}`, form)
      alert('Persona actualizada de Previred')
      navigate('/')
    } catch (err: any) {
      alert('Error al actualizar: ' + (err.response?.data || err.message))
    }
  }

  if (!form) return <div>Cargando datos...</div>

  return (
    <div>
      <h2>Editar Persona</h2>
      <form onSubmit={handleSubmit}>
        <div><label>Nombre: <input name="nombre" value={form.nombre} onChange={handleChange} /></label></div>
        <div><label>Apellido: <input name="apellido" value={form.apellido} onChange={handleChange} /></label></div>
        <div><label>Fecha de Nacimiento: <input type="date" name="fechaNacimiento" value={form.fechaNacimiento} onChange={handleChange} /></label></div>
        <div><label>Calle: <input name="direccionCalle" value={form.direccionCalle} onChange={handleChange} /></label></div>
        <div><label>Comuna: <input name="direccionComuna" value={form.direccionComuna} onChange={handleChange} /></label></div>
        <div><label>Región: <input name="direccionRegion" value={form.direccionRegion} onChange={handleChange} /></label></div>
        <button type="submit">Guardar</button>{' '}
        <button type="button" onClick={() => navigate('/')}>Cancelar</button>
      </form>
    </div>
  )
}

