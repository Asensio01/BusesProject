import { Form } from "../components/Form";
import { Input } from "../components/Input";
import svgMail from "../assets/imgSvg/mail.svg";
import svgLock from "../assets/imgSvg/lock.svg";

export function Login() {

   const handleSubmit = (e) => {
     e.preventDefault();
     console.log("Formulario enviado");
   };

   const inputs = [
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
    <div>
      <Form
        title="Iniciar Sesión"
        subtitle="Use su correo y contraseña"
        inputs={inputs}
        buttonText="INICIAR SESIÓN"
        onSubmit={handleSubmit}
      />
    </div>
  );
}