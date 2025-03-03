import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { AuthProvider } from "./Context/AuthContext";
import ProtectedRoute from "./Context/ProtectedRoute";
import Login from "./pages/Login";
import Home from "./pages/Home";
import MenuAdmin from "./pages/MenuAdmin";
import Roles from "./pages/Roles";
import AsignarTramos from "./pages/AsignarTramos";
import ForgotPassword from "./components/ForgotPassword";
import ResetPassword from "./components/ResetPassword";

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />{" "}
          {/* ✅ Nueva ruta */}
          <Route path="/reset-password" element={<ResetPassword />} />
          <Route
            path="/home"
            element={
              <ProtectedRoute>
                <Home />
              </ProtectedRoute>
            }
          />
          <Route
            path="/MenuAdmin/*"
            element={
              <ProtectedRoute>
                <MenuAdmin />
              </ProtectedRoute>
            }
          />
          <Route
            path="/Roles"
            element={
              <ProtectedRoute>
                <Roles />
              </ProtectedRoute>
            }
          />
          <Route path="*" element={<Navigate to="/login" />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
