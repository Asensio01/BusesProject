import "../assets/styles/Button.css";
import PropTypes from "prop-types";

function Button({ variant = "solid", className = "" ,text, onSwitch, styleButton}) {
  const buttonClass = `button ${
    variant === "solid" ? "solid" : "outline"
  } ${className}`;

  return (
    <button onClick={onSwitch} className={buttonClass} style={styleButton}>
      {text}
    </button>
  );
}
export default Button

Button.propTypes = {
  styleButton: PropTypes.any,
  onSwitch: PropTypes.func,
  variant: PropTypes.string,
  className: PropTypes.string,
  text:PropTypes.string
};
