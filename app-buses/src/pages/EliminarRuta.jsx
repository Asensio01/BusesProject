import React, { useState, useEffect } from "react";
import "../assets/styles/CrearRutas.css";

function EliminarRuta() {
  const [rutas, setRutas] = useState([]);
  const [rutaSeleccionada, setRutaSeleccionada] = useState("");
  const [mensaje, setMensaje] = useState("");
  const [cargando, setCargando] = useState(false);
  const [mostrarPopup, setMostrarPopup] = useState(false);

  // 🔹 Cargar las rutas desde la API
  useEffect(() => {
    fetch("http://localhost:8080/api/rutas")
      .then((res) => res.json())
      .then((data) => setRutas(Array.isArray(data) ? data : []))
      .catch(() => setRutas([]));
  }, []);

  // 🔹 Manejar la eliminación con confirmación
  const handleEliminar = async () => {
    setCargando(true);
    setMensaje("");

    try {
      const response = await fetch(`http://localhost:8080/api/rutas/${rutaSeleccionada}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error("⚠️ No se pudo eliminar la ruta.");
      }

      setMensaje("✅ Ruta eliminada correctamente.");
      setRutas(rutas.filter((ruta) => ruta.idRuta.toString() !== rutaSeleccionada));
      setRutaSeleccionada("");

      setTimeout(() => setMensaje(""), 5000);
    } catch (error) {
      setMensaje(`❌ ${error.message}`);
    }

    setCargando(false);
    setMostrarPopup(false);
  };

  return (
    <div className="crear-rutas-container">
      <h1>Eliminar Ruta</h1>
      {mensaje && <p className={`mensaje ${mensaje.startsWith("✅") ? "success" : "error"}`}>{mensaje}</p>}

      <form onSubmit={(e) => e.preventDefault()}>
        <h3>Seleccionar Ruta</h3>
        <select value={rutaSeleccionada} onChange={(e) => setRutaSeleccionada(e.target.value)} required>
          <option value="">Seleccionar Ruta</option>
          {rutas.map((ruta) => (
            <option key={ruta.idRuta} value={ruta.idRuta}>
              {ruta.nombreRuta}
            </option>
          ))}
        </select>

        <button type="button" className="btn-danger" onClick={() => setMostrarPopup(true)} disabled={!rutaSeleccionada || cargando}>
          {cargando ? "Eliminando..." : "Eliminar"}
        </button>
      </form>

      {/* 🔹 Pop-up de Confirmación */}
      {mostrarPopup && (
        <div className="popup">
          <div className="popup-content">
            <h3>¿Estás seguro de eliminar esta ruta?</h3>
            <button className="btn-primary" onClick={handleEliminar}>Confirmar</button>
            <button className="btn-cancel" onClick={() => setMostrarPopup(false)}>Cancelar</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default EliminarRuta;
