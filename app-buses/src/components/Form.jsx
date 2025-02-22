import "../assets/styles/Form.css";
import PropTypes from "prop-types";
import Button from "./Button";

export function Form({ title, subtitle, inputs, buttonText, onSubmit, methodName, actionName }) {
  return (
    <form className="form-container" onSubmit={onSubmit} action={actionName} method={methodName}>
      <h2 className="form-title">{title}</h2>
      <span className="form-subtitle">{subtitle}</span>
      <div className="form-inputs">
        {inputs.map((input, index) => (
          <input.component
            key={index}
            icon={input.icon}
            type={input.type}
            name={input.name}
            placeholder={input.placeholder}
            required={input.required}
          />
        ))}
      </div>

      <Button type="submit" text={buttonText}></Button>
    </form>
  );
}

Form.propTypes = {
  title: PropTypes.string,
  subtitle: PropTypes.string,
  inputs: PropTypes.array,
  buttonText: PropTypes.string,
  onSubmit: PropTypes.string,
  methodName: PropTypes.string,
  actionName:PropTypes.string
};
