import React from "react";
import "../assets/styles/Menu.css"; // Importa el archivo CSS

function Navbar() {
  return (
    <nav className="NavBarMenu">
      <ul>
        <li><a href="#roles">Asignación de Roles</a></li>
        <li><a href="#rutas">Creación de Rutas</a></li>
        <li><a href="#">Perfil</a></li>
      </ul>
    </nav>
  );
}

export default Navbar;