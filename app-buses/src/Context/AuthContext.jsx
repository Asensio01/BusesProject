import { createContext, useState, useContext, useEffect } from "react";
import PropTypes from "prop-types";

const AuthContext = createContext(null); // ✅ Asegurar que el contexto tenga un valor por defecto

export function AuthProvider({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [token, setToken] = useState(null);

  // ✅ Verificar si hay un token en localStorage al cargar la app
  useEffect(() => {
    const savedToken = localStorage.getItem("token");
    if (savedToken) {
      setIsAuthenticated(true);
      setToken(savedToken);
    }
  }, []);

  const login = (userToken) => {
    setIsAuthenticated(true);
    setToken(userToken);
    localStorage.setItem("token", userToken);
  };

  const logout = () => {
    setIsAuthenticated(false);
    setToken(null);
    localStorage.removeItem("token");
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

// ✅ Asegurar que useAuth solo se llame dentro de un componente válido
export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth debe usarse dentro de un AuthProvider");
  }
  return context;
}

AuthProvider.propTypes = {
  children: PropTypes.any,
};
