import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "./Theme/DarkTheme";
import Navbar from "./components/Navbar";
import { CssBaseline } from "@mui/material";

export default function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline></CssBaseline>
      <h1 className="text-3xl font-bold underline">Hello world!</h1>
      <Navbar></Navbar>
    </ThemeProvider>
  );
}
