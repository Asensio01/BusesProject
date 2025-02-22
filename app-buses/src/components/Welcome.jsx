import PropTypes from "prop-types";
import Button from "./Button";
import "../assets/styles/Welcome.css";

export default function Welcome({
  title,
  text,
  buttonText,
  onSwitch,
  className = "",
}) {
  return (
    <div className={`welcome-container ${className}`}>
      <h3 className="welcome-title">{title}</h3>
      <p className="welcome-text">{text}</p>
      <Button variant="outline" onClick={onSwitch} className="welcome-button">
        {buttonText}
      </Button>
    </div>
  );
}

Welcome.propTypes = {
  title: PropTypes.string.isRequired,
  text: PropTypes.string.isRequired,
  buttonText: PropTypes.string.isRequired,
  onSwitch: PropTypes.func.isRequired,
  className: PropTypes.string,
};
