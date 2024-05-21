import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "./Theme/DarkTheme";
import { CssBaseline } from "@mui/material";
import Routers from "./Routes/Routers.jsx";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getUser } from "./components/State/Authentication/Action.js";

export default function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store.auth);
  useEffect(() => {
    dispatch(getUser(auth?.jwt || jwt));
  }, [auth?.jwt]);
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline></CssBaseline>
      <Routers></Routers>
    </ThemeProvider>
  );
}
