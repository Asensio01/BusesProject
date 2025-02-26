import { useState} from "react";
import "../assets/styles/Form.css";
import PropTypes from "prop-types";
import Button from "./Button";

export function Form({
  title,
  subtitle,
  inputs,
  classForm = "",
  buttonText,
  onSubmit,
  styleButton,
  textA,
  errors,
}) {
  const initialFormState = inputs.reduce((acc, input) => {
    acc[input.name] = "";
    return acc;
  }, {});

  const [formData, setFormData] = useState(initialFormState);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  return (
    <form
      className={`form-container ${classForm}`}
      onSubmit={(e) => {
        e.preventDefault();
        onSubmit(formData);
      }}
    >
      <h2 className="form-title">{title}</h2>
      <span className="form-subtitle">{subtitle}</span>
      <div className="form-inputs">
        {inputs.map((input, index) => {
          const InputComponent = input.component;
          return (
            <div key={index} className="input-group">
              <InputComponent
                icon={input.icon}
                type={input.type}
                name={input.name}
                placeholder={input.placeholder}
                required={input.required}
                value={formData[input.name] || ""}
                detect={handleChange}
              />
              {errors?.[input.name] && (
                <p className="floating-error">{errors[input.name]}</p>
              )}
            </div>
          );
        })}
      </div>
      <a>{textA}</a>
      <Button type="submit" text={buttonText} styleButton={styleButton} />
    </form>
  );
}
Form.propTypes = {
  styleButton: PropTypes.object,
  title: PropTypes.string.isRequired,
  subtitle: PropTypes.string.isRequired,
  inputs: PropTypes.arrayOf(
    PropTypes.shape({
      component: PropTypes.elementType.isRequired,
      name: PropTypes.string.isRequired,
      type: PropTypes.string,
      placeholder: PropTypes.string,
      required: PropTypes.bool,
      icon: PropTypes.element,
    })
  ).isRequired,
  classForm: PropTypes.string,
  buttonText: PropTypes.string.isRequired,
  onSubmit: PropTypes.func,
  textA: PropTypes.string,
  errors: PropTypes.string,
};
