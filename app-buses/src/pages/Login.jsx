import { useState } from "react";
import { Form } from "../components/Form";
import { Input } from "../components/Input";
import svgMail from "../assets/imgSvg/mail.svg";
import svgLock from "../assets/imgSvg/lock.svg";
import svgUser from "../assets/imgSvg/userProfile.svg";
import svgContact from "../assets/imgSvg/contact.svg";
import Welcome from "../components/Welcome";
import "../assets/styles/Login.css";

function Login() {
  const [isSignUp, setIsSignUp] = useState(false);
  const [errors, setErrors] = useState({});

  const handleToggle = () => {
    setIsSignUp(!isSignUp);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Formulario enviado");
  };


  const handleRegister = async (data) => {

    let validationErrors = {};

    // Validar email
    if (!data.email.includes("@")) {
      validationErrors.email = "Ingrese un correo válido.";
    }

    // Validar teléfono (máximo 10 caracteres)
    if (data.telefono.length > 10) {
      validationErrors.telefono =
        "El teléfono no puede tener más de 10 dígitos.";
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
        return;
      }

      console.log("Registro exitoso");
      setErrors({});
    } catch (error) {
      console.error("Error en el registro:", error.message);
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
      placeholder: "Ingrese su nombre",
      required: true,
    },
    {
      component: Input,
      icon: svgUser,
      type: "text",
      name: "apellido",
      placeholder: "Ingrese su apellido",
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
      <div className="container-form">
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
    </div>
  );
}

export default Login;
