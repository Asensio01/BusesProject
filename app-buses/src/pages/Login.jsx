import { useState } from "react";
import { useAuth } from "../Context/AuthContext";
import { useNavigate } from "react-router-dom";
import { Form } from "../components/Form";
import { Input } from "../components/Input";
import VerifyCodeForm from "../components/VerifyCodeForm";
import svgMail from "../assets/imgSvg/mail.svg";
import svgLock from "../assets/imgSvg/lock.svg";
import svgUser from "../assets/imgSvg/userProfile.svg";
import svgContact from "../assets/imgSvg/contact.svg";
import Welcome from "../components/Welcome";
import "../assets/styles/Login.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function Login() {
  const [isSignUp, setIsSignUp] = useState(false);
  const [errors, setErrors] = useState({});
  const [isVerifying, setIsVerifying] = useState(false);
  const [userEmail, setUserEmail] = useState("");
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [error, setError] = useState(null);

  const { login } = useAuth();
  const navigate = useNavigate();

  const notifySuccess = (message) => {
    toast.success(message, { position: "top-right", autoClose: 3000 });
  };

  const notifyError = (message) => {
    toast.error(message, { position: "top-right", autoClose: 3000 });
  };

  const handleChange = (e) => {
    setFormData((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }));
  };

  const handleToggle = () => {
    setIsSignUp(!isSignUp);
  };

  const handleSubmit = async () => {
    console.log({ formData });
    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      const data = await response.json();

      if (response.ok) {
        login(data.token); // Guardar token en contexto y localStorage
        navigate("/home"); // Redirigir al home
      } else {
        setError(data.message || "Error en el inicio de sesión");
      }
    } catch (error) {
      console.error("Error en el login:", error);
      setError("Error al conectar con el servidor");
    }
  };

  const handleRegister = async (data) => {
    let validationErrors = {};

    // Validar email
    if (!data.email.includes("@")) {
      validationErrors.email = "Ingrese un correo válido.";
    }

    // Validar teléfono (máximo 10 caracteres)
    if (!/^\d{4}-\d{4}$/.test(data.telefono)) {
      validationErrors.telefono =
        "El teléfono debe tener el formato 1234-5678.";
    }

    // Validar contraseña
    const password = data.password;
    const hasUpperCase = /[A-Z]/.test(password);
    const hasSpecialChar = /[\W_]/.test(password);
    const hasNumber = /\d/.test(password);
    const hasMinLength = password.length >= 8;

    if (!hasMinLength) {
      validationErrors.password = "Debe tener al menos 8 caracteres.";
    } else if (!hasUpperCase) {
      validationErrors.password = "Debe contener al menos una mayúscula.";
    } else if (!hasSpecialChar) {
      validationErrors.password =
        "Debe contener al menos un carácter especial.";
    } else if (!hasNumber) {
      validationErrors.password = "Debe contener al menos un número.";
    }

    if (Object.keys(validationErrors).length > 0) {
      console.log("Errores antes de actualizar:", validationErrors);
      setErrors(validationErrors);
      return;
    }

    setErrors({});

    try {
      const response = await fetch("http://localhost:8080/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ ...data, rol: "USER" }),
      });

      const responseData = await response.json();

      if (!response.ok) {
        if (responseData.email) {
          setErrors({ email: responseData.email });
        }
        else if (responseData.username) {
          setErrors({username:responseData.username})
        }
        return;
      }

      notifySuccess("Registro exitoso. Verifique su correo");
      setErrors({});
      setUserEmail(data.email);
      setIsVerifying(true);
      setIsSignUp(false);
    } catch (error) {
      notifyError("Error en el registro.");
      console.log(error);
    }
  };

  const handleVerify = async ({ email, verificationCode }) => {
    console.log(JSON.stringify(email, verificationCode));
    const response = await fetch("http://localhost:8080/auth/verify", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, verificationCode }),
    });

    const data = await response.json();

    if (response.ok) {
      console.log("Código validado. Usuario autenticado.");
      setIsVerifying(false);
    } else {
      console.error("Código incorrecto:", data.message);
    }
  };
  const StyleWelcomeBtn = {
    backgroundColor: "#E7BA45",
    color: "#243774",
  };

  const StyleFormBtn = {
    backgroundColor: "#243774",
    color: "white",
  };

  const inputSignIn = [
    {
      component: Input,
      icon: svgMail,
      type: "email",
      name: "email",
      placeholder: "Email",
      required: true,
    },
    {
      component: Input,
      icon: svgLock,
      type: "password",
      name: "password",
      placeholder: "Contraseña",
      required: true,
    },
  ];

  const inputSignUp = [
    {
      component: Input,
      icon: svgUser,
      type: "text",
      name: "nombre",
      placeholder: "Nombre",
      required: true,
    },
    {
      component: Input,
      icon: svgUser,
      type: "text",
      name: "apellido",
      placeholder: "Apellido",
      required: true,
    },
    {
      component: Input,
      icon: svgUser,
      type: "text",
      name: "username",
      placeholder: "Usuario",
      required: true,
    },
    {
      component: Input,
      icon: svgMail,
      type: "email",
      name: "email",
      placeholder: "Email",
      required: true,
    },
    {
      component: Input,
      icon: svgLock,
      type: "password",
      name: "password",
      placeholder: "Contraseña",
      required: true,
    },
    {
      component: Input,
      icon: svgContact,
      type: "tel",
      name: "telefono",
      placeholder: "Teléfono",
      required: true,
    },
  ];

  return (
    <div className={`container-Login ${isSignUp ? "toggle" : ""}`}>
      <ToastContainer />
      {isVerifying ? (
        <div className="container-form-verify">
          <VerifyCodeForm onVerify={handleVerify} email={userEmail} />
        </div>
      ) : (
        <>
          <div className="container-form">
            {error && <p style={{ color: "red" }}>{error}</p>}
            <Form
              title="Iniciar Sesión"
              subtitle="Use su correo y contraseña"
              inputs={inputSignIn}
              buttonText="INICIAR SESIÓN"
              onSubmit={handleSubmit}
              methodName={"POST"}
              classForm={"sign-in"}
              styleButton={StyleFormBtn}
              textA={"¿Olvidó su contraseña?"}
              detectChange={handleChange}
            />
          </div>

          <div className="container-form">
            <Form
              title="Registrarse"
              subtitle="Llene todos los campos"
              inputs={inputSignUp}
              buttonText="REGISTRARSE"
              onSubmit={handleRegister}
              classForm={"sign-up"}
              styleButton={StyleFormBtn}
              errors={errors}
            />
          </div>

          <div className="container-welcome">
            <Welcome
              className="welcome-sign-up welcome"
              title={"¡Bienvenido!"}
              text={
                "Ingrese sus datos personales para usar todas las funciones del sitio"
              }
              buttonText={"Registrarse"}
              onSwitch={handleToggle}
              styleButton={StyleWelcomeBtn}
            />

            <Welcome
              className="welcome-sign-in welcome"
              title={"¡Hola!"}
              text={
                "Regístrese con sus datos personales para usar todas las funciones del sitio"
              }
              buttonText={"Iniciar Sesión"}
              onSwitch={handleToggle}
              styleButton={StyleWelcomeBtn}
            />
          </div>
        </>
      )}
    </div>
  );
}

export default Login;
