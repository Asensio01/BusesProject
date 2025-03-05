import React, { useState, useEffect } from "react";
import "../assets/styles/CrearRutas.css";

function EliminarRutaTramo() {
  const [asignaciones, setAsignaciones] = useState([]);
  const [asignacionSeleccionada, setAsignacionSeleccionada] = useState("");
  const [mensaje, setMensaje] = useState("");
  const [cargando, setCargando] = useState(false);
  const [mostrarPopup, setMostrarPopup] = useState(false);

  // 🔹 Cargar las rutas-tramos desde la API
  useEffect(() => {
    fetch("http://localhost:8080/api/rutas-tramos")
      .then((res) => res.json())
      .then((data) => setAsignaciones(Array.isArray(data) ? data : []))
      .catch(() => setAsignaciones([]));
  }, []);

  // 🔹 Manejar la eliminación con confirmación
  const handleEliminar = async () => {
    setCargando(true);
    setMensaje("");

    try {
      const response = await fetch(`http://localhost:8080/api/rutas-tramos/${asignacionSeleccionada}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error("⚠️ No se pudo eliminar la asignación.");
      }

      setMensaje("✅ Asignación eliminada correctamente.");
      setAsignaciones(asignaciones.filter((a) => a.idRutaTramo.toString() !== asignacionSeleccionada));
      setAsignacionSeleccionada("");

      setTimeout(() => setMensaje(""), 5000);
    } catch (error) {
      setMensaje(`❌ ${error.message}`);
    }

    setCargando(false);
    setMostrarPopup(false);
  };

  return (
    <div className="crear-rutas-container">
      <h1>Eliminar Ruta-Tramo</h1>
      {mensaje && <p className={`mensaje ${mensaje.startsWith("✅") ? "success" : "error"}`}>{mensaje}</p>}

      <form onSubmit={(e) => e.preventDefault()}>
        <h3>Seleccionar Ruta-Tramo</h3>
        <select value={asignacionSeleccionada} onChange={(e) => setAsignacionSeleccionada(e.target.value)} required>
          <option value="">Seleccionar Asignación</option>
          {asignaciones.map((asignacion) => (
            <option key={asignacion.idRutaTramo} value={asignacion.idRutaTramo}>
              {asignacion.ruta.nombreRuta} - {asignacion.tramo.ciudadOrigen.nombre} → {asignacion.tramo.ciudadDestino.nombre} ({asignacion.tramo.horaSalida})
            </option>
          ))}
        </select>

        <button type="button" className="btn-danger" onClick={() => setMostrarPopup(true)} disabled={!asignacionSeleccionada || cargando}>
          {cargando ? "Eliminando..." : "Eliminar"}
        </button>
      </form>

      {/* 🔹 Pop-up de Confirmación */}
      {mostrarPopup && (
        <div className="popup">
          <div className="popup-content">
            <h3>¿Estás seguro de eliminar esta asignación?</h3>
            <button className="btn-primary" onClick={handleEliminar}>Confirmar</button>
            <button className="btn-cancel" onClick={() => setMostrarPopup(false)}>Cancelar</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default EliminarRutaTramo;
