import React, { useState } from "react";

import ProfileNavigation from "./ProfileNavigation";
import UserProfile from "./UserProfile";
import Orders from "./Orders";
import Address from "./Address";
import Favorites from "./Favorites";
import Events from "./Events";
import { Route, Routes } from "react-router-dom";
const Profile = () => {
  const [openSideBar, setOpenSideBar] = useState(false);
  return (
    <div className="justify-between lg:flex">
      <div className=" sticky h-[80px] lg:w-[20%]">
        <ProfileNavigation open={openSideBar}></ProfileNavigation>
      </div>
      <div className="lg:w-[80%]">
        <Routes>
          <Route path="/" element={<UserProfile />}></Route>
          <Route path="/orders" element={<Orders />}></Route>
          <Route path="/address" element={<Address />}></Route>
          <Route path="/favorites" element={<Favorites />}></Route>
          <Route path="/events" element={<Events />}></Route>
        </Routes>
      </div>
    </div>
  );
};

export default Profile;
