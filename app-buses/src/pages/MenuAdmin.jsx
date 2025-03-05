import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "../components/NavBar";
import "../assets/styles/MenuAdmin.css";
import CrearRutas from "./CrearRutas";
import AscenderUsuarios from "./AscenderUsuarios";
import AsignarTramos from "./AsignarTramos";
import EditarTramos from "./EditarTramos";
import EliminarTramo from "./EliminarRutaTramo";
import EliminarRuta from "./EliminarRuta";
import EditarRutas from "./EditarRutas";
import WelcomeAdmin from "./WelcomeAdmin";
import EliminarRutaTramo from "./EliminarRutaTramo";

function MenuAdmin() {
  return (
    <div className="admin-container">
      {/* Barra de Navegación (Menú Lateral) */}
      <Navbar
        links={[
          { label: "Crear Rutas", path: "/MenuAdmin/crear-rutas" },
          { label: "Editar Rutas", path: "/MenuAdmin/editar-rutas" },
          { label: "Eliminar Rutas ", path: "/MenuAdmin/eliminar-rutas" },
          { label: "Asignar Tramo", path: "/MenuAdmin/asignar-rutas-tramos" },
          { label: "Editar Tramo", path: "/MenuAdmin/editar-rutas-tramos" },
          { label: "Eliminar Tramo ", path: "/MenuAdmin/eliminar-rutas-tramos" },
          { label: "Ascender Usuarios", path: "/MenuAdmin/ascender-usuarios" },
        ]}
      />

      {/* Contenido Dinámico */}
      <div className="admin-content">
        <Routes>
          <Route index element={<Navigate to="inicio-admin" />} />
          <Route path="inicio-admin" element={<WelcomeAdmin />} />
          <Route path="crear-rutas" element={<CrearRutas />} />
          <Route path="editar-rutas" element={<EditarRutas />} />
          <Route path="eliminar-rutas" element={<EliminarRuta/>} />
          <Route path="asignar-rutas-tramos" element={<AsignarTramos />} />
          <Route path="editar-rutas-tramos" element={<EditarTramos />} />
          <Route path="eliminar-rutas-tramos" element={<EliminarRutaTramo/>} />
          <Route path="ascender-usuarios" element={<AscenderUsuarios />} />
        </Routes>
      </div>
    </div>
  );
}

export default MenuAdmin;
