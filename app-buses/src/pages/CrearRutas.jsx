import { useState } from "react";
import "../assets/styles/CrearRutas.css";

function CrearRutas() {
  const [nombreRuta, setNombreRuta] = useState("");
  const [mensaje, setMensaje] = useState("");
  const [cargando, setCargando] = useState(false);

  // Enviar la nueva ruta a la API
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!nombreRuta.trim()) {
      alert("⚠️ Debe ingresar un nombre para la ruta.");
      return;
    }

    setCargando(true);
    setMensaje("");

    try {
      const response = await fetch("http://localhost:8080/api/rutas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nombreRuta }),
      });

      if (!response.ok) {
        throw new Error(`Error ${response.status}: No se pudo crear la ruta.`);
      }

      setMensaje("✅ Ruta creada exitosamente.");
      setNombreRuta("");

      // Limpiar mensaje después de 5 segundos
      setTimeout(() => setMensaje(""), 5000);
    } catch (error) {
      setMensaje(`❌ Error: ${error.message}`);
    }

    setCargando(false);
  };

  return (
    <div className="crear-rutas-container">
      <h1>Crear Nueva Ruta</h1>
      {mensaje && (
        <p
          className={`mensaje ${
            mensaje.startsWith("✅") ? "success" : "error"
          }`}
        >
          {mensaje}
        </p>
      )}

      <form onSubmit={handleSubmit}>
        <h3>Nombre de la Ruta</h3>
        <input
          type="text"
          placeholder="Ingrese el nombre de la ruta"
          value={nombreRuta}
          onChange={(e) => setNombreRuta(e.target.value)}
          required
        />

        <button type="submit" className="btn-primary" disabled={cargando}>
          {cargando ? "Creando..." : "Crear Ruta"}
        </button>
      </form>
    </div>
  );
}

export default CrearRutas;
