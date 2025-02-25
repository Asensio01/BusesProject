import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import "./App.css";
import PropTypes from "prop-types";
import Login from "./pages/Login";
import Home from "./pages/Home";
import MenuAdmin from "./pages/MenuAdmin";
import Roles from "./pages/Roles";

import { useAuth, AuthProvider } from "./Context/AuthContext";

function ProtectedRoute({ children }) {
  const { isAuthenticated } = useAuth();
  return isAuthenticated ? children : <Navigate to="/login" />;
}

function App() {
  return (
    <>
      <AuthProvider>
        <Router>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route
              path="/home"
              element={
                <ProtectedRoute>
                  <Home></Home>
                </ProtectedRoute>
              }
            />
            <Route path="/MenuAdmin" element={<MenuAdmin />} />
            <Route path="/Roles" element={<Roles />} />

            <Route path="*" element={<Navigate to="/login" />} />
          </Routes>
        </Router>
      </AuthProvider>
    </>
  );
}

export default App;

ProtectedRoute.propTypes = {
  children: PropTypes.any
}
