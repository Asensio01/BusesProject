import { useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "../assets/styles/ResetPassword.css";
import { Input } from "../components/Input"; // ✅ Importar el componente Input

function ResetPassword() {
  const [searchParams] = useSearchParams();
  const token = searchParams.get("token");
  const navigate = useNavigate();
  const [newPassword, setNewPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const handleResetPassword = async (e) => {
    e.preventDefault();
    if (!newPassword || newPassword.length < 6) {
      toast.error("La contraseña debe tener al menos 6 caracteres.");
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
        toast.success("Contraseña restablecida con éxito.");
        navigate("/login");
      } else {
        const errorMessage = await response.text();
        toast.error(errorMessage || "Error al restablecer la contraseña.");
      }
    } catch (error) {
      console.error("Error en la solicitud:", error);
      toast.error("Error al conectar con el servidor.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="reset-password-container">
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
