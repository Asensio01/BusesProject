import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faLock, faEnvelope } from '@fortawesome/free-solid-svg-icons';
import './Register.css';

const Register = () => {
  const [name, setName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    if (password.trim() !== passwordConfirm.trim()) {
      setError("La contraseña no coincide");
    } else {
      setError("Registro exitoso");
      setTimeout(() => {
        setError("");
      }, 3000);
    }
  };

  return (
    <div className="cuerpoOut">
      <div className="RegCuadroIzq">
        <h2>¿Ya posees una cuenta?</h2>
        <br></br>
        <p>Inicia sesión</p>
        <button className="register-btn" onClick={() => navigate("/")}>
          Iniciar sesión
        </button>
      </div>

      <div className="cuerpoIn container">
        <h2> Registrarse con </h2>
        <div className="filabtn">
          <button className="round-button"><FontAwesomeIcon icon={faEnvelope} /></button>
          <button className="round-button">icon2</button>
          <button className="round-button">icon3</button>
          <button className="round-button">icon4</button>
        </div>
        <h4> Rellena los siguientes campos </h4>
        <form onSubmit={handleSubmit} className="cuerpoIn">
          <div className="filabtn RegInput">
            <FontAwesomeIcon icon={faUser} />
            <label htmlFor="name">Nombre:</label>
            <input
              id="name"
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
              className="RegInput"
            />
          </div>

          <div className="filabtn RegInput">
            <FontAwesomeIcon icon={faEnvelope} />
            <label htmlFor="email">Email:</label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className="RegInput"
            />
          </div>

          <div className="filabtn RegInput">
            <FontAwesomeIcon icon={faLock} />
            <label htmlFor="password">Contraseña:</label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="RegInput"
            />
          </div>

          <div className="filabtn RegInput">
            <FontAwesomeIcon icon={faLock} />
            <label htmlFor="passwordConfirm">Confirmar Contraseña:</label>
            <input
              id="passwordConfirm"
              type="password"
              value={passwordConfirm}
              onChange={(e) => setPasswordConfirm(e.target.value)}
              required
              className="RegInput"
            />
          </div>

          <button type="submit" className="ingresarBtn">Registrarse</button>
          {error && <p className="error-popup">{error}</p>}
        </form>
      </div>
    </div>
  );
};

export default Register;