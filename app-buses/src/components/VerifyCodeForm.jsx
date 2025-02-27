import { useState, useRef, useEffect } from "react";
import PropTypes from "prop-types";
import Button from "./Button";
import { toast } from "react-toastify";
import "../assets/styles/VerifyCodeForm.css"; // Agrega estilos según necesites

function VerifyCodeForm({ onVerify, email }) {
  const [code, setCode] = useState(["", "", "", "", "", ""]);
  const inputsRef = useRef([]);
  const [canResend, setCanResend] = useState(false);
  const [resendAttempts, setResendAttempts] = useState(3);
  const [timer, setTimer] = useState(5);
  const timerRef = useRef(null);

  // Temporizador para reenviar el código
  useEffect(() => {
    if (timer > 0) {
      timerRef.current = setInterval(() => {
        setTimer((prev) => prev - 1);
      }, 1000);
    } else {
      setCanResend(true);
      clearInterval(timerRef.current);
    }

    return () => clearInterval(timerRef.current);
  }, [timer]);

  const StyleVerifyBtn = {
    backgroundColor: "#E7BA45",
    color: "black",
  };

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
      toast.error("El código debe tener 6 dígitos.");
      return;
    }

    // ✅ Enviar verificationCode y email
    const requestBody = {
      email: email,
      verificationCode: fullCode,
    };
    onVerify(requestBody);
  };

  const handleResendCode = async () => {
    if (resendAttempts > 0) {
      try {
        const requestBody = { email }; // ✅ Enviando el email en la solicitud
        const response = await fetch("http://localhost:8080/auth/resend", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(requestBody),
        });

        if (response.ok) {
          toast.success("Código reenviado exitosamente.");
          setResendAttempts((prev) => prev - 1);
          setCanResend(false);
          setTimer(5); // ✅ Reiniciar temporizador
          clearInterval(timerRef.current); // ✅ Reiniciar intervalo si está activo
          timerRef.current = setInterval(() => {
            setTimer((prev) => prev - 1);
          }, 1000);
        } else {
          toast.error("Error al reenviar el código.");
        }
      } catch (error) {
        toast.error("Error en la solicitud.");
        console.log(error)
      }
    } else {
      toast.error("Se alcanzó el máximo de intentos.");
    }
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
            name="verificationCode"
            type="text"
            maxLength="1"
            value={digit}
            onChange={(e) => handleChange(index, e.target.value)}
            onKeyDown={(e) => handleKeyDown(index, e)}
          />
        ))}
      </div>
      <Button
        type="submit"
        styleButton={StyleVerifyBtn}
        text="Verificar Código"
      />

      {/* Botón de reenvío con contador y limitación */}
      <button
        onClick={handleResendCode}
        disabled={!canResend || resendAttempts === 0}
        className="resend-button"
      >
        {canResend ? "Reenviar Código" : `Reenviar en ${timer}s`}
      </button>
      <p className="attempts-text">Intentos restantes: {resendAttempts}</p>
    </form>
  );
}

VerifyCodeForm.propTypes = {
  onVerify: PropTypes.func.isRequired,
  email: PropTypes.string.isRequired,
};

export default VerifyCodeForm;
