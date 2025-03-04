import { useEffect, useState } from "react";
//import BusLogo from "../components/Logo"; // Ruta corregida

function WelcomeAdmin() {
  const [userName, setUserName] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/users/")
      .then((res) => res.json())
      .then((data) => {
        if (data.length > 0) {
          setUserName(data[0].nombre);
        }
      })
      .catch((error) =>
        console.error("Error al obtener los datos del usuario:", error)
      );
  }, []);

  return (
    <div>
      <h1>Bienvenido, {userName || "user"}!</h1>
    </div>
  );
}

export default WelcomeAdmin;
