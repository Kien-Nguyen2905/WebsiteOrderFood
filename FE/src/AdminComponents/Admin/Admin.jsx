import React from "react";
import AdminSidebar from "./AdminSidebar";
import { Route, Routes } from "react-router-dom";
import Orders from "../Orders/Orders";
import Menu from "../Menu/Menu";
import FoodCategory from "../FoodCategory/FoodCategory";
import Ingredients from "../Ingredients/Ingredients";
import Events from "../Events/Events";
import RestaurantDashboard from "../Dashboard/Dashboard";
import RestaurantDetails from "./RestaurantDetails";
import CreateMenuForm from "../Menu/CreateMenuForm";

const Admin = () => {
  const handleClose = () => {};
  return (
    <div>
      <div className="justify-between lg:flex">
        <div className="">
          <AdminSidebar handleClose={handleClose}></AdminSidebar>
        </div>
        <div className="lg:w-[80%]">
          <Routes>
            <Route path="/" element={<RestaurantDashboard />}></Route>
            <Route path="/orders" element={<Orders />}></Route>
            <Route path="/menu" element={<Menu />}></Route>
            <Route path="/category" element={<FoodCategory />}></Route>
            <Route path="/ingredients" element={<Ingredients />}></Route>
            <Route path="/events" element={<Events />}></Route>
            <Route path="/details" element={<RestaurantDetails />}></Route>
            <Route path="/add-menu" element={<CreateMenuForm />}></Route>
          </Routes>
        </div>
      </div>
    </div>
  );
};

export default Admin;
