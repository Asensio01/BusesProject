import NavBar from "../components/NavBar";

function Home() {
  const userLinks = [
    { label: "Inicio", path: "/home" },
    { label: "Reservar Asiento", path: "/reservas" },
    { label: "Mis Viajes", path: "/mis-viajes" },
  ];

  return (
    <>
      <NavBar links={userLinks} />
      <div className="home-container">
        <h1>Bienvenido al Panel de Usuario</h1>
      </div>
    </>
  );
}

export default Home;
