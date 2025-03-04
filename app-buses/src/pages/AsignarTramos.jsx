import { useState, useEffect } from "react";
import "../assets/styles/CrearRutas.css";

function AsignarTramos() {
  const [rutas, setRutas] = useState([]);
  const [ciudades, setCiudades] = useState([]);
  const [asignaciones, setAsignaciones] = useState([]);
  const [rutaSeleccionada, setRutaSeleccionada] = useState("");
  const [ciudadOrigen, setCiudadOrigen] = useState("");
  const [ciudadDestino, setCiudadDestino] = useState("");
  const [horaSalida, setHoraSalida] = useState("");
  const [duracion, setDuracion] = useState("");
  const [tipoViaje, setTipoViaje] = useState("");
  const [mensaje, setMensaje] = useState("");
  const [mensajeError, setMensajeError] = useState(""); // Mensaje específico para duplicados
  const [cargando, setCargando] = useState(false);

  // Cargar rutas, ciudades y asignaciones desde la API
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

  // 🔹 Verificar si la asignación ya existe para la misma ruta en la misma hora
  const asignacionDuplicada = () => {
    return asignaciones.some(
      (asignacion) =>
        asignacion.ruta.idRuta.toString() === rutaSeleccionada &&
        asignacion.tramo.horaSalida === horaSalida &&
        asignacion.tramo.ciudadOrigen.idCiudad.toString() === ciudadOrigen &&
        asignacion.tramo.ciudadDestino.idCiudad.toString() === ciudadDestino &&
        asignacion.tramo.duracion.toString() === duracion &&
        asignacion.tipoViaje === tipoViaje
    );
  };

  // Enviar la relación ruta-tramo a la API
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!rutaSeleccionada || !ciudadOrigen || !ciudadDestino || !horaSalida) {
      setMensajeError("⚠️ Todos los campos son obligatorios.");
      return;
    }

    if (ciudadOrigen === ciudadDestino) {
      setMensajeError(
        "⚠️ La ciudad de origen y destino no pueden ser la misma."
      );
      return;
    }

    // 🔹 Validar si la ruta ya tiene un tramo con los mismos datos y la misma hora
    if (asignacionDuplicada()) {
      setMensajeError(
        "⚠️ No se puede asignar el mismo tramo a la misma ruta con la misma hora."
      );
      setTimeout(() => setMensajeError(""), 5000); // Eliminar mensaje después de 5 segundos
      return;
    }

    setCargando(true);
    setMensaje("");
    setMensajeError("");

    try {
      // Crear el tramo en la API
      const tramoResponse = await fetch("http://localhost:8080/api/tramos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          idCiudadOrigen: parseInt(ciudadOrigen, 10),
          idCiudadDestino: parseInt(ciudadDestino, 10),
          horaSalida,
          duracion: parseInt(duracion, 10),
        }),
      });

      if (!tramoResponse.ok) throw new Error("No se pudo crear el tramo.");
      const tramoCreado = await tramoResponse.json();

      // Asignar el tramo a la ruta
      const rutaTramoResponse = await fetch(
        "http://localhost:8080/api/rutas-tramos/con-tipo",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            idRuta: parseInt(rutaSeleccionada, 10),
            idTramo: tramoCreado.idTramo,
            tipoViaje: tipoViaje || "IDA",
          }),
        }
      );

      if (!rutaTramoResponse.ok)
        throw new Error("No se pudo asignar el tramo a la ruta.");

      setMensaje("✅ Tramo asignado correctamente a la ruta.");

      // Recargar la lista de asignaciones para evitar futuros duplicados
      fetch("http://localhost:8080/api/rutas-tramos")
        .then((res) => res.json())
        .then((data) => setAsignaciones(Array.isArray(data) ? data : []));

      setRutaSeleccionada("");
      setCiudadOrigen("");
      setCiudadDestino("");
      setHoraSalida("");
      setDuracion("");
      setTipoViaje("");

      setTimeout(() => setMensaje(""), 5000);
    } catch (error) {
      setMensajeError(`❌ Error: ${error.message}`);
    }

    setCargando(false);
  };

  return (
    <div className="crear-rutas-container">
      <h1>Asignar Tramo a Ruta</h1>
      {mensaje && <p className="mensaje success">{mensaje}</p>}
      {mensajeError && <p className="mensaje error">{mensajeError}</p>}

      <form onSubmit={handleSubmit}>
        <h3>Seleccionar Ruta</h3>
        <select
          value={rutaSeleccionada}
          onChange={(e) => setRutaSeleccionada(e.target.value)}
          required
        >
          <option value="">Seleccionar Ruta</option>
          {rutas.map((ruta) => (
            <option key={ruta.idRuta} value={ruta.idRuta}>
              {ruta.nombreRuta}
            </option>
          ))}
        </select>

        <h3>Seleccionar Tramo</h3>
        <select
          value={ciudadOrigen}
          onChange={(e) => setCiudadOrigen(e.target.value)}
          required
        >
          <option value="">Seleccionar Origen</option>
          {ciudades.map((ciudad) => (
            <option key={ciudad.idCiudad} value={ciudad.idCiudad}>
              {ciudad.nombre}
            </option>
          ))}
        </select>

        <select
          value={ciudadDestino}
          onChange={(e) => setCiudadDestino(e.target.value)}
          required
        >
          <option value="">Seleccionar Destino</option>
          {ciudades.map((ciudad) => (
            <option key={ciudad.idCiudad} value={ciudad.idCiudad}>
              {ciudad.nombre}
            </option>
          ))}
        </select>

        <input
          type="time"
          value={horaSalida}
          onChange={(e) => setHoraSalida(e.target.value)}
          required
        />
        <input
          type="number"
          min="1"
          value={duracion}
          placeholder="Duración en minutos"
          onChange={(e) => setDuracion(e.target.value)}
          required
        />

        <h3>Tipo de Viaje</h3>
        <select
          value={tipoViaje}
          onChange={(e) => setTipoViaje(e.target.value)}
          required
        >
          <option value="IDA">IDA</option>
          <option value="VUELTA">VUELTA</option>
        </select>

        <button type="submit" className="btn-primary" disabled={cargando}>
          {cargando ? "Asignando..." : "Asignar Tramo a Ruta"}
        </button>
      </form>
    </div>
  );
}

export default AsignarTramos;
