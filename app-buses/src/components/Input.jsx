import "../assets/styles/Input.css";
import PropTypes from "prop-types";

export function Input({ icon, type, name, placeholder, required,detect }) {
  return (
    <div className="input-container">
      {icon && <img src={icon} className="input-icon" />}
      <input
        className="input-field"
        type={type}
        name={name}
        placeholder={placeholder}
        required={required}
        onChange={detect}
      />
    </div>
  );
}

Input.propTypes = {
  icon: PropTypes.string,
  type: PropTypes.string,
  name: PropTypes.string,
  placeholder: PropTypes.string,
  required: PropTypes.bool,
  detect:PropTypes.any
};
