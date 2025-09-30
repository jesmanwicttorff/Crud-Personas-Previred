# Frontend - CRUD Personas

Requisitos:
- Node 18+
- npm

Instalar dependencias:

```bash
npm install
```

Correr en dev:

```bash
npm run dev
```

Por defecto el frontend asume backend en http://localhost:8080/api. Para cambiarlo, exporta VITE_API_URL.

-- Componentes:
A- PersonForm:
Este componente nos sirve para crear y editar persona
Usamos:
Reset() : Limpia los campos
setValue: con esto completamos los datos en el formulario

B- Edit Person:

1- useEffect(() => {
  api.get(`/personas/${rut}`)
    .then(res => setForm(res.data))
    .catch(() => alert('Error al cargar persona'))
}, [rut])
A traves del useEffect obtengo los datos buscado en la api de la base de datos

2- const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  if (!form) return
  setForm({ ...form, [e.target.name]: e.target.value })
}  
// Cada input del formulario esta conectado al estado form cuando hay un cambio
// Se actualiza automaticamente

3- const handleSubmit = async (e: React.FormEvent) => {
  e.preventDefault()
  try {
    await api.put(`/personas/${rut}`, form)
    alert('Persona actualizada de Previred')
    navigate('/')
  } catch (err: any) {
    alert('Error al actualizar: ' + (err.response?.data || err.message))
  }
}  

Se dispara el handleSubmit y actualiza los cambios asociado al rut

C- PersonList
Este componente se uso la siguientes hoock que nos permite React:

1- useState : Maneja el estado del componente (personas, loading).

2: useEffect : Ejecuta código cuando el componente se monta (aquí se carga la lista).

3 - useNavigate : Para navegar entre páginas (ej. ir a la vista de editar).

 4- api : Cliente Axios configurado para llamar a tu backend (/personas).

Persona : Interfaz TypeScript que define la estructura de los datos de una persona.Importado en PersonaInterface

PersonForm : Componente hijo para agregar una nueva persona.


