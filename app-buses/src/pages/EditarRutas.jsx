import React, { useState, useEffect } from "react";
import "../assets/styles/CrearRutas.css";

function EditarRutas() {
  const [rutas, setRutas] = useState([]);
  const [rutaSeleccionada, setRutaSeleccionada] = useState("");
  const [nuevoNombreRuta, setNuevoNombreRuta] = useState("");
  const [mensaje, setMensaje] = useState("");
  const [cargando, setCargando] = useState(false);
  const [confirmarEdicion, setConfirmarEdicion] = useState(false);

  // Obtener las rutas desde la API
  useEffect(() => {
    fetch("http://localhost:8080/api/rutas")
      .then((res) => res.json())
      .then((data) => setRutas(Array.isArray(data) ? data : []))
      .catch(() => setRutas([]));
  }, []);

  // Manejar la selección de la ruta
  const handleRutaChange = (e) => {
    const idRuta = e.target.value;
    setRutaSeleccionada(idRuta);

    // Obtener el nombre actual de la ruta seleccionada
    const ruta = rutas.find((ruta) => ruta.idRuta.toString() === idRuta);
    if (ruta) {
      setNuevoNombreRuta(ruta.nombreRuta);
    }
  };

  // Manejar la edición de la ruta
  const handleEditarRuta = async () => {
    if (!rutaSeleccionada || !nuevoNombreRuta.trim()) {
      alert("⚠️ Debe seleccionar una ruta y escribir un nuevo nombre.");
      return;
    }

    setCargando(true);
    setMensaje("");

    try {
      const response = await fetch(`http://localhost:8080/api/rutas/${rutaSeleccionada}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nombreRuta: nuevoNombreRuta }),
      });

      let data;
      const contentType = response.headers.get("content-type");

      // ✅ Verificar si la respuesta es JSON antes de intentar leerla
      if (contentType && contentType.includes("application/json")) {
        data = await response.json();
      } else {
        data = await response.text(); // Si no es JSON, leer como texto
      }

      if (!response.ok) {
        if (response.status === 400 && data.includes("Ya existe una ruta")) {
          throw new Error("⚠️ El nombre de esta ruta ya está asignado. Pruebe con otro nombre.");
        }
        throw new Error(`Error ${response.status}: ${data}`);
      }

      setMensaje("✅ Ruta actualizada exitosamente.");

      // Actualizar la lista de rutas
      setRutas((prevRutas) =>
        prevRutas.map((ruta) =>
          ruta.idRuta.toString() === rutaSeleccionada ? { ...ruta, nombreRuta: nuevoNombreRuta } : ruta
        )
      );

      setRutaSeleccionada("");
      setNuevoNombreRuta("");

      // Limpiar mensaje después de 5 segundos
      setTimeout(() => setMensaje(""), 5000);
    } catch (error) {
      setMensaje(`❌ ${error.message}`);
    }

    setCargando(false);
    setConfirmarEdicion(false);
  };

  return (
    <div className="crear-rutas-container">
      <h1>Editar Ruta</h1>
      {mensaje && <p className={`mensaje ${mensaje.startsWith("✅") ? "success" : "error"}`}>{mensaje}</p>}

      <form onSubmit={(e) => e.preventDefault()}>
        <h3>Seleccionar Ruta</h3>
        <select value={rutaSeleccionada} onChange={handleRutaChange} required>
          <option value="">Seleccionar Ruta</option>
          {rutas.map((ruta) => (
            <option key={ruta.idRuta} value={ruta.idRuta}>
              {ruta.nombreRuta}
            </option>
          ))}
        </select>

        <h3>Editar Nombre de la Ruta</h3>
        <input
          type="text"
          placeholder="Nuevo nombre de la ruta"
          value={nuevoNombreRuta}
          onChange={(e) => setNuevoNombreRuta(e.target.value)}
          required
        />

        <button type="button" className="btn-primary" disabled={cargando} onClick={() => setConfirmarEdicion(true)}>
          {cargando ? "Guardando..." : "Guardar Cambios"}
        </button>
      </form>

      {/* Popup de Confirmación */}
      {confirmarEdicion && (
        <div className="popup">
          <div className="popup-content">
            <h3>¿Estás seguro de actualizar esta ruta?</h3>
            <p>Nombre nuevo: <strong>{nuevoNombreRuta}</strong></p>
            <button className="btn-primary" onClick={handleEditarRuta}>Confirmar</button>
            <button className="btn-cancel" onClick={() => setConfirmarEdicion(false)}>Cancelar</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default EditarRutas;
