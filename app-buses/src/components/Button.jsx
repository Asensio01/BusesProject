import "../assets/styles/Button.css";
import PropTypes from "prop-types";

function Button({ variant = "solid", className = "" ,text}) {
  const buttonClass = `button ${
    variant === "solid" ? "solid" : "outline"
  } ${className}`;

  return <button className={buttonClass}>{text }</button>;
}
export default Button

Button.propTypes = {
  variant: PropTypes.string,
  className: PropTypes.string,
  text:PropTypes.string
};
