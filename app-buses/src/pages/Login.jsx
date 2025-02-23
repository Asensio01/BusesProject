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

  const handleToggle = () => {
    setIsSignUp(!isSignUp);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Formulario enviado");
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
        />
      </div>

      <div className="container-form">
        <Form
          title="Registrarse"
          subtitle="Use su correo y contraseña"
          inputs={inputSignUp}
          buttonText="REGISTRARSE"
          onSubmit={handleSubmit}
          methodName={"POST"}
          classForm={"sign-up"}
          styleButton={StyleFormBtn}
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
