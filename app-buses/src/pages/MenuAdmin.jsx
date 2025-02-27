import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "../components/NavBar";
import "../assets/styles/MenuAdmin.css";
import CrearRutas from "./CrearRutas";
import AscenderUsuarios from "./AscenderUsuarios";

function MenuAdmin() {
  return (
    <div className="admin-container">
      {/* Navbar con links internos */}
      <Navbar
        links={[
          { label: "Crear Rutas", path: "crear-rutas" },
          { label: "Ascender Usuarios", path: "ascender-usuarios" },
        ]}
      />

      {/* Contenido dinámico basado en la opción seleccionada */}
      <div className="admin-content">
        <Routes>
          {/* Redirigir a "Crear Rutas" si se entra a /MenuAdmin directamente */}
          <Route path="/" element={<Navigate to="crear-rutas" />} />
          <Route path="crear-rutas" element={<CrearRutas />} />
          <Route path="ascender-usuarios" element={<AscenderUsuarios />} />
        </Routes>
      </div>
    </div>
  );
}

export default MenuAdmin;
