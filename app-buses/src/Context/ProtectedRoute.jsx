import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";
import PropTypes from "prop-types";

function ProtectedRoute({ children, allowedRoles }) {
  const { isAuthenticated, userRole } = useAuth();

  // ✅ Si no está autenticado, redirigir al login
  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  // ✅ Si el rol del usuario NO está en los roles permitidos, redirigir a su página correspondiente
  if (!allowedRoles.includes(userRole)) {
    return <Navigate to={userRole === "ADMIN" ? "/MenuAdmin" : "/home"} />;
  }

  return children;
}

ProtectedRoute.propTypes = {
  children: PropTypes.any,
  allowedRoles: PropTypes.arrayOf(PropTypes.string).isRequired,
};

export default ProtectedRoute;
