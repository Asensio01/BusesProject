import React from "react";
import "../assets/styles/Menu.css"; // Importa el archivo CSS
import Navbar from "../components/NavBar"; // Importa el componente Navbar

function Roles() {
  return (
    <div className="new-pageMenu">
        <Navbar /> {/* Usa el componente Navbar */}
      <div className="content-Menu">
        <br />
        <h1>Asignación de Roles</h1> <br />
        <p>En esta página podrás asignar roles a los usuarios de tu aplicación.</p>
      </div>
    </div>
    
  );
}

export default Roles;