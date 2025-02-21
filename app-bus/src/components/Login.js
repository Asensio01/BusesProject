// filepath: /C:/Users/Usuario/Desktop/BusesProject/app-bus/src/components/Login.js
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faLock, faEnvelope} from '@fortawesome/free-solid-svg-icons';import './Login.css'; // Asegúrate de que este archivo exista
import { fab} from '@fortawesome/free-brands-svg-icons'

const Login = ({ onLogin }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    if (email.trim() === "test@example.com" && password.trim() === "password123") {
      onLogin({ email });
    } else {
      setError("Correo o contraseña incorrectos");

      setTimeout(() => {
        setError("");
      }, 3000);
    }
  };

  return (
    <div className="cuerpoOut">
      <div className="cuerpoIn container">
        <h2> Sign in</h2>
        <div className="filabtn">
          <button className="round-button"><FontAwesomeIcon icon={faEnvelope} /></button>
          <button className="round-button">icon2</button>
          <button className="round-button">icon3</button>
          <button className="round-button">icon4</button>
        </div>
        <form onSubmit={handleSubmit} className="cuerpoIn">
        <div className="filabtn">
          <FontAwesomeIcon icon={faUser} />
          <label htmlFor="email">Email:</label>
        </div>
          <div className="input-group">
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
  
          <div className="filabtn">
          <FontAwesomeIcon icon={faLock} />
          <label htmlFor="password">Contraseña:</label>          
          </div>

          <div className="input-group">
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
  
          <button type="submit" className="ingresarBtn">Ingresar</button>
          {error && <p className="error-popup">{error}</p>}
        </form>
      </div>

      <div className="welcome-section">
        <h2>Bienvenido</h2>
        <br></br>
        <p>Nosotros somos <b>BusToYou</b></p>
        <p className="bold-text">Si no tienes una cuenta <br /> puedes registrarte con nosotros</p>
        <button className="register-btn" onClick={() => navigate("/register")}>
          Registrarse
        </button>
      </div>
    </div>
  );
};

export default Login;