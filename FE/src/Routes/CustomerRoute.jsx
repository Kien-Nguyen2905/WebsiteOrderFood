import React from "react";
import Navbar from "../components/Navbar/Navbar";
import { Route, Routes } from "react-router-dom";
import Home from "../components/Home/Home";
import Cart from "../components/Cart/Cart";
import Profile from "../components/Profile/Profile";
import Auth from "../components/Auth/Auth";
import RestaurantDetail from "../components/Restaurant/RestaurantDetail";
const CustomerRoute = () => {
  return (
    <div>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/account/:resgier" element={<Home />}></Route>
        <Route path="/restaurant/*" element={<RestaurantDetail />}></Route>
        <Route path="/cart" element={<Cart />}></Route>
        <Route path="/my-profile/*" element={<Profile />}></Route>
      </Routes>
      <Auth />
    </div>
  );
};

export default CustomerRoute;
