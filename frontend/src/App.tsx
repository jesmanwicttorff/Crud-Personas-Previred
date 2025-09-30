import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PersonList from './components/PersonList';
import EditPerson from './components/EditarPersona';

export default function App() {
  return (
    <Router>
      <div style={{ maxWidth: 900, margin: '24px auto', fontFamily: 'Arial, sans-serif' }}>
        <h1>CRUD Personas Previred</h1>
        <Routes>
          {/* Página principal con listado y formulario */}
          <Route path="/" element={<PersonList />} />

          {/* Página para editar */}
          <Route path="/edit/:rut" element={<EditPerson />} />
        </Routes>
      </div>
    </Router>
  );
}
