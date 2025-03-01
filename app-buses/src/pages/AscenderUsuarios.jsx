import { useState, useEffect } from "react";
import "../assets/styles/AscenderUsuarios.css";

function AscenderUsuarios() {
  const [users, setUsers] = useState([]);
  const [search, setSearch] = useState("");
  const [mensaje, setMensaje] = useState("");

  // Cargar lista de usuarios desde el backend
  useEffect(() => {
    fetch("http://localhost:8080/users/")
      .then((res) => res.json())
      .then((data) => setUsers(data))
      .catch((error) => console.error("Error al cargar usuarios:", error));
  }, []);

  // Filtrar usuarios según la búsqueda
  const filteredUsers = users.filter(
    (user) =>
      user.nombre.toLowerCase().includes(search.toLowerCase()) ||
      user.email.toLowerCase().includes(search.toLowerCase())
  );

  // Función para ascender usuario a ADMIN
  const handlePromote = async (idUsuario) => {
    const token = localStorage.getItem("token"); // ✅ Obtener el token del localStorage

    try {
      const response = await fetch(
        `http://localhost:8080/users/${idUsuario}/promote`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`, // ✅ Enviar el token en el header
          },
        }
      );

      if (response.ok) {
        setUsers(
          users.map((user) =>
            user.idUsuario === idUsuario ? { ...user, rol: "ADMIN" } : user
          )
        );
        setMensaje("Usuario ascendido a Administrador.");
      } else {
        const errorData = await response.json();
        setMensaje(errorData.error || "Error al ascender el usuario.");
      }
    } catch (error) {
      console.error("Error en la solicitud:", error);
      setMensaje("Error al ascender el usuario.");
    }
  };


  return (
    <div className="ascender-usuarios-container">
      <h1>Ascender Usuarios a Administradores</h1>
      {mensaje && <p className="mensaje">{mensaje}</p>}

      {/* Barra de búsqueda */}
      <input
        type="text"
        className="search-bar"
        placeholder="Buscar usuario por nombre o email..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {/* Tabla de Usuarios */}
      <table className="user-table">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Email</th>
            <th>Rol</th>
            <th>Acción</th>
          </tr>
        </thead>
        <tbody>
          {filteredUsers.map((user) => (
            <tr key={user.idUsuario}>
              <td>
                {user.nombre} {user.apellido}
              </td>
              <td>{user.email}</td>
              <td>{user.rol}</td>
              <td>
                {user.rol === "USER" ? (
                  <button
                    className="promote-btn"
                    onClick={() => handlePromote(user.idUsuario)}
                  >
                    Ascender
                  </button>
                ) : (
                  <span className="admin-tag">Administrador</span>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AscenderUsuarios;
