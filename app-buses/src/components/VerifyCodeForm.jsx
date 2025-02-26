import { useState, useRef } from "react";
import PropTypes from "prop-types";
import "../assets/styles/VerifyCodeForm.css"; // Agrega estilos según necesites

function VerifyCodeForm({ onVerify }) {
  const [code, setCode] = useState(["", "", "", "", "", ""]);
  const inputsRef = useRef([]);



  const handleChange = (index, value) => {
    if (!/^\d?$/.test(value)) return; // Solo permitir un dígito numérico

    const newCode = [...code];
    newCode[index] = value;
    setCode(newCode);

    // Mover al siguiente input si se ha escrito un número
    if (value && index < 5) {
      inputsRef.current[index + 1]?.focus();
    }
  };

  const handleKeyDown = (index, e) => {
    if (e.key === "Backspace" && !code[index] && index > 0) {
      // Si se presiona Backspace en un input vacío, mover al anterior
      inputsRef.current[index - 1]?.focus();
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const fullCode = code.join(""); // Unir los valores del array en un string

    if (fullCode.length !== 6) {
      alert("El código debe tener 6 dígitos.");
      return;
    }

    onVerify(fullCode);
  };

  return (
    <form className="verify-code-form" onSubmit={handleSubmit}>
      <h2>Ingrese el Código</h2>
      <p>Revise su correo y escriba el código de verificación</p>

      <div className="code-inputs">
        {code.map((digit, index) => (
          <input
            key={index}
            ref={(el) => (inputsRef.current[index] = el)}
            type="text"
            maxLength="1"
            value={digit}
            onChange={(e) => handleChange(index, e.target.value)}
            onKeyDown={(e) => handleKeyDown(index, e)}
          />
        ))}
      </div>
      <button type="submit">Verificar Código</button>
    </form>
  );
}

VerifyCodeForm.propTypes = {
  onVerify: PropTypes.func.isRequired,
};

export default VerifyCodeForm;
