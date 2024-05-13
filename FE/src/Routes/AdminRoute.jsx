import React from "react";
import { Route, Routes } from "react-router-dom";
import Admin from "../AdminComponents/Admin/Admin";
import CreateRestaurantForm from "../AdminComponents/CreateRestaurantForm/CreateRestaurantForm";

const AdminRoute = () => {
  return (
    <div>
      <Routes>
        <Route path="/*" element={false ? <CreateRestaurantForm /> : <Admin />} />
      </Routes>
    </div>
  );
};

export default AdminRoute;
