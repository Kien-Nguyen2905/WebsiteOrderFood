import React from "react";
import SearchIcon from "@mui/icons-material/Search";
import { pink } from "@mui/material/colors";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { Badge, IconButton, Avatar } from "@mui/material";
import "./Navbar.css";
import { Person } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const Navbar = () => {
  const { auth } = useSelector((store) => store);
  const navigate = useNavigate();
  const handleClick = () => {
    if (auth?.user?.data?.role === "CUSTOMER") {
      navigate("/my-profile");
    } else {
      navigate("/admin/restaurant");
    }
  };
  return (
    <div className="px-5 z-50 py-[.8rem] bg-[#e91e63] lg:px-20 flex justify-between">
      <div
        onClick={() => navigate("/")}
        className="flex items-center space-x-4 cursor-pointer lg:mr-10"
      >
        <li className="font-semibold text-gray-300 logo text-2x1">KNBC Food</li>
      </div>

      <div className="flex items-center space-x-2 lg:space-x-10">
        <div className="">
          <IconButton>
            <SearchIcon sx={{ fontSize: "1.5rem" }} />
          </IconButton>
        </div>

        <div className="">
          {auth.user ? (
            <Avatar
              onClick={handleClick}
              sx={{ bgcolor: "white", color: pink.A400 }}
            >
              {auth?.user?.data?.fullName?.[0].toUpperCase()}
            </Avatar>
          ) : (
            <IconButton onClick={() => navigate("/account/login")}>
              <Person />
            </IconButton>
          )}
        </div>

        <div className="">
          <Badge color="primary" badgeContent={3}>
            <ShoppingCartIcon sx={{ fontSize: "1.5rem" }} />
          </Badge>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
