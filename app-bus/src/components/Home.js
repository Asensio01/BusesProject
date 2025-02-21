import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./Register";
import Login from "./Login";

function Home() {
   return (
      <Router>
         <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/register" element={<Register />} />
         </Routes>
      </Router>
   );
}

export default Home;