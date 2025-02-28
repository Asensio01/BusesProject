import { useState, useEffect } from "react";
import "../assets/styles/CrearRutas.css";

function CrearRutas() {
  const [nombreRuta, setNombreRuta] = useState("");
  const [ciudades, setCiudades] = useState([]);
  const [tramos, setTramos] = useState([]);
  const [ciudadOrigen, setCiudadOrigen] = useState("");
  const [ciudadDestino, setCiudadDestino] = useState("");
  const [horario, setHorario] = useState("");
  const [mensaje, setMensaje] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/ciudades")
      .then((res) => res.json())
      .then((data) => setCiudades(data))
      .catch((error) => console.error("Error al cargar ciudades:", error));
  }, []);

  const agregarTramo = () => {
    if (!ciudadOrigen || !ciudadDestino || !horario) {
      alert("Todos los campos son obligatorios.");
      return;
    }
    if (ciudadOrigen === ciudadDestino) {
      alert("El origen y el destino no pueden ser iguales.");
      return;
    }

    setTramos([
      ...tramos,
      { origen: ciudadOrigen, destino: ciudadDestino, horario },
    ]);

    // Limpiar los campos después de agregar el tramo
    setCiudadOrigen("");
    setCiudadDestino("");
    setHorario("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (tramos.length === 0) {
      alert("Debe agregar al menos un tramo.");
      return;
    }

    const nuevaRuta = { nombre_ruta: nombreRuta, tramos };

    try {
      const response = await fetch("http://localhost:8080/api/rutas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevaRuta),
      });

      if (response.ok) {
        setMensaje("Ruta creada exitosamente.");
        setNombreRuta("");
        setTramos([]);
      } else {
        setMensaje("Error al crear la ruta.");
      }
    } catch (error) {

      return (
        <div className="crear-rutas-container">
          <h1>Crear Rutas</h1>
          {mensaje && <p className="mensaje">{mensaje}</p>}
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              placeholder="Nombre de la Ruta"
              value={nombreRuta}
              onChange={(e) => setNombreRuta(e.target.value)}
              required
            />

            <h3>Agregar Tramos</h3>

            <select
              value={ciudadOrigen}
              onChange={(e) => setCiudadOrigen(e.target.value)}
              required
            >
              <option value="">Seleccionar Origen</option>
              {ciudades.map((ciudad) => (
                <option key={ciudad.id_ciudad} value={ciudad.id_ciudad}>
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
                <option key={ciudad.id_ciudad} value={ciudad.id_ciudad}>
                  {ciudad.nombre}
                </option>
              ))}
            </select>

            <input
              type="time"
              value={horario}
              onChange={(e) => setHorario(e.target.value)}
              required
            />

            <button type="button" className="btn-primary" onClick={agregarTramo}>
              Agregar Tramo
            </button>

            <h3>Tramos Agregados</h3>
            <ul className="tramos-list">
              {tramos.map((tramo, index) => (
                <li key={index}>
                  {ciudades.find((c) => c.id_ciudad == tramo.origen)?.nombre} →{" "}
                  {ciudades.find((c) => c.id_ciudad == tramo.destino)?.nombre} |
                  Horario: {tramo.horario}
                </li>
              ))}
            </ul>

            <button type="submit" className="btn-primary">
              Crear Ruta
            </button>
          </form>
        </div>
      );
    }
  }
}
export default CrearRutas;
