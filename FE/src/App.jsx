import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "./Theme/DarkTheme";
import { CssBaseline } from "@mui/material";
import Navbar from "./components/Navbar/Navbar.jsx";
import Home from "./components/Home/Home.jsx";
import Profile from "./components/Profile/Profile.jsx";
import CustomerRoute from "./Routes/CustomerRoute.jsx";

export default function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline></CssBaseline>
      {/* <Navbar></Navbar> */}
      {/* <Profile></Profile> */}
      {/* <Home></Home> */}
      <CustomerRoute></CustomerRoute>
    </ThemeProvider>
  );
}
