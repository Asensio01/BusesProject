const API_URL_AUTH = "http://localhost:8080/auth";

export const fetchData = async (url, method = "GET", data = null) => {
  const options = {
    method,
    headers: {
      "Content-Type": "application/json",
    },
    body: data ? JSON.stringify(data) : null,
  };

  try {
    const response = await fetch(`${API_URL_AUTH}${url}`, options);
    if (!response.ok) throw new Error(`Error: ${response.status}`);
    return await response.json();
  } catch (error) {
    console.error("API Error:", error);
    throw error;
  }
};

export const registerUser = (userData) =>
  fetchData("/register", "POST", userData);
export const loginUser = (loginData) => fetchData("/login", "POST", loginData);
export const getUsers = () => fetchData("/users", "GET");
export const updateUser = (id, userData) =>
  fetchData(`/users/${id}`, "PUT", userData);
export const deleteUser = (id) => fetchData(`/users/${id}`, "DELETE");