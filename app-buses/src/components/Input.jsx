import "../assets/styles/Input.css";
import PropTypes from "prop-types";
import { useState } from "react";
import { Eye, EyeOff } from "lucide-react"; // Usa Lucide para los iconos

export function Input({ icon, type, name, placeholder, required, detect }) {
  const [isVisible, setIsVisible] = useState(false);

  const toggleVisibility = () => {
    setIsVisible(!isVisible);
  };

  return (
    <div className="input-container">
      {icon && <img src={icon} className="input-icon" />}
      <input
        className="input-field"
        type={type === "password" ? (isVisible ? "text" : "password") : type}
        name={name}
        placeholder={placeholder}
        required={required}
        onChange={detect}
      />
      {type === "password" && (
        <button
          type="button"
          className="toggle-password"
          onClick={toggleVisibility}
        >
          {isVisible ? (
            <EyeOff className="input-icon" />
          ) : (
            <Eye className="input-icon" />
          )}
        </button>
      )}
    </div>
  );
}

Input.propTypes = {
  icon: PropTypes.string,
  type: PropTypes.string,
  name: PropTypes.string,
  placeholder: PropTypes.string,
  required: PropTypes.bool,
  detect: PropTypes.func,
};
