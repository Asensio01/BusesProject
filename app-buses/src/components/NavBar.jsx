import { useState } from "react";
import PropTypes from "prop-types";
import { NavLink } from "react-router-dom";
import "../assets/styles/NavBar.css";

function Navbar({ links }) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav className="navbar-container">
      {/* Botón hamburguesa para móvil */}
      <button className="menu-toggle" onClick={() => setIsOpen(!isOpen)}>
        ☰
      </button>

      {/* Lista de enlaces */}
      <ul className={`navbar-links ${isOpen ? "open" : ""}`}>
        {links.map((link, index) => (
          <li key={index} className="nav-item">
            {/* Usamos rutas absolutas para evitar concatenaciones */}
            <NavLink className="nav-link" to={`/MenuAdmin/${link.path}`} end>
              {link.label}
            </NavLink>
          </li>
        ))}
      </ul>
    </nav>
  );
}

Navbar.propTypes = {
  links: PropTypes.arrayOf(
    PropTypes.shape({
      label: PropTypes.string.isRequired,
      path: PropTypes.string.isRequired,
    })
  ).isRequired,
};

export default Navbar;
