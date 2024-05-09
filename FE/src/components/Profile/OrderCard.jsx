import { Button, Card } from "@mui/material";
import React from "react";

const OrderCard = () => {
  return (
    <Card className="flex items-center justify-between p-5">
      <div className="flex items-center space-x-5">
        <img
          className="w-16 h-16"
          src="https://images.pexels.com/photos/67468/pexels-photo-67468.jpeg?auto=compress&cs=tinysrgb&w=300"
          alt=""
        />
        <div className="">
          <p>Biryai</p>
          <p>$133</p>
        </div>
      </div>
      <div className="">
        <Button className="cursor-not-allowed ">Completed</Button>
      </div>
    </Card>
  );
};

export default OrderCard;
