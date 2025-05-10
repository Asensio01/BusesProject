import { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import "../assets/styles/ForgotPassword.css";

// ✅ Funciones para manejar notificaciones
const notifySuccess = (message) => {
  toast.success(message, { position: "top-right", autoClose: 3000 });
};

const notifyError = (message) => {
  toast.error(message, { position: "top-right", autoClose: 3000 });
};

function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [loading, setLoading] = useState(false);

  const handleRequestReset = async (e) => {
    e.preventDefault();

    if (!email.trim()) {
      notifyError("Por favor, ingrese su correo electrónico.");
      return;
    }

    setLoading(true);
    try {
      // ✅ Enviar `email` como parámetro en la URL
      const response = await fetch(
        `http://localhost:8080/auth/forgot-password?email=${encodeURIComponent(
          email.trim()
        )}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
        }
      );

      if (response.ok) {
        notifySuccess(
          "Se ha enviado un enlace de restablecimiento a tu correo."
        );
      } else {
        const errorMessage = await response.text(); // ✅ Capturar el error del servidor
        notifyError(
          errorMessage || "No se encontró una cuenta con este correo."
        );
      }
    } catch (error) {
      console.error("Error al solicitar restablecimiento:", error);
      notifyError("Error al conectar con el servidor.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="forgot-password-container">
      <ToastContainer/>
      <h2>Restablecer Contraseña</h2>
      <p>
        Ingrese su correo electrónico y le enviaremos un enlace para restablecer
        su contraseña.
      </p>

      <form onSubmit={handleRequestReset}>
        <input
          type="email"
          placeholder="Correo Electrónico"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <button type="submit" disabled={loading}>
          {loading ? "Enviando..." : "Enviar Enlace"}
        </button>
      </form>
    </div>
  );
}

export default ForgotPassword;
