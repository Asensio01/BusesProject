import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "../components/NavBar";
import "../assets/styles/MenuAdmin.css";
import CrearRutas from "./CrearRutas";
import AscenderUsuarios from "./AscenderUsuarios";
import AuditoriaRoles from "../components/AuditoriaRoles";

function MenuAdmin() {
  return (
    <div className="admin-container">
      <Navbar
        links={[
          { label: "Crear Rutas", path: "/MenuAdmin/crear-rutas" },
          { label: "Ascender Usuarios", path: "/MenuAdmin/ascender-usuarios" },
          { label: "Auditoría de Roles", path: "/MenuAdmin/auditoria-roles" },
        ]}
      />

      <div className="admin-content">
        <Routes>
          <Route index element={<Navigate to="crear-rutas" />} />
          <Route path="crear-rutas" element={<CrearRutas />} />
          <Route path="ascender-usuarios" element={<AscenderUsuarios />} />
          <Route path="auditoria-roles" element={<AuditoriaRoles />} />
        </Routes>
      </div>
    </div>
  );
}

export default MenuAdmin;
