import { useState, useEffect } from "react";
import "../assets/styles/AuditoriaRoles.css";

function AuditoriaRoles() {
  const [auditorias, setAuditorias] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/users/auditoria")
      .then((res) => res.json())
      .then((data) => setAuditorias(data))
      .catch((error) => console.error("Error al cargar auditoría:", error));
  }, []);

  return (
    <div className="auditoria-container">
      <h1>Auditoría de Cambios de Rol</h1>
      <table className="auditoria-table">
        <thead>
          <tr>
            <th>Usuario Ascendido</th>
            <th>Ascendido por</th>
            <th>Fecha</th>
          </tr>
        </thead>
        <tbody>
          {auditorias.map((auditoria) => (
            <tr key={auditoria.idAuditoria}>
              <td>
                {auditoria.usuarioAscendido.nombre} (
                {auditoria.usuarioAscendido.email})
              </td>
              <td>
                {auditoria.administrador.nombre} (
                {auditoria.administrador.email})
              </td>
              <td>{new Date(auditoria.fecha).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AuditoriaRoles;
