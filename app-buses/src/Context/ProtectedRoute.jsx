import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";
import PropTypes from "prop-types"
function ProtectedRoute({ children }) {
  const { isAuthenticated } = useAuth();
  return isAuthenticated ? children : <Navigate to="/login" />;
}
ProtectedRoute.propTypes = {
  children: PropTypes.any
}
export default ProtectedRoute;
