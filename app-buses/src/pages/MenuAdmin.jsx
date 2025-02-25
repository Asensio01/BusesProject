import React from "react";
import Navbar from "../components/NavBar"; // Importa el componente Navbar
import "../assets/styles/Menu.css"; // Asegúrate de que el archivo CSS tenga la extensión correcta

function MenuAdmin() {
  return (
    <div className="new-pageMenu">
      <Navbar /> {/* Usa el componente Navbar */}
      <div className="content-Menu">
        <br />
        <h1>Bienvenido de nuevo</h1> <br />
        <p>This is the admin menu page where you can manage your application.</p>
      </div>
    </div>
  );
}

export default MenuAdmin;