import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "./Theme/DarkTheme";
import { CssBaseline } from "@mui/material";
import Navbar from "./components/Navbar/Navbar.jsx";
import Home from "./components/Home/Home.jsx";
import RestaurantDetail from "./components/Restaurant/RestaurantDetail.jsx";
import Cart from "./components/Cart/Cart.jsx";

export default function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline></CssBaseline>
      <Navbar></Navbar>
      {/* <Home></Home> */}
      {/* <RestaurantDetail></RestaurantDetail> */}
      <Cart></Cart>
    </ThemeProvider>
  );
}
