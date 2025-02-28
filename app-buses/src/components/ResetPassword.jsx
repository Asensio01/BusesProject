import { useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import "../assets/styles/ResetPassword.css";
import { Input } from "../components/Input"; // ✅ Importar el componente Input

// ✅ Funciones para manejar notificaciones
const notifySuccess = (message) => {
  toast.success(message, { position: "top-right", autoClose: 3000 });
};

const notifyError = (message) => {
  toast.error(message, { position: "top-right", autoClose: 3000 });
};

function ResetPassword() {
  const [searchParams] = useSearchParams();
  const token = searchParams.get("token");
  const navigate = useNavigate();
  const [newPassword, setNewPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const validatePassword = (password) => {
    const errors = [];

    if (password.length < 8) {
      errors.push("Debe tener al menos 8 caracteres.");
    }
    if (!/[A-Z]/.test(password)) {
      errors.push("Debe contener al menos una mayúscula.");
    }
    if (!/\d/.test(password)) {
      errors.push("Debe contener al menos un número.");
    }
    if (!/[\W_]/.test(password)) {
      errors.push("Debe contener al menos un carácter especial.");
    }

    return errors;
  };

  const handleResetPassword = async (e) => {
    e.preventDefault();

    const passwordErrors = validatePassword(newPassword);
    if (passwordErrors.length > 0) {
      notifyError(passwordErrors.join(" "));
      return;
    }

    setLoading(true);
    try {
      // ✅ Enviar `token` y `newPassword` como parámetros en la URL
      const response = await fetch(
        `http://localhost:8080/auth/reset-password?token=${encodeURIComponent(
          token
        )}&newPassword=${encodeURIComponent(newPassword)}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
        }
      );

      if (response.ok) {
        notifySuccess("Contraseña restablecida con éxito.");
        navigate("/login");
      } else {
        const errorMessage = await response.text();
        notifyError(errorMessage || "Error al restablecer la contraseña.");
      }
    } catch (error) {
      console.error("Error en la solicitud:", error);
      notifyError("Error al conectar con el servidor.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="reset-password-container">
      <ToastContainer/>
      <h2>Restablecer Contraseña</h2>
      <p>Ingrese su nueva contraseña.</p>

      <form onSubmit={handleResetPassword}>
        {/* ✅ Usamos el componente Input en lugar del input normal */}
        <Input
          type="password"
          name="newPassword"
          placeholder="Nueva Contraseña"
          required={true}
          detect={(e) => setNewPassword(e.target.value)}
        />

        <button type="submit" disabled={loading}>
          {loading ? "Restableciendo..." : "Restablecer Contraseña"}
        </button>
      </form>
    </div>
  );
}

export default ResetPassword;
