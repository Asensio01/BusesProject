import { useState } from "react";
import "../assets/styles/Menu.css";

function Navbar() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <nav className="NavBarMenu">
      <div className="menu-container">
        <button className="menu-toggle" onClick={toggleMenu}>
          ☰
        </button>

        <ul className={`menu-items ${isOpen ? "open" : ""}`}>
          <li>
            <a href="#roles">Asignación de Roles</a>
          </li>
          <li>
            <a href="#rutas">Creación de Rutas</a>
          </li>
          <li>
            <a href="#">Perfil</a>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
