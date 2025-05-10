import { useState } from "react";
import PropTypes from "prop-types";
import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../Context/AuthContext";
import "../assets/styles/NavBar.css";

function Navbar({ links }) {
  const [isOpen, setIsOpen] = useState(false);
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    localStorage.removeItem("token");
    navigate("/login");
  };

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
            <NavLink className="nav-link" to={link.path} end>
              {link.label}
            </NavLink>
          </li>
        ))}
      </ul>

      {/* ✅ Botón de Logout en la parte superior derecha */}
      <button className="logout-button" onClick={handleLogout}>
        Cerrar Sesión
      </button>
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
