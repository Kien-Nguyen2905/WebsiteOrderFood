import React from "react";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { Button } from "@mui/material";
const UserProfile = () => {
  const handleLogout = () => {};
  return (
    <div className=" flex flex-col justify-center items-center text-center min-h-[80vh]">
      <div className="flex flex-col items-center justify-center">
        <AccountCircleIcon sx={{ fontSize: "9rem" }} />
        <h1 className="py-5 text-2xl font-semibold">Code With Zosh</h1>
        <p className="pb-4">Email: codewithzosh@gmail.com</p>
        <Button
          variant="contained"
          onClick={handleLogout}
          sz={{ margin: "2rem 0rem" }}
        >
          Logout
        </Button>
      </div>
    </div>
  );
};

export default UserProfile;
