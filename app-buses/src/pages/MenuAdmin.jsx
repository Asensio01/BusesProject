import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "../components/NavBar";
import "../assets/styles/MenuAdmin.css";
import CrearRutas from "./CrearRutas";
import EditarRutas from "./EditarRutas";
import AsignarTramos from "./AsignarTramos";
import EditarTramos from "./EditarTramos";
import AscenderUsuarios from "./AscenderUsuarios";
import AuditoriaRoles from "../components/AuditoriaRoles";
import WelcomeAdmin from "./WelcomeAdmin";

function MenuAdmin() {
  return (
    <div className="admin-container">
      <Navbar
        links={[
          { label: "Crear Rutas", path: "/MenuAdmin/crear-rutas" },
          { label: "Editar Rutas", path: "/MenuAdmin/editar-rutas" },
          { label: "Asignar Tramo", path: "/MenuAdmin/asignar-tramos" },
          { label: "Editar Tramo", path: "/MenuAdmin/editar-tramos" },
          { label: "Ascender Usuarios", path: "/MenuAdmin/ascender-usuarios" },
          { label: "Auditoría de Roles", path: "/MenuAdmin/auditoria-roles" },
        ]}
      />

      <div className="admin-content">
        <Routes>
          <Route index element={<Navigate to="inicio-admin" />} />
          <Route path="inicio-admin" element={<WelcomeAdmin />} />
          <Route path="crear-rutas" element={<CrearRutas />} />
          <Route path="editar-rutas" element={<EditarRutas />} />
          <Route path="asignar-tramos" element={<AsignarTramos />} />
          <Route path="editar-tramos" element={<EditarTramos />} />
          <Route path="ascender-usuarios" element={<AscenderUsuarios />} />
          <Route path="auditoria-roles" element={<AuditoriaRoles />} />
        </Routes>
      </div>
    </div>
  );
}

export default MenuAdmin;
