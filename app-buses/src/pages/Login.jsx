import { Form } from "../components/Form";
import { Input } from "../components/Input";
import svgMail from "../assets/imgSvg/mail.svg";
import svgLock from "../assets/imgSvg/lock.svg";

function Login() {
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Formulario enviado");
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

  return (
    <>
      <div className="container-form">
        <Form
          title="Iniciar Sesión"
          subtitle="Use su correo y contraseña"
          inputs={inputSignIn}
          buttonText="INICIAR SESIÓN"
          onSubmit={handleSubmit}
          methodName={"POST"}
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
        />
      </div>
    </>
  );
}

export default Login;
