import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "./Theme/DarkTheme";
import { CssBaseline } from "@mui/material";
import Routers from "./Routes/Routers.jsx";

export default function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline></CssBaseline>

      <Routers></Routers>

    </ThemeProvider>
  );
}
