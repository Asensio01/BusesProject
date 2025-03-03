import React, { useState, useEffect } from "react";
import "../assets/styles/CrearRutas.css";

function EditarTramos() {
  const [rutas, setRutas] = useState([]);
  const [ciudades, setCiudades] = useState([]);
  const [asignaciones, setAsignaciones] = useState([]);
  const [asignacionSeleccionada, setAsignacionSeleccionada] = useState("");
  const [rutaSeleccionada, setRutaSeleccionada] = useState("");
  const [ciudadOrigen, setCiudadOrigen] = useState("");
  const [ciudadDestino, setCiudadDestino] = useState("");
  const [horaSalida, setHoraSalida] = useState("");
  const [duracion, setDuracion] = useState("");
  const [tipoViaje, setTipoViaje] = useState("");
  const [mensaje, setMensaje] = useState("");
  const [mensajeError, setMensajeError] = useState("");
  const [cargando, setCargando] = useState(false);

  // 🔹 Cargar datos desde la API al cargar el componente
  useEffect(() => {
    fetch("http://localhost:8080/api/rutas")
      .then((res) => res.json())
      .then((data) => setRutas(Array.isArray(data) ? data : []))
      .catch(() => setRutas([]));

    fetch("http://localhost:8080/api/ciudades")
      .then((res) => res.json())
      .then((data) => setCiudades(Array.isArray(data) ? data : []))
      .catch(() => setCiudades([]));

    fetch("http://localhost:8080/api/rutas-tramos")
      .then((res) => res.json())
      .then((data) => setAsignaciones(Array.isArray(data) ? data : []))
      .catch(() => setAsignaciones([]));
  }, []);

  // 🔹 Cargar los datos de la asignación seleccionada
  useEffect(() => {
    if (asignacionSeleccionada) {
      const asignacion = asignaciones.find((a) => a.idRutaTramo.toString() === asignacionSeleccionada);
      if (asignacion) {
        setRutaSeleccionada(asignacion.ruta.idRuta.toString());
        setCiudadOrigen(asignacion.tramo.ciudadOrigen.idCiudad.toString());
        setCiudadDestino(asignacion.tramo.ciudadDestino.idCiudad.toString());
        setHoraSalida(asignacion.tramo.horaSalida);
        setDuracion(asignacion.tramo.duracion.toString());
        setTipoViaje(asignacion.tipoViaje);
      }
    }
  }, [asignacionSeleccionada, asignaciones]);

  // 🔹 Validar si ya existe la asignación con los mismos datos
  const asignacionDuplicada = () => {
    return asignaciones.some(
      (asignacion) =>
        asignacion.idRutaTramo.toString() !== asignacionSeleccionada && // Excluir la actual
        asignacion.ruta.idRuta.toString() === rutaSeleccionada &&
        asignacion.tramo.horaSalida === horaSalida &&
        asignacion.tramo.ciudadOrigen.idCiudad.toString() === ciudadOrigen &&
        asignacion.tramo.ciudadDestino.idCiudad.toString() === ciudadDestino &&
        asignacion.tramo.duracion.toString() === duracion &&
        asignacion.tipoViaje === tipoViaje
    );
  };

  // 🔹 Enviar la actualización a la API
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!asignacionSeleccionada || !rutaSeleccionada || !ciudadOrigen || !ciudadDestino || !horaSalida) {
      setMensajeError("⚠️ Todos los campos son obligatorios.");
      return;
    }

    if (ciudadOrigen === ciudadDestino) {
      setMensajeError("⚠️ La ciudad de origen y destino no pueden ser la misma.");
      return;
    }

    if (asignacionDuplicada()) {
      setMensajeError("⚠️ No se puede actualizar, ya existe una asignación con estos datos.");
      setTimeout(() => setMensajeError(""), 5000);
      return;
    }

    setCargando(true);
    setMensaje("");
    setMensajeError("");

    try {
      const response = await fetch(`http://localhost:8080/api/rutas-tramos/${asignacionSeleccionada}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          idRuta: parseInt(rutaSeleccionada, 10),
          idCiudadOrigen: parseInt(ciudadOrigen, 10),
          idCiudadDestino: parseInt(ciudadDestino, 10),
          horaSalida,
          duracion: parseInt(duracion, 10),
          tipoViaje: tipoViaje || "IDA",
        }),
      });

      if (!response.ok) throw new Error("No se pudo actualizar la asignación.");

      setMensaje("✅ Asignación actualizada correctamente.");
      setTimeout(() => setMensaje(""), 5000);
    } catch (error) {
      setMensajeError(`❌ Error: ${error.message}`);
    }

    setCargando(false);
  };

  return (
    <div className="crear-rutas-container">
      <h1>Editar Ruta-Tramo</h1>
      {mensaje && <p className="mensaje success">{mensaje}</p>}
      {mensajeError && <p className="mensaje error">{mensajeError}</p>}

      <form onSubmit={handleSubmit}>
        <h3>Seleccionar Asignación</h3>
        <select value={asignacionSeleccionada} onChange={(e) => setAsignacionSeleccionada(e.target.value)} required>
          <option value="">Seleccionar Asignación</option>
          {asignaciones.map((asignacion) => (
            <option key={asignacion.idRutaTramo} value={asignacion.idRutaTramo}>
              {asignacion.ruta.nombreRuta} - {asignacion.tramo.ciudadOrigen.nombre} → {asignacion.tramo.ciudadDestino.nombre} ({asignacion.tramo.horaSalida})
            </option>
          ))}
        </select>

        <h3>Modificar Tramo</h3>
        <select value={ciudadOrigen} onChange={(e) => setCiudadOrigen(e.target.value)} required>
          <option value="">Seleccionar Origen</option>
          {ciudades.map((ciudad) => (
            <option key={ciudad.idCiudad} value={ciudad.idCiudad}>
              {ciudad.nombre}
            </option>
          ))}
        </select>

        <select value={ciudadDestino} onChange={(e) => setCiudadDestino(e.target.value)} required>
          <option value="">Seleccionar Destino</option>
          {ciudades.map((ciudad) => (
            <option key={ciudad.idCiudad} value={ciudad.idCiudad}>
              {ciudad.nombre}
            </option>
          ))}
        </select>

        <input type="time" value={horaSalida} onChange={(e) => setHoraSalida(e.target.value)} required />
        <input type="number" min="1" value={duracion} placeholder="Duración en minutos" onChange={(e) => setDuracion(e.target.value)} required />

        <h3>Tipo de Viaje</h3>
        <select value={tipoViaje} onChange={(e) => setTipoViaje(e.target.value)} required>
          <option value="IDA">IDA</option>
          <option value="VUELTA">VUELTA</option>
        </select>

        <button type="submit" className="btn-primary" disabled={cargando}>
          {cargando ? "Actualizando..." : "Actualizar Asignación"}
        </button>
      </form>
    </div>
  );
}

export default EditarTramos;
