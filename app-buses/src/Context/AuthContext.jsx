import { createContext, useState, useContext, useEffect } from "react";
import PropTypes from "prop-types";

const AuthContext = createContext(null); // ✅ Asegurar que el contexto tenga un valor por defecto

export function AuthProvider({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [token, setToken] = useState(null);
  const [userRole, setUserRole] = useState(null); // ✅ Guardar el rol del usuario

  useEffect(() => {
    const savedToken = localStorage.getItem("token");
    if (savedToken) {
      setIsAuthenticated(true);
      setToken(savedToken);

      // ✅ Obtener el rol del usuario desde el backend
      fetch("http://localhost:8080/auth/me", {
        method: "GET",
        headers: { Authorization: `Bearer ${savedToken}` },
      })
        .then((res) => res.json())
        .then((data) => {
          setUserRole(data.rol); // ✅ Guardar el rol en el contexto
        })
        .catch(() => {
          setIsAuthenticated(false);
          setToken(null);
          localStorage.removeItem("token");
        });
    }
  }, []);

  const login = (userToken) => {
    setIsAuthenticated(true);
    setToken(userToken);
    localStorage.setItem("token", userToken);

    // ✅ Obtener el rol del usuario después de iniciar sesión
    fetch("http://localhost:8080/auth/me", {
      method: "GET",
      headers: { Authorization: `Bearer ${userToken}` },
    })
      .then((res) => res.json())
      .then((data) => {
        setUserRole(data.rol);
      });
  };

  const logout = () => {
    setIsAuthenticated(false);
    setToken(null);
    setUserRole(null);
    localStorage.removeItem("token");
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, token, userRole, login, logout }}
    >
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
